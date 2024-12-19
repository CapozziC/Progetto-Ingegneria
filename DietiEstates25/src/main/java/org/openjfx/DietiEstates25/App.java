package org.openjfx.DietiEstates25;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Locale;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
    	WindowsManager.setLanguage(Locale.of("it", "IT"));
    	WindowsManager.setPrimaryStage(primaryStage);
		WindowsManager.loadWelcomeScene();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}