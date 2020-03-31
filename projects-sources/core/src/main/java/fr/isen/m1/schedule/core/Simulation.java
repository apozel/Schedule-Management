package fr.isen.m1.schedule.core;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import fr.isen.m1.schedule.utilities.Appointement;
import fr.isen.m1.schedule.utilities.Diagnosis;
import fr.isen.m1.schedule.utilities.Doctor;
import fr.isen.m1.schedule.utilities.Patient;
import fr.isen.m1.schedule.utilities.Position;

public class Simulation {
    // cette partie sert a simuler le fonctionnement du reste de la plateforme
    // donc il faut qu'il contienne (simule) toute les fonctions comme la demande de
    // position et celle de l'heure
    // pour pouvoir faire plus tard une simulation ou le docteur pourra ce deplacer
    // en temps reel dans la simulation
    // cest aussi elle qui contient notre partie du projet representer par
    // EmploiDuTemps

    public JunctionInformation schedule = new JunctionInformationSimulation(this);
    public List<Doctor> docList = new ArrayList<Doctor>();
    public List<Patient> malades = new ArrayList<Patient>();
    public List<Appointement> listRDV = new ArrayList<Appointement>();
    private LocalDateTime DateHeureActuel;

    public Simulation() {

        this.DateHeureActuel = LocalDateTime.now();

        Doctor testDocteur = new Doctor("arthur", "saucisson", "mentoniste", new Position(90, 70));
        Patient testPatient = new Patient(new Position(11, 3), "Rafael", "le clown");
        Appointement testRendezVous = new Appointement(DateHeureActuel.toLocalDate(), LocalTime.of(10, 0),
                Duration.ofMinutes(45), testDocteur, new Diagnosis(1, "gastro", testPatient));

        this.docList.add(testDocteur);
        this.addPatient(testPatient);
        this.listRDV.add(testRendezVous);

    }

    public void addPatient(Patient nouveauxPatient) {
        this.malades.add(nouveauxPatient);
    }

    public List<Doctor> getDoc() {
        return docList;
    }

    public void addDoc(Doctor doc) {
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

    public List<Appointement> getListRDV() {
        return listRDV;
    }

    public void setListRDV(List<Appointement> listRDV) {
        this.listRDV = listRDV;
    }

    public void suppressRendezvousSelonID(Appointement ref) {
        for (int i = 0; i < listRDV.size(); i++) {
            if (listRDV.get(i).equals(ref)) {
                System.out.println("Simulation : suppressRendezVousSelonID() : supprimer");
                listRDV.remove(i);
            }
        }
    }

    public ArrayList<Position> montrerScheduleSelonDocteur(Doctor docteurChoisit) {
        ArrayList<Position> ListePositionRendezVous = new ArrayList<Position>();
        if (listRDV.size() > 0) {
            for (Appointement rdv : listRDV) {

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

    public ArrayList<Position> retourPositionRdvSelonDateDocteur(Doctor docteurChoisit, LocalDate jourChoisit) {
        ArrayList<Position> ListePositionRendezVous = new ArrayList<Position>();
        if (listRDV.size() > 0) {
            for (Appointement rdv : listRDV) {

                if (rdv.getMedecinAffecte().equals(docteurChoisit) && rdv.getDate().equals(jourChoisit)) {
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

    public String retourStringDesRdvSelonDocteur(Doctor docteurChoisit) {
        String result = " ";
        for (Appointement rdv : listRDV) {
            if (rdv.getMedecinAffecte().equals(docteurChoisit)) {
                result += rdv.toString() + "\n";
            }
        }
        return result;
    }

    public String retourStringRdvSelonDateDocteur(Doctor docteurChoisit, LocalDate jourChoisit) {
        String result = " ";
        for (Appointement rdv : listRDV) {
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

    public void addRdv(List<Appointement> listeTriee) {
        for (Appointement rendezVous : listeTriee) {

            System.out.println("simulation : addRdv() : " + rendezVous);
            listRDV.add(rendezVous);
            System.out.println(
                    "simulation : addRdv() : " + retourStringDesRdvSelonDocteur(rendezVous.getMedecinAffecte()));
        }

    }

}