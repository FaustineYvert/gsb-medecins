package fr.faustine.gsbmedecins.controleur;

import fr.faustine.gsbmedecins.MainController;
import fr.faustine.gsbmedecins.modele.Utilisateur;
import fr.faustine.gsbmedecins.modele.UtilisateurDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ConnexionController {
    // FXML Variables
    @FXML
    private TextField nom_utilisateur;

    @FXML
    private PasswordField mot_de_passe;


    public void connexionButtonClicked(ActionEvent event) throws IOException {
        // On vérifie si le nom d'utilisateur ou le mot de passe sont vides?
        if(nom_utilisateur.getText().trim().isEmpty() || mot_de_passe.getText().trim().isEmpty()) {
            // Fenêtre d'erreur
            Alert alert = new Alert(Alert.AlertType.NONE, "Tous les champs doivent être remplis.", ButtonType.OK);
            alert.showAndWait();

            return;
        } else { // Sinon, on regarde si les entrées sont bonnes
            Utilisateur utilisateur = UtilisateurDAO.checkUtilisateur(nom_utilisateur.getText(), mot_de_passe.getText());

            if(utilisateur == null) {
                // Fenêtre d'erreur
                Alert alert = new Alert(Alert.AlertType.NONE, "Nom d'utilisateur ou mot de passe incorrect.", ButtonType.OK);
                alert.showAndWait();
            } else {
                MainController.changerPage("vue/accueil-view.fxml", event);
            }
        }
    }
}
