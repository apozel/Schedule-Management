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

    public Point2D getLieu() {
        return lieu;
    }

    public void setLieu(Point2D lieu) {
        this.lieu = lieu;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

}