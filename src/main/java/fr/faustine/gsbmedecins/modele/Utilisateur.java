package fr.faustine.gsbmedecins.modele;

public class Utilisateur {
    // Variables
    private Integer id;
    private String nom_utilisateur;
    private String mdp;

    private static Utilisateur utilisateurActuel = null;

    // Constructeur
    public Utilisateur(Integer id, String nom_utilisateur, String mdp) {
        this.id = id;
        this.nom_utilisateur = nom_utilisateur;
        this.mdp = mdp;
    }

    // Getter & Setter
    public Integer getId() {
        return id;
    }

    public String getNom_utilisateur() {
        return nom_utilisateur;
    }

    public String getMdp() {
        return mdp;
    }

    public static void setUtilisateurActuel(Utilisateur utilisateur) {
        Utilisateur.utilisateurActuel = utilisateur;
    }

    public static void disconnectUser() { Utilisateur.utilisateurActuel = null; }

    public static Utilisateur getUtilisateurActuel() {
        return Utilisateur.utilisateurActuel;
    }
}
