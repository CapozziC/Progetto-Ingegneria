package org.openjfx.DietiEstates25;

import java.util.function.Consumer;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class FilterWindowController {

	@FXML
	private ComboBox<String> comboBoxPrezzoDa;
	@FXML
	private ComboBox<String> comboBoxPrezzoA;
	@FXML
	private ComboBox<String> comboBoxSuperficieDa;
	@FXML
	private ComboBox<String> comboBoxSuperficieA;
	@FXML
	private ComboBox<String> comboBoxLocaliDa;
	@FXML
	private ComboBox<String> comboBoxLocaliA;
	@FXML
	private ComboBox<String> comboBoxBagniDa;
	@FXML
	private ComboBox<String> comboBoxBagniA;
	@FXML
	private ComboBox<String> comboBoxTipologia;
	@FXML
	private Button buttonApplica;
	
	private Consumer<Boolean> applyCallback;
	
	@FXML
	public void initialize() {
		buttonApplica.setOnAction(event -> handleOk());
		populateCombobox();
	}
	
	public void setApplyCallback(Consumer<Boolean> callback) {
        this.applyCallback = callback;
    }
	
	@FXML
    private void handleOk() {
        if (applyCallback != null) {
            applyCallback.accept(true);
        }
        buttonApplica.getScene().getWindow().hide();
    }

    @FXML
    private void closePopup() {
        buttonApplica.getScene().getWindow().hide();
    }
    
    private void populateCombobox() {
    	addItemsToPriceCombobox();
    	addItemsToSurfaceCombobox();
    	addItemsToRoomCombobox();
    	addItemsToBathroomCombobox();
    	addItemsToTypeCombobox();
    }
    
    private void addItemsToPriceCombobox() {
    	String pricelist[] = {"50000", "100000", "150000", "200000"};
    	comboBoxPrezzoDa.getItems().addAll(pricelist);
    	comboBoxPrezzoA.getItems().addAll(pricelist);
    }
    
    private void addItemsToSurfaceCombobox() {
    	String surfacelist[] = {"50", "100", "150", "200"};
    	comboBoxSuperficieDa.getItems().addAll(surfacelist);
    	comboBoxSuperficieA.getItems().addAll(surfacelist);
    }
    
    private void addItemsToRoomCombobox() {
    	String roomlist[] = {"1", "2", "3", "4", "5+"};
    	comboBoxLocaliDa.getItems().addAll(roomlist);
    	comboBoxLocaliA.getItems().addAll(roomlist);
    }
    
    private void addItemsToBathroomCombobox() {
    	String bathroomlist[] = {"1", "2", "3", "4", "5+"};
    	comboBoxBagniDa.getItems().addAll(bathroomlist);
    	comboBoxBagniA.getItems().addAll(bathroomlist);
    }
    
    private void addItemsToTypeCombobox() {
    	String typelist[] = {"In Vendita", "In Affitto"};
    	comboBoxTipologia.getItems().addAll(typelist);
    }

}
