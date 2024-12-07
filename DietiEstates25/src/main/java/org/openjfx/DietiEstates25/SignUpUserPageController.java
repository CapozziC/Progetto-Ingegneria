package org.openjfx.DietiEstates25;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

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


public void initialize() {
	setFocusListenerSignUp(textFieldNomeSignUp);
	setFocusListenerSignUp(textFieldCognomeSignUp);
	setFocusListenerSignUp(textFieldIndirizzoSignUp);
	setFocusListenerSignUp(textFieldEmailSignUp);
	setFocusListenerSignUp(textFieldConfermaPasswordSignUp);
	buttonBackSignUp.setOnAction(event->loadLoginUserScene());
	buttonInfoSignUp.setOnMouseClicked(this::showInfoPasswordSignUp);
	
  }

public void loadLoginUserScene() {
    try {
        WindowsManager.loadLoginUserScene();
    } catch (IOException e) {
        e.printStackTrace();
    }
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
}
