package org.openjfx.DietiEstates25;

import java.util.function.Consumer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
	
	private Consumer<Boolean> okCallback;
	
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
