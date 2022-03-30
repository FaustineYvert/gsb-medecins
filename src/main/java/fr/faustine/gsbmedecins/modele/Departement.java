package fr.faustine.gsbmedecins.modele;

public class Departement {

    // Variables
    private Integer id;
    private String libelle;
    private Integer pays_id;

    // Constructeur
    public Departement(Integer id, String libelle, Integer pays_id) {
        this.id = id;
        this.libelle = libelle;
        this.pays_id = pays_id;
    }

    // Getter
    public Integer getId() {
        return id;
    }

    public String getLibelle() {
        return libelle;
    }

    public Integer getPays_id() {
        return pays_id;
    }
}
