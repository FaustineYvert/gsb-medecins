package fr.faustine.gsbmedecins.controleur.medecin;

import fr.faustine.gsbmedecins.modele.Medecin;

public class EditController {
    // Variable medecin actuel
    private static Medecin medecinActuel = null;

    // Définir le medecin actuel
    public static void setMedecinActuel(Medecin medecin) {
        medecinActuel = medecin;
        }

    // Récupérer le medecin actuel
    public static Medecin getMedecinActuel() {
        return medecinActuel;
    }
}
