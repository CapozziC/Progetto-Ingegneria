package org.openjfx.DietiEstates25;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class EstateAgentPageController {
	@FXML
	private Button buttonChiudi, buttonIconizza;
	@FXML
	private VBox vBoxPrimoGiorno, vBoxSecondoGiorno, vBoxTerzoGiorno, vBoxQuartoGiorno, vBoxQuintoGiorno;
	@FXML
	private Button buttonAggiungiPrimoGiorno, buttonAggiungiSecondoGiorno, buttonAggiungiTerzoGiorno, buttonAggiungiQuartoGiorno, buttonAggiungiQuintoGiorno;
	@FXML
	private Label labelPrimoGiorno, labelSecondoGiorno, labelTerzoGiorno, labelQuartoGiorno, labelQuintoGiorno;
	@FXML
	private ScrollPane scrollPaneAppuntamenti;
	@FXML
	private VBox vBoxAppuntamenti;
	@FXML
	private Label labelNumeroAppuntamenti;
	
	@FXML
	public void initialize() {
		buttonIconizza.setOnAction(event -> WindowsManager.iconizeWindows());
		buttonChiudi.setOnAction(event -> WindowsManager.closeWindow());
		setDaysForAppointmentLabel();
		setActionForAppointmentButtons();
		loadAppointment();
	}
	
	private void setActionForAppointmentButtons() {
		buttonAggiungiPrimoGiorno.setOnAction(event -> createChoiceBoxTime(vBoxPrimoGiorno, buttonAggiungiPrimoGiorno));
		buttonAggiungiSecondoGiorno.setOnAction(event -> createChoiceBoxTime(vBoxSecondoGiorno, buttonAggiungiSecondoGiorno));
		buttonAggiungiTerzoGiorno.setOnAction(event -> createChoiceBoxTime(vBoxTerzoGiorno, buttonAggiungiTerzoGiorno));
		buttonAggiungiQuartoGiorno.setOnAction(event -> createChoiceBoxTime(vBoxQuartoGiorno, buttonAggiungiQuartoGiorno));
		buttonAggiungiQuintoGiorno.setOnAction(event -> createChoiceBoxTime(vBoxQuintoGiorno, buttonAggiungiQuintoGiorno));
	}
	
	private void createChoiceBoxTime(VBox vbox, Button buttonplus) {
		ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("9:00-10:30", "10:30-12:00", "12:00-13:30");
        comboBox.setPromptText("Scegli un orario");
        comboBox.setPrefWidth(100);
        Button okButton = new Button("OK");
        okButton.setDisable(true);
        okButton.setPrefWidth(100);
        okButton.setStyle("-fx-background-color: white; -fx-background-radius: 2; -fx-border-color: #6756BE; -fx-border-radius: 2; -fx-text-fill: #6756BE;");
        Button cancelbutton = new Button("Annulla");
        cancelbutton.setPrefWidth(100);
        cancelbutton.setStyle("-fx-background-color: white; -fx-background-radius: 2; -fx-border-color: #6756BE; -fx-border-radius: 2; -fx-text-fill: #6756BE;");
        VBox vboxtime = new VBox(comboBox, okButton, cancelbutton);
        vboxtime.setSpacing(2);
		vbox.getChildren().add(vboxtime);
		buttonplus.setDisable(true);
        comboBox.setOnAction(e -> okButton.setDisable(comboBox.getValue() == null));
        okButton.setOnAction(e -> createAppointment(vbox, comboBox.getValue(), vboxtime, buttonplus));
        cancelbutton.setOnAction(event -> {
        	vbox.getChildren().remove(vboxtime);
        	buttonplus.setDisable(false);
        });
	}
	
	private void createAppointment(VBox vbox, String time, VBox vboxtime, Button buttonplus) {
		if (vbox.getChildren().size()<7) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("AppointmentTimeStyle.fxml"));
			Pane root;
			try {
				root = loader.load();
				Label childLabel = (Label) root.getChildren().get(0);
				childLabel.setText(time);
				vbox.getChildren().addAll(root);
				Button buttonelimina = createButtonToEliminateAppointment();
				root.setOnMouseEntered(event -> root.getChildren().addAll(buttonelimina));
				buttonelimina.setLayoutY(75);
				buttonelimina.setOnAction(event -> vbox.getChildren().remove(root));
				root.setOnMouseExited(event -> root.getChildren().remove(buttonelimina));
				vbox.getChildren().remove(vboxtime);
				buttonplus.setDisable(false);
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
	}

	private Button createButtonToEliminateAppointment() {
		Button button = new Button();
		button.getStylesheets().add(getClass().getResource("CssFiles/ButtonDeleteStyle.css").toExternalForm());
		button.setPrefWidth(100);
		button.setText("Elimina");
		
		return button;
		
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
