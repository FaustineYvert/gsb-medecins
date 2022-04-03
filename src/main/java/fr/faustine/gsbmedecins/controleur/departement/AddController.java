package fr.faustine.gsbmedecins.controleur.departement;

import fr.faustine.gsbmedecins.modele.DepartementDAO;
import fr.faustine.gsbmedecins.modele.Pays;
import fr.faustine.gsbmedecins.modele.PaysDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class AddController implements Initializable {
    // Valeur (pays_id) actuelle
    private int valuePays = 0;

    // Bouton annuler
    @FXML
    private Button retour_button;

    // Textfield nom du département
    @FXML
    private TextField nom_departement;

    // Combobox choix du pays
    @FXML
    private ComboBox<Pays> pays_departement;

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
        if(nom_departement.getText().trim().isEmpty()) {
            // Fenêtre d'erreur
            Alert alert = new Alert(Alert.AlertType.NONE, "Tous les champs doivent être remplis.", ButtonType.OK);
            alert.showAndWait();

            return; // Va stopper le code et n'exécute pas ce qui suit.
        }

        // On vérifie si le texte contient du numérique (chiffres/nombres)
        if( !(nom_departement.getText()).matches("[a-zA-Z]{2,45}") ) {
            // Fenêtre d'erreur
            Alert alert = new Alert(Alert.AlertType.NONE, "Le nom d'un pays doit contenir entre 2 et 45 caractères et ne peut contenir des chiffres.", ButtonType.OK);
            alert.showAndWait();

            return; // Va stopper le code et n'exécute pas ce qui suit.
        }

        // On vérifie si le pays existe déjà
        if(DepartementDAO.getDepartementByLibelle(nom_departement.getText()) != null) {
            // Fenêtre d'erreur
            Alert alert = new Alert(Alert.AlertType.NONE, "Le pays " + nom_departement.getText() + " existe déjà.", ButtonType.OK);
            alert.showAndWait();

            return; // Va stopper le code et n'exécute pas ce qui suit.
        }


        // Dans le cas où il n'y a pas d'erreur, on ajoute le pays !
        DepartementDAO.addDepartement(nom_departement.getText(), valuePays);

        Alert alert = new Alert(Alert.AlertType.NONE, "Le département " + nom_departement.getText() + " a bien été créé.", ButtonType.OK);
        alert.showAndWait();

        retourButtonClicked();
    }

    // Méthode qui "initialize()" qui se lance -automatiquement- lors du chargement du contrôleur
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // On va créer la liste de tous les pays grâce au "PaysDAO.getAll()"
        ObservableList<Pays> pays_List = PaysDAO.getAll();

        // On les ajoute dans la combobox (l'outil qui permet de sélectionner des options)
        pays_departement.getItems().addAll(pays_List);

        // On convertis les objets récupérés de la liste dans la combobox en string, pour faire afficher le nom
        pays_departement.setConverter(new StringConverter<>() {
            @Override
            public String toString(Pays object) {
                return object.getLibelle();
            }

            @Override
            public Pays fromString(String string) {
                return pays_departement.getItems().stream().filter(ap -> ap.getLibelle().equals(string)).findFirst().orElse(null);
            }
        });

        // On lance un "écouteur de valeur" qui va permettre de récupérer l'objet sur lequel on vient de cliquer et donc son ID, qui sera utilisé ensuite.
        pays_departement.valueProperty().addListener((obs, oldval, newval) -> {
            if (newval != null) {
                valuePays = newval.getId();
            }
        });
    }
}