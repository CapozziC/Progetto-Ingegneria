package org.openjfx.DietiEstates25;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    private static final String BUNDLE_BASE_NAME = "org.openjfx.DietiEstates25.Languages.language";
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_BASE_NAME, Locale.getDefault());

    // File FXML
    private static final String WELCOME_PAGE = "FXMLFiles/WelcomePage/WelcomePage.fxml";
    private static final String LOGIN_USER_PAGE = "FXMLFiles/UserAuthenticationPage/LoginUserPage.fxml";
    private static final String SIGN_UP_PAGE = "FXMLFiles/UserAuthenticationPage/SignUpUserPage.fxml";
    private static final String SEARCH_PAGE = "SearchPage.fxml";
    private static final String HOME_PAGE = "FXMLFiles/HomePage/HomePage.fxml";
    private static final String SPLASH_PAGE = "SplashPage.fxml";
    private static final String ESTATE_AGENT_PAGE = "FXMLFiles/EstateAgentPage/EstateAgentPage.fxml";

    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
        primaryStage.initStyle(StageStyle.UNDECORATED);
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void setLanguage(Locale locale) {
        resourceBundle = ResourceBundle.getBundle(BUNDLE_BASE_NAME, locale);
    }

    public static ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

    public static void openWindow(String fxmlPath, String title, int width, int height, Modality modality, Stage fatherStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(WindowsManager.class.getResource(fxmlPath), resourceBundle);
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

        if (fatherStage == null) {
            stage.initOwner(getPrimaryStage());
        } else {
            stage.initOwner(fatherStage);
        }

        if (modality != null) {
            stage.initModality(modality);
        }

        stage.show();
    }

    public static void changeScene(String fxmlPath, double width, double height) throws IOException {
        FXMLLoader loader = new FXMLLoader(WindowsManager.class.getResource(fxmlPath), getResourceBundle());
        Parent root = loader.load();

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
            if (!primaryStage.isFullScreen()) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        root.setOnMouseDragged((MouseEvent event) -> {
            if (!primaryStage.isFullScreen()) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });
    }

    // Metodi per caricare le scene specifiche
    public static void loadWelcomeScene() {
        try {
            changeScene(WELCOME_PAGE, WINDOW_WIDTH, WINDOW_HEIGHT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadLoginUserScene() {
        try {
            changeScene(LOGIN_USER_PAGE, WINDOW_WIDTH, WINDOW_HEIGHT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadSignUpScene() {
        try {
            changeScene(SIGN_UP_PAGE, WINDOW_WIDTH, WINDOW_HEIGHT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadSearchScene() {
        try {
            changeScene(SEARCH_PAGE, 1000, 500);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadHomeScene() {
        try {
            changeScene(HOME_PAGE, WINDOW_WIDTH, WINDOW_HEIGHT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadSplashScene() {
        try {
            changeScene(SPLASH_PAGE, WINDOW_WIDTH, WINDOW_HEIGHT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadEstateAgentScene() {
        try {
            changeScene(ESTATE_AGENT_PAGE, WINDOW_WIDTH, WINDOW_HEIGHT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
