package org.openjfx.DietiEstates25;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
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
	
	public static void openWindow(String fxmlPath, String title, Modality modality) throws IOException {
		FXMLLoader loader = new FXMLLoader(WindowsManager.class.getResource(fxmlPath));
	    Parent root = loader.load();
	    
	    Stage stage = new Stage();
	    stage.setTitle(title);
	    stage.setScene(new Scene(root));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setWidth(WINDOW_WIDTH);
        stage.setHeight(WINDOW_HEIGHT);
        stage.setMinWidth(WINDOW_WIDTH);
        stage.setMinHeight(WINDOW_HEIGHT);
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
        primaryStage.setWidth(WINDOW_WIDTH);
        primaryStage.setHeight(WINDOW_HEIGHT);
        primaryStage.setMinWidth(WINDOW_WIDTH);
        primaryStage.setMinHeight(WINDOW_HEIGHT);
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
			changeScene("WelcomePage.fxml", "WelcomeScene");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public static void loadLoginUserScene() {
		try {
			changeScene("LoginUserPage.fxml", "LoginScene");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public static void loadSignUpScene() {
        try {
			changeScene("SignUpUserPage.fxml", "SignUpScene");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    

    public static void loadHomeScene() {
		try {
			changeScene("HomePage.fxml", "HomeScene");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public static void loadSplashScene() {
		try {
			changeScene("SplashPage.fxml", "SplashScene");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public static void showDecisionPopup(String title, String headertext, String contenttext, String headertextinformation) {
    	Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(headertext);
        alert.setContentText(contenttext);

        ButtonType buttonOk = new ButtonType("OK");
        ButtonType buttonCancel = new ButtonType("Annulla");
        alert.getButtonTypes().setAll(buttonOk, buttonCancel);

        alert.showAndWait().ifPresent(response -> {
            if (response == buttonOk) {
                showInformationPopup(title, headertextinformation);
            } else if (response == buttonCancel) {
                System.out.println("L'utente ha annullato l'operazione");
            }
        });
    }
    
    public static void showInformationPopup(String title, String headertext) {
    	Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(headertext);
        alert.showAndWait();
    }
}
