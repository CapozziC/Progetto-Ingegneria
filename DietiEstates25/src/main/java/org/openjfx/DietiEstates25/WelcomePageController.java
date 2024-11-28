package org.openjfx.DietiEstates25;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.input.MouseEvent;

public class WelcomePageController {
	@FXML
	private Button buttonEntraAgenzia;
	@FXML
	private Button buttonAccediUtente;
	@FXML
	private Button buttonRegistratiUtente;
	
	@FXML
	public void initialize() {
		setButtonHoverEffect(buttonEntraAgenzia, "#4a3470", "#9593D9");
		setButtonHoverEffect(buttonAccediUtente, "#4a3470", "#9593D9");
		setButtonHoverEffect(buttonRegistratiUtente, "#4a3470", "#9593D9");
	}

	private void setButtonHoverEffect(Button button, String hoverColor, String originalColor) {
	       button.setOnMouseEntered(event -> {
	            button.setStyle("-fx-background-color: "+ hoverColor +"; -fx-text-fill: white; -fx-background-radius: 25; -fx-border-color: #4A3371; -fx-border-radius: 25;");
	        });
	       
	       button.setOnMouseExited(event -> {
	            button.setStyle("-fx-background-color: "+ originalColor +"; -fx-text-fill: white; -fx-background-radius: 25; -fx-border-color: #4A3371; -fx-border-radius: 25;");
	        });
	}
}
