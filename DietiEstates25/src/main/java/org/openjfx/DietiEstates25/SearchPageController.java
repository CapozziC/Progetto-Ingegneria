package org.openjfx.DietiEstates25;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class SearchPageController {
	@FXML
	private Button buttonChiudi, buttonIconizza;
	@FXML
	private Button buttonCercaIcona;
	@FXML
	private Button buttonEliminaTesto;
	@FXML
	private Button buttonCerca;
	@FXML
	private TextField textFieldCerca;
	@FXML
	private VBox vBoxCerca;
	
	@FXML
	public void initialize() {
		buttonIconizza.setOnAction(event -> WindowsManager.iconizeWindows());
		buttonChiudi.setOnAction(event -> WindowsManager.closeWindow());
		buttonEliminaTesto.setOnAction(event -> textFieldCerca.setText(""));
		buttonCerca.setOnAction(event -> WindowsManager.loadHomeScene());
		buttonCercaIcona.setOnAction(event -> WindowsManager.loadHomeScene());
		showAndHideButtonCleanSearchBar();
		enableOrDisableButtonSearch();
		setShadowForComponent();
	}
	
	private void showAndHideButtonCleanSearchBar() {
    	textFieldCerca.textProperty().addListener((observable, oldValue, newValue) -> {
            if(textFieldCerca.getText().length()!=0) {
            	buttonEliminaTesto.setVisible(true);
            }
            else {
            	buttonEliminaTesto.setVisible(false);
            }
        });
    }
	
	private void enableOrDisableButtonSearch() {
    	buttonCerca.disableProperty().bind(
                Bindings.createBooleanBinding(() -> 
                	textFieldCerca.getText().isEmpty(),
                    textFieldCerca.textProperty()
                )
        );
    	
    	buttonCercaIcona.disableProperty().bind(
                Bindings.createBooleanBinding(() -> 
                	textFieldCerca.getText().isEmpty(),
                    textFieldCerca.textProperty()
                )
        );
    }
	
	private void setShadowForComponent() {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(25);
        dropShadow.setColor(Color.color(0.403921568627451, 0.33725490196078434, 0.7450980392156863));
        vBoxCerca.setEffect(dropShadow);
        vBoxCerca.setEffect(dropShadow);
    }

}
