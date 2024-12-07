package org.openjfx.DietiEstates25;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class WindowsManager {
	private static Stage primaryStage;
	private static double xOffset = 0;
	private static double yOffset = 0;
	
	public static void setPrimaryStage(Stage stage) {
		primaryStage = stage;
		primaryStage.initStyle(StageStyle.UNDECORATED);
	}
	
	public static void openWindow(String fxmlPath, String title, Modality modality) throws IOException {
		FXMLLoader loader = new FXMLLoader(WindowsManager.class.getResource(fxmlPath));
	    Parent root = loader.load();
	    
	    Stage stage = new Stage();
	    stage.setTitle(title);
	    stage.setScene(new Scene(root));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setWidth(1600);
        stage.setHeight(900);
        stage.setMinWidth(1600);
        stage.setMinHeight(900);
        stage.setResizable(false);
        stage.centerOnScreen();
	    if (modality != null) {
	    	stage.initModality(modality);
	    }
	    stage.show();
	}
	
    public static void changeScene(String fxmlPath, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(WindowsManager.class.getResource(fxmlPath));
        Parent root = loader.load();
        primaryStage.setTitle(title);
        primaryStage.setScene(new Scene(root));
        primaryStage.setWidth(1600);
        primaryStage.setHeight(900);
        primaryStage.setMinWidth(1600);
        primaryStage.setMinHeight(900);
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        makeStageDraggable(root); 
    }
	
	public static void closeWindow(Stage stage) {
        stage.close();
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
    
    public static void loadWelcomeScene() throws IOException {
        changeScene("WelcomePage.fxml", "WelcomeScene");
    }

    public static void loadLoginUserScene() throws IOException {
        changeScene("LoginUserPage.fxml", "LoginScene");
    }

    public static void loadHomeScene() throws IOException {
        changeScene("HomePage.fxml", "HomeScene");
    }
    
    public static void loadSplashScene() throws IOException {
    	changeScene("SplashPage", "SplashScene");
    }
    public static void BackWelcomeScene() throws IOException {
    	changeScene("WelcomePag.fxml", "WelcomeScene");
    }
    public static void loadSignUpScene() throws IOException {
        changeScene("SignUpUserPage.fxml", "SignUpScene");
    }
    
}
