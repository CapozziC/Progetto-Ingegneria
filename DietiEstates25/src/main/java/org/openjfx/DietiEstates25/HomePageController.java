package org.openjfx.DietiEstates25;

import org.openjfx.DietiEstates25.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class HomePageController {
	private App main;
	@FXML
    private Button buttonHome;
	@FXML
	private Button buttonProfilo;
	@FXML
	private Button buttonOfferte;
	@FXML
	private Button buttonLogout;
	@FXML
	private Button buttonNascondi;
	@FXML
	private Pane PaneLaterale;
	@FXML
	private VBox VBoxLaterale;
	
	public void setMain(App main) {
		this.main=main;
	}
	
	@FXML
	public void initialize() {
		setButtonHoverEffect(buttonHome, "#4a3470", "#9593D9");
		setButtonHoverEffect(buttonProfilo, "#4a3470", "#9593D9");
		setButtonHoverEffect(buttonOfferte, "#4a3470", "#9593D9");
		setButtonHoverEffect(buttonLogout, "#4a3470", "#9593D9");
		setButtonHoverEffect(buttonNascondi, "#4a3470", "#9593D9");
		buttonNascondi.setOnAction(event -> handleButtonClick());
	}

    private void setButtonHoverEffect(Button button, String originalColor, String hoverColor) {
        button.setOnMouseEntered(event -> {
            button.setStyle("-fx-background-color: "+ hoverColor +"; -fx-text-fill: white;");
        });

        button.setOnMouseExited(event -> {
            button.setStyle("-fx-background-color: "+ originalColor +"; -fx-text-fill: white;");
        });
    }
    
    private void handleButtonClick() {
		setTextAndWidthOfSideButton(buttonHome, "Home");
		setTextAndWidthOfSideButton(buttonProfilo, "Profilo");
		setTextAndWidthOfSideButton(buttonOfferte, "Offerte");
		setTextAndWidthOfSideButton(buttonLogout, "Logout");
		setTextAndWidthOfSideButton(buttonNascondi, "Nascondi pannello");
			
		setWidthOfPaneLaterale(VBoxLaterale, PaneLaterale);
    }

	private void setTextAndWidthOfSideButton(Button button, String filltext) {
		if (button.getText().equals("")) {
			button.setText(filltext);
			button.setPrefWidth(170);
		}
		else {
			button.setText("");
			button.setPrefWidth(30);
		}
	}
	
	private void setWidthOfPaneLaterale(VBox vbox, Pane pane) {
		if (vbox.getWidth()==70) {
			vbox.setPrefWidth(200);
			pane.setPrefWidth(200);
		}
		else {
			vbox.setPrefWidth(70);
			pane.setPrefWidth(70);
		}
	}
}
