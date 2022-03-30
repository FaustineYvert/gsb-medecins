package fr.faustine.gsbmedecins.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartementDAO extends ConnexionBDD {
    public static ObservableList<Departement> getAll() {
        ObservableList<Departement> departement_List = FXCollections.observableArrayList();

        try {
            ResultSet request = ConnexionBDD.query("SELECT * FROM departement;");

            while(request.next()) {
                departement_List.addAll(
                        new Departement(
                                request.getInt("id"),
                                request.getString("libelle"),
                                request.getInt("pays_id")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return departement_List;
    }
}
