package fr.isen.m1.schedule.ejbs.implementation;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import fr.isen.m1.schedule.ejbs.ejbinterface.AlgorithmInterface;
import fr.isen.m1.schedule.marchant.moteur.Calcul;
import fr.isen.m1.schedule.marchant.moteur.Noeud;
import fr.isen.m1.schedule.utilities.Appointement;
import fr.isen.m1.schedule.utilities.Doctor;
import fr.isen.m1.schedule.utilities.Position;
import fr.isen.m1.schedule.utilities.Request;

@Stateless(mappedName = "AlgorithmInterface")
public class AlgorithmBean implements AlgorithmInterface {

    private Doctor docChoisit;
    private LocalDateTime momentOuOnRegarde;
    private List<Appointement> rdvDuJour;

    public void ajouterRendezVous(Request nouvelleDemandeATraiter) {
        // la fonction doit demander quel jour on est, ensuite a partir de cette journee
        // demander les rendez vous de cette meme journee, la fonction regarde ensuite
        // si la journee est complete si c'est le cas alors la fonction demande les
        // rendez vous de la jounee suivante et ainsi de suite jusqu'a tomber sur une
        // journee libre l"algorithme devra donc les trier et renvoyer cette meme
        // journee au junctioninformation

        System.out.println(" Algorithme : ajouterRendezVous() : " + nouvelleDemandeATraiter);
        List<Appointement> rdvReturn = new ArrayList<Appointement>();
        this.momentOuOnRegarde = getDateEtHeure();

        this.docChoisit = choixDocteur();
        checkHoraires();
        // TODO changer l'heure de debut en fonction de ou commence les modifcation
        this.rdvDuJour = avoirLesRendezVousDejaDonnee(momentOuOnRegarde, docChoisit);

        checkSiLaJourneeEstPleine();
        // TODO changer la fonction pour renvoyer les rendez vous futur de la journee et
        // pas toute la journee

        Noeud[] transformationPositionNoeud = new Noeud[rdvDuJour.size() + 2];
        transformationPositionNoeud[0] = new Noeud(0, docChoisit.getLieuDeDepart().getX(),
                docChoisit.getLieuDeDepart().getY());
        for (int i = 0; i < rdvDuJour.size(); i++) {
            Position positionrdv = rdvDuJour.get(i).getMalade().getLieuDeVie();
            transformationPositionNoeud[i + 1] =
                    new Noeud(i + 1, positionrdv.getX(), positionrdv.getY());
        }
        Position positionRequest = nouvelleDemandeATraiter.getMalade().getLieuDeVie();
        transformationPositionNoeud[rdvDuJour.size() + 1] =
                new Noeud(rdvDuJour.size() + 1, positionRequest.getX(), positionRequest.getY());

        Noeud positionApresAlgo[] = Calcul.l2(transformationPositionNoeud);

        for (int i = 1; i < positionApresAlgo.length; i++) {

            checkHoraires();

            if (rdvDuJour.size() != 0) {
                for (Appointement rdvDejaPresent : rdvDuJour) {

                    if (rdvDejaPresent.equalsPosition(positionApresAlgo[i])) { // positif si la
                                                                               // position
                                                                               // correspond au
                        // rendez vous
                        rdvDejaPresent.setDate(momentOuOnRegarde.toLocalDate());
                        rdvDejaPresent.setHeureDebut(momentOuOnRegarde.toLocalTime());
                        rdvReturn.add(rdvDejaPresent);
                        break;
                    } else if (nouvelleDemandeATraiter.getMalade().getLieuDeVie()
                            .equalsPosition(positionApresAlgo[i])) {
                        // c'est le cas de la nouvelle demande
                        rdvReturn.add(new Appointement(momentOuOnRegarde.toLocalDate(),
                                momentOuOnRegarde.toLocalTime(), Duration.ofMinutes(45), docChoisit,
                                nouvelleDemandeATraiter.getMalade(),
                                nouvelleDemandeATraiter.getDiag()));
                        break;
                    }
                }
            } else {
                if (nouvelleDemandeATraiter.getMalade().getLieuDeVie()
                        .equalsPosition(positionApresAlgo[i])) {
                    // c'est le cas de la nouvelle demande
                    rdvReturn.add(new Appointement(momentOuOnRegarde.toLocalDate(),
                            momentOuOnRegarde.toLocalTime(), Duration.ofMinutes(45), docChoisit,
                            nouvelleDemandeATraiter.getMalade(),
                            nouvelleDemandeATraiter.getDiag()));

                }
            }


            momentOuOnRegarde = momentOuOnRegarde.plus(Duration.ofMinutes(45));

        }

        this.renvoyeListeTrieeRendezVousStockage(rdvReturn);
        System.out.println(" Algorithme : ajouterRendezVous() : rendezvous enregistrer");

    }

    public Doctor choixDocteur() {
        List<Doctor> docteurs = avoirLesDocteurDisponible();

        // TODO changer le choix du docteur en fonction de la situation ou de son
        // occupation
        // il faudra un aqlgorithme pour regrouper les rdv geographiquement en fonction
        // du medecin (de sa zone de depart)

        return docteurs.get(0);
    }

    /*
     * donner heure explications cas possibles : ( heure de la demande = jourOuOnRegarde ) : - si il
     * y a des rendez-vous deja donnée : - les rendez vous qui vont apparaitre dans la liste sont
     * ceux apres le jour ou on regarde donc il faut juste donner l'heure du premier rendez vous et
     * si les rendez vous sont tous donnees jusqua la fin du service passé sur la journee suivante -
     * si aucun rendez-vous ai donnée : (fonction pour donner l'heure de plusieur rendez vous) - si
     * l'heure de la demande est entre minuit et l'heure de debut du medecin alors on commence a
     * l'heure du medecin(0) - si l'heure de la demande est apres l'heure de debut du medecin mais
     * avant la pause du midi alors on prend en compte l'heure de la demande avec un delai de 30
     * minutes (pour le deplacement) - si l'heure de la demande est dans la pause du midi alors on
     * choisit le rdv a lheure de la reprise - si l'heure de la demande est apres l'heure de debut
     * d'apres midi du medecin mais avant l'arret du soir alors on prend en compte l'heure de la
     * demande avec un delai de 30 minutes (pour le deplacement) - si l'heure de la demande est
     * apres l'arret de travail du medecin alors la date doit etre changer au lendemain il en est de
     * meme pour la liste des rendez-vous donnée (et on return donnerHeure du jour suivant )
     */
    public void checkHoraires() {

        if (this.momentOuOnRegarde.toLocalTime().isBefore(this.docChoisit.getHoraires(0))) {
            this.momentOuOnRegarde = LocalDateTime.of(momentOuOnRegarde.toLocalDate(),
                    this.docChoisit.getHoraires(0));
        } else if (this.momentOuOnRegarde.toLocalTime().isAfter(this.docChoisit.getHoraires(1))
                && this.momentOuOnRegarde.toLocalTime().isBefore(this.docChoisit.getHoraires(2))) {
            this.momentOuOnRegarde = LocalDateTime.of(momentOuOnRegarde.toLocalDate(),
                    this.docChoisit.getHoraires(2));
        } else if (this.momentOuOnRegarde.toLocalTime().isAfter(this.docChoisit.getHoraires(3))) {
            this.momentOuOnRegarde = LocalDateTime.of(momentOuOnRegarde.toLocalDate().plusDays(1),
                    this.docChoisit.getHoraires(0));
        }

    }

    public void checkSiLaJourneeEstPleine() {
        for (Appointement rendezVous : rdvDuJour) {
            if (rendezVous.getHeureDebut().plus(rendezVous.getDureeConsultation())
                    .isAfter(this.docChoisit.getHoraires(3))) {
                this.momentOuOnRegarde =
                        LocalDateTime.of(momentOuOnRegarde.toLocalDate().plusDays(1),
                                this.docChoisit.getHoraires(0));
                this.rdvDuJour = avoirLesRendezVousDejaDonnee(momentOuOnRegarde, docChoisit);
                checkSiLaJourneeEstPleine();
            }
        }
    }

    public List<Appointement> avoirLesRendezVousDejaDonnee(LocalDateTime dateDemander,
            Doctor docteurChoisit) {
        return AccesStockageInformation.getRendezVousDuJour(dateDemander, docteurChoisit);
    }

    public List<Appointement> rendezvousDejaDonneeAPartirDunMoment(LocalDateTime dateDemander,
            Doctor docteurChoisit) {
        List<Appointement> retour = new ArrayList<Appointement>();
        for (Appointement rendezVous : AccesStockageInformation.getRendezVousDuJour(dateDemander,
                docteurChoisit)) {
            if (rendezVous.getHeureDebut().isAfter(dateDemander.toLocalTime())) {
                retour.add(rendezVous);
            }
        }
        return retour;
    }

    public List<Doctor> avoirLesDocteurDisponible() {
        return AccesStockageInformation.getDocteurDisponible();
    }

    public LocalDateTime getDateEtHeure() {
        return AccesStockageInformation.getDateTime();
    }

    public void renvoyeListeTrieeRendezVousStockage(List<Appointement> listeTriee) {
        System.out.println(
                " Algorithme : renvoyerListeTrieeRendezVousStockage() : notif enregistrement ");
        AccesStockageInformation.comparaisonEtStockageRDV(listeTriee);
    }



}