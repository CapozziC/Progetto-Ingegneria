package org.openjfx.DietiEstates25.Controllers;

import org.openjfx.DietiEstates25.*;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EstateAgentPageProfileController {
	@FXML
	private TextField textFieldNome, textFieldCognome, textFieldIdAgente, textFieldData;
	@FXML
	private PasswordField textFieldPasswordCorrente, textFieldNuovaPassword, textFieldConfermaNuovaPassword;
	@FXML
	private ImageView imageViewErroreVecchiaPassword, imageViewErroreNuovaPassword, imageViewErroreConfermaNuovaPassword;
	@FXML
	private Label labelErroreVecchiaPassword, labelErroreNuovePassword;
	@FXML
	private Button buttonMostraPasswordCorrente, buttonMostraNuovaPassword, buttonMostraConfermaNuovaPassword;
	@FXML
	private Label labelMostraPasswordCorrente, labelMostraNuovaPassword, labelMostraConfermaNuovaPassword;
	@FXML
	private ImageView imageViewMostraPasswordCorrente, imageViewMostraNuovaPassword, imageViewMostraConfermaNuovaPassword;
	@FXML
	private Button buttonAggiornaPassword;
	@FXML
	private Button buttonEliminaAccount;
	
	@FXML
	public void initialize() {
		enableOrDisableButtonUpdatePassword();
		managePasswordTextfieldsStyleOnInput();
		buttonMostraPasswordCorrente.setOnAction(event -> showPassword(labelMostraPasswordCorrente, imageViewMostraPasswordCorrente));
		buttonMostraNuovaPassword.setOnAction(event -> showPassword(labelMostraNuovaPassword, imageViewMostraNuovaPassword));
		buttonMostraConfermaNuovaPassword.setOnAction(event -> showPassword(labelMostraConfermaNuovaPassword, imageViewMostraConfermaNuovaPassword));
	}
	
	private void showPassword(Label password, ImageView image) {
		if(password.isVisible()) {
			image.setImage(new Image(getClass().getResource("Icon/LoginUser/icons8-occhio-chiuso-30.png").toExternalForm()));
		}
		else {
			image.setImage(new Image(getClass().getResource("Icon/LoginUser/icons8-visibile-30.png").toExternalForm()));
		}
		password.setVisible(!password.isVisible());
	}
	
	private void checkOldPasswordTextfield() {
    	if(textFieldPasswordCorrente.getText().isEmpty()) {
    		textFieldPasswordCorrente.setStyle("-fx-border-color: #E56B6F;");
    		imageViewErroreVecchiaPassword.setVisible(true);
    		labelErroreVecchiaPassword.setVisible(true);
    	}
    	else {
    		textFieldPasswordCorrente.setStyle(null);
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
                textFieldPasswordCorrente.getText().isEmpty() || 
                	textFieldNuovaPassword.getText().isEmpty() || 
                	textFieldConfermaNuovaPassword.getText().isEmpty() || 
                    !textFieldNuovaPassword.getText().equals(textFieldConfermaNuovaPassword.getText()),
                    textFieldPasswordCorrente.textProperty(),
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
    @SuppressWarnings("unused")
	private void managePasswordTextfieldsStyleOnInput() {
    	textFieldPasswordCorrente.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                checkOldPasswordTextfield();
            }
        });
        
    	textFieldPasswordCorrente.textProperty().addListener((observable, oldValue, newValue) -> {
    		labelMostraPasswordCorrente.setText(newValue);
            checkOldPasswordTextfield();
        });
        
        textFieldNuovaPassword.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                checkNewAndConfirmPasswordTetxFields();
            }
        });
        
        textFieldNuovaPassword.textProperty().addListener((observable, oldValue, newValue) -> {
        	labelMostraNuovaPassword.setText(newValue);
            checkNewAndConfirmPasswordTetxFields();
        });
        
        textFieldConfermaNuovaPassword.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                checkNewAndConfirmPasswordTetxFields();
            }
        });
        
        textFieldConfermaNuovaPassword.textProperty().addListener((observable, oldValue, newValue) -> {
        	labelMostraConfermaNuovaPassword.setText(newValue);
            checkNewAndConfirmPasswordTetxFields();
        });
    }
    
    @SuppressWarnings("unused")
	public void DeleteAccount() {
    	PopupManager.showPopup("Elimina account", "Sei sicuro di voler eliminare il tuo account?", "Error", result -> {
    		PopupManager.showInfoPopup("Elimina account", "Il tuo account è stato eliminato");
    	}, true);
    }

}
