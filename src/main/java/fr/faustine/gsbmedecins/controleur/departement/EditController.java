package fr.faustine.gsbmedecins.controleur.departement;

import fr.faustine.gsbmedecins.modele.Departement;
import fr.faustine.gsbmedecins.modele.DepartementDAO;
import fr.faustine.gsbmedecins.modele.Pays;
import fr.faustine.gsbmedecins.modele.PaysDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class EditController implements Initializable {
    // Variables FXML
    @FXML
    private Button retour_button;

    @FXML
    private TextField id_departement;

    @FXML
    private TextField libelle_departement; //j'ai compris mais dcp si prcq regarde je veux faire comme medecins

    @FXML
    private ComboBox<String> pays_departement;

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

    // Exécution du bouton retour
    public void retourButtonClicked() {
        Stage stage = (Stage) retour_button.getScene().getWindow();
        stage.close();
    }

    // Exécution du bouton supprimer
    public void supprimerButtonClicked() {
        Alert alert = new Alert(Alert.AlertType.NONE, "Êtes-vous sûr de supprimer le département " + getDepartementActuel().getLibelle() + " provenant du pays suivant : " + PaysDAO.getLibelleByID(getDepartementActuel().getPays_id()), ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            DepartementDAO.deleteDepartementByID(getDepartementActuel().getId());
            retourButtonClicked();
        }
    }

    // Exécution du bouton modifier
    public void modifierButtonClicked() {
        if(!Objects.equals(libelle_departement.getText(), getDepartementActuel().getLibelle()) || !Objects.equals(pays_departement.getValue(), PaysDAO.getLibelleByID(getDepartementActuel().getPays_id()))) {
            if(libelle_departement.getText().trim().isEmpty()) {
                // Show popup
                Alert alert = new Alert(Alert.AlertType.NONE, "Tous les champs doivent être remplis.", ButtonType.OK);
                alert.showAndWait();

                return;
            }

            if(!libelle_departement.getText().matches("[a-zA-Z\\sàâäéèêëîïôöùûüçÀÂÄÉÈÊËÎÏÔÖÙÛÜÇ-]{2,30}")) {
                // Show popup
                Alert alert = new Alert(Alert.AlertType.NONE, "Le libellé d'un département doit comporter entre 2 et 45 caractères et ne peut contenir seulement des lettres.", ButtonType.OK);
                alert.showAndWait();

                return;
            }

            if(DepartementDAO.getDepartementByLibelle(libelle_departement.getText()) != null) {
                // Show popup
                Alert alert = new Alert(Alert.AlertType.NONE, "Ce département existe déjà.", ButtonType.OK);
                alert.showAndWait();

                return;
            }

            // Add infos
            String new_libelle = libelle_departement.getText();
            String new_pays = pays_departement.getValue();

            // Send & update
            DepartementDAO.updateDepartementByID(getDepartementActuel().getId(), new_libelle, new_pays);

            // Show popup
            Alert alert = new Alert(Alert.AlertType.NONE, "Les valeurs du département " + getDepartementActuel().getLibelle() + " ont bien étés modifiées.", ButtonType.OK);
            alert.showAndWait();
        }

        // Close stage & update tableview
        retourButtonClicked();
    }

    // Faire afficher les informations du médecins sélectionné
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Variables
        ObservableList<Pays> pays_List = PaysDAO.getAll();

        // Setup
        for (Pays pays : pays_List) {
            pays_departement.getItems().add(pays.getLibelle());
        }

        id_departement.setText(Integer.toString(getDepartementActuel().getId()));
        libelle_departement.setText(getDepartementActuel().getLibelle());
        pays_departement.setValue(PaysDAO.getLibelleByID(getDepartementActuel().getPays_id()));
    }
}