module jpa.ni.practicapae {
    requires javafx.controls;
    requires javafx.fxml;

    opens jpa.ni.practicapae to javafx.fxml;
    opens jpa.ni.practicapae.Controller to javafx.fxml;
    opens jpa.ni.practicapae.Model to javafx.fxml;

    exports jpa.ni.practicapae;
    exports jpa.ni.practicapae.Controller;
    exports jpa.ni.practicapae.Model;
}