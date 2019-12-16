package Schedule.moteur;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Schedule.utilities.Diagnostic;
import Schedule.utilities.Docteur;
import Schedule.utilities.Patient;
import Schedule.utilities.Position;
import Schedule.utilities.RendezVous;

public class Simulation {
    // cette partie sert a simuler le fonctionnement du reste de la plateforme
    // donc il faut qu'il contienne (simule) toute les fonctions comme la demande de
    // position et celle de l'heure
    // pour pouvoir faire plus tard une simulation ou le docteur pourra ce deplacer
    // en temps reel dans la simulation
    // cest aussi elle qui contient notre partie du projet representer par
    // EmploiDuTemps

    public JunctionInformation schedule = new JunctionInformation(this);
    public List<Docteur> docList = new ArrayList<Docteur>();
    public List<Patient> malades = new ArrayList<Patient>();
    public List<RendezVous> listRDV = new ArrayList<RendezVous>();
    private LocalDateTime DateHeureActuel;

    public Simulation() {

        this.DateHeureActuel = LocalDateTime.now();

        Docteur testDocteur = new Docteur("arthur", "saucisson", "mentoniste", new Position(90, 70));
        Patient testPatient = new Patient(new Position(11, 3), "Rafael", "le clown");
        RendezVous testRendezVous = new RendezVous(DateHeureActuel.toLocalDate(), testDocteur.getHoraires(0).of(10, 0),
                Duration.ofMinutes(45), testDocteur, new Diagnostic(1, "gastro", testPatient));

        this.docList.add(testDocteur);
        this.addPatient(testPatient);
        this.listRDV.add(testRendezVous);

    }

    public void addPatient(Patient nouveauxPatient) {
        this.malades.add(nouveauxPatient);
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

    public Patient getPatientAleatoire() {
        return malades.get(new Random().nextInt(malades.size()));
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

    public List<RendezVous> getListRDV() {
        return listRDV;
    }

    public void setListRDV(List<RendezVous> listRDV) {
        this.listRDV = listRDV;
    }

    public void suppressRendezvousSelonID(RendezVous ref){    
        for (int i = 0; i < listRDV.size(); i++) {
            if (listRDV.get(i).equals(ref)) {
                System.out.println("Simulation : suppressRendezVousSelonID() : supprimer");
                listRDV.remove(i);
            }
        }
    }

    public ArrayList<Position> montrerScheduleSelonDocteur(Docteur docteurChoisit) {
        ArrayList<Position> ListePositionRendezVous = new ArrayList<Position>();
        if (listRDV.size() > 0) {
            for (RendezVous rdv : listRDV) {

                if (rdv.getMedecinAffecte().equals(docteurChoisit)) {
                    System.out.println("simulation : montrerScheduleSelonDocteur() : affichage : " + rdv);
                    ListePositionRendezVous.add(rdv.getLieu());

                }

            }
            if (ListePositionRendezVous.size() > 0) {
                return ListePositionRendezVous;
            }
        }
        return null;
    }

    public String retourStringDesRdvSelonDocteur(Docteur docteurChoisit) {
        String result = " ";
        for (RendezVous rdv : listRDV) {
            if (rdv.getMedecinAffecte().equals(docteurChoisit)) {
                result += rdv.toString() + "\n";
            }
        }
        return result;
    }

    public String retourStringRdvSelonDateDocteur(Docteur docteurChoisit, LocalDate jourChoisit) {
        String result = " ";
        for (RendezVous rdv : listRDV) {
            System.out.println("Simulation : retourStringRdvSelonDateDocteur : rendez vous du jour " + rdv.toString()
                    + "\n la date choisie est : " + jourChoisit.toString());
            if (rdv.getMedecinAffecte().equals(docteurChoisit) && jourChoisit.isEqual(rdv.getDate())) {

                System.out.println("Simulation : retourStringRdvSelonDateDocteur : la date coincide");
                result += rdv.toString() + "\n";

            }
        }
        return result;
    }

    public Patient retourPatientSelonID(String idPatient) {

        for (Patient pat : malades) {
            if (pat.getIDPatient() == idPatient) {
                return pat;
            }
        }
        return null;
    }

    public void nouveauDiagnostic(int crit, String descript, String patientid) {
        schedule.recuperationNouveauxDiagnostic(crit, descript, patientid);
    }

    public void addRdv(List<RendezVous> listeTriee) {
        for (RendezVous rendezVous : listeTriee) {

            System.out.println("simulation : addRdv() : " + rendezVous);
            listRDV.add(rendezVous);
            System.out.println(
                    "simulation : addRdv() : " + retourStringDesRdvSelonDocteur(rendezVous.getMedecinAffecte()));
        }

    }

}