import java.awt.geom.Point2D;

/**
 * docteur
 */
public class Docteur {

    private Point2D lieu;
    private String nom, prenom, special;
    private Position emplacement;
    private Position lieuDeDepart;
     

    public Docteur(String nom, String prenom, String special,Position lieuDeDepart) {
        
        this.nom = nom;
        this.prenom = prenom;
        this.special = special;
        this.lieuDeDepart = lieuDeDepart;
        this.emplacement = lieuDeDepart;
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

    @Override
    public String toString() {
        return "Docteur " + nom + " "+  prenom ;
    }

    public Position getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(Position emplacement) {
        this.emplacement = emplacement;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nom == null) ? 0 : nom.hashCode());
        result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Docteur other = (Docteur) obj;
        if (nom == null) {
            if (other.nom != null)
                return false;
        } else if (!nom.equals(other.nom))
            return false;
        if (prenom == null) {
            if (other.prenom != null)
                return false;
        } else if (!prenom.equals(other.prenom))
            return false;
        return true;
    }

}