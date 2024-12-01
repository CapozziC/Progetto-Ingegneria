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

    private VBox splashLayout;
    private HBox welcomeLayout;

    @Override
    public void start(Stage primaryStage) {
    	WindowsManager.setPrimaryStage(primaryStage);
    	try {
			WindowsManager.loadWelcomeScene();
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

//    public void showSplashScreen(Stage stage) {
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("SplashPage.fxml"));
//        try {
//            splashLayout = loader.load();
//        } catch (IOException e) {
//            e.printStackTrace();
//            return;
//        }
//
//        Scene scene = new Scene(splashLayout);
//        stage.initStyle(StageStyle.UNDECORATED);
//        stage.setScene(scene);
//        stage.setMinWidth(640);
//        stage.setMinHeight(360);
//        stage.centerOnScreen();
//        stage.setResizable(false);
//
//        stage.show();
//
//        PauseTransition pause = new PauseTransition(Duration.seconds(1));
//        pause.setOnFinished(event -> {
//            FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), splashLayout);
//            fadeOut.setFromValue(1.0);
//            fadeOut.setToValue(0.0);
//            fadeOut.setOnFinished(fadeEvent -> {
//                stage.close();
//                showWelcomeScreen();
//            });
//            fadeOut.play();
//        });
//        pause.play();
//    }

    public static void main(String[] args) {
        launch(args);
    }

}