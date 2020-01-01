package Schedule.moteur;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import Marchant.Moteur.Calcul;
import Marchant.Moteur.Noeud;
import Schedule.utilities.Demande;
import Schedule.utilities.Docteur;
import Schedule.utilities.RendezVous;

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
        LocalDateTime jourOuOnRegarde = getDateEtHeure();

        this.docChoisit = choixDocteur();
        // TODO changer l'heure de debut en fonction de ou commence les modifcation
        LocalTime heureDebut = donnerHeure(jourOuOnRegarde);
        // TODO changer la fonction pour renvoyer les rendez vous futur de la journee et
        // pas toute la journee
        List<RendezVous> rdvDuJour = avoirLesRendezVousDejaDonnee(jourOuOnRegarde, docChoisit);

        Noeud[] transformationPositionNoeud = new Noeud[rdvDuJour.size() + 2];
        transformationPositionNoeud[0] = new Noeud(0, docChoisit.getLieuDeDepart());
        for (int i = 0; i < rdvDuJour.size(); i++) {
            transformationPositionNoeud[i + 1] = new Noeud(i, rdvDuJour.get(i).getMalade().getLieuDeVie());
        }
        transformationPositionNoeud[rdvDuJour.size() + 1] = new Noeud(rdvDuJour.size() + 1,
                nouvelleDemandeATraiter.getMalade().getLieuDeVie());

        Noeud positionApresAlgo[] = Calcul.l2(transformationPositionNoeud);

        for (int i = 1; i < positionApresAlgo.length; i++) {
            for (RendezVous rdvDejaPresent : rdvDuJour) {

                if (rdvDejaPresent.equalsPosition(positionApresAlgo[i])) { // positif si la position correspond au
                    // rendez vous
                    rdvDejaPresent.setDate(jourOuOnRegarde.toLocalDate());
                    rdvDejaPresent.setHeureDebut(heureDebut);
                    rdvReturn.add(rdvDejaPresent);
                    break;
                } else if (nouvelleDemandeATraiter.getMalade().getLieuDeVie().equalsPosition(positionApresAlgo[i])) { // c'est
                                                                                                                      // le
                                                                                                                      // cas
                                                                                                                      // de
                                                                                                                      // la
                                                                                                                      // nouvelle
                                                                                                                      // demande
                    rdvReturn.add(new RendezVous(jourOuOnRegarde.toLocalDate(), heureDebut, Duration.ofMinutes(45),
                            docChoisit, nouvelleDemandeATraiter.getMalade(), nouvelleDemandeATraiter.getDiag()));
                    break;
                }
            }

            // TODO changer le temps en fonction de la duree de la consulatation au dessus
            // pareil pour les heures de pauses
            heureDebut = heureDebut.plus(Duration.ofMinutes(45));
            // TODO si le rdv depasse les horaires du medecin passer a un autre jour

        }

        this.renvoyeListeTrieeRendezVousStockage(rdvReturn);
        System.out.println(" Algorithme : ajouterRendezVous() : rendezvous enregistrer");

    }

    public Docteur choixDocteur() {
        List<Docteur> docteurs = avoirLesDocteurDisponible();

        // TODO changer le choix du docteur en fonction de la situation ou de son
        // occupation

        return docteurs.get(0);
    }

    public LocalTime donnerHeure(LocalDateTime jourChoisit) {
        // TODO changer l'heure de debut en fonction de ou commence les modifcation
        /*
         * if (jourChoisit.toLocalTime().isAfter(docChoisit.getHoraires(0))) { return
         * jourChoisit.toLocalTime(); }
         */
        return docChoisit.getHoraires(0);
    }

    public List<RendezVous> avoirLesRendezVousDejaDonnee(LocalDateTime dateDemander, Docteur docteurChoisit) {
        return AccesStockageInformation.getRendezVousDuJour(dateDemander, docteurChoisit);
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