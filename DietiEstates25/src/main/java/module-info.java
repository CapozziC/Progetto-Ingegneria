module org.openjfx.DietiEstates25 {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;

    opens org.openjfx.DietiEstates25 to javafx.fxml;
    opens org.openjfx.DietiEstates25.Controllers to javafx.fxml;
    exports org.openjfx.DietiEstates25;
}