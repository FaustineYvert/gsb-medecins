package fr.faustine.gsbmedecins.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaysDAO extends ConnexionBDD {
    public static ObservableList<Pays> getAll() {
        ObservableList<Pays> pays_List = FXCollections.observableArrayList();

        try {
            ResultSet request = ConnexionBDD.query("SELECT * FROM pays;");

            while(request.next()) {
                pays_List.addAll(
                        new Pays(
                                request.getInt("id"),
                                request.getString("libelle")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pays_List;
    }

    // Récupérer un pays grâce à son libellé
    public static Pays getPaysByLibelle(String libelle) {
        // Je définis le pays sur null de base
        Pays pays = null;

        try {
            // Je fais ma requête select
            ResultSet request = ConnexionBDD.query("SELECT * FROM pays WHERE libelle = '" + libelle + "';");

            // Si lors de la requête il trouve quelque chose, alors la variable "pays" ne sera plus égale à "null", mais à l'objet "Pays"
            if(request.next()) {
                pays = new Pays(
                        request.getInt("id"),
                        request.getString("libelle")
                );
            }
        } catch(SQLException e) {
            // Dans le cas où il y a une erreur lors de ma requête SQL, je la fais afficher pour savoir ce que c'est.
            e.printStackTrace();
        }

        // Je retourne alors la variable "pays", qui peut être soit "null" ou un objet selon le libellé recherché.
        return pays;
    }

    public static void addPays(String libelle) {
        try {
            ConnexionBDD.execute("INSERT INTO pays(libelle) VALUES ('" + libelle + "');");
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getLibelleByID(int id) {
        Pays pays = null;

        try {
            ResultSet request = ConnexionBDD.query("SELECT * FROM pays WHERE id = " + id);

            if(request.next()) {
                pays = new Pays(
                        request.getInt("id"),
                        request.getString("libelle")
                );
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return pays.getLibelle();
    }
}
