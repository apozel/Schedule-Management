package Schedule.moteur;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    private List<RendezVous> RendezVousList = new ArrayList<RendezVous>();
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

        System.out.println(" Algorithme : ajouterRendezVous() : j'ai recu la notif");
        List<RendezVous> rdvReturn = new ArrayList<RendezVous>();
        LocalDateTime jourOuOnRegarde = getDateEtHeure();
        List<Docteur> docteurs = avoirLesDocteurDisponible();
        for (Docteur docChoisit : docteurs) {
            @SuppressWarnings("unused")
            List<RendezVous> rdvDuJour = avoirLesRendezVousDejaDonnee(jourOuOnRegarde.toLocalDate(), docChoisit);

            rdvReturn.add(new RendezVous(jourOuOnRegarde.toLocalDate(), docChoisit.getHoraires(0),
                    Duration.ofMinutes(45), docChoisit, nouvelleDemandeATraiter.getPatientConsernee(),
                    nouvelleDemandeATraiter.getDiag()));

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