package fr.faustine.gsbmedecins.controleur.pays;

import fr.faustine.gsbmedecins.MainController;
import fr.faustine.gsbmedecins.modele.Departement;
import fr.faustine.gsbmedecins.modele.Pays;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class EditController {
    // Variable paysactuel
    private static Pays paysActuel = null;

    // Définir le pays actuel
    public static void setPaysActuel(Pays pays) {
        paysActuel = pays;
    }

    // Récupérer le pays actuel
    public static Pays getPaysActuel() {
        return paysActuel;
    }


    // Retour sur pays
    @FXML
    private void retourButtonClicked(ActionEvent event) throws IOException {
        MainController.changerPage("vue/pays-view.fxml", event);
    }

    @FXML
    private void retourButtonClicked2(ActionEvent event) throws IOException {
        MainController.changerPage("vue/pays-view.fxml", event);
    }
}
