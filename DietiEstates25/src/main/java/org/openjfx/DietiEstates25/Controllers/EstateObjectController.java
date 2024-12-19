package org.openjfx.DietiEstates25.Controllers;

import org.openjfx.DietiEstates25.*;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EstateObjectController {
    
    @FXML
    private Button buttonVisita, buttonOffri, buttonVisualizza;
    @FXML
    private ImageView imageViewVisita, imageViewOffri;

    @FXML
    public void initialize() {
        setButtonImage(buttonVisita, imageViewVisita, createImageForButton("Icon/EstateObject/icons8-calendario-bianco-20.png"), createImageForButton("Icon/EstateObject/icons8-calendario-giallo-20.png"));
        setButtonImage(buttonOffri, imageViewOffri, createImageForButton("Icon/EstateObject/icons8-soldi-bianco-20.png"), createImageForButton("Icon/EstateObject/icons8-soldi-giallo-20.png"));
    }

    private Image createImageForButton(String imagepath) {
    	Image image = new Image(getClass().getResource(imagepath).toExternalForm());
    	return image;
    }

    private void setButtonImage(Button button, ImageView imageView, Image imageon, Image imageoff) {
        button.setOnMouseEntered(event -> {
        	imageView.setImage(imageon);
        });

        button.setOnMouseExited(event -> {
        	imageView.setImage(imageoff);

        });
    }
}
