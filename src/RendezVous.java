import java.awt.geom.Point2D;
import java.time.LocalDateTime;

/**
 * RendezVous
 */
public class RendezVous {

    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private Point2D lieu;

    RendezVous(LocalDateTime debut, LocalDateTime fin, Point2D lieu) {
        dateDebut = debut;
        dateFin = fin;
        this.lieu = lieu;
    }

}