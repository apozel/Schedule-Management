package fr.isen.m1.schedule.builder;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import fr.isen.m1.schedule.utilities.Appointement;
import fr.isen.m1.schedule.utilities.Diagnosis;
import fr.isen.m1.schedule.utilities.Doctor;
import fr.isen.m1.schedule.utilities.Patient;
import fr.isen.m1.schedule.utilities.Position;

public class AppointementBuilder {

    private LocalDate date;
    private LocalTime startTime;
    private Duration consultTime;
    private Position location;
    private Doctor doctor;
    private Patient patient;
    private Diagnosis diagnosis;

    public Appointement build() {
        Appointement newAppointement = new Appointement();
        newAppointement.setDate(date);
        newAppointement.setHeureDebut(startTime);
        if (consultTime == null) {
            newAppointement.setDureeConsultation(Duration.ofMinutes(45));
        } else {
            newAppointement.setDureeConsultation(consultTime);
        }

        newAppointement.setLieu(location);
        newAppointement.setMedecinAffecte(doctor);
        newAppointement.setMalade(patient);
        newAppointement.setDiag(diagnosis);
        return newAppointement;
    }

    /**
     * @param date the date to set
     */
    public AppointementBuilder setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    /**
     * @param startTime the startTime to set
     */
    public AppointementBuilder setStartTime(LocalTime startTime) {
        this.startTime = startTime;
        return this;
    }

    /**
     * @param consultTime the consultTime to set
     */
    public AppointementBuilder setConsultTime(Duration consultTime) {
        this.consultTime = consultTime;
        return this;
    }

    /**
     * @param location the location to set
     */
    public AppointementBuilder setLocation(Position location) {
        this.location = (this.location != null) ? this.location : location;
        return this;
    }

    /**
     * @param doctor the doctor to set
     */
    public AppointementBuilder setDoctor(Doctor doctor) {
        this.doctor = (this.doctor != null) ? this.doctor : doctor;
        return this;
    }

    /**
     * @param patient the patient to set
     */
    public AppointementBuilder setPatient(Patient patient) {
        this.patient = (this.patient != null) ? this.patient : patient;
        this.location = (this.location != null) ? this.location : this.patient.getLieuDeVie();
        return this;
    }

    /**
     * @param diagnosis the diagnosis to set
     */
    public AppointementBuilder setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
        this.patient = (this.patient != null) ? this.patient : diagnosis.getPatientConserne();
        this.location = (this.location != null) ? this.location : this.patient.getLieuDeVie();
        return this;
    }

}