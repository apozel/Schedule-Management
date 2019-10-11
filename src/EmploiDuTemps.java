import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * emploiDuTemps
 */
public class EmploiDuTemps {

    private List<RendezVous> RendezVousList = new ArrayList<RendezVous>();
    private Systeme abonnementService;
    private List<Docteur> docteursList = new ArrayList<Docteur>();

    public EmploiDuTemps(Systeme sys) {
        this.abonnementService = sys;
    }

    void ajouterRendezVous(Patient malade, int criticite, Point2D lieu) {
        int choixDoc = 0;
        LocalDateTime heureDeDebut = abonnementService.getDateHeureActuel();
        LocalDateTime heureDeFin = heureDeDebut.plusHours(1);
        RendezVousList.add(new RendezVous(heureDeDebut, heureDeFin, malade, docteursList.get(choixDoc)));
    }

    public void addDocteur(Docteur doc) {
        docteursList.add(doc);
    }

    public RendezVous[] renvoyerRDVjournee(LocalDate jour){
        ArrayList<RendezVous> listetampon = new ArrayList<RendezVous>();
        for (RendezVous var : this.RendezVousList) {
            if (var.getDateDebut().toLocalDate() == jour ) {
                listetampon.add(var);
            }
        }
        RendezVous[] listeretour = new RendezVous[listetampon.size()];
        for (int i = 0; i < listetampon.size(); i++) {
            
        }
    }
}