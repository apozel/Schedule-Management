package Common;

import Marchant.Moteur.Noeud;
import Schedule.utilities.Demande;

/**
 * DataAlgorithme
 */
public class DataAlgorithme {

    private Demande demandeCorrespondante;
    private Noeud noeudCorrespondant;

    public DataAlgorithme(int id, Demande dem) {
        this.demandeCorrespondante = dem;
        this.noeudCorrespondant = new Noeud(id, dem.getMalade().getLieuDeVie().getX(),
                dem.getMalade().getLieuDeVie().getY());
    }

    public int getId() {
        return noeudCorrespondant.getId();
    }

    public double getX() {
        return noeudCorrespondant.getX();
    }

    public double getY() {
        return noeudCorrespondant.getY();
    }

    public void setId(int id) {
        noeudCorrespondant.setId(id);
    }

    public void setX(double x) {
        noeudCorrespondant.setX(x);
    }

    public void setY(double y) {
        noeudCorrespondant.setY(y);
    }

    public void setIdXY(int id, double x, double y) {
        noeudCorrespondant.setIdXY(id, x, y);
    }

    public String toString() {
        return noeudCorrespondant.toString();
    }

    public int hashCode() {
        return noeudCorrespondant.hashCode();
    }

    public boolean equals(Object obj) {
        return noeudCorrespondant.equals(obj);
    }

    public Demande getDemandeCorrespondante() {
        return demandeCorrespondante;
    }

    public void setDemandeCorrespondante(Demande demandeCorrespondante) {
        this.demandeCorrespondante = demandeCorrespondante;
    }

    public Noeud getNoeudCorrespondant() {
        return noeudCorrespondant;
    }

    public void setNoeudCorrespondant(Noeud noeudCorrespondant) {
        this.noeudCorrespondant = noeudCorrespondant;
    }

}