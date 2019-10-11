import java.awt.geom.Point2D;

/**
 * Patient
 */
public class Patient {

    private Point2D lieuDeVie;
    private String nom, prenom;
    private Systeme abonnementService;

    public Patient(Point2D lieuDeVie, String nom, String prenom, Systeme abonnementService) {
        this.lieuDeVie = lieuDeVie;
        this.nom = nom;
        this.prenom = prenom;
        this.abonnementService = abonnementService;
    }

    public void demandeDeRDV(){
        this.abonnementService.ajouterNouveauRDV(this);
    }

    public Point2D getLieuDeVie() {
        return lieuDeVie;
    }
 
    public void setLieuDeVie(Point2D lieuDeVie) {
        this.lieuDeVie = lieuDeVie;
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

    
}