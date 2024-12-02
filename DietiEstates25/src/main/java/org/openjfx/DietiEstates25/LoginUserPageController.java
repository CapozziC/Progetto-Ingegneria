package org.openjfx.DietiEstates25;

import javafx.fxml.FXML;
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.control.CheckBox;
import org.openjfx.DietiEstates25.WindowsManager;

public class LoginUserPageController {
	@FXML
	private Button buttonMinimizzaUser;
    @FXML
	private ImageView buttonMassimizzaUser;
	@FXML
	private ImageView buttonChiudiUser;
	@FXML
	private CheckBox buttonRicordamiUser;
	@FXML
	private Button buttonRicordaPasswordUser;
	@FXML
	private Button buttonGoogleUser;
	@FXML
	private Button buttonFvUser;
	@FXML
	private Button buttonGitUser;
	@FXML
	private Button buttonBackUser;
	@FXML
	private Button buttonRegistratiUser;
	@FXML
	private Button buttonAccediUser;
	
	@FXML
	public void initialize() {
	 buttonBackUser.setOnAction(event -> {
		try {
			WindowsManager.loadWelcomeScene();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	});
	 buttonAccediUser.setOnAction(event->{
		try {
			WindowsManager.loadHomeScene();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	});
	 
	}
	
	
	

}
