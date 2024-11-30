package org.openjfx.DietiEstates25;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class WindowsManager {
	
	public static void openWindow(String fxmlPath, String title, Modality modality) throws IOException {
		FXMLLoader loader = new FXMLLoader(WindowsManager.class.getResource(fxmlPath));
	    Parent root = loader.load();
	    
	    Stage stage = new Stage();
	    stage.setTitle(title);
	    stage.setScene(new Scene(root));
        stage.initStyle(StageStyle.DECORATED);
        stage.setWidth(Screen.getPrimary().getBounds().getWidth());
        stage.setHeight(Screen.getPrimary().getBounds().getHeight());
        stage.setResizable(true);
        stage.centerOnScreen();
        stage.setMinWidth(1280);
        stage.setMinHeight(720);
	    if (modality != null) {
	    	stage.initModality(modality);
	    	}
	    stage.show();
	    }
	
	public static void closeWindow(Stage stage) {
        stage.close();
    }

}
