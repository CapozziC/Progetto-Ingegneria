package org.openjfx.DietiEstates25;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.util.Duration;
import javafx.animation.Timeline;

public class LoginUserPageController {
	@FXML
	private Button buttonMinimizzaUser;
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
	private TextField textFieldPasswordVisible;
	@FXML
	private Button buttonClosedEye;
	@FXML
	private ImageView imgEye;
	@FXML
	private HBox hBoxUser;
	
	private boolean isPasswordVisible = false;
	


	@FXML
	public void initialize() {
		setFocusListener(textFieldEmailUser);
		setFocusListener(passwordFieldPasswordUser);	
		buttonRegistratiUser.setOnAction(event -> transitionToSignUp());
		buttonClosedEye.setOnAction(event -> showTextPassword());
		buttonInfoUser.setOnMouseClicked(this::showInfoPassword);
		buttonChiudiUser.setOnMouseClicked(event -> closeLoginPage());
		buttonMinimizzaUser.setOnMouseClicked(event ->minimizeLoginPage());
		buttonBackUser.setOnAction(event -> WindowsManager.loadWelcomeScene());
		buttonAccediUser.setOnAction(event -> WindowsManager.loadHomeScene());
		passwordFieldPasswordUser.textProperty().addListener((observable, oldValue, newValue) -> {
    	textFieldPasswordVisible.setText(newValue);
		});
        textFieldPasswordVisible.textProperty().addListener((observable, oldValue, newValue) -> {
        passwordFieldPasswordUser.setText(newValue);
        
        });
    }
	
	private void closeLoginPage() {
	        Stage stage = (Stage) buttonChiudiUser.getScene().getWindow();
	        stage.close();
	    }

	    private void minimizeLoginPage() {
	        Stage stage = (Stage) buttonMinimizzaUser.getScene().getWindow();
	        stage.setIconified(true);
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
	   
	
	  public void showInfoPassword(MouseEvent event) {
	    if (infoLabelUser != null) {
	        infoLabelUser.setVisible(!infoLabelUser.isVisible());
	    }
	


	}
	
	  private void showTextPassword() {
		
		if(isPasswordVisible == true) {
			textFieldPasswordVisible.setVisible(true);
			passwordFieldPasswordUser.setVisible(false);
			setButtonIcon("/org/openjfx/DietiEstates25/Icon/LoginUser/icons8-visibile-30.png");
			isPasswordVisible = false;
			
		}else {
	
		   textFieldPasswordVisible.setVisible(false);
		   passwordFieldPasswordUser.setVisible(true);
		   setButtonIcon("/org/openjfx/DietiEstates25/Icon/LoginUser/icons8-occhio-chiuso-30.png");
		   isPasswordVisible = true;
		   
		}
	}
  
	 private void setButtonIcon(String Imgpath) {
			Image image = new Image(getClass().getResource(Imgpath).toExternalForm());
			imgEye.setImage(image);	
	}
	 
	

	 private void transitionToSignUp() {
		    try {
		     
		        Parent root = FXMLLoader.load(getClass().getResource("/org/openjfx/DietiEstates25/SignUpUserPage.fxml"));
		        
		        Scene scene = buttonRegistratiUser.getScene();

		        
		        root.translateXProperty().set(scene.getWidth());

		        
		        borderPaneUser.getChildren().add(root);

		       
		        Timeline timeline = new Timeline();
		        KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
		        KeyFrame kf = new KeyFrame(Duration.seconds(0.2), kv);
		        timeline.getKeyFrames().add(kf);
		        timeline.setOnFinished(event -> {
		        borderPaneUser.getChildren().clear();
		        borderPaneUser.setCenter(root);
		            
		            
		            
		        });
		        timeline.play();

		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
   
	 
		
	
   }



	

	
