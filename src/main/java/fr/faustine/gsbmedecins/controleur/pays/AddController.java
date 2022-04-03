package fr.faustine.gsbmedecins.controleur.pays;

import fr.faustine.gsbmedecins.modele.PaysDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddController {
    // Bouton annuler
    @FXML
    private Button retour_button;

    // Textfield nom du pays
    @FXML
    private TextField nom_pays;

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
        if(nom_pays.getText().trim().isEmpty()) {
            // Fenêtre d'erreur
            Alert alert = new Alert(Alert.AlertType.NONE, "Tous les champs doivent être remplis.", ButtonType.OK);
            alert.showAndWait();

            return; // Va stopper le code et n'exécute pas ce qui suit.
        }

        // On vérifie si le texte contient du numérique (chiffres/nombres)
        if( !(nom_pays.getText()).matches("[a-zA-Z]{2,45}") ) {
            // Fenêtre d'erreur
            Alert alert = new Alert(Alert.AlertType.NONE, "Le nom d'un pays doit contenir entre 2 et 45 caractères et ne peut contenir des chiffres.", ButtonType.OK);
            alert.showAndWait();

            return; // Va stopper le code et n'exécute pas ce qui suit.
        }

        // On vérifie si le pays existe déjà
        if(PaysDAO.getPaysByLibelle(nom_pays.getText()) != null) {
            // Fenêtre d'erreur
            Alert alert = new Alert(Alert.AlertType.NONE, "Le pays " + nom_pays.getText() + " existe déjà.", ButtonType.OK);
            alert.showAndWait();

            return; // Va stopper le code et n'exécute pas ce qui suit.
        }


        // Dans le cas où il n'y a pas d'erreur, on ajoute le pays !
        PaysDAO.addPays(nom_pays.getText());

        Alert alert = new Alert(Alert.AlertType.NONE, "Le pays " + nom_pays.getText() + " a bien été créé.", ButtonType.OK);
        alert.showAndWait();

        retourButtonClicked();
    }
}
