package org.openjfx.DietiEstates25;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class HomePageAppointmentController {
	
	@FXML
	private Label labelNumeroAppuntamenti;
	@FXML
	private ScrollPane scrollPaneAppuntamenti;
	@FXML
	private VBox vBoxAppuntamenti;
	
	@FXML
	public void initialize() {
		loadAppointment();
	}
	
	/*
     * Crea oggeti di tipo Appuntamento e li inserisce 
     * all'interno di una ScrollPane
     */
    private void loadAppointment() {
    	int numeroAppuntamenti = 0;
    	Pane root;
		try {
			for (int i = 0; i < 10; i++) {
				if(i%2==0) {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("AppointmentObject.fxml"));
					root = loader.load();
				}
				else {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("AppointmentObject-Alternative.fxml"));
					root = loader.load();
				}
				vBoxAppuntamenti.getChildren().add(root);
				numeroAppuntamenti++;
				labelNumeroAppuntamenti.setText(String.valueOf(numeroAppuntamenti)+" Appuntamenti");
			}
			scrollPaneAppuntamenti.setContent(vBoxAppuntamenti);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

}
