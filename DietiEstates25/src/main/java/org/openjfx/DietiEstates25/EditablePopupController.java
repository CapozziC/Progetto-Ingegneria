package org.openjfx.DietiEstates25;

import java.util.function.Consumer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class EditablePopupController {
	@FXML
	private Label labelPopup;
	@FXML
	private Text textPopup;
	@FXML
	private ImageView imageViewPopup;
	@FXML
	private Button buttonOkPopup, buttonAnnullaPopup;
	@FXML
	private Pane paneSuperiore, paneInferiore;
	@FXML
	private AnchorPane anchorPanePopup;
	
	private Consumer<Boolean> okCallback;
	
	public void setColorBorder(String type) {
		if (type.equals("Info")) {
			paneSuperiore.setStyle("-fx-background-color: #6756BE");
			paneInferiore.setStyle("-fx-background-color: #6756BE");
			anchorPanePopup.setStyle("-fx-background-color: white; -fx-border-color: #6756BE");
		}
		else if(type.equals("Error")) {
			paneSuperiore.setStyle("-fx-background-color: #E56B6F");
			paneInferiore.setStyle("-fx-background-color: #E56B6F");
			anchorPanePopup.setStyle("-fx-background-color: white; -fx-border-color: #E56B6F");
		}
	}
	
	public void setTitle(String title) {
		labelPopup.setText(title);
	}
	
	public void setMessage(String message) {
		textPopup.setText(message);
	}
	
	public void setCancelButtonVisible(boolean visible) {
		buttonAnnullaPopup.setVisible(visible);
	}
	
	public void setImage(String type) {
		if(type.equals("Error")) {
			imageViewPopup.setImage(new Image(getClass().getResource("Icon/Popup/icons8-errore-50.png").toExternalForm()));
		}
		else if(type.equals("Information")) {
			imageViewPopup.setImage(new Image(getClass().getResource("Icon/Popup/icons8-informazioni-50.png").toExternalForm()));
		}
	}
	
	public void setOkCallback(Consumer<Boolean> callback) {
        this.okCallback = callback;
    }
	
	@FXML
    private void handleOk() {
        if (okCallback != null) {
            okCallback.accept(true);
        }
        buttonOkPopup.getScene().getWindow().hide();
    }

    @FXML
    private void closePopup() {
        buttonOkPopup.getScene().getWindow().hide();
    }

}
