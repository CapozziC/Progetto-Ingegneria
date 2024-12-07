package org.openjfx.DietiEstates25;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import org.openjfx.DietiEstates25.WindowsManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomePageController {
	
	// TitleBar
	@FXML
    private Button buttonIconizza, buttonChiudi;
	@FXML
	private ImageView imageIconizza, imageChiudi;
	
	@FXML
    private StackPane stackPaneGenerale;
	
	// Side Panel
    @FXML
    private Button buttonHome, buttonProfilo, buttonOfferte, buttonAppuntamenti, buttonLogout, buttonNascondi;
    @FXML
    private Pane paneLaterale, paneSegnalino;
    @FXML
    private VBox vBoxLaterale;
    @FXML
    private ImageView imageNascondi;
    
	// Home Pane
	@FXML
	private Pane paneHome;
	@FXML
	private Button buttonPulisciRicerca;
	@FXML
	private TextField textFieldRicerca;
	@FXML
	private ScrollPane scrollPaneImmobili;
	@FXML
	private Label labelNumeroImmobili;
	@FXML
	private VBox vBoxImmobili;
	@FXML
	private ChoiceBox<String> choiceBoxOrdine;
    
    // Profile Pane
    @FXML
    private Pane paneProfilo;
    @FXML
    private TextField textFieldNome, textFieldCognome, textFieldEmail, textFieldIndirizzo, textFieldData;
    @FXML
    private PasswordField textFieldPassword, textFieldNuovaPassword, textFieldConfermaNuovaPassword;
    @FXML
    private Button buttonAggiornaPassword;
    
    // Supply Pane
    @FXML
    private Pane paneOfferte;
    
    // Appointment Pane
    @FXML
    private Pane paneAppuntamenti;
    @FXML
    private ScrollPane scrollPaneAppuntamenti;
    @FXML
    private VBox vBoxAppuntamenti;
    @FXML
    private Label labelNumeroAppuntamenti;

    private static final double SIDEPANE_WIDTH_EXPANDED = 220;
    private static final double SIDEPANE_WIDTH_COLLAPSED = 70;
    private static final double BUTTON_WIDTH_EXPANDED = 180;
    private static final double BUTTON_WIDTH_COLLAPSED = 30;

    @FXML
    public void initialize() {
    	buttonIconizza.setOnAction(event -> WindowsManager.iconizeWindows());
    	buttonChiudi.setOnAction(event -> WindowsManager.closeWindow());
        buttonNascondi.setOnAction(event -> handleButtonClick());
        buttonHome.setOnAction(event -> {
        	openRightPane(paneHome);
        	setIndicatorPositionOfSidePanel(buttonHome);
        });
        buttonProfilo.setOnAction(event -> {
        	openRightPane(paneProfilo);
        	setIndicatorPositionOfSidePanel(buttonProfilo);
        });
        buttonOfferte.setOnAction(event -> {
        	openRightPane(paneOfferte);
        	setIndicatorPositionOfSidePanel(buttonOfferte);
        });
        buttonAppuntamenti.setOnAction(event -> {
        	openRightPane(paneAppuntamenti);
        	setIndicatorPositionOfSidePanel(buttonAppuntamenti);
        });
        buttonLogout.setOnAction(event -> WindowsManager.loadLoginUserScene());
        buttonPulisciRicerca.setOnAction(event -> cleanSearchBar());
        setButtonCloseImage();
        setButtonIconizeImage();
        addListenerToTextFieldsPassword(textFieldNuovaPassword);
        addListenerToTextFieldsPassword(textFieldConfermaNuovaPassword);
        addListenerToTextFieldsPassword(textFieldPassword);
//        testTextFielsPassword(textFieldPassword);
//        testTextFielsPassword(textFieldNuovaPassword);
//        testTextFielsPassword(textFieldConfermaNuovaPassword);
        showAndHideButtonCleanSearchBar();
        fillChoiceBoxOrder();
        changeArrowChoiceBoxOrientation();
        loadAppointment();
        loadEstates();
    }

    private void handleButtonClick() {
        toggleButtonTextAndWidth(buttonHome, "Home");
        toggleButtonTextAndWidth(buttonProfilo, "Profilo");
        toggleButtonTextAndWidth(buttonOfferte, "Offerte");
        toggleButtonTextAndWidth(buttonAppuntamenti, "Appuntamenti");
        toggleButtonTextAndWidth(buttonLogout, "Logout");
        toggleButtonTextAndWidth(buttonNascondi, "Nascondi");
        toggleSidePaneWidth();
    }

    private void toggleButtonTextAndWidth(Button button, String expandedText) {
        if (button.getText().isEmpty()) {
            button.setText(expandedText);
            button.setPrefWidth(BUTTON_WIDTH_EXPANDED);
        } else {
            button.setText("");
            button.setPrefWidth(BUTTON_WIDTH_COLLAPSED);
        }
    }
    
    /*
     * Metodo che gestisce la chiusura e l'apertura
     * del pannello laterale
     */
    private void toggleSidePaneWidth() {
        double currentWidth = vBoxLaterale.getWidth();
        if (currentWidth == SIDEPANE_WIDTH_COLLAPSED) {
            vBoxLaterale.setPrefWidth(SIDEPANE_WIDTH_EXPANDED);
            paneLaterale.setPrefWidth(SIDEPANE_WIDTH_EXPANDED);
            Image image = new Image(getClass().getResource("/org/openjfx/DietiEstates25/Icon/HomePage/icons8-nascondi-pannello-30.png").toExternalForm());
            imageNascondi.setImage(image);
        } else {
            vBoxLaterale.setPrefWidth(SIDEPANE_WIDTH_COLLAPSED);
            paneLaterale.setPrefWidth(SIDEPANE_WIDTH_COLLAPSED);
            Image image = new Image(getClass().getResource("/org/openjfx/DietiEstates25/Icon/HomePage/icons8-apri-pannello-30.png").toExternalForm());
            imageNascondi.setImage(image);
        }
    }
    
    /*
     * Metodo che gestisce l'apertura del giusto Pane
     * quando viene cliccato un button sul Pane laterale
     */
    private void openRightPane(Pane paneShowed) {
    	List<Pane> panes = List.of(paneHome, paneProfilo, paneOfferte, paneAppuntamenti);
    	List<Pane> panesArray = new ArrayList<>(panes);
     	panesArray.remove(paneShowed);
    	for (Pane pane : panesArray) {
			pane.setVisible(false);
		}
    	paneShowed.setVisible(true);
    }
    
    private void setIndicatorPositionOfSidePanel(Button button) {
    	paneSegnalino.setVisible(true);
    	TranslateTransition transition = new TranslateTransition(Duration.millis(200), paneSegnalino);
        transition.setToY(button.getLayoutY()-20);
        transition.play();
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
    
    private void addListenerToTextFieldsPassword(PasswordField passwordfield) {
        passwordfield.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                checkIfPasswordsIsEquals();
            }
        });
        
        passwordfield.textProperty().addListener((observable, oldValue, newValue) -> {
            checkIfPasswordsIsEquals();
        });
    }
    
//    private void changeTextFieldBorderColor(TextField textfield, String color) {
//    	textfield.setStyle(color);
//    }
//    
//    private void testTextFielsPassword(PasswordField passwordfield) {
//        passwordfield.focusedProperty().addListener((observable, oldValue, newValue) -> {
//            if (!newValue) {
//            	showUpdatePasswordButton();
//            }
//        });
//    	
//        passwordfield.textProperty().addListener((observable, oldValue, newValue) -> {
//        	showUpdatePasswordButton();
//      });
//    	
//    }
//    
//    private boolean checkOldPasswordLength() {
//    	String vecchiapassword = textFieldPassword.getText();
//    	if(vecchiapassword.length()!=0) {
//    		changeTextFieldBorderColor(textFieldPassword, null);
//    		return true;
//    	}
//    	else {
//    		changeTextFieldBorderColor(textFieldPassword, "-fx-border-color: red;");
//    		return false;
//    	}
//    }
//    
//    private boolean checkNewAandConfirmPassword() {
//    	String nuovapassword = textFieldNuovaPassword.getText();
//    	String confermanuovapassword = textFieldConfermaNuovaPassword.getText();
//    	if(nuovapassword.equals(confermanuovapassword) && nuovapassword.length()!=0 && confermanuovapassword.length()!=0) {
//    		changeTextFieldBorderColor(textFieldNuovaPassword, "-fx-border-color: green;");
//    		changeTextFieldBorderColor(textFieldConfermaNuovaPassword, "-fx-border-color: green;");
//    		return true;
//    	}
//    	else {
//    		changeTextFieldBorderColor(textFieldNuovaPassword, "-fx-border-color: red;");
//    		changeTextFieldBorderColor(textFieldConfermaNuovaPassword, "-fx-border-color: red;");
//    		return false;
//    	}
//    }
//    
//    private void showUpdatePasswordButton() {
//    	if(checkOldPasswordLength() && checkNewAandConfirmPassword()) {
//    		buttonAggiornaPassword.setDisable(false);
//    	}
//    	else {
//    		checkNewAandConfirmPassword();
//    		buttonAggiornaPassword.setDisable(true);
//    	}
//    }
    
    private void checkIfPasswordsIsEquals() {
    	String vecchiapassword = textFieldPassword.getText();
    	String nuovapassword = textFieldNuovaPassword.getText();
    	String confermanuovapassword = textFieldConfermaNuovaPassword.getText();
    	if(nuovapassword.equals(confermanuovapassword) && nuovapassword.length()!=0 && confermanuovapassword.length()!=0 && vecchiapassword.length()!=0) {
    		textFieldNuovaPassword.setStyle("-fx-border-color: green;");
    		textFieldConfermaNuovaPassword.setStyle("-fx-border-color: green;");
    		textFieldPassword.setStyle(null);
    		buttonAggiornaPassword.setDisable(false);
    	}
    	else {
    		textFieldNuovaPassword.setStyle("-fx-border-color: red;");
    		textFieldConfermaNuovaPassword.setStyle("-fx-border-color: red;");
    		textFieldPassword.setStyle("-fx-border-color: red;");
    		buttonAggiornaPassword.setDisable(true);
    	}
    }
    
    public void DeleteAccount() {
    	WindowsManager.showDecisionPopup("Elimina account", "Stai per eliminare il tuo account per sempre", "Sei sicuro di voler eliminare l'account?", "Il tuo account è stato eliminato con successo");
    }
    
    private void showAndHideButtonCleanSearchBar() {
    	textFieldRicerca.textProperty().addListener((observable, oldValue, newValue) -> {
            if(textFieldRicerca.getText().length()!=0) {
            	buttonPulisciRicerca.setVisible(true);
            }
            else {
            	buttonPulisciRicerca.setVisible(false);
            }
        });
    }
    
    private void cleanSearchBar() {
    	textFieldRicerca.setText("");
    }
    
    private void fillChoiceBoxOrder() {
    	choiceBoxOrdine.getItems().addAll("Più caro", "Meno caro", "Più vicino", "Meno vicino", "Più grande", "Meno grande", "Più locali", "Meno locali");
    	choiceBoxOrdine.setValue("Più caro");
    }
    
    private void changeArrowChoiceBoxOrientation() {
    	choiceBoxOrdine.setOnShowing(event -> {
            choiceBoxOrdine.lookup(".arrow").setStyle("-fx-rotate: 180;");
        });

        choiceBoxOrdine.setOnHiding(event -> {
            choiceBoxOrdine.lookup(".arrow").setStyle("-fx-rotate: 0;");
        });
    }
    
    /*
     * Crea oggeti di tipo Immobile e li inserisce 
     * all'interno di una ScrollPane
     */
    private void loadEstates() {
    	int numeroImmobili = 0;
		try {
			for (int i = 0; i < 5; i++) {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("EstateObject.fxml"));
				Pane root = loader.load();
				vBoxImmobili.getChildren().add(root);
				numeroImmobili++;
				labelNumeroImmobili.setText(String.valueOf(numeroImmobili)+" Immobili");
			}
			scrollPaneImmobili.setContent(vBoxImmobili);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    /*
     * Crea oggeti di tipo Appuntamento e li inserisce 
     * all'interno di una ScrollPane
     */
    private void loadAppointment() {
    	int numeroAppuntamenti = 0;
		try {
			for (int i = 0; i < 10; i++) {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("AppointmentObject.fxml"));
				Pane root = loader.load();
				if(i%2==0) {
					String oldstyle = root.getChildren().get(0).getStyle();
					root.getChildren().get(0).setStyle(oldstyle+"; -fx-background-color: #edecf8;");
				}
				vBoxAppuntamenti.getChildren().add(root);
				numeroAppuntamenti++;
				labelNumeroAppuntamenti.setText(String.valueOf(numeroAppuntamenti)+" Appuntamenti");
			}
			scrollPaneAppuntamenti.setContent(vBoxAppuntamenti);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
