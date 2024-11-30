package org.openjfx.DietiEstates25;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

import org.openjfx.DietiEstates25.WindowsManager;

public class LoginPageUsersController {
	@FXML
	private Button ButtonAccediUsers;
	@FXML
	private CheckBox ButtonRicordaUsers;
	@FXML
	private Button ButtonGoogleUsers;
	@FXML
	private ImageView Google;
	@FXML
	private Button ButtonFBUsers;
	@FXML
	private ImageView Fb;
	@FXML
	private Button ButtonGitUsers;
	@FXML
	private ImageView Git;
	@FXML
	private Button ButtonInfoUsers;
	@FXML
	private ImageView Back;
	@FXML
	private Button ButtunBackUsers;
	
	@FXML
	public void initialize() {
		ButtonAccediUsers.setOnAction(event -> openHomePage());
	}
	public void openHomePage() {
		try {
			Stage stage = (Stage) ButtonAccediUsers.getScene().getWindow();
			WindowsManager.closeWindow(stage);
			WindowsManager.openWindow("HomePage.fxml", "Home", null);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
