package fr.faustine.gsbmedecins.controleur.pays;

import fr.faustine.gsbmedecins.modele.Pays;
import fr.faustine.gsbmedecins.modele.PaysDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class EditController implements Initializable {
    // Variables FXML
    @FXML
    private Button retour_button;

    @FXML
    private TextField id_pays;

    @FXML
    private TextField nom_pays; //j'ai compris mais dcp si prcq regarde je veux faire comme medecins

    // Variable departement actuel
    private static Pays paysActuel = null;

    // Définir le departement actuel
    public static void setPaysActuel(Pays pays) {
        paysActuel = pays;
    }

    // Récupérer le pays actuel
    public static Pays getPaysActuel() {
        return paysActuel;
    }

    // Exécution du bouton retour
    public void retourButtonClicked() {
        Stage stage = (Stage) retour_button.getScene().getWindow();
        stage.close();
    }

    // Exécution du bouton supprimer
    public void supprimerButtonClicked() {
        Alert alert = new Alert(Alert.AlertType.NONE, "Êtes-vous sûr de supprimer le pays " + getPaysActuel().getLibelle() + " ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            PaysDAO.deletePaysById(getPaysActuel().getId());
            retourButtonClicked();
        }
    }

    // Exécution du bouton modifier
    public void modifierButtonClicked() {
        if(!Objects.equals(nom_pays.getText(), getPaysActuel().getLibelle())) {
            if(nom_pays.getText().trim().isEmpty()) {
                // Show popup
                Alert alert = new Alert(Alert.AlertType.NONE, "Tous les champs doivent être remplis.", ButtonType.OK);
                alert.showAndWait();

                return;
            }

            if(!nom_pays.getText().matches("[a-zA-Z\\sàâäéèêëîïôöùûüçÀÂÄÉÈÊËÎÏÔÖÙÛÜÇ-]{2,45}")) {
                // Show popup
                Alert alert = new Alert(Alert.AlertType.NONE, "Le libellé d'un pays doit comporter entre 2 et 45 caractères et ne peut contenir seulement des lettres.", ButtonType.OK);
                alert.showAndWait();

                return;
            }

            if(PaysDAO.getPaysByLibelle(nom_pays.getText()) != null) {
                // Show popup
                Alert alert = new Alert(Alert.AlertType.NONE, "Ce pays existe déjà.", ButtonType.OK);
                alert.showAndWait();

                return;
            }

            // Add infos
            String new_libelle = nom_pays.getText();

            // Send & update
            PaysDAO.updatePaysByID(getPaysActuel().getId(), new_libelle);

            // Show popup
            Alert alert = new Alert(Alert.AlertType.NONE, "Les valeurs du pays " + getPaysActuel().getLibelle() + " ont bien étés modifiées.", ButtonType.OK);
            alert.showAndWait();
        }

        // Close stage & update tableview
        retourButtonClicked();
    }

    // Faire afficher les informations du médecins sélectionné
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id_pays.setText(Integer.toString(getPaysActuel().getId()));
        nom_pays.setText(getPaysActuel().getLibelle());
    }
}