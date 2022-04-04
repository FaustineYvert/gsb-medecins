package fr.faustine.gsbmedecins.modele;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilisateurDAO extends ConnexionBDD {
    public static Utilisateur checkUtilisateur(String nom_utilisateur, String mot_de_passe) {
        Utilisateur utilisateur = null;

        try {
            ResultSet request = ConnexionBDD.query(
        "SELECT * FROM utilisateur WHERE " +
                "nom_utilisateur = '" + nom_utilisateur + "' AND " +
                "mdp = '" + mot_de_passe + "';"
            );

            if(request.next()) {
                utilisateur = new Utilisateur(
                        request.getInt("id"),
                        request.getString("nom_utilisateur"),
                        request.getString("mdp")
                );
            }

            Utilisateur.setUtilisateurActuel(utilisateur);
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return utilisateur;
    }
}
