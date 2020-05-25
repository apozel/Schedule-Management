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


    /**
     * @return Appointement
     */
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
     * add a date to the builder
     *
     * @param date the date to set
     * @see LocalDate
     * @return AppointementBuilder
     */
    public AppointementBuilder setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    /**
     * add a StartTime to the builder
     *
     * @param startTime the startTime to set
     * @see LocalTime
     * @return AppointementBuilder
     */
    public AppointementBuilder setStartTime(LocalTime startTime) {
        this.startTime = startTime;
        return this;
    }

    /**
     * add a consultTime to the builder
     *
     * @param consultTime the consultTime to set
     * @see Duration
     * @return AppointementBuilder
     */
    public AppointementBuilder setConsultTime(Duration consultTime) {
        this.consultTime = consultTime;
        return this;
    }

    /**
     * add a Position to the builder
     *
     * @param location the location to set
     * @see Position
     * @return AppointementBuilder
     */
    public AppointementBuilder setLocation(Position location) {
        this.location = (this.location != null) ? this.location : location;
        return this;
    }

    /**
     * add a Doctor to the builder
     *
     * @param doctor the doctor to set
     * @see Doctor
     * @return AppointementBuilder
     */
    public AppointementBuilder setDoctor(Doctor doctor) {
        this.doctor = (this.doctor != null) ? this.doctor : doctor;
        return this;
    }

    /**
     * add a Patient to the builder
     *
     * @param patient the patient to set
     * @see Patient
     * @return AppointementBuilder
     */
    public AppointementBuilder setPatient(Patient patient) {
        this.patient = (this.patient != null) ? this.patient : patient;
        this.location = (this.location != null) ? this.location : this.patient.getLieuDeVie();
        return this;
    }

    /**
     * add a Diagnosis to the builder
     *
     * @param diagnosis the diagnosis to set
     * @see Diagnosis
     * @return AppointementBuilder
     */
    public AppointementBuilder setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
        this.patient = (this.patient != null) ? this.patient : diagnosis.getPatientConserne();
        this.location = (this.location != null) ? this.location : this.patient.getLieuDeVie();
        return this;
    }

}