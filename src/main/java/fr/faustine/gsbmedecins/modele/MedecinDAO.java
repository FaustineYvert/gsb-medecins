package fr.faustine.gsbmedecins.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MedecinDAO extends ConnexionBDD {
    public static ObservableList<Medecin> getAll() {
        ObservableList<Medecin> medecins_List = FXCollections.observableArrayList();

        try {
            ResultSet request = ConnexionBDD.query("SELECT * FROM medecin;");

            while(request.next()) {
                medecins_List.addAll(
                        new Medecin(
                            request.getInt("id"),
                            request.getString("nom"),
                            request.getString("prenom"),
                            request.getString("adresse"),
                            request.getString("tel"),
                            request.getString("specialiteComplementaire"),
                            request.getInt("departement_id")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return medecins_List;
    }
}
