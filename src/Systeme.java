import java.util.ArrayList;
import java.util.List;

import java.awt.geom.Point2D;
import java.time.LocalDateTime;

public class Systeme {
    // cette partie sert a simuler le fonctionnement du reste de la plateforme
    // donc il faut qu'il contienne (simule) toute les fonctions comme la demande de
    // position et celle de l'heure
    // pour pouvoir faire plus tard une simulation ou le docteur pourra ce deplacer
    // en temps reel dans la simulation
    //cest aussi elle qui contient notre partie du projet representer par EmploiDuTemps

    public EmploiDuTemps schedule = new EmploiDuTemps();
    public Docteur doc;
    public List<Patient> malades = new ArrayList<Patient>();
    private LocalDateTime DateHeureActuel;

    public Systeme() {

        this.doc = new Docteur("doc", "gros", "mentoniste");
    }

    public void addPatient(Patient nouveauxPatient) {
        this.malades.add(nouveauxPatient);
    }

    public void ajouterNouveauRDV(Patient demandePatient) {
        schedule.ajouterRendezVous(demandePatient, this.donnerCriti(), demandePatient.getLieuDeVie());
    }

    private int donnerCriti() {
        return 1;
    }

}