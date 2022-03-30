package fr.faustine.gsbmedecins.modele;

public class Pays {

    // Variables
    private Integer id;
    private String libelle;

    // Constructeur
    public Pays(Integer id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    // Getter
    public Integer getId() {
        return id;
    }

    public String getLibelle() {
        return libelle;
    }
}
