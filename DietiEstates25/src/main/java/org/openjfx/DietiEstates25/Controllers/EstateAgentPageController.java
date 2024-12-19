package org.openjfx.DietiEstates25.Controllers;

import org.openjfx.DietiEstates25.*;

import java.io.IOException;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class EstateAgentPageController {
	
	// TitleBar
	@FXML
	private Button buttonChiudi, buttonIconizza;
	@FXML
	private ImageView imageIconizza, imageChiudi;
	
	// Side Panel
	@FXML
	private Button buttonDashboard, buttonProfilo, buttonImmobili, buttonAppuntamenti, buttonLogout;
	@FXML
	private Pane paneSegnalino;
	
	// MainPage
	@FXML
	private StackPane stackPaneMain;
	
	private final String DASHBOARD_PAGE = "FXMLFiles/EstateAgentPage/EstateAgentPage-Dashboard.fxml";
 	private final String PROFILE_PAGE = "FXMLFiles/EstateAgentPage/EstateAgentPage-Profile.fxml";
 	private final String ESTATE_PAGE = "FXMLFiles/EstateAgentPage/EstateAgentPage-Estate.fxml";
 	private final String APPOINTMENT_PAGE = "FXMLFiles/EstateAgentPage/EstateAgentPage-Appointment.fxml";
	
	@FXML
	public void initialize() {
		buttonIconizza.setOnAction(event -> WindowsManager.iconizeWindows());
		buttonChiudi.setOnAction(event -> WindowsManager.closeWindow());
		buttonDashboard.setOnAction(event -> {
			setIndicatorPositionOfSidePanel(buttonDashboard);
			loadScreen(DASHBOARD_PAGE);
		});
		buttonProfilo.setOnAction(event -> {
			setIndicatorPositionOfSidePanel(buttonProfilo);
			loadScreen(PROFILE_PAGE);
		});
		buttonImmobili.setOnAction(event -> {
			setIndicatorPositionOfSidePanel(buttonImmobili);
			loadScreen(ESTATE_PAGE);
		});
		buttonAppuntamenti.setOnAction(event -> {
			setIndicatorPositionOfSidePanel(buttonAppuntamenti);
			loadScreen(APPOINTMENT_PAGE);
		});
		setButtonCloseImage();
    	setButtonIconizeImage();
	}
	
	private void setIndicatorPositionOfSidePanel(Button button) {
    	paneSegnalino.setVisible(true);
    	TranslateTransition transition = new TranslateTransition(Duration.millis(200), paneSegnalino);
        transition.setToY(button.getLayoutY()-20);
        transition.play();
    }
	
	private void setButtonCloseImage() {
    	buttonChiudi.setOnMouseEntered(event -> {imageChiudi.setImage(new Image(getClass().getResource("Icon/TitleBar/icons8-eliminare-bianca-30.png").toExternalForm()));
    			});
    	buttonChiudi.setOnMouseExited(event -> {imageChiudi.setImage(new Image(getClass().getResource("Icon/TitleBar/icons8-eliminare-30.png").toExternalForm()));
		});
    }
    
    private void setButtonIconizeImage() {
    	buttonIconizza.setOnMouseEntered(event -> {imageIconizza.setImage(new Image(getClass().getResource("Icon/TitleBar/icons8-linea-orizzontale-bianca-30.png").toExternalForm()));
    			});
    	buttonIconizza.setOnMouseExited(event -> {imageIconizza.setImage(new Image(getClass().getResource("Icon/TitleBar/icons8-linea-orizzontale-30.png").toExternalForm()));
		});
    }
	
	private void loadScreen(String fxmlFile) {
	    try {
	        FXMLLoader loader = new FXMLLoader(WindowsManager.class.getResource(fxmlFile));
	        stackPaneMain.getChildren().clear();
	        stackPaneMain.getChildren().add(loader.load());
	    } catch (IOException e) {
	    	e.printStackTrace();
	        PopupManager.showPopup("Impossibile visualizzare la schermata", "Per qualche motivo il caricamento della schermata non è andato a buon fine", "Error", null, false);
	    }
	}
	
	private void loadScreenWithProgressBar(String fxmlFile) {
	    ProgressBar progressBar = new ProgressBar();
	    progressBar.setPrefWidth(200);
	    progressBar.setProgress(-1);
	    
	    stackPaneMain.getChildren().clear();
	    stackPaneMain.getChildren().add(progressBar);

	    new Thread(() -> {
	        try {
	            FXMLLoader loader = new FXMLLoader(WindowsManager.class.getResource(fxmlFile));
	            Pane newContent = loader.load();

	            Platform.runLater(() -> {
	                stackPaneMain.getChildren().clear();
	                stackPaneMain.getChildren().add(newContent);
	            });
	        } catch (IOException e) {
	            e.printStackTrace();
	            Platform.runLater(() -> {
	                stackPaneMain.getChildren().clear();
	                e.printStackTrace();
	                PopupManager.showPopup("Impossibile visualizzare la schermata", "Per qualche motivo il caricamento della schermata non è andato a buon fine", "Error", null, false);
	            });
	        }
	    }).start();
	}

}
