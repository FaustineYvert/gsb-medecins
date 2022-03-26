module fr.faustine.gsbmedecins {
    requires javafx.controls;
    requires javafx.fxml;


    opens fr.faustine.gsbmedecins to javafx.fxml;
    exports fr.faustine.gsbmedecins;
}