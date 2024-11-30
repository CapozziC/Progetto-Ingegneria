package org.openjfx.DietiEstates25;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.openjfx.DietiEstates25.WindowsManager;

import java.util.List;

public class HomePageController {

    private App main;
    
    @FXML
    private Button buttonHome, buttonProfilo, buttonOfferte, buttonAppuntamenti, buttonLogout, buttonNascondi;
    @FXML
    private Pane PaneLaterale;
    @FXML
    private VBox VBoxLaterale;
    @FXML
    private Button buttonIconizza;

    private static final String ORIGINAL_COLOR = "#6756be";
    private static final String HOVER_COLOR = "#9593D9";
    private static final double EXPANDED_WIDTH = 200;
    private static final double COLLAPSED_WIDTH = 70;
    private static final double BUTTON_WIDTH_EXPANDED = 170;
    private static final double BUTTON_WIDTH_COLLAPSED = 30;

    public void setMain(App main) {
        this.main = main;
    }

    @FXML
    public void initialize() {
        List<Button> buttons = List.of(buttonHome, buttonProfilo, buttonOfferte, buttonAppuntamenti, buttonLogout, buttonNascondi);
        buttons.forEach(button -> setButtonHoverEffect(button, ORIGINAL_COLOR, HOVER_COLOR));
        buttonNascondi.setOnAction(event -> handleButtonClick());
        buttonIconizza.setOnAction(event -> iconizedWindow());
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
        } else {
            VBoxLaterale.setPrefWidth(COLLAPSED_WIDTH);
            PaneLaterale.setPrefWidth(COLLAPSED_WIDTH);
        }
    }
    
    public void iconizedWindow() {
    	Stage stage = (Stage) buttonHome.getScene().getWindow();
    	stage.setIconified(true);
    }
}
