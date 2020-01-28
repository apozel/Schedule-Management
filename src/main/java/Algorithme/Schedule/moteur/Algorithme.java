package bigData.Schedule.moteur;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import bigData.Marchant.Moteur.Calcul;
import bigData.Marchant.Moteur.Noeud;
import bigData.Schedule.utilities.Demande;
import bigData.Schedule.utilities.Docteur;
import bigData.Schedule.utilities.RendezVous;

/**
 * emploiDuTemps cette algorithme prend les rendez vous dont il a besoin pour
 * s'executer en fonction de la journee ou il est et il doit renvoyer toute la
 * list des rendez vous qu'il a demander. cette list sera ensuite renvoyer et
 * accestockage pourras ensuite comparer cette liste et faire les ajustement lui
 * meme dans la bdd
 */
public class Algorithme {

    private JunctionInformation AccesStockageInformation;
    private Docteur docChoisit;
    private LocalDateTime momentOuOnRegarde;
    private List<RendezVous> rdvDuJour;

    public Algorithme(JunctionInformation lienAvecBdd) {

        this.AccesStockageInformation = lienAvecBdd;
    }

    // fonction qui va utiliser les autres pour renvoyer la liste trier de rendez
    // vous
    public void ajouterRendezVous(Demande nouvelleDemandeATraiter) {
        // la fonction doit demander quel jour on est, ensuite a partir de cette journee
        // demander les rendez vous de cette meme journee, la fonction regarde ensuite
        // si la journee est complete si c'est le cas alors la fonction demande les
        // rendez vous de la jounee suivante et ainsi de suite jusqu'a tomber sur une
        // journee libre l"algorithme devra donc les trier et renvoyer cette meme
        // journee au junctioninformation

        System.out.println(" Algorithme : ajouterRendezVous() : " + nouvelleDemandeATraiter);
        List<RendezVous> rdvReturn = new ArrayList<RendezVous>();
        this.momentOuOnRegarde = getDateEtHeure();

        this.docChoisit = choixDocteur();
        checkHoraires();
        // TODO changer l'heure de debut en fonction de ou commence les modifcation
        this.rdvDuJour = avoirLesRendezVousDejaDonnee(momentOuOnRegarde, docChoisit);
        
        checkSiLaJourneeEstPleine();
        // TODO changer la fonction pour renvoyer les rendez vous futur de la journee et
        // pas toute la journee

        Noeud[] transformationPositionNoeud = new Noeud[rdvDuJour.size() + 2];
        transformationPositionNoeud[0] = new Noeud(0, docChoisit.getLieuDeDepart());
        for (int i = 0; i < rdvDuJour.size(); i++) {
            transformationPositionNoeud[i + 1] = new Noeud(i + 1, rdvDuJour.get(i).getMalade().getLieuDeVie());
        }
        transformationPositionNoeud[rdvDuJour.size() + 1] = new Noeud(rdvDuJour.size() + 1,
                nouvelleDemandeATraiter.getMalade().getLieuDeVie());

        Noeud positionApresAlgo[] = Calcul.l2(transformationPositionNoeud);

        for (int i = 1; i < positionApresAlgo.length; i++) {

            checkHoraires();

            if (rdvDuJour.size() != 0) {
                for (RendezVous rdvDejaPresent : rdvDuJour) {

                    if (rdvDejaPresent.equalsPosition(positionApresAlgo[i])) { // positif si la position correspond au
                        // rendez vous
                        rdvDejaPresent.setDate(momentOuOnRegarde.toLocalDate());
                        rdvDejaPresent.setHeureDebut(momentOuOnRegarde.toLocalTime());
                        rdvReturn.add(rdvDejaPresent);
                        break;
                    } else if (nouvelleDemandeATraiter.getMalade().getLieuDeVie()
                            .equalsPosition(positionApresAlgo[i])) {
                        // c'est le cas de la nouvelle demande
                        rdvReturn.add(new RendezVous(momentOuOnRegarde.toLocalDate(), momentOuOnRegarde.toLocalTime(),
                                Duration.ofMinutes(45), docChoisit, nouvelleDemandeATraiter.getMalade(),
                                nouvelleDemandeATraiter.getDiag()));
                        break;
                    }
                }
            } else {
                if (nouvelleDemandeATraiter.getMalade().getLieuDeVie().equalsPosition(positionApresAlgo[i])) {
                    // c'est le cas de la nouvelle demande
                    rdvReturn.add(new RendezVous(momentOuOnRegarde.toLocalDate(), momentOuOnRegarde.toLocalTime(),
                            Duration.ofMinutes(45), docChoisit, nouvelleDemandeATraiter.getMalade(),
                            nouvelleDemandeATraiter.getDiag()));
                    
                }
            }
            

            momentOuOnRegarde = momentOuOnRegarde.plus(Duration.ofMinutes(45));

        }

        this.renvoyeListeTrieeRendezVousStockage(rdvReturn);
        System.out.println(" Algorithme : ajouterRendezVous() : rendezvous enregistrer");

    }

    public Docteur choixDocteur() {
        List<Docteur> docteurs = avoirLesDocteurDisponible();

        // TODO changer le choix du docteur en fonction de la situation ou de son
        // occupation
        // il faudra un aqlgorithme pour regrouper les rdv geographiquement en fonction
        // du medecin (de sa zone de depart)

        return docteurs.get(0);
    }

    /*
     * donner heure explications cas possibles : ( heure de la demande =
     * jourOuOnRegarde ) : - si il y a des rendez-vous deja donnée : - les rendez
     * vous qui vont apparaitre dans la liste sont ceux apres le jour ou on regarde
     * donc il faut juste donner l'heure du premier rendez vous et si les rendez
     * vous sont tous donnees jusqua la fin du service passé sur la journee suivante
     * - si aucun rendez-vous ai donnée : (fonction pour donner l'heure de plusieur
     * rendez vous) - si l'heure de la demande est entre minuit et l'heure de debut
     * du medecin alors on commence a l'heure du medecin(0) - si l'heure de la
     * demande est apres l'heure de debut du medecin mais avant la pause du midi
     * alors on prend en compte l'heure de la demande avec un delai de 30 minutes
     * (pour le deplacement) - si l'heure de la demande est dans la pause du midi
     * alors on choisit le rdv a lheure de la reprise - si l'heure de la demande est
     * apres l'heure de debut d'apres midi du medecin mais avant l'arret du soir
     * alors on prend en compte l'heure de la demande avec un delai de 30 minutes
     * (pour le deplacement) - si l'heure de la demande est apres l'arret de travail
     * du medecin alors la date doit etre changer au lendemain il en est de meme
     * pour la liste des rendez-vous donnée (et on return donnerHeure du jour
     * suivant )
     */
    public void checkHoraires() {

        if (this.momentOuOnRegarde.toLocalTime().isBefore(this.docChoisit.getHoraires(0))) {
            this.momentOuOnRegarde = LocalDateTime.of(momentOuOnRegarde.toLocalDate(), this.docChoisit.getHoraires(0));
        } else if (this.momentOuOnRegarde.toLocalTime().isAfter(this.docChoisit.getHoraires(1))
                && this.momentOuOnRegarde.toLocalTime().isBefore(this.docChoisit.getHoraires(2))) {
            this.momentOuOnRegarde = LocalDateTime.of(momentOuOnRegarde.toLocalDate(), this.docChoisit.getHoraires(2));
        } else if (this.momentOuOnRegarde.toLocalTime().isAfter(this.docChoisit.getHoraires(3))) {
            this.momentOuOnRegarde = LocalDateTime.of(momentOuOnRegarde.toLocalDate().plusDays(1),
                    this.docChoisit.getHoraires(0));
        }

    }

    public void checkSiLaJourneeEstPleine() {
        for (RendezVous rendezVous : rdvDuJour) {
            if (rendezVous.getHeureDebut().plus(rendezVous.getDureeConsultation())
                    .isAfter(this.docChoisit.getHoraires(3))) {
                this.momentOuOnRegarde = LocalDateTime.of(momentOuOnRegarde.toLocalDate().plusDays(1),
                        this.docChoisit.getHoraires(0));
                this.rdvDuJour = avoirLesRendezVousDejaDonnee(momentOuOnRegarde, docChoisit);
                checkSiLaJourneeEstPleine();
            }
        }
    }

    public List<RendezVous> avoirLesRendezVousDejaDonnee(LocalDateTime dateDemander, Docteur docteurChoisit) {
        return AccesStockageInformation.getRendezVousDuJour(dateDemander, docteurChoisit);
    }

    public List<RendezVous> rendezvousDejaDonneeAPartirDunMoment(LocalDateTime dateDemander, Docteur docteurChoisit) {
        List<RendezVous> retour = new ArrayList<RendezVous>();
        for (RendezVous rendezVous : AccesStockageInformation.getRendezVousDuJour(dateDemander, docteurChoisit)) {
            if (rendezVous.getHeureDebut().isAfter(dateDemander.toLocalTime())) {
                retour.add(rendezVous);
            }
        }
        return retour;
    }

    public List<Docteur> avoirLesDocteurDisponible() {
        return AccesStockageInformation.getDocteurDisponible();
    }

    public LocalDateTime getDateEtHeure() {
        return AccesStockageInformation.getDateTime();
    }

    public void renvoyeListeTrieeRendezVousStockage(List<RendezVous> listeTriee) {
        System.out.println(" Algorithme : renvoyerListeTrieeRendezVousStockage() : notif enregistrement ");
        AccesStockageInformation.comparaisonEtStockageRDV(listeTriee);
    }
}