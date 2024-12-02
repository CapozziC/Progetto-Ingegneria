package org.openjfx.DietiEstates25;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.openjfx.DietiEstates25.WindowsManager;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
    	WindowsManager.setPrimaryStage(primaryStage);
    	try {
			WindowsManager.loadWelcomeScene();
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public static void main(String[] args) {
        launch(args);
    }

}