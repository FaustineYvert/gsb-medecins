package fr.faustine.gsbmedecins;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainController {
    // Variables
    private static Stage stage;

    // Normal Functions
    public static void changerPage(String page, ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(MainController.class.getResource(page)));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static boolean isNumeric(String string) {
        int intValue;

        if(string == null || string.equals("")) {
            return false;
        }

        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return false;
    }

    // FXML Functions
        // NavBar
    @FXML
    private void accueilButtonClicked(ActionEvent event) throws IOException {
        changerPage("vue/accueil-view.fxml", event);
    }

    @FXML
    private void paysButtonClicked(ActionEvent event) throws IOException {
        changerPage("vue/pays-view.fxml", event);
    }

    @FXML
    private void departementButtonClicked(ActionEvent event) throws IOException {
        changerPage("vue/departement-view.fxml", event);
    }

    @FXML
    private void medecinButtonClicked(ActionEvent event) throws IOException {
        changerPage("vue/medecin-view.fxml", event);
    }

    public void switchButtonClicked(ActionEvent event) throws IOException {
        changerPage("vue/connect-view.fxml", event);
    }
}