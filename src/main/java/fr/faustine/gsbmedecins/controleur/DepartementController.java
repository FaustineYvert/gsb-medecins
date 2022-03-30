package fr.faustine.gsbmedecins.controleur;

import fr.faustine.gsbmedecins.MainController;
import fr.faustine.gsbmedecins.modele.Departement;
import fr.faustine.gsbmedecins.modele.DepartementDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DepartementController implements Initializable {
    // FXML Variables
    @FXML
    public TableView<Departement> departements_table;

    @FXML
    public TableColumn<Departement, Integer> departement_id;

    @FXML
    public TableColumn<Departement, String> departement_libelle, departement_action;

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

        // Ajout d'un departement
    @FXML
    private void ajouterButtonClicked(ActionEvent event) throws IOException {
        MainController.changerPage("vue/ajout-departement-view.fxml", event);
    }

        // Ajout d'un departement
    @FXML
    private void retourButtonClicked(ActionEvent event) throws IOException {
        MainController.changerPage("vue/departement-view.fxml", event);
    }

    @FXML
    private void retourButtonClicked2(ActionEvent event) throws IOException {
        MainController.changerPage("vue/departement-view.fxml", event);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        departements_table.getItems().clear();
        departements_table.refresh();

        departement_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        departement_libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));

        // Cacher la colonne action si pas admin
        departement_action.setVisible(false);

        departements_table.getItems().addAll(DepartementDAO.getAll());
        departements_table.refresh();
    }
}
