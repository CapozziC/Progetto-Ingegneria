package org.openjfx.DietiEstates25;

import java.io.IOException;
import java.util.function.Consumer;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class WindowsManager {
	private static Stage primaryStage;
	private static double xOffset = 0;
	private static double yOffset = 0;
	private static final double WINDOW_WIDTH = 1600;
	private static final double WINDOW_HEIGHT = 900;
	
	
	public static void setPrimaryStage(Stage stage) {
		primaryStage = stage;
		primaryStage.initStyle(StageStyle.UNDECORATED);
	}
	
	public static Stage getPrimaryStage() {
		return primaryStage;
		
	}
	
	public static void openWindow(String fxmlPath, String title, int width, int height, Modality modality, Stage fatherstage) throws IOException {
		FXMLLoader loader = new FXMLLoader(WindowsManager.class.getResource(fxmlPath));
	    Parent root = loader.load();
	    
	    Stage stage = new Stage();
	    stage.setTitle(title);
	    stage.setScene(new Scene(root));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setWidth(width);
        stage.setHeight(height);
        stage.setMinWidth(width);
        stage.setMinHeight(height);
        stage.setResizable(false);
        stage.centerOnScreen();
        if (fatherstage==null) {
			stage.initOwner(getPrimaryStage());
		}
        else {
        	stage.initOwner(fatherstage);
        }
		if (modality != null) {
	    	stage.initModality(modality);
	    }
	    stage.show();
	}
	
    public static void changeScene(String fxmlPath, String title, double width, double height) throws IOException {
        FXMLLoader loader = new FXMLLoader(WindowsManager.class.getResource(fxmlPath));
        Parent root = loader.load();
        primaryStage.setTitle(title);
        primaryStage.setScene(new Scene(root));
        primaryStage.setWidth(width);
        primaryStage.setHeight(height);
        primaryStage.setMinWidth(width);
        primaryStage.setMinHeight(height);
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.show();
        makeStageDraggable(root); 
    }
    
    public static void iconizeWindows() {
    	primaryStage.setIconified(true);
    }
	
	public static void closeWindow() {
        primaryStage.close();
    }
	
    private static void makeStageDraggable(Parent root) {
        root.setOnMousePressed((MouseEvent event) -> {
        	if(!primaryStage.isFullScreen()) {
        		xOffset = event.getSceneX();
                yOffset = event.getSceneY();
        	}
        });

        root.setOnMouseDragged((MouseEvent event) -> {
        	if(!primaryStage.isFullScreen()) {
        		primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
        	}
        });
    }
    
    public static void loadWelcomeScene() {
		try {
			changeScene("WelcomePage.fxml", "WelcomeScene", WINDOW_WIDTH, WINDOW_HEIGHT);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public static void loadLoginUserScene() {
		try {
			changeScene("LoginUserPage.fxml", "LoginScene", WINDOW_WIDTH, WINDOW_HEIGHT);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public static void loadSignUpScene() {
        try {
			changeScene("SignUpUserPage.fxml", "SignUpScene", WINDOW_WIDTH, WINDOW_HEIGHT);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public static void loadSearchScene() {
    	try {
			changeScene("SearchPage.fxml", "Search", 1000, 500);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public static void loadHomeScene() {
		try {
			changeScene("HomePage.fxml", "HomeScene", WINDOW_WIDTH, WINDOW_HEIGHT);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public static void loadSplashScene() {
		try {
			changeScene("SplashPage.fxml", "SplashScene", WINDOW_WIDTH, WINDOW_HEIGHT);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public static void loadEstateAgentScene() {
    	try {
			changeScene("EstateAgentPage.fxml", "EstateAgentScene", WINDOW_WIDTH, WINDOW_HEIGHT);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
