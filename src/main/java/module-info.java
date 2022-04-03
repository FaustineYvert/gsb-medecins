module fr.faustine.gsbmedecins {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens fr.faustine.gsbmedecins to javafx.fxml;
    exports fr.faustine.gsbmedecins;

    opens fr.faustine.gsbmedecins.controleur to javafx.fxml;
    exports fr.faustine.gsbmedecins.controleur;

    opens fr.faustine.gsbmedecins.controleur.pays to javafx.fxml;
    exports fr.faustine.gsbmedecins.controleur.pays;

    opens fr.faustine.gsbmedecins.controleur.departement to javafx.fxml;
    exports fr.faustine.gsbmedecins.controleur.departement;

    opens fr.faustine.gsbmedecins.controleur.medecin to javafx.fxml;
    exports fr.faustine.gsbmedecins.controleur.medecin;

    opens fr.faustine.gsbmedecins.modele to javafx.fxml;
    exports fr.faustine.gsbmedecins.modele;
}