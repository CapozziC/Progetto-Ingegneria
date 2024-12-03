package org.openjfx.DietiEstates25;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

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
	private Button buttonPannelloMobileSinistra;
	@FXML
	private Button buttonPannelloMobileDestra;
	@FXML
	private VBox vboxPannelloMobileSinistra;
	@FXML
	private VBox vboxPannelloMobileDestra;
	@FXML
	private VBox vboxSinistra;
	@FXML
	private VBox vboxDestra;
    @FXML
    private Button buttonIconizza;
    @FXML
    private Button buttonMassimizza;
    @FXML
    private Button buttonChiudi;
    
	
    private static final String ORIGINAL_COLOR = "#6756be";
    private static final String HOVER_COLOR = "#9593D9";
	
	
	@FXML
	public void initialize() {
		setButtonHoverEffect(buttonEntraAgenzia, HOVER_COLOR, ORIGINAL_COLOR);
		setButtonHoverEffect(buttonAccediUtente, HOVER_COLOR, ORIGINAL_COLOR);
		setButtonHoverEffect(buttonRegistratiUtente, HOVER_COLOR, ORIGINAL_COLOR);
        setButtonHoverEffect(buttonChiudi, "white", "#ff6767");
        setButtonHoverEffect(buttonIconizza, "white", "#a7ff97");
        setButtonHoverEffect(buttonMassimizza, "white", "#faff67");
		buttonAccediUtente.setOnAction(event -> openLoginUserPage());
		buttonPannelloMobileSinistra.setOnAction(event -> hideAndShowSidePanel(vboxPannelloMobileSinistra, vboxPannelloMobileDestra));
		buttonPannelloMobileDestra.setOnAction(event -> hideAndShowSidePanel(vboxPannelloMobileDestra, vboxPannelloMobileSinistra));
	}

    private void setButtonHoverEffect(Button button, String originalColor, String hoverColor) {
        button.setOnMouseEntered(event -> button.setStyle("-fx-background-color: " + hoverColor + "; -fx-text-fill: white;"));
        button.setOnMouseExited(event -> button.setStyle("-fx-background-color: " + originalColor + "; -fx-text-fill: white;"));
    }
	
	public void openLoginUserPage() {
		try {
			WindowsManager.loadLoginUserScene();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void hideAndShowSidePanel(VBox hidepanelmobile, VBox showpanelmobile) {
	    FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.1), hidepanelmobile);
	    fadeOut.setFromValue(1.0);
	    fadeOut.setToValue(0.0);
	    fadeOut.setOnFinished(event -> {
	        hidepanelmobile.setVisible(false);
	        showpanelmobile.setVisible(true);
	        showpanelmobile.setOpacity(0.0);
	        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.1), showpanelmobile);
	        fadeIn.setFromValue(0.0);
	        fadeIn.setToValue(1.0);
	        fadeIn.play();
	    });
	    fadeOut.play();
	}
	
    public void iconizedWindow() {
    	Stage stage = (Stage) buttonChiudi.getScene().getWindow();
    	stage.setIconified(true);
    }
    
    public void maximizedWindow() {
    	Stage stage = (Stage) buttonChiudi.getScene().getWindow();
    	if (!stage.isFullScreen()) {
			stage.setFullScreen(true);
    	}
    	else {
    		stage.setFullScreen(false);
    	}
    }
    
    public void closeWindow() {
    	Stage stage = (Stage) buttonChiudi.getScene().getWindow();
    	stage.close();
    }
}
