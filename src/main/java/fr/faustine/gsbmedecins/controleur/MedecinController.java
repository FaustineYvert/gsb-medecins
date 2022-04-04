package fr.faustine.gsbmedecins.controleur;

import fr.faustine.gsbmedecins.MainController;
import fr.faustine.gsbmedecins.controleur.medecin.EditController;
import fr.faustine.gsbmedecins.modele.Medecin;
import fr.faustine.gsbmedecins.modele.MedecinDAO;
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

public class MedecinController implements Initializable {
    // FXML Variables
    @FXML
    public TableView<Medecin> medecins_table;

    @FXML
    public TableColumn<Medecin, Integer> medecin_id;

    @FXML
    public TableColumn<Medecin, String> medecin_lastname, medecin_firstname, medecin_action;

    @FXML
    public TextField medecins_searchbar;

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
        MainController.changerPage("vue/medecin-view.fxml", event);
    }

    @FXML
    private void rechercherButtonClicked() {
        medecins_table_load(MedecinDAO.getByLike(medecins_searchbar.getText()));
    }

        // Ajout d'un médecin
    @FXML
    private void ajouterButtonClicked() {
        // Créer une nouvelle fenêtre
        Stage stage = new Stage();
        Pane scene_window = null;
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fr/faustine/gsbmedecins/vue/ajout-medecin-view.fxml"));

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

    private void medecins_table_load(ObservableList<Medecin> pays_List) {
        medecins_table.getItems().clear();
        medecins_table.refresh();

        medecin_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        medecin_lastname.setCellValueFactory(new PropertyValueFactory<>("nom"));
        medecin_firstname.setCellValueFactory(new PropertyValueFactory<>("prenom"));

        // Ajout d'un bouton "voir"
        // Create buttons for every rows
        Callback<TableColumn<Medecin, String>, TableCell<Medecin, String>> cellFactory = (param) -> new TableCell<>() {
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
                        Medecin medecinClicked = getTableView().getItems().get(getIndex());
                        EditController.setMedecinActuel(medecinClicked); // Définir le pays sur lequel on a cliqué

                        // Create new stage
                        Stage stage = new Stage();
                        Pane scene_window = null;
                        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fr/faustine/gsbmedecins/vue/voirplus-medecin-view.fxml"));

                        try {
                            scene_window = loader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        // Set Scene
                        assert scene_window != null; // scene_window might be null, so assert
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

        // Attribute buttons to the column
        medecin_action.setCellFactory(cellFactory);

        table_reload(pays_List);
        medecins_table.setPlaceholder(new Label("Médecin(s) non trouvé(s)"));
    }

    private void table_reload(ObservableList<Medecin> medecins_List) {
        medecins_table.getItems().clear();
        medecins_table.refresh();
        medecins_table.getItems().addAll(medecins_List);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        medecins_table_load(MedecinDAO.getAll());
    }
}
