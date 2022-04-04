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

    // Récupérer toutes les spécialités
    public static ObservableList<String> getAllLibelle() {
        // Je définis la liste des spécialités
        ObservableList<String> libelle_List = FXCollections.observableArrayList();

        // On va chercher les spécialités
        try {
            ResultSet request = ConnexionBDD.query("SELECT DISTINCT libelle FROM departement WHERE libelle IS NOT NULL;");

            while(request.next()) {
                libelle_List.addAll(
                        request.getString("libelle")
                );
            }
        } catch(SQLException e) { // Dans le cas où il y aurait une erreur
            e.printStackTrace();
        }

        // On retourne la liste
        return libelle_List;
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

    // ajout d'un département
    public static void addDepartement(String libelle, int pays_id) {
        try {
            ConnexionBDD.execute("INSERT INTO departement(libelle, pays_id) VALUES ('" + libelle + "', " + pays_id + ");");
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    // suppression d'un département
    public static void deleteDepartementByID(int id) {
        try {
            ConnexionBDD.execute("DELETE FROM departement WHERE id = " + id + ";");
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static Departement getDepartementByID(int id) {
        Departement departement = null;

        try {
            ResultSet request = ConnexionBDD.query("SELECT * FROM departement WHERE id = " + id + ";");

            if(request.next()) {
                departement = new Departement(
                        request.getInt("id"),
                        request.getString("libelle"),
                        request.getInt("pays_id")
                );
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return departement;
    }

    public static void updateDepartementByID(Integer id, String new_libelle, String new_pays) {
        Pays pays = PaysDAO.getPaysByLibelle(new_pays);

        try {
            ConnexionBDD.execute(
        "UPDATE departement " +
                "SET libelle = '" + new_libelle + "', " +
                "pays_id = " + pays.getId() + " " +
                "WHERE id = " + id + ";"
            );
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<Departement> getByLike(String search) {
        ObservableList<Departement> departement_List = FXCollections.observableArrayList();

        try {
            ResultSet request = ConnexionBDD.query(
            "SELECT * FROM departement AS D " +
                    "JOIN pays P ON D.pays_id = P.id " +
                    "WHERE D.libelle LIKE '%" + search + "%' OR P.libelle LIKE '%" + search + "%';"
            );

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
