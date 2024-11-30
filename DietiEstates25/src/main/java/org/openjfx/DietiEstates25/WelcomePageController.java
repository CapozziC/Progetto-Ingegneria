package org.openjfx.DietiEstates25;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

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
	private Pane paneMobile;
	@FXML
	private Button buttonMobile;
	
	
	@FXML
	public void initialize() {
		setButtonHoverEffect(buttonEntraAgenzia, "#4a3470", "#9593D9");
		setButtonHoverEffect(buttonAccediUtente, "#4a3470", "#9593D9");
		setButtonHoverEffect(buttonRegistratiUtente, "#4a3470", "#9593D9");
		buttonAccediUtente.setOnAction(event -> openLoginUserPage());
	}

	private void setButtonHoverEffect(Button button, String hoverColor, String originalColor) {
	       button.setOnMouseEntered(event -> {
	            button.setStyle("-fx-background-color: "+ hoverColor +"; -fx-text-fill: white; -fx-background-radius: 25; -fx-border-color: #4A3371; -fx-border-radius: 25;");
	        });
	       
	       button.setOnMouseExited(event -> {
	            button.setStyle("-fx-background-color: "+ originalColor +"; -fx-text-fill: white; -fx-background-radius: 25; -fx-border-color: #4A3371; -fx-border-radius: 25;");
	        });
	}
	
	public void openLoginUserPage() {
		try {
			Stage stage = (Stage) buttonAccediUtente.getScene().getWindow();
			WindowsManager.closeWindow(stage);
			WindowsManager.openWindow("LoginUserPage.fxml", "Login", null);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
