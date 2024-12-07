package org.openjfx.DietiEstates25;

import javafx.application.Platform;
import javafx.fxml.FXML;
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import org.openjfx.DietiEstates25.WindowsManager;




public class LoginUserPageController {
	@FXML
	private Button buttonMinimizzaUser;
    @FXML
	private Button buttonMassimizzaUser;
	@FXML
	private Button buttonChiudiUser;
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
	private TextField textFieldEmailUser;
	@FXML
	private PasswordField passwordFieldPasswordUser;
	@FXML
	private Button buttonInfoUser;
	@FXML
	private Label infoLabelUser;
	@FXML
	private BorderPane borderPaneUser;
	

	
	
	
	@FXML
	public void initialize() {
		
	buttonChiudiUser.setOnMouseClicked(event -> {
    Stage stage = (Stage) buttonChiudiUser.getScene().getWindow();
    stage.close();
 });

    buttonMinimizzaUser.setOnMouseClicked(event -> {
    Stage stage = (Stage) buttonMinimizzaUser.getScene().getWindow();
    stage.setIconified(true);
 });
		        
		    

    
	 buttonBackUser.setOnAction(event -> WindowsManager.loadWelcomeScene());
		        
	 
	 buttonAccediUser.setOnAction(event-> WindowsManager.loadHomeScene());
		        
}
		 
private void setFocusListener(TextField textField) {
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
	   
	
	public void ShowInfoPassword(MouseEvent event) {
	    if (infoLabelUser != null) {
	        infoLabelUser.setVisible(!infoLabelUser.isVisible());
	    }
	


	}
	}

	

	
