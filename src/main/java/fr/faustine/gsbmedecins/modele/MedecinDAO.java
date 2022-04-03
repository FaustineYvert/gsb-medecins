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

    // Récupérer toutes les spécialités
    public static ObservableList<String> getAllSpeciality() {
        // Je définis la liste des spécialités
        ObservableList<String> speciality_List = FXCollections.observableArrayList();

        // On va chercher les spécialités
        try {
            ResultSet request = ConnexionBDD.query("SELECT DISTINCT specialiteComplementaire FROM medecin WHERE specialiteComplementaire IS NOT NULL;");

            while(request.next()) {
                speciality_List.addAll(
                        request.getString("specialiteComplementaire")
                );
            }
        } catch(SQLException e) { // Dans le cas où il y aurait une erreur
            e.printStackTrace();
        }

        // On retourne la liste
        return speciality_List;
    }

    // Récupérer un medecin grâce à son nom
    public static Medecin getMedecinByNom(String nom) {
        // Je définis le medecin sur null de base
        Medecin medecin = null;

        try {
            // Je fais ma requête select
            ResultSet request = ConnexionBDD.query("SELECT * FROM medecin WHERE nom = '" + nom + "';");

            // Si lors de la requête il trouve quelque chose, alors la variable "medecin" ne sera plus égale à "null", mais à l'objet "Medecin"
            if(request.next()) {
                medecin = new Medecin(
                        request.getInt("id"),
                        request.getString("nom"),
                        request.getString("prenom"),
                        request.getString("adresse"),
                        request.getString("tel"),
                        request.getString("specialiteComplementaire"),
                        request.getInt("departement_id")
                );
            }
        } catch(SQLException e) {
            // Dans le cas où il y a une erreur lors de ma requête SQL, je la fais afficher pour savoir ce que c'est.
            e.printStackTrace();
        }

        // Je retourne alors la variable "medecin", qui peut être soit "null" ou un objet selon le libellé recherché.
        return medecin;
    }

    public static void addMedecin(String nom, String prenom, String adresse, String tel, String specialiteComplementaire, Integer departement_id) {
        try {
            ConnexionBDD.execute("INSERT INTO medecin (nom, prenom, adresse, tel, specialiteComplementaire, departement_id) VALUES ('" + nom + "', '" + prenom + "', '" + adresse + "', '" + tel + "', '" + specialiteComplementaire + "', '" + departement_id + "');");
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteMedecinByID(int id) {
        try {
            ConnexionBDD.execute("DELETE * FROM medecin WHERE id = " + id + ";");
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getLibelleByID(int id) {
        Medecin medecin = null;

        try {
            ResultSet request = ConnexionBDD.query("SELECT * FROM medecin WHERE id = " + id);

            if(request.next()) {
                medecin = new Medecin(
                        request.getInt("id"),
                        request.getString("nom"),
                        request.getString("prenom"),
                        request.getString("adresse"),
                        request.getString("tel"),
                        request.getString("specialiteComplementaire"),
                        request.getInt("departement_id")
                );
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return medecin.getNom();
    }
}
