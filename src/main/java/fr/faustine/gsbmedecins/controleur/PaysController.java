package fr.faustine.gsbmedecins.controleur;

import fr.faustine.gsbmedecins.MainController;
import fr.faustine.gsbmedecins.modele.Pays;
import fr.faustine.gsbmedecins.modele.PaysDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PaysController implements Initializable {
    // FXML Variables
    @FXML
    public TableView<Pays> pays_table;

    @FXML
    public TableColumn<Pays, Integer> pays_id;

    @FXML
    public TableColumn<Pays, String> pays_libelle, pays_action;


    // FXML Functions
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

        // Ajout d'un pays
    @FXML
    private void ajouterButtonClicked(ActionEvent event) throws IOException {
        MainController.changerPage("vue/ajout-pays-view.fxml", event);
    }

        // Retour sur pays
    @FXML
    private void testlolButtonMdr(ActionEvent event) throws IOException {
        System.out.println("got");
        MainController.changerPage("vue/pays-view.fxml", event);
    }

    @FXML
    private void retourButtonClicked2(ActionEvent event) throws IOException {
        MainController.changerPage("vue/pays-view.fxml", event);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pays_table.getItems().clear();
        pays_table.refresh();

        pays_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        pays_libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));

        // Cacher la colonne action si pas admin
        pays_action.setVisible(false);

        pays_table.getItems().addAll(PaysDAO.getAll());
        pays_table.refresh();
    }
}
