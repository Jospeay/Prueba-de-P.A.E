module jpa.ni.practicapae {
    requires javafx.controls;
    requires javafx.fxml;


    opens jpa.ni.practicapae to javafx.fxml;
    exports jpa.ni.practicapae;
}