module org.openjfx.DietiEstates25 {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;

    opens org.openjfx.DietiEstates25 to javafx.fxml;
    exports org.openjfx.DietiEstates25;
}
