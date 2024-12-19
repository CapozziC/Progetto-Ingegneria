package org.openjfx.DietiEstates25;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import javafx.util.Duration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

import org.openjfx.DietiEstates25.WindowsManager;

public class WelcomePageController {
    @FXML
    private Button buttonEntraAgenzia;
    @FXML
    private Button buttonAccediUtente;
    @FXML
    private Button buttonRegistratiUtente;
    @FXML
    private SplitPane splitPane;
    @FXML
    private Button buttonIconizza;
    @FXML
    private ImageView imageIconizza;
    @FXML
    private Button buttonChiudi;
    @FXML
    private ImageView imageChiudi;
    @FXML
    private Button buttonGroupMobile;
    @FXML
    private Group groupMobile;
    @FXML
    private Label labelGroupMobileLeft, labelGroupMobileRight;
    @FXML
    private VBox vBoxMobile;
    @FXML
    private StackPane stackPaneCorniceDestra;
    @FXML
    private StackPane stackPaneCorniceSinistra;
    @FXML
    private ComboBox<String> comboBoxLingua;

    private boolean isAtInitialPosition = true;

    @FXML
    public void initialize() {
    	buttonIconizza.setOnAction(event -> WindowsManager.iconizeWindows());
    	buttonChiudi.setOnAction(event -> WindowsManager.closeWindow());
        buttonAccediUtente.setOnAction(event -> WindowsManager.loadLoginUserScene());
        buttonRegistratiUtente.setOnAction(event -> WindowsManager.loadSignUpScene());
        buttonGroupMobile.setOnAction(event -> movePaneMobile());
        buttonEntraAgenzia.setOnAction(event -> WindowsManager.loadEstateAgentScene());
        setShadowForComponent();
        setButtonCloseImage();
        setButtonIconizeImage();
        setComboBoxLanguageItems();
    }

    public void movePaneMobile() {
        String vboxMobileOldStyle = vBoxMobile.getStyle();
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(groupMobile);
        transition.setDuration(Duration.seconds(0.25));

        FadeTransition fadeTransitionDestra = new FadeTransition();
        fadeTransitionDestra.setNode(stackPaneCorniceDestra);
        fadeTransitionDestra.setDuration(Duration.seconds(0.25));

        FadeTransition fadeTransitionSinistra = new FadeTransition();
        fadeTransitionSinistra.setNode(stackPaneCorniceSinistra);
        fadeTransitionSinistra.setDuration(Duration.seconds(0.25));

        if (isAtInitialPosition) {
            transition.setByX(800);
            transition.setByY(0);
            transition.setOnFinished(e -> {
            	labelGroupMobileLeft.setOpacity(0);
            	labelGroupMobileRight.setOpacity(1);
                vBoxMobile.setStyle(vboxMobileOldStyle + "; -fx-background-color: linear-gradient(to right, #6756be, #c084f5);");
            });

            fadeTransitionDestra.setFromValue(1);
            fadeTransitionDestra.setToValue(0);

            fadeTransitionSinistra.setFromValue(0);
            fadeTransitionSinistra.setToValue(1);
        } else {
            transition.setByX(-800);
            transition.setByY(0);
            transition.setOnFinished(e -> {
            	labelGroupMobileRight.setOpacity(0);
            	labelGroupMobileLeft.setOpacity(1);
                vBoxMobile.setStyle(vboxMobileOldStyle + "; -fx-background-color: linear-gradient(to left, #6756be, #c084f5);");
            });

            fadeTransitionDestra.setFromValue(0);
            fadeTransitionDestra.setToValue(1);

            fadeTransitionSinistra.setFromValue(1);
            fadeTransitionSinistra.setToValue(0);
        }

        transition.setCycleCount(1);
        transition.setAutoReverse(false);
        transition.play();

        fadeTransitionDestra.play();
        fadeTransitionSinistra.play();

        isAtInitialPosition = !isAtInitialPosition;
    }

    private void setShadowForComponent() {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(25);
        dropShadow.setColor(Color.color(0.403921568627451, 0.33725490196078434, 0.7450980392156863));
        stackPaneCorniceDestra.setEffect(dropShadow);
        stackPaneCorniceSinistra.setEffect(dropShadow);
    }
    
    private void setButtonCloseImage() {
    	buttonChiudi.setOnMouseEntered(event -> {imageChiudi.setImage(new Image(getClass().getResource("Icon/TitleBar/icons8-eliminare-bianca-30.png").toExternalForm()));
    			});
    	buttonChiudi.setOnMouseExited(event -> {imageChiudi.setImage(new Image(getClass().getResource("Icon/TitleBar/icons8-eliminare-30.png").toExternalForm()));
		});
    }
    
    private void setButtonIconizeImage() {
    	buttonIconizza.setOnMouseEntered(event -> {imageIconizza.setImage(new Image(getClass().getResource("Icon/TitleBar/icons8-linea-orizzontale-bianca-30.png").toExternalForm()));
    			});
    	buttonIconizza.setOnMouseExited(event -> {imageIconizza.setImage(new Image(getClass().getResource("Icon/TitleBar/icons8-linea-orizzontale-30.png").toExternalForm()));
		});
    }
    
    private void setComboBoxLanguageItems() {
        comboBoxLingua.getItems().addAll(
        	"/org/openjfx/DietiEstates25/Icon/WelcomePage/icons8-italia-40.png",
            "/org/openjfx/DietiEstates25/Icon/WelcomePage/icons8-gran-bretagna-40.png"
        );

        Callback<ListView<String>, ListCell<String>> cellFactory = new Callback<>() {
            @Override
            public ListCell<String> call(ListView<String> param) {
                return new ListCell<>() {
                    private final ImageView imageView = new ImageView();

                    @Override
                    protected void updateItem(String iconPath, boolean empty) {
                        super.updateItem(iconPath, empty);
                        if (empty || iconPath == null) {
                            setGraphic(null);
                        } else {
                            imageView.setImage(new Image(getClass().getResourceAsStream(iconPath)));
                            imageView.setFitWidth(20);
                            imageView.setFitHeight(20);
                            setGraphic(imageView);
                        }
                    }
                };
            }
        };

        comboBoxLingua.setCellFactory(cellFactory);
        comboBoxLingua.setButtonCell(cellFactory.call(null));
        if (WindowsManager.getResourceBundle().getLocale().toString().equals("it")) {
			comboBoxLingua.getSelectionModel().select("/org/openjfx/DietiEstates25/Icon/WelcomePage/icons8-italia-40.png");
		}
        else if(WindowsManager.getResourceBundle().getLocale().toString().equals("en")) {
        	comboBoxLingua.getSelectionModel().select("/org/openjfx/DietiEstates25/Icon/WelcomePage/icons8-gran-bretagna-40.png");
        }
		comboBoxLingua.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                Locale newLocale;
                if (newValue.equals("/org/openjfx/DietiEstates25/Icon/WelcomePage/icons8-italia-40.png")) {
                    newLocale = Locale.of("it", "IT");
                }
                else {
                    newLocale = Locale.of("en", "US");
                }
                WindowsManager.setLanguage(newLocale);
                WindowsManager.loadWelcomeScene();
            }
        });
    }
}
