package org.openjfx.DietiEstates25;

import javafx.animation.TranslateTransition;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

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
    
	// Search Pane
	@FXML
	private Pane paneHome;
	@FXML
	private Button buttonPulisciRicerca, buttonFiltri;
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
    private Label labelErroreVecchiaPassword, labelErroreNuovePassword;
    @FXML
    private ImageView imageViewErroreVecchiaPassword, imageViewErroreNuovaPassword, imageViewErroreConfermaNuovaPassword;
    @FXML
    private Button buttonAggiornaPassword, buttonEliminaAccount;
    
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
    	setButtonCloseImage();
        setButtonIconizeImage();
        buttonNascondi.setOnAction(event -> manageButtonClick());
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
        buttonEliminaAccount.setOnAction(event -> DeleteAccount());
        buttonFiltri.setOnAction(event -> showFilterWindow(null));
        managePasswordTextfieldsStyleOnInput();
        enableOrDisableButtonUpdatePassword();
        showAndHideButtonCleanSearchBar();
        fillChoiceBoxOrder();
        changeArrowChoiceBoxOrientation();
        loadAppointment();
        loadEstates();
    }

    private void manageButtonClick() {
        setSidePanelButtonsTextAndWidth(buttonHome, "Home");
        setSidePanelButtonsTextAndWidth(buttonProfilo, "Profilo");
        setSidePanelButtonsTextAndWidth(buttonOfferte, "Offerte");
        setSidePanelButtonsTextAndWidth(buttonAppuntamenti, "Appuntamenti");
        setSidePanelButtonsTextAndWidth(buttonLogout, "Logout");
        setSidePanelButtonsTextAndWidth(buttonNascondi, "Nascondi");
        resizeSidePanelWidth();
    }

    private void setSidePanelButtonsTextAndWidth(Button button, String expandedText) {
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
    private void resizeSidePanelWidth() {
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
    
    private void checkOldPasswordTextfield() {
    	if(textFieldPassword.getText().isEmpty()) {
    		textFieldPassword.setStyle("-fx-border-color: #E56B6F;");
    		imageViewErroreVecchiaPassword.setVisible(true);
    		labelErroreVecchiaPassword.setVisible(true);
    	}
    	else {
    		textFieldPassword.setStyle(null);
    		imageViewErroreVecchiaPassword.setVisible(false);
    		labelErroreVecchiaPassword.setVisible(false);
    	}
    }
    
    private void checkNewAndConfirmPaswordTetxFields() {
    	if(textFieldNuovaPassword.getText().isEmpty() || textFieldConfermaNuovaPassword.getText().isEmpty() || !textFieldNuovaPassword.getText().equals(textFieldConfermaNuovaPassword.getText())) {
    		textFieldNuovaPassword.setStyle("-fx-border-color: #E56B6F;");
    		textFieldConfermaNuovaPassword.setStyle("-fx-border-color: #E56B6F;");
    		imageViewErroreNuovaPassword.setVisible(true);
    		imageViewErroreConfermaNuovaPassword.setVisible(true);
    		labelErroreNuovePassword.setVisible(true);
    	}
    	else {
    		textFieldNuovaPassword.setStyle("-fx-border-color: #A7FF97;");
    		textFieldConfermaNuovaPassword.setStyle("-fx-border-color: #A7FF97;");
    		imageViewErroreNuovaPassword.setVisible(false);
    		imageViewErroreConfermaNuovaPassword.setVisible(false);
    		labelErroreNuovePassword.setVisible(false);
    	}
    }
    
    /*
     * Attiva o disattiva il button per aggiornare la password in base
     * al contenuto delle textfields
     */
    private void enableOrDisableButtonUpdatePassword() {
    	buttonAggiornaPassword.disableProperty().bind(
                Bindings.createBooleanBinding(() -> 
                	textFieldPassword.getText().isEmpty() || 
                	textFieldNuovaPassword.getText().isEmpty() || 
                	textFieldConfermaNuovaPassword.getText().isEmpty() || 
                    !textFieldNuovaPassword.getText().equals(textFieldConfermaNuovaPassword.getText()),
                    textFieldPassword.textProperty(),
                    textFieldNuovaPassword.textProperty(),
                    textFieldConfermaNuovaPassword.textProperty()
                )
        );
    }
    
    /*
     * Ogni volta che l'utente modifica il testo di una textfield o
     * la textfield perde il focus, i metodi controllano il testo delle
     * textfields per decidere come colorare i loro bordi
     */
    private void managePasswordTextfieldsStyleOnInput() {
    	textFieldPassword.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                checkOldPasswordTextfield();
            }
        });
        
        textFieldPassword.textProperty().addListener((observable, oldValue, newValue) -> {
            checkOldPasswordTextfield();
        });
        
        textFieldNuovaPassword.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                checkNewAndConfirmPaswordTetxFields();
            }
        });
        
        textFieldNuovaPassword.textProperty().addListener((observable, oldValue, newValue) -> {
            checkNewAndConfirmPaswordTetxFields();
        });
        
        textFieldConfermaNuovaPassword.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                checkNewAndConfirmPaswordTetxFields();
            }
        });
        
        textFieldConfermaNuovaPassword.textProperty().addListener((observable, oldValue, newValue) -> {
            checkNewAndConfirmPaswordTetxFields();
        });
    }
    
    public void DeleteAccount() {
    	PopupManager.showPopup("Elimina account", "Sei sicuro di voler eliminare il tuo account?", "Error", result -> {
    		PopupManager.showInfoPopup("Elimina account", "Il tuo account è stato eliminato");
    	}, true);
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
    
    public static void showFilterWindow(Consumer<Boolean> applyAction) {
        try {
            FXMLLoader loader = new FXMLLoader(WindowsManager.class.getResource("FilterWindow.fxml"));
            Parent root = loader.load();
            
            FilterWindowController controller = loader.getController();
            
            if (applyAction != null) {
                controller.setApplyCallback(applyAction);
            }
            
            Stage popupStage = new Stage();
            popupStage.setResizable(false);
            popupStage.initStyle(StageStyle.UNDECORATED);
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setScene(new Scene(root));
            popupStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    	Pane root;
		try {
			for (int i = 0; i < 10; i++) {
				if(i%2==0) {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("AppointmentObject.fxml"));
					root = loader.load();
				}
				else {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("AppointmentObject-Alternative.fxml"));
					root = loader.load();
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
