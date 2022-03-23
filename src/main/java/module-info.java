module fr.faustine.gsb_medecins {
    requires javafx.controls;
    requires javafx.fxml;


    opens fr.faustine.gsb_medecins to javafx.fxml;
    exports fr.faustine.gsb_medecins;
}