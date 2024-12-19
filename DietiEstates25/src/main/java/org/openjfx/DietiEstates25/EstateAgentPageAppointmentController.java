package org.openjfx.DietiEstates25;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class EstateAgentPageAppointmentController {
	@FXML
	private ScrollPane scrollPaneAppuntamenti;
	@FXML
	private VBox vBoxAppuntamenti;
	@FXML
	private Label labelNumeroAppuntamenti;
	@FXML
	private Label labelPrimoGiorno;
	@FXML
	private Label labelSecondoGiorno;
	@FXML
	private Label labelTerzoGiorno;
	@FXML
	private Label labelQuartoGiorno;
	@FXML
	private Label labelQuintoGiorno;
	
	@FXML
	public void initialize() {
		setDaysForAppointmentLabel();
		loadAppointment();
	}
	
	private void setDaysForAppointmentLabel() {
		LocalDate today = LocalDate.now();
	    LocalDate nextWeekStart = today.plusWeeks(1).with(DayOfWeek.MONDAY);
	    
	    List<Label> labellist = List.of(labelPrimoGiorno, labelSecondoGiorno, labelTerzoGiorno, labelQuartoGiorno, labelQuintoGiorno);
	    
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM");
	    
	    for (int i = 0; i < 5; i++) {
	        LocalDate dayOfWeek = nextWeekStart.plusDays(i);
	        labellist.get(i).setText(dayOfWeek.format(formatter));
	    }
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
