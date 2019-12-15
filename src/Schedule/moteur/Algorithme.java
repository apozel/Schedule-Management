package Schedule.moteur;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import Common.Calcul;
import Common.DataAlgorithme;
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
        List<Docteur> docteurs = avoirLesDocteurDisponible();
        // TODO changer le for en choix du docteur
        for (Docteur docChoisit : docteurs) {
            @SuppressWarnings("unused")
            // TODO changer la fonction pour renvoyer les rendez vous futur de la journee et
            // pas toute la journee
            List<RendezVous> rdvDuJour = avoirLesRendezVousDejaDonnee(jourOuOnRegarde.toLocalDate(), docChoisit);
            // TODO rajouter le depart du docteur dans l'agoritme et l'enlever
            Noeud[] transformationPositionNoeud = new Noeud[rdvDuJour.size() + 1];
            for (int i = 0; i < rdvDuJour.size(); i++) {
                transformationPositionNoeud[i] = new Noeud(i, rdvDuJour.get(i).getMalade().getLieuDeVie());
            }
            transformationPositionNoeud[rdvDuJour.size()] = new Noeud(rdvDuJour.size(),
                    nouvelleDemandeATraiter.getMalade().getLieuDeVie());

            Noeud positionApresAlgo[] = Calcul.l2(transformationPositionNoeud);
            // TODO changer l'heure de debut en fonction de ou commence les modifcation
            LocalTime heureDebut = docChoisit.getHoraires(0);
            for (Noeud positionOrdonner : positionApresAlgo) {
                for (RendezVous rdvDejaPresent : rdvDuJour) {
                    // TODO regler probleme si le meme patient a 2 rendez vous dans la meme journee
                    // avec le docteur
                    if (rdvDejaPresent.equalsPosition(positionOrdonner)) { // psoitif si la position correspond au
                                                                           // rendez vous
                        rdvDejaPresent.setDate(jourOuOnRegarde.toLocalDate());
                        rdvDejaPresent.setHeureDebut(heureDebut);
                        rdvReturn.add(rdvDejaPresent);
                        break;
                    } else { // c'est le cas de la nouvelle demande
                        rdvReturn.add(new RendezVous(jourOuOnRegarde.toLocalDate(), heureDebut, Duration.ofMinutes(45),
                                docChoisit, nouvelleDemandeATraiter.getMalade(), nouvelleDemandeATraiter.getDiag()));
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

    }

    public List<RendezVous> avoirLesRendezVousDejaDonnee(LocalDate dateDemander, Docteur docteurChoisit) {
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