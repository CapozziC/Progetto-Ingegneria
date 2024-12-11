package org.openjfx.DietiEstates25;

import java.util.function.Consumer;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PopupManager {
	
	public static void showErrorPopup(String title, String message) {
        showPopup(title, message, "Error", null, true);
    }

    public static void showInfoPopup(String title, String message) {
        showPopup(title, message, "Information", null, false);
    }
	
	public static void showPopup(String title, String message, String type, Consumer<Boolean> okAction, boolean showCancelButton) {
        try {
            FXMLLoader loader = new FXMLLoader(PopupManager.class.getResource("EditablePopup.fxml"));
            Parent root = loader.load();

            EditablePopupController controller = loader.getController();
            controller.setTitle(title);
            controller.setMessage(message);
            controller.setImage(type);
            controller.setCancelButtonVisible(showCancelButton);

            if (okAction != null) {
                controller.setOkCallback(okAction);
            }

            Stage popupStage = new Stage();
            popupStage.setTitle(title);
            popupStage.setResizable(false);
            popupStage.initStyle(StageStyle.UNDECORATED);
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setScene(new Scene(root));
            popupStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
