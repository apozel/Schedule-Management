package base;

import java.time.LocalDateTime;

/**
 * RendezVous
 */
public class RendezVous {

    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private Position lieu;
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

    public Position getLieu() {
        return lieu;
    }

    public void setLieu(Position lieu) {
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