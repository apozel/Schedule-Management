import java.awt.geom.Point2D;
import java.time.LocalDateTime;

/**
 * RendezVous
 */
public class RendezVous {

    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private Point2D lieu;
    private Docteur medecinAffecte;
    private Patient malade;

    RendezVous(LocalDateTime debut, LocalDateTime fin,Patient malade,Docteur doc) {
        this.dateDebut = debut;
        this.dateFin = fin;
        this.lieu = malade.getLieuDeVie();
        this.malade = malade;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }

    public Point2D getLieu() {
        return lieu;
    }

    public void setLieu(Point2D lieu) {
        this.lieu = lieu;
    }

    public Docteur getMedecinAffecte() {
        return medecinAffecte;
    }

    public void setMedecinAffecte(Docteur medecinAffecte) {
        this.medecinAffecte = medecinAffecte;
    }

    public Patient getMalade() {
        return malade;
    }

    public void setMalade(Patient malade) {
        this.malade = malade;
    }

} 