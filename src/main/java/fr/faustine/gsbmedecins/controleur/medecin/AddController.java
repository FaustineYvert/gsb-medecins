package fr.faustine.gsbmedecins.controleur.medecin;

import fr.faustine.gsbmedecins.MainController;
import fr.faustine.gsbmedecins.modele.Departement;
import fr.faustine.gsbmedecins.modele.DepartementDAO;
import fr.faustine.gsbmedecins.modele.MedecinDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class AddController implements Initializable {
    // Valeur (valueSpecialite) actuelle
    public static String valueSpecialite = "";
    public static Integer valueDepartement = null;

    // Bouton annuler
    @FXML
    private Button retour_button;

    // Textfield nom du medecin
    @FXML
    private TextField nom_medecin;

    // Textfield prenom du medecin
    @FXML
    private TextField prenom_medecin;

    // Textfield adresse du medecin
    @FXML
    private TextField adresse_medecin;

    // Textfield tel du medecin
    @FXML
    private TextField tel_medecin;

    // Combobox choix de la specialité du medecin
    @FXML
    private ComboBox<String> specialite_medecin;

    // Combobox choix du departement du medecin
    @FXML
    private ComboBox<Departement> departement_medecin;


    // Bouton fonction annuler
    @FXML
    private void retourButtonClicked() {
        Stage stage = (Stage) retour_button.getScene().getWindow();
        stage.close();
    }

    // Bouton fonction ajouter
    @FXML
    private void ajoutButtonClicked() {
        // On vérifie si le texte est vide
        if(nom_medecin.getText().trim().isEmpty()) {
            // Fenêtre d'erreur
            Alert alert = new Alert(Alert.AlertType.NONE, "Tous les champs doivent être remplis.", ButtonType.OK);
            alert.showAndWait();

            return; // Va stopper le code et n'exécute pas ce qui suit.
        }

        // On vérifie si le texte contient du numérique (chiffres/nombres)
        if( !(nom_medecin.getText()).matches("[a-zA-Z]{2,45}") || !(prenom_medecin.getText()).matches("[a-zA-Z]{2,45}") ) {
            // Fenêtre d'erreur
            Alert alert = new Alert(Alert.AlertType.NONE, "Le nom ou prénom d'un médecin doit contenir au moins 2 caractère et ne peut contenir des chiffres.", ButtonType.OK);
            alert.showAndWait();

            return; // Va stopper le code et n'exécute pas ce qui suit.
        }

        // On vérifie le numéro de téléphone
        if(!MainController.isNumeric(tel_medecin.getText()) || tel_medecin.getText().length() < 10) {
            // Fenêtre d'erreur
            Alert alert = new Alert(Alert.AlertType.NONE, "Le numéro de téléphone ne peut contenir des lettres et moins de 10 caractères.", ButtonType.OK);
            alert.showAndWait();

            return; // Va stopper le code et n'exécute pas ce qui suit.
        }

        // Dans le cas où il n'y a pas d'erreur, on ajoute le medecin !
        MedecinDAO.addMedecin(nom_medecin.getText(), prenom_medecin.getText(), adresse_medecin.getText(), tel_medecin.getText(), valueSpecialite, valueDepartement);

        Alert alert = new Alert(Alert.AlertType.NONE, "Le médecin  " + nom_medecin.getText() + " " + prenom_medecin.getText() + " a bien été créé.", ButtonType.OK);
        alert.showAndWait();

        retourButtonClicked();
    }

    // Méthode qui "initialize()" qui se lance -automatiquement- lors du chargement du contrôleur
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Variables des listes (spécialités / départements)
        ObservableList<String> speciality_List = MedecinDAO.getAllSpeciality();
        ObservableList<Departement> departement_List = DepartementDAO.getAll();

        // On ajoute toutes les spécialités et les départements
        specialite_medecin.getItems().addAll(speciality_List);
        departement_medecin.getItems().addAll(departement_List);


        // On va afficher le nom des spécialité et récupérer celle où l'utilisateur a cliqué
        specialite_medecin.setConverter(new StringConverter<>() {
            @Override
            public String toString(String object) {
                return object;
            }

            @Override
            public String fromString(String string) {
                return specialite_medecin.getItems().stream().filter(ap -> ap.equals(string)).findFirst().orElse(null);
            }
        });
        specialite_medecin.valueProperty().addListener((obs, oldval, newval) -> {
            if (newval != null) {
                valueSpecialite = newval;
            }
        });


        // On va afficher le nom des spécialité et récupérer celle où l'utilisateur a cliqué
        departement_medecin.setConverter(new StringConverter<>() {
            @Override
            public String toString(Departement object) {
                return object.getLibelle();
            }

            @Override
            public Departement fromString(String string) {
                return departement_medecin.getItems().stream().filter(ap -> ap.getLibelle().equals(string)).findFirst().orElse(null);
            }
        });
        departement_medecin.valueProperty().addListener((obs, oldval, newval) -> {
            if (newval != null) {
                valueDepartement = newval.getId();
            }
        });
    }
}