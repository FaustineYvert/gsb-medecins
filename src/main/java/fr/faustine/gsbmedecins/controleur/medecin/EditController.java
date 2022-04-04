package fr.faustine.gsbmedecins.controleur.medecin;

import fr.faustine.gsbmedecins.MainController;
import fr.faustine.gsbmedecins.modele.Departement;
import fr.faustine.gsbmedecins.modele.DepartementDAO;
import fr.faustine.gsbmedecins.modele.Medecin;
import fr.faustine.gsbmedecins.modele.MedecinDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class EditController implements Initializable {
    // Variables
    private static Medecin medecinActuel = null;
    private static int valueDepartement = 0;

    // Variables FXML
    @FXML
    private Button retour_button;

    @FXML
    private TextField id_medecin;

    @FXML
    private TextField nom_medecin;

    @FXML
    private TextField prenom_medecin;

    @FXML
    private TextField tel_medecin;

    @FXML
    private TextField adresse_medecin;

    @FXML
    private ComboBox<String> specialite_medecin;

    @FXML
    private ComboBox<String> departement_medecin;

    // Définir le medecin actuel
    public static void setMedecinActuel(Medecin medecin) {
        medecinActuel = medecin;
        }

    // Récupérer le medecin actuel
    public static Medecin getMedecinActuel() {
        return medecinActuel;
    }

    // Fermer la fenêtre
    public void retourButtonClicked() {
        Stage stage = (Stage) retour_button.getScene().getWindow();
        stage.close();
    }

    // Exécution du bouton modifier
    public void modifierButtonClicked() {
        // Nous vérifions déjà si l'une des valeurs a été modifiée, que ce soit le nom, le prénom, l'adresse, etc.
        if( (!Objects.equals(nom_medecin.getText(), getMedecinActuel().getNom())) || (!Objects.equals(prenom_medecin.getText(), getMedecinActuel().getPrenom())) || (!Objects.equals(adresse_medecin.getText(), getMedecinActuel().getAdresse())) || (!Objects.equals(tel_medecin.getText(), getMedecinActuel().getTel())) || (!Objects.equals(specialite_medecin.getValue(), getMedecinActuel().getSpecialiteComplementaire())) || (!Objects.equals(departement_medecin.getValue(), Integer.toString(getMedecinActuel().getDepartement_id())))) {
            // On vérifie si le nom, le prénom ou bien le n° de téléphone sont des champs "vides"
            if(nom_medecin.getText().trim().isEmpty() || prenom_medecin.getText().trim().isEmpty() || adresse_medecin.getText().trim().isEmpty() || tel_medecin.getText().trim().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.NONE, "Tous les champs doivent être remplis.", ButtonType.OK);
                alert.showAndWait();

                return;
            }

            // On vérifie que le nom ne puisse pas avoir de chiffres et soit entre 2 et 30 caractères
            if(!nom_medecin.getText().matches("[a-zA-Z\\sàâäéèêëîïôöùûüçÀÂÄÉÈÊËÎÏÔÖÙÛÜÇ-]{2,30}")) {
                Alert alert = new Alert(Alert.AlertType.NONE, "Le nom d'un médecin doit comporter entre 2 et 30 caractères et ne peut contenir seulement des lettres.", ButtonType.OK);
                alert.showAndWait();

                return;
            }

            // On vérifie que le prénom ne puisse pas avoir de chiffres et soit entre 2 et 30 caractères
            if(!prenom_medecin.getText().matches("[a-zA-Z\\sàâäéèêëîïôöùûüçÀÂÄÉÈÊËÎÏÔÖÙÛÜÇ-]{2,30}")) {
                // Show popup
                Alert alert = new Alert(Alert.AlertType.NONE, "Le prénom d'un médecin doit comporter entre 2 et 30 caractères et ne peut contenir seulement des lettres.", ButtonType.OK);
                alert.showAndWait();

                return;
            }

            /*
            // On vérifie si le n° de téléphone contient que des chiffres (de 0 à 9)
            if(!tel_medecin.getText().matches("^[0-9]+$")) {
                Alert alert = new Alert(Alert.AlertType.NONE, "Le numéro de téléphone doit comporter seulement des chiffres", ButtonType.OK);
                alert.showAndWait();

                return;
            }
             */

            // On vérifie le numéro de téléphone
            if(!MainController.isNumeric(tel_medecin.getText()) || tel_medecin.getText().length() < 10) {
                // Fenêtre d'erreur
                Alert alert = new Alert(Alert.AlertType.NONE, "Le numéro de téléphone ne peut contenir des lettres et moins de 10 caractères.", ButtonType.OK);
                alert.showAndWait();

                return; // Va stopper le code et n'exécute pas ce qui suit.
            }

            // Add infos
            String new_nom = nom_medecin.getText();
            String new_prenom = prenom_medecin.getText();
            String new_adresse = adresse_medecin.getText();
            String new_tel = tel_medecin.getText();
            String new_specialite = String.valueOf(specialite_medecin.getValue());
            String new_departement = String.valueOf(departement_medecin.getValue());

            // Send & update
            MedecinDAO.updateMedicByID(getMedecinActuel().getId(), new_nom, new_prenom, new_adresse, new_tel, new_specialite, new_departement);

            // Show popup
            Alert alert = new Alert(Alert.AlertType.NONE, "Les valeurs du médecin " + getMedecinActuel().getNom() + " " + getMedecinActuel().getPrenom() + " ont bien étés modifiées.", ButtonType.OK);
            alert.showAndWait();
        }

        // Close stage & update tableview
        retourButtonClicked();
    }

    // Exécution du bouton supprimer
    public void supprimerButtonClicked() {
        Alert alert = new Alert(Alert.AlertType.NONE, "Êtes-vous sûr de supprimer le médecin " + getMedecinActuel().getNom() + " " + getMedecinActuel().getPrenom(), ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            MedecinDAO.deleteMedecinByID(getMedecinActuel().getId());
            retourButtonClicked();
        }
    }

    // Faire afficher les informations du médecins sélectionné
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Variables
        ObservableList<String> specialite_List = MedecinDAO.getAllSpeciality();
        ObservableList<Departement> departement_List = DepartementDAO.getAll();

        // Setup
        for (String spe : specialite_List) {
            specialite_medecin.getItems().add(spe);
        }

        for (Departement dpt : departement_List) {
            departement_medecin.getItems().add(dpt.getLibelle());
        }

        id_medecin.setText(Integer.toString(getMedecinActuel().getId()));
        nom_medecin.setText(getMedecinActuel().getNom());
        prenom_medecin.setText(getMedecinActuel().getPrenom());
        adresse_medecin.setText(getMedecinActuel().getAdresse());
        tel_medecin.setText(getMedecinActuel().getTel());

        if(getMedecinActuel().getSpecialiteComplementaire() == null) {
            specialite_medecin.setValue("AUCUNE SPÉCIALITÉ");
        } else {
            specialite_medecin.setValue(getMedecinActuel().getSpecialiteComplementaire());
        }

        departement_medecin.setValue(DepartementDAO.getDepartementByID(getMedecinActuel().getDepartement_id()).getLibelle());
    }
}
