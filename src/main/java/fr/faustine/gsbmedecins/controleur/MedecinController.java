package fr.faustine.gsbmedecins.controleur;

import fr.faustine.gsbmedecins.MainController;
import fr.faustine.gsbmedecins.modele.Medecin;
import fr.faustine.gsbmedecins.modele.MedecinDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MedecinController implements Initializable {
    // FXML Variables
    @FXML
    public TableView<Medecin> medecins_table;

    @FXML
    public TableColumn<Medecin, Integer> medecin_id;

    @FXML
    public TableColumn<Medecin, String> medecin_lastname, medecin_firstname, medecin_action;

    // FXML Functions
        // NavBar
    @FXML
    private void accueilButtonClicked(ActionEvent event) throws IOException {
        MainController.changerPage("vue/accueil-view.fxml", event);
    }

    @FXML
    private void paysButtonClicked(ActionEvent event) throws IOException {
        MainController.changerPage("vue/pays-view.fxml", event);
    }

    @FXML
    private void departementButtonClicked(ActionEvent event) throws IOException {
        MainController.changerPage("vue/departement-view.fxml", event);
    }

    @FXML
    private void medecinButtonClicked(ActionEvent event) throws IOException {
        MainController.changerPage("vue/medecin-view.fxml", event);
    }

    @FXML
    private void rechercherButtonClicked() {
        //
    }

        // Ajout d'un médecin
    @FXML
    private void ajouterButtonClicked(ActionEvent event) throws IOException {
        MainController.changerPage("vue/ajouter-medecin-view.fxml", event);
    }

        // Retour Medecin
    @FXML
    private void retourButtonClicked(ActionEvent event) throws IOException {
        MainController.changerPage("vue/medecin-view.fxml", event);
    }

    @FXML
    private void retourButtonClicked2(ActionEvent event) throws IOException {
        MainController.changerPage("vue/medecin-view.fxml", event);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        medecins_table.getItems().clear();
        medecins_table.refresh();

        medecin_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        medecin_lastname.setCellValueFactory(new PropertyValueFactory<>("nom"));
        medecin_firstname.setCellValueFactory(new PropertyValueFactory<>("prenom"));

        // Cacher la colonne action si pas admin
        medecin_action.setVisible(false);

        medecins_table.getItems().addAll(MedecinDAO.getAll());
        medecins_table.refresh();
    }
}
