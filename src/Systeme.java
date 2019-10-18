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
    // cest aussi elle qui contient notre partie du projet representer par
    // EmploiDuTemps

    public EmploiDuTemps schedule = new EmploiDuTemps(this);
    public List<Docteur> docList = new ArrayList<Docteur>();
    public List<Patient> malades = new ArrayList<Patient>();
    private LocalDateTime DateHeureActuel;

    public Systeme() {

        this.docList.add(new Docteur("doc", "gros", "mentoniste")); 
        this.addPatient(new Patient(new Point2D.Double(0, 0), "krusty", "le clown", this));
        this.DateHeureActuel = LocalDateTime.now(); 
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

    public EmploiDuTemps getSchedule() {
        return schedule;
    }

    public void setSchedule(EmploiDuTemps schedule) {
        this.schedule = schedule;
    }

    public List<Docteur> getDoc() {
        return docList;
    }

    public void addDoc(Docteur doc) {
        this.docList.add(doc);
    }

    public List<Patient> getMalades() {
        return malades;
    }

    public void setMalades(List<Patient> malades) {
        this.malades = malades;
    }

    public LocalDateTime getDateHeureActuel() {
        return DateHeureActuel;
    }

    public void setDateHeureActuel(LocalDateTime dateHeureActuel) {
        DateHeureActuel = dateHeureActuel;
    }

}