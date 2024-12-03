package org.openjfx.DietiEstates25;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.openjfx.DietiEstates25.WindowsManager;

import java.util.List;

public class HomePageController {
	
    @FXML
    private Button buttonHome, buttonProfilo, buttonOfferte, buttonAppuntamenti, buttonLogout, buttonNascondi;
    @FXML
    private Pane PaneLaterale;
    @FXML
    private VBox VBoxLaterale;
    @FXML
    private ImageView imageNascondi;
    @FXML
    private Button buttonIconizza;
    @FXML
    private Button buttonMassimizza;
    @FXML
    private Button buttonChiudi;
    @FXML
    private TextField textFieldNome;
    @FXML
    private TextField textFieldCognome;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private TextField textFieldPassword;
    @FXML
    private TextField textFieldIndirizzo;
    @FXML
    private Button buttonModificaDati;
    @FXML
    private Button buttonSalvaDati;
    @FXML
    private Pane paneProfilo;
    @FXML
    private Pane paneOfferte;
    @FXML
    private Pane paneAppuntamenti;

    private static final String ORIGINAL_COLOR = "#6756be";
    private static final String HOVER_COLOR = "#9593D9";
    private static final double EXPANDED_WIDTH = 220;
    private static final double COLLAPSED_WIDTH = 70;
    private static final double BUTTON_WIDTH_EXPANDED = 180;
    private static final double BUTTON_WIDTH_COLLAPSED = 30;

    @FXML
    public void initialize() {
        List<Button> buttonsPannelloLaterale = List.of(buttonHome, buttonProfilo, buttonOfferte, buttonAppuntamenti);
        buttonsPannelloLaterale.forEach(button -> setButtonHoverEffect(button, ORIGINAL_COLOR, HOVER_COLOR));
        setButtonHoverEffect(buttonLogout, "#6756be", "#E56B6F");
        setButtonHoverEffect(buttonChiudi, "white", "#ff6767");
        setButtonHoverEffect(buttonIconizza, "white", "#a7ff97");
        buttonNascondi.setOnAction(event -> handleButtonClick());
        buttonModificaDati.setOnAction(event -> setTextFieldsEditable());
        buttonProfilo.setOnAction(event -> openProfiloPane());
        buttonOfferte.setOnAction(event -> openOffertePane());
        buttonAppuntamenti.setOnAction(event -> openAppuntamentiPane());
    }

    private void setButtonHoverEffect(Button button, String originalColor, String hoverColor) {
        button.setOnMouseEntered(event -> button.setStyle("-fx-background-color: " + hoverColor + "; -fx-text-fill: white;"));
        button.setOnMouseExited(event -> button.setStyle("-fx-background-color: " + originalColor + "; -fx-text-fill: white;"));
    }

    private void handleButtonClick() {
        toggleButtonTextAndWidth(buttonHome, "Home");
        toggleButtonTextAndWidth(buttonProfilo, "Profilo");
        toggleButtonTextAndWidth(buttonOfferte, "Offerte");
        toggleButtonTextAndWidth(buttonAppuntamenti, "Appuntamenti");
        toggleButtonTextAndWidth(buttonLogout, "Logout");
        toggleButtonTextAndWidth(buttonNascondi, "Nascondi pannello");
        toggleSidePaneWidth();
    }

    private void toggleButtonTextAndWidth(Button button, String expandedText) {
        if (button.getText().isEmpty()) {
            button.setText(expandedText);
            button.setPrefWidth(BUTTON_WIDTH_EXPANDED);
        } else {
            button.setText("");
            button.setPrefWidth(BUTTON_WIDTH_COLLAPSED);
        }
    }

    private void toggleSidePaneWidth() {
        double currentWidth = VBoxLaterale.getWidth();
        if (currentWidth == COLLAPSED_WIDTH) {
            VBoxLaterale.setPrefWidth(EXPANDED_WIDTH);
            PaneLaterale.setPrefWidth(EXPANDED_WIDTH);
            Image image = new Image(getClass().getResource("/org/openjfx/DietiEstates25/Icon/HomePage/icons8-nascondi-pannello-30.png").toExternalForm());
            imageNascondi.setImage(image);
        } else {
            VBoxLaterale.setPrefWidth(COLLAPSED_WIDTH);
            PaneLaterale.setPrefWidth(COLLAPSED_WIDTH);
            Image image = new Image(getClass().getResource("/org/openjfx/DietiEstates25/Icon/HomePage/icons8-apri-pannello-30.png").toExternalForm());
            imageNascondi.setImage(image);
        }
    }
    
    public void setTextFieldsEditable() {
    	textFieldNome.setEditable(true);
    	textFieldCognome.setEditable(true);
    	textFieldEmail.setEditable(true);
    	textFieldPassword.setEditable(true);
    	textFieldIndirizzo.setEditable(true);
    	buttonModificaDati.setDisable(true);
    	buttonSalvaDati.setDisable(false);
    }
    
    public void setTextFieldsNotEditable() {
    	textFieldNome.setEditable(false);
    	textFieldCognome.setEditable(false);
    	textFieldEmail.setEditable(false);
    	textFieldPassword.setEditable(false);
    	textFieldIndirizzo.setEditable(false);
    	buttonModificaDati.setDisable(false);
    	buttonSalvaDati.setDisable(true);
    }
    
    public void openProfiloPane() {
    	paneProfilo.setVisible(true);
    	paneOfferte.setVisible(false);
    	paneAppuntamenti.setVisible(false);
    }
    
    public void openOffertePane() {
    	paneProfilo.setVisible(false);
    	paneOfferte.setVisible(true);
    	paneAppuntamenti.setVisible(false);
    }
    
    public void openAppuntamentiPane() {
    	paneProfilo.setVisible(false);
    	paneOfferte.setVisible(false);
    	paneAppuntamenti.setVisible(true);
    }
    
    public void iconizedWindow() {
    	Stage stage = (Stage) buttonChiudi.getScene().getWindow();
    	stage.setIconified(true);
    }
    
    public void closeWindow() {
    	Stage stage = (Stage) buttonChiudi.getScene().getWindow();
    	stage.close();
    }
}
