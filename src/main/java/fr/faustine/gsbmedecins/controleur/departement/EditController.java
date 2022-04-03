package fr.faustine.gsbmedecins.controleur.departement;

import fr.faustine.gsbmedecins.MainController;
import fr.faustine.gsbmedecins.modele.Departement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class EditController {

        // Variable departement actuel
        private static Departement departementActuel = null;

        // Définir le departement actuel
        public static void setDepartementActuel(Departement departement) {
            departementActuel = departement;
        }

        // Récupérer le pays actuel
        public static Departement getDepartementActuel() {
            return departementActuel;
        }
    }
