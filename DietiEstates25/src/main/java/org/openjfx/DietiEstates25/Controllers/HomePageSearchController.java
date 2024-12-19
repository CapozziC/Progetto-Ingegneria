package org.openjfx.DietiEstates25.Controllers;

import org.openjfx.DietiEstates25.*;

import java.io.IOException;
import java.util.function.Consumer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

public class HomePageSearchController {
	@FXML
	private ScrollPane scrollPaneImmobili;
	@FXML
	private VBox vBoxImmobili;
	@FXML
	private Label labelNumeroImmobili;
	@FXML
	private TextField textFieldRicerca;
	@FXML
	private ChoiceBox<String> choiceBoxOrdine;
	@FXML
	private Button buttonFiltri;
	@FXML
	private Button buttonPulisciRicerca;
	
	private Consumer<Boolean> applyaction;
	
	@FXML
    public void initialize() {
		buttonFiltri.setOnAction(e -> showFilterWindow(applyaction));
		cleanSearchBar();
		showAndHideButtonCleanSearchBar();
		fillChoiceBoxOrder();
		changeArrowChoiceBoxOrientation();
		loadEstates();
    }
	
	private void showAndHideButtonCleanSearchBar() {
    	textFieldRicerca.textProperty().addListener((observable, oldValue, newValue) -> {
            if(textFieldRicerca.getText().length()!=0) {
            	buttonPulisciRicerca.setVisible(true);
            }
            else {
            	buttonPulisciRicerca.setVisible(false);
            }
        });
    }
    
    private void cleanSearchBar() {
    	textFieldRicerca.setText("");
    }
    
    private void fillChoiceBoxOrder() {
    	choiceBoxOrdine.getItems().addAll("Più caro", "Meno caro", "Più vicino", "Meno vicino", "Più grande", "Meno grande", "Più locali", "Meno locali");
    	choiceBoxOrdine.setValue("Più caro");
    }
    
    private void changeArrowChoiceBoxOrientation() {
    	choiceBoxOrdine.setOnShowing(event -> {
            choiceBoxOrdine.lookup(".arrow").setStyle("-fx-rotate: 180;");
        });

        choiceBoxOrdine.setOnHiding(event -> {
            choiceBoxOrdine.lookup(".arrow").setStyle("-fx-rotate: 0;");
        });
    }
    
    public static void showFilterWindow(Consumer<Boolean> applyAction) {
        try {
            FXMLLoader loader = new FXMLLoader(WindowsManager.class.getResource("FXMLFiles/HomePage/FilterWindow.fxml"));
            Parent root = loader.load();
            
            FilterWindowController controller = loader.getController();
            
            if (applyAction != null) {
                controller.setApplyCallback(applyAction);
            }
            
            Stage popupStage = new Stage();
            popupStage.setResizable(false);
            popupStage.initStyle(StageStyle.UNDECORATED);
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setScene(new Scene(root));
            popupStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /*
     * Crea oggeti di tipo Immobile e li inserisce 
     * all'interno di una ScrollPane
     */
    private void loadEstates() {
    	int numeroImmobili = 0;
		try {
			for (int i = 0; i < 5; i++) {
				FXMLLoader loader = new FXMLLoader(WindowsManager.class.getResource("FXMLFiles/Objects/EstateObject.fxml"));
				Pane root = loader.load();
				vBoxImmobili.getChildren().add(root);
				numeroImmobili++;
				labelNumeroImmobili.setText(String.valueOf(numeroImmobili)+" Immobili");
			}
			scrollPaneImmobili.setContent(vBoxImmobili);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

}
