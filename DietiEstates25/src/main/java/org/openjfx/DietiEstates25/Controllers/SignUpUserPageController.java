package org.openjfx.DietiEstates25.Controllers;

import org.openjfx.DietiEstates25.*;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import org.openjfx.DietiEstates25.WindowsManager;

public class SignUpUserPageController {
	@FXML
	private TextField textFieldNomeSignUp;
	@FXML
	private TextField textFieldCognomeSignUp;
	@FXML
	private TextField textFieldIndirizzoSignUp;
	@FXML
	private TextField textFieldEmailSignUp;
	@FXML
	private TextField textFieldConfermaPasswordSignUp;
	@FXML
	private TextField textFieldPasswordSignUp;
	@FXML
	private Button buttonInfoSignUp;
	@FXML
	private Label labelInfoPasswordSignUp;
	@FXML
	private Button buttonRegistratiSignUp;
	@FXML
	private Button buttonBackSignUp;
	@FXML
	private HBox hBoxSignUp;
	@FXML
	private Button buttonCloseSignUp;
	@FXML
	private Button buttonIconizeSignUp;
	
	




	@FXML
	public void initialize() {
		setFocusListenerSignUp(textFieldNomeSignUp);
		setFocusListenerSignUp(textFieldCognomeSignUp);
		setFocusListenerSignUp(textFieldIndirizzoSignUp);
		setFocusListenerSignUp(textFieldEmailSignUp);
		setFocusListenerSignUp(textFieldConfermaPasswordSignUp);
		buttonCloseSignUp.setOnAction(event -> WindowsManager.closeWindow());
		buttonIconizeSignUp.setOnAction(event ->WindowsManager.iconizeWindows());
		buttonBackSignUp.setOnAction(event->WindowsManager.loadLoginUserScene());
		buttonInfoSignUp.setOnMouseClicked(this::showInfoPasswordSignUp);
		textFieldPasswordSignUp.textProperty().addListener((observable, oldValue, newValue) -> ValidatePassword());
		textFieldConfermaPasswordSignUp.textProperty().addListener((observable, oldValue, newValue) -> ValidatePassword());
		
		
	}


	private void setFocusListenerSignUp(TextField textField) {
		textField.focusedProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue) {
            textField.setStyle("-fx-border-color: #6756be; -fx-border-width: 2px; -fx-border-radius: 5;");
        } else {
            if (textField.getText().isEmpty()) {
                textField.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 5;");
            } else {
                textField.setStyle("-fx-border-color: #6756be; -fx-border-width: 2px; -fx-border-radius: 5;");
            }
        }
    });
 }
	public void showInfoPasswordSignUp(MouseEvent event) {
		if (labelInfoPasswordSignUp != null) {
			labelInfoPasswordSignUp.setVisible(!labelInfoPasswordSignUp.isVisible());
		}

	}
	
	private void ValidatePassword() {
		String Password = textFieldPasswordSignUp.getText();
		String ConfermaPassword  = textFieldConfermaPasswordSignUp.getText(); 
		
		if (Password.equals(ConfermaPassword)) {
			textFieldConfermaPasswordSignUp.setStyle("-fx-border-color: #6756be ; -fx-border-width: 2px; -fx-border-radius: 5;");
			buttonRegistratiSignUp.setDisable(false);
			}else {
				textFieldConfermaPasswordSignUp.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 5;");
				buttonRegistratiSignUp.setDisable(true);
				
				
			}
	}
}
