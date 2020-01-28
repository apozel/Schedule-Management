package bigData.Schedule.utilities;

import java.util.UUID;

/**
 * Patient
 */
public class Patient {

    private Position lieuDeVie;
    private String nom, prenom;
    private final String IDPatient = UUID.randomUUID().toString();

    public Patient(Position lieuDeVie, String nom, String prenom) {
        this.lieuDeVie = lieuDeVie;
        this.nom = nom;
        this.prenom = prenom;

    }

    public Position getLieuDeVie() {
        return lieuDeVie;
    }

    public void setLieuDeVie(Position lieuDeVie) {
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((IDPatient == null) ? 0 : IDPatient.hashCode());
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
        Patient other = (Patient) obj;
        if (IDPatient == null) {
            if (other.IDPatient != null)
                return false;
        } else if (!IDPatient.equals(other.IDPatient))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return nom + " " + prenom;
    }

    public String getIDPatient() {
        return IDPatient;
    }

}