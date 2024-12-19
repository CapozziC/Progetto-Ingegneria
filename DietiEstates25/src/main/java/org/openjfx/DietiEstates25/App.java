package org.openjfx.DietiEstates25;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Locale;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
    	WindowsManager.setLanguage(new Locale("it", "IT"));
    	WindowsManager.setPrimaryStage(primaryStage);
		WindowsManager.loadWelcomeScene();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}