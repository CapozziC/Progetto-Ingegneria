package org.openjfx.DietiEstates25;

import java.io.File;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class EstateAgentPageEstateController {
	@FXML
	private Button buttonAggiungiFoto;
	@FXML
	private Button buttonPrima, buttonSeconda, buttonTerza, buttonQuarta, buttonQuinta, buttonSesta, buttonSettima, buttonOttava, buttonNona;
	@FXML
	private ImageView imageViewPrima, imageViewSeconda, imageViewTerza, imageViewQuarta, imageViewQuinta, imageViewSesta, imageViewSettima, imageViewOttava, imageViewNona;
	@FXML
	private TextField textFieldPrezzo, textFieldLocali, textFieldSuperficie, textFieldPiano, textFieldVia, textFieldCittà, textFieldProvincia, textFieldCAP;
	@FXML
	private ComboBox<String> comboBoxClasseEnergetica, comboBoxTipologia;
	@FXML
	private CheckBox checkBoxGarage, checkBoxBalconi, checkBoxAscensore, checkBoxPostoAuto, checkBoxPannelliFotovoltaici;
	@FXML
	private CheckBox checkBoxMobili, checkBoxClimatizzazione, checkBoxRiscaldamento, checkBoxPortineria, checkBoxGiardino;
	@FXML
	private Label labelNumeroCaratteri;
	@FXML
	private TextArea textAreaDescrizione;
	@FXML
	private Label labelPrezzoRiassunto, labelLocaliRiassunto, labelSuperficieRiassunto, labelPianoRiassunto, labelClasseEnergeticaRiassunto, labelTipologiaRiassunto, labelIndirizzoRiassunto;
	@FXML
	private VBox vBoxInformazioniAggiuntive;
	@FXML
	private Button buttonPubblicaAnnuncio;
	
	private Map<CheckBox, Label> checkBoxToLabelMap = new HashMap<>();
	
	@FXML
	private void initialize() {
		buttonAggiungiFoto.setOnAction(event -> openFileChooserForImages());
		buttonPubblicaAnnuncio.setOnAction(event -> validateFieldsForPublication());
		addItemsToEneryClassCombobox();
		addItemsToTypeCombobox();
		addListenerToTextField();
		addListenerToCheckBox();
		addListenerToButtons();
		addListenerToTextArea();
	}

	private void addItemsToEneryClassCombobox() {
    	String eneryclasslist[] = {"A4", "A3", "A2", "A1", "B", "C"};
    	comboBoxClasseEnergetica.getItems().addAll(eneryclasslist);
    }
	
	private void addItemsToTypeCombobox() {
    	String typelist[] = {"In Vendita", "In Affitto"};
    	comboBoxTipologia.getItems().addAll(typelist);
    }
	
	/*
	 * Permette all'agente immobiliare di scegliere un massimo di 9 foto
	 * da caricare per l'annuncio
	 */
	private void openFileChooserForImages() {
	    FileChooser filechooser = new FileChooser();
	    filechooser.setTitle("Seleziona uno o più file");
	    filechooser.setInitialDirectory(new File(System.getProperty("user.home")));
	    filechooser.getExtensionFilters().addAll(
	        new FileChooser.ExtensionFilter("All images", "*.jpeg", "*.jpg", "*.png"),
	        new FileChooser.ExtensionFilter("JPEG images", "*.jpeg"),
	        new FileChooser.ExtensionFilter("JPG images", "*.jpg"),
	        new FileChooser.ExtensionFilter("PNG images", "*.png")
	    );

	    List<File> selectedFiles = filechooser.showOpenMultipleDialog(WindowsManager.getPrimaryStage());

	    if (selectedFiles != null && !selectedFiles.isEmpty()) {
	        List<ImageView> imagelist = List.of(
	            imageViewPrima, imageViewSeconda, imageViewTerza, imageViewQuarta,
	            imageViewQuinta, imageViewSesta, imageViewSettima, imageViewOttava, imageViewNona
	        );

	        int imageIndex = 0;

	        for (File file : selectedFiles) {
	            while (imageIndex < imagelist.size() && imagelist.get(imageIndex).getImage() != null) {
	                imageIndex++;
	            }

	            if (imageIndex < imagelist.size()) {
	                imagelist.get(imageIndex).setImage(new Image(file.toURI().toString()));
	                imagelist.get(imageIndex).getParent().setDisable(false);
	            } else {
	                PopupManager.showInfoPopup("Impossibile aggiungere tutte le immagini", "Si possono aggiungere al massimo 9 immagini");
	                break;
	            }
	        }
	    }
	}
	
	private void addListenerToButtons() {
		List<Button> buttonslist;
		buttonslist = List.of(buttonPrima, buttonSeconda, buttonTerza, buttonQuarta, buttonQuinta,
				buttonSesta, buttonSettima, buttonSettima, buttonOttava, buttonNona);
		for (Button button : buttonslist) {
			addDeleteImageButton(button);
		}
	}
	
	private void addDeleteImageButton(Button button) {
		ImageView imageview = (ImageView) button.getGraphic();
		button.setOnMouseEntered(event -> {
			imageview.setOpacity(0.5);
		});
		
		button.setOnMouseExited(event -> {
			imageview.setOpacity(1);
		});
		
		button.setOnAction(event -> {
			button.setDisable(true);
			imageview.setImage(null);
		});
	}
	
	private void addListenerToTextField() {
		textFieldPrezzo.textProperty().addListener((observable, oldvalue, newvalue) -> {
			if(!newvalue.matches("\\d*") || newvalue.length()>12) {
				textFieldPrezzo.setText(oldvalue);
			}
			else {
				if(!newvalue.isEmpty()) {
					NumberFormat formatter = NumberFormat.getInstance(Locale.ITALIAN);
		            String formattedValue = formatter.format(Long.parseLong(newvalue));
		            textFieldPrezzo.positionCaret(formattedValue.length());
					labelPrezzoRiassunto.setText("Prezzo: "+formattedValue+" €");
				}
				else {
					labelPrezzoRiassunto.setText("Prezzo: €");
				}
			}
		});
		textFieldLocali.textProperty().addListener((observable, oldvalue, newvalue) -> {
			if(!newvalue.matches("\\d*") || newvalue.length()>2) {
				textFieldLocali.setText(oldvalue);
			}
			else {
				labelLocaliRiassunto.setText("Locali: "+newvalue);
			}
		});
		textFieldSuperficie.textProperty().addListener((observable, oldvalue, newvalue) -> {
			if(!newvalue.matches("\\d*") || newvalue.length()>5) {
				textFieldSuperficie.setText(oldvalue);
			}
			else {
				labelSuperficieRiassunto.setText("Superficie: "+newvalue+" m²");
			}
		});
		textFieldPiano.textProperty().addListener((observable, oldvalue, newvalue) -> {
			if(!newvalue.matches("\\d*") || newvalue.length()>3) {
				textFieldPiano.setText(oldvalue);
			}
			else {
				labelPianoRiassunto.setText("Piano: "+newvalue);
			}
		});
		textFieldVia.textProperty().addListener((observable, oldvalue, newvalue) -> {
			labelIndirizzoRiassunto.setText("Indirizzo: "+newvalue+", "+textFieldCittà.getText()+", "+textFieldProvincia.getText()+", "+textFieldCAP.getText());
		});
		textFieldCittà.textProperty().addListener((observable, oldvalue, newvalue) -> {
			labelIndirizzoRiassunto.setText("Indirizzo: "+textFieldVia.getText()+", "+newvalue+", "+textFieldProvincia.getText()+", "+textFieldCAP.getText());
		});
		textFieldProvincia.textProperty().addListener((observable, oldvalue, newvalue) -> {
			labelIndirizzoRiassunto.setText("Indirizzo: "+textFieldVia.getText()+", "+textFieldCittà.getText()+", "+newvalue+", "+textFieldCAP.getText());
		});
		textFieldCAP.textProperty().addListener((observable, oldvalue, newvalue) -> {
			labelIndirizzoRiassunto.setText("Indirizzo: "+textFieldVia.getText()+", "+textFieldCittà.getText()+", "+textFieldProvincia.getText()+", "+newvalue);
		});
		comboBoxClasseEnergetica.valueProperty().addListener((observable, oldvalue, newvalue) -> {
			labelClasseEnergeticaRiassunto.setText("Classe Energertica: "+newvalue);
		});
		comboBoxTipologia.valueProperty().addListener((observable, oldvalue, newvalue) -> {
			labelTipologiaRiassunto.setText("Tipologia: "+newvalue);
		});
	}
	
	private void addListenerToCheckBox() {
		List<CheckBox> checkboxeslist;
		checkboxeslist = List.of(checkBoxGarage, checkBoxBalconi, checkBoxAscensore, checkBoxPostoAuto, checkBoxPannelliFotovoltaici,
				checkBoxMobili, checkBoxClimatizzazione, checkBoxRiscaldamento, checkBoxPortineria, checkBoxGiardino);
		for (CheckBox checkbox : checkboxeslist) {
			createLabelFromCheckBox(checkbox);
		}
	}
	
	private void createLabelFromCheckBox(CheckBox checkbox) {
		checkbox.selectedProperty().addListener((observable, oldValue, newValue) -> {
		    if (newValue) {
		    	Label label = new Label();
		    	label.getStylesheets().add(getClass().getResource("CssFiles/LabelStyle.css").toExternalForm());
		    	label.getStyleClass().add("label-regular");
		    	label.setStyle("-fx-font-size: 17px;");
		        label.setText("-"+checkbox.getText());
		        vBoxInformazioniAggiuntive.getChildren().add(label);
		        checkBoxToLabelMap.put(checkbox, label);
		    } else {
		    	Label labelToRemove = checkBoxToLabelMap.get(checkbox);
	            if (labelToRemove != null) {
	                vBoxInformazioniAggiuntive.getChildren().remove(labelToRemove);
	                checkBoxToLabelMap.remove(checkbox);
	            }
		    }
		});
	}
	
	private void addListenerToTextArea() {
		textAreaDescrizione.textProperty().addListener((observable, oldvalue, newvalue) -> {
			labelNumeroCaratteri.setText("Caratteri: "+textAreaDescrizione.getText().length()+"/500");
			if(textAreaDescrizione.getText().length()>500) {
				PopupManager.showPopup("Superato limite consentito di caratteri", "Non puoi inserire più caratteri", "Error", null, false);
				textAreaDescrizione.setText(oldvalue);
			}
		});
	}
	
	private void setFieldBorderColorIfEmpty(TextField textField) {
	    if (textField.getText().isBlank()) {
	        textField.setStyle("-fx-border-color: #E56B6F; -fx-border-width: 2;");
	    } else {
	        textField.setStyle("");
	    }
	}

	private void setComboBoxBorderColorIfEmpty(ComboBox<String> comboBox) {
	    if (comboBox.getValue() == null) {
	        comboBox.setStyle("-fx-border-color: #E56B6F; -fx-border-width: 2;");
	    } else {
	        comboBox.setStyle("");
	    }
	}
	
	private void validateFieldsForPublication() {
		String prezzo = textFieldPrezzo.getText();
	    String locali = textFieldLocali.getText();
	    String superficie = textFieldSuperficie.getText();
	    String piano = textFieldPiano.getText();
	    String classeEnergetica = comboBoxClasseEnergetica.getValue();
	    String tipologia = comboBoxTipologia.getValue();
	    String via = textFieldVia.getText();
	    String città = textFieldCittà.getText();
	    String provincia = textFieldProvincia.getText();
	    String cap = textFieldCAP.getText();
	    
	    boolean almenounafoto = List.of(
	        imageViewPrima, imageViewSeconda, imageViewTerza, imageViewQuarta,
	        imageViewQuinta, imageViewSesta, imageViewSettima, imageViewOttava, imageViewNona
	    ).stream().anyMatch(imageView -> imageView.getImage() != null);
	    
	    setFieldBorderColorIfEmpty(textFieldPrezzo);
	    setFieldBorderColorIfEmpty(textFieldLocali);
	    setFieldBorderColorIfEmpty(textFieldSuperficie);
	    setFieldBorderColorIfEmpty(textFieldPiano);
	    setFieldBorderColorIfEmpty(textFieldVia);
	    setFieldBorderColorIfEmpty(textFieldCittà);
	    setFieldBorderColorIfEmpty(textFieldProvincia);
	    setFieldBorderColorIfEmpty(textFieldCAP);
	    setComboBoxBorderColorIfEmpty(comboBoxClasseEnergetica);
	    setComboBoxBorderColorIfEmpty(comboBoxTipologia);
	    
	    if (prezzo.isBlank() || locali.isBlank() || superficie.isBlank() || piano.isBlank() ||
	        classeEnergetica == null || tipologia == null || via.isBlank() ||
	        città.isBlank() || provincia.isBlank() || cap.isBlank() || !almenounafoto) {
	        PopupManager.showPopup("Impossibile pubblicare l'annuncio", "Compila tutti i campi obbligatori e carica almeno una foto", "Error", null, false);
	    }
	    else {
	    	PopupManager.showInfoPopup("Annuncio pubblicato", "Il tuo annuncio è stato pubblicato correttamente");
	    }
	}
}
