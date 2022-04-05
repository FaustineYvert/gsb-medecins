package fr.faustine.gsbmedecins.modele;

public class Medecin {
    //Variables
    private Integer id;
    private String nom;
    private String prenom;
    private String adresse;
    private String tel;
    private String specialiteComplementaire;
    private Integer departement_id;

    // Constructeur
    public Medecin(Integer id, String nom, String prenom, String adresse, String tel, String specialiteComplementaire, Integer departement_id) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.tel = tel;
        this.specialiteComplementaire = specialiteComplementaire;
        this.departement_id = departement_id;
    }

    // Getter
    public Integer getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getTel() {
        return tel;
    }

    public String getSpecialiteComplementaire() {
        return specialiteComplementaire;
    }

    public Integer getDepartement_id() {
        return departement_id;
    }
}
