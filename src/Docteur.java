import java.awt.geom.Point2D;

/**
 * docteur
 */
public class Docteur {

    private Point2D lieu;
    private String nom, prenom, special;

    public Docteur(String nom, String prenom, String special) {
        this.nom = nom;
        this.prenom = prenom;
        this.special = special;
    }

}