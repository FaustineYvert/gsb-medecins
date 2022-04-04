package fr.faustine.gsbmedecins.controleur;

import fr.faustine.gsbmedecins.MainController;
import fr.faustine.gsbmedecins.controleur.departement.EditController;
import fr.faustine.gsbmedecins.modele.Departement;
import fr.faustine.gsbmedecins.modele.DepartementDAO;
import fr.faustine.gsbmedecins.modele.PaysDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

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
    public TableColumn<Departement, String> departement_libelle, departement_nomPays;

    @FXML
    public TableColumn<Departement, String> departement_action;

    @FXML
    private TextField departementsearchbar;

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
    private void refreshButtonClicked(ActionEvent event) throws IOException {
        MainController.changerPage("vue/departement-view.fxml", event);
    }

    @FXML
    private void rechercherButtonClicked() {
        departement_table_load(DepartementDAO.getByLike(departementsearchbar.getText()));
    }

        // Ajout d'un departement
    @FXML
    private void ajouterButtonClicked() {
        // Créer une nouvelle fenêtre
        Stage stage = new Stage();
        Pane scene_window = null;
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fr/faustine/gsbmedecins/vue/ajout-departement-view.fxml"));

        try {
            scene_window = loader.load();
        } catch(IOException e) {
            e.printStackTrace();
        }

        // Ajouter la Scene
        assert scene_window != null;
        Scene scene = new Scene(scene_window);
        stage.setScene(scene);
        stage.setResizable(false);

        stage.show();
    }

    private void departement_table_load(ObservableList<Departement> departement_List) {
        departements_table.getItems().clear();
        departements_table.refresh();

        departement_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        departement_libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        departement_nomPays.setCellValueFactory(c-> new SimpleStringProperty(PaysDAO.getLibelleByID(c.getValue().getPays_id())));

        // Cacher les ID (ne sert à rien en soit)
        departement_id.setVisible(false);

        // Ajout d'un bouton "voir"
        // Create buttons for every rows
        Callback<TableColumn<Departement, String>, TableCell<Departement, String>> cellFactory = (param) -> new TableCell<>() {
            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    final Button voir_button = new Button("Voir plus");
                    //voir_button.getStyleClass().add("button-blue");
                    voir_button.setCursor(Cursor.HAND);

                    voir_button.setOnAction(event -> {
                        Departement departementClicked = getTableView().getItems().get(getIndex());
                        EditController.setDepartementActuel(departementClicked);

                        // Create new stage
                        Stage stage = new Stage();
                        Pane scene_window = null;
                        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fr/faustine/gsbmedecins/vue/voirplus-departement-view.fxml"));

                        try {
                            scene_window = loader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        // Set Scene
                        assert scene_window != null;
                        Scene scene = new Scene(scene_window);
                        stage.setScene(scene);
                        stage.setResizable(false);

                        stage.show();
                    });

                    setGraphic(voir_button);
                    setText(null);
                }
            }
        };

        departement_action.setCellFactory(cellFactory);

        table_reload(departement_List);
        departements_table.setPlaceholder(new Label("Départements non trouvé(s)"));
    }

    private void table_reload(ObservableList<Departement> departement_list) {
        departements_table.getItems().clear();
        departements_table.refresh();
        departements_table.getItems().addAll(departement_list);
    }

    // Ajout d'un departement
    @FXML
    private void retourButtonClicked(ActionEvent event) throws IOException {
        MainController.changerPage("vue/departement-view.fxml", event);
    }

    @FXML
    private void ajoutButtonClicked(ActionEvent event) throws IOException {
        MainController.changerPage("vue/departement-view.fxml", event);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        departement_table_load(DepartementDAO.getAll());
    }
}
