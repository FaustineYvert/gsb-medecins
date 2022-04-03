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

    // Récupérer un departement grâce à son libellé
    public static Departement getDepartementByLibelle(String libelle) {
        // Je définis le departement sur null de base
        Departement departement = null;

        try {
            // Je fais ma requête select
            ResultSet request = ConnexionBDD.query("SELECT * FROM departement WHERE libelle = '" + libelle + "';");

            // Si lors de la requête il trouve quelque chose, alors la variable "departement" ne sera plus égale à "null", mais à l'objet "Departement"
            if(request.next()) {
                departement = new Departement (
                        request.getInt("id"),
                        request.getString("libelle"),
                        request.getInt("pays_id")
                );
            }
        } catch(SQLException e) {
            // Dans le cas où il y a une erreur lors de ma requête SQL, je la fais afficher pour savoir ce que c'est.
            e.printStackTrace();
        }

        // Je retourne alors la variable "departement", qui peut être soit "null" ou un objet selon le libellé recherché.
        return departement;
    }

    public static void addDepartement(String libelle, int pays_id) {
        try {
            ConnexionBDD.execute("INSERT INTO departement(libelle, pays_id) VALUES ('" + libelle + "', " + pays_id + ");");
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
