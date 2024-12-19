package org.openjfx.DietiEstates25;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.control.PasswordField;

public class HomePageProfileController {
	@FXML
	private TextField textFieldNome;
	@FXML
	private TextField textFieldCognome;
	@FXML
	private TextField textFieldEmail;
	@FXML
	private TextField textFieldIndirizzo;
	@FXML
	private TextField textFieldData;
	@FXML
	private PasswordField textFieldPassword;
	@FXML
	private PasswordField textFieldNuovaPassword;
	@FXML
	private PasswordField textFieldConfermaNuovaPassword;
	@FXML
	private Button buttonAggiornaPassword;
	@FXML
	private Button buttonEliminaAccount;
	@FXML
	private Label labelErroreVecchiaPassword;
	@FXML
	private Label labelErroreNuovePassword;
	@FXML
	private ImageView imageViewErroreVecchiaPassword;
	@FXML
	private ImageView imageViewErroreNuovaPassword;
	@FXML
	private ImageView imageViewErroreConfermaNuovaPassword;
	
	@FXML
    public void initialize() {
		managePasswordTextfieldsStyleOnInput();
		enableOrDisableButtonUpdatePassword();
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
    
    private void checkNewAndConfirmPasswordTetxFields() {
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
                checkNewAndConfirmPasswordTetxFields();
            }
        });
        
        textFieldNuovaPassword.textProperty().addListener((observable, oldValue, newValue) -> {
            checkNewAndConfirmPasswordTetxFields();
        });
        
        textFieldConfermaNuovaPassword.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                checkNewAndConfirmPasswordTetxFields();
            }
        });
        
        textFieldConfermaNuovaPassword.textProperty().addListener((observable, oldValue, newValue) -> {
            checkNewAndConfirmPasswordTetxFields();
        });
    }
    
    public void DeleteAccount() {
    	PopupManager.showPopup("Elimina account", "Sei sicuro di voler eliminare il tuo account?", "Error", result -> {
    		PopupManager.showInfoPopup("Elimina account", "Il tuo account è stato eliminato");
    	}, true);
    }

}
