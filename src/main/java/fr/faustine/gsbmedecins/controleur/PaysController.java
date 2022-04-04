package fr.faustine.gsbmedecins.controleur;

import fr.faustine.gsbmedecins.MainController;
import fr.faustine.gsbmedecins.controleur.pays.EditController;
import fr.faustine.gsbmedecins.modele.Pays;
import fr.faustine.gsbmedecins.modele.PaysDAO;
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

public class PaysController implements Initializable {
    // FXML Variables
    @FXML
    public TableView<Pays> pays_table;

    @FXML
    public TableColumn<Pays, Integer> pays_id;

    @FXML
    public TableColumn<Pays, String> pays_libelle, pays_action;

    @FXML
    private TextField payssearchbar;


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
    private void refreshButtonClicked(ActionEvent event) throws IOException {
        MainController.changerPage("vue/pays-view.fxml", event);
    }

    @FXML
    private void rechercherButtonClicked() {
        pays_table_load(PaysDAO.getByLike(payssearchbar.getText()));
    }

        // Ajout d'un pays
    @FXML
    private void ajouterButtonClicked() {
        // Créer une nouvelle fenêtre
        Stage stage = new Stage();
        Pane scene_window = null;
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fr/faustine/gsbmedecins/vue/ajout-pays-view.fxml"));

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

    private void pays_table_load(ObservableList<Pays> pays_List) {
        pays_table.getItems().clear();
        pays_table.refresh();

        pays_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        pays_libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));

        // Ajout d'un bouton "voir"
        // Create buttons for every rows
        Callback<TableColumn<Pays, String>, TableCell<Pays, String>> cellFactory = (param) -> new TableCell<>() {
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
                        Pays paysClicked = getTableView().getItems().get(getIndex());
                        EditController.setPaysActuel(paysClicked); // Définir le pays sur lequel on a cliqué

                        // Create new stage
                        Stage stage = new Stage();
                        Pane scene_window = null;
                        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fr/faustine/gsbmedecins/vue/voirplus-pays-view.fxml"));

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
        pays_action.setCellFactory(cellFactory);

        table_reload(pays_List);
        pays_table.setPlaceholder(new Label("Pays non trouvé(s)"));
    }

    private void table_reload(ObservableList<Pays> pays_List) {
        pays_table.getItems().clear();
        pays_table.refresh();
        pays_table.getItems().addAll(pays_List);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       pays_table_load(PaysDAO.getAll());
    }
}
