package fr.isen.m1.schedule.web.rest;

import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import fr.isen.m1.schedule.ejbs.ejbinterface.AlgorithmInterface;
import fr.isen.m1.schedule.ejbs.ejbinterface.CrudPuInterface;
import fr.isen.m1.schedule.random.RandomBuilder;
import fr.isen.m1.schedule.utilities.Appointement;
import fr.isen.m1.schedule.utilities.Diagnosis;
import fr.isen.m1.schedule.utilities.Doctor;
import fr.isen.m1.schedule.utilities.Patient;
import fr.isen.m1.schedule.utilities.Position;
import fr.isen.m1.schedule.utilities.Request;

@Path("API")
@Produces(MediaType.APPLICATION_JSON)

public class API {

    @EJB
    AlgorithmInterface algo;

    @EJB
    CrudPuInterface crud;

    @PUT
    @Path("addAppointement/{idDiagnosis}")
    public void addAppointement(@PathParam("idDiagnosis") Long idDiagnosis) {
        Diagnosis diagnosis = crud.findDiagnosisById(idDiagnosis);
        if (diagnosis != null)
            algo.addAppointementSchedule(new Request(diagnosis, diagnosis.getPatientConserne()));

    }



    @GET
    @Path("putDoctorRandom")
    public void putDoctorRandom() {
        crud.createDoctor(new RandomBuilder().buildRandomDoctor());
    }

    @GET
    @Path("putPatientRandom")
    public void putPatientRandom() {
        crud.createPatient(new RandomBuilder().buildRandomPatient());
    }

    @GET
    @Path("findAllDoctor")
    public List<Doctor> findAllDoctor() {

        return crud.findAllDoctor();
    }

    @PUT
    @Path("createDoctor")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createDoctor(Doctor newDoc) {
        crud.createDoctor(newDoc);
        return Response.ok().build();
    }

    @GET
    @Path(" findDoctorById/{id}")
    public Response findDoctorById(@PathParam("id") Long id) {
        Response response;
        Doctor doctor = crud.findDoctorById(id);
        response = (doctor != null) ? Response.ok(doctor).build() : Response.noContent().build();
        return response;
    }

    @GET
    @Path("findDoctorByName/{firstname}/{lastName}")
    public Response findDoctorByName(@PathParam("firstName") String firstName,
            @PathParam("lastName") String lastName) {
        Response response;
        Doctor doctor = crud.findDoctorByName(lastName, firstName);
        response = (doctor != null) ? Response.ok(doctor).build() : Response.noContent().build();
        return response;

    }

    @DELETE
    @Path("suppressDoctor/{id}")
    public Response suppressDoctor(@PathParam("id") Long id) {
        Response response;
        Doctor doctor = crud.findDoctorById(id);

        response = (doctor != null) ? Response.ok(doctor).build() : Response.noContent().build();
        if (doctor != null) {
            crud.suppressDoctor(doctor);
        }

        return response;

    }

    @GET
    @Path("findAllPatient")
    public List<Patient> findAllPatient() {

        return crud.findAllPatient();
    }

    @PUT
    @Path("createPatient")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPatient(Patient patient) {
        return Response.ok(crud.createPatient(patient)).build();
    }

    @GET
    @Path("findPatientById/{id}")
    public Response findPatientById(@PathParam("id") Long id) {
        Response response;
        Patient patient = crud.findPatientById(id);
        response = (patient != null) ? Response.ok(patient).build() : Response.noContent().build();
        return response;
    }

    @GET
    @Path("findPatientByName/{name}")
    public Response findPatientByName(@PathParam("name") String name) {
        Response response;
        Patient patient = crud.findPatientByName(name);
        response = (patient != null) ? Response.ok(patient).build() : Response.noContent().build();
        return response;
    }

    @DELETE
    @Path("suppressPatient/{id}")
    public Response suppressPatient(@PathParam("id") Long idPatientSuppress) {
        Response response;
        Patient patient = crud.findPatientById(idPatientSuppress);

        response = (patient != null) ? Response.ok(patient).build() : Response.noContent().build();
        if (patient != null) {
            crud.suppressPatient(patient);
        }

        return response;
    }

    @GET
    @Path("findAllAppointement")
    public List<Appointement> findAllAppointement() {
        return crud.findAllAppointement();
    }

    @GET
    @Path("createRandomAppointement")
    public Response createRandomAppointement() {
        RandomBuilder randombuilder = new RandomBuilder();
        Doctor doctor = randombuilder.buildRandomDoctor();
        doctor = crud.createDoctor(doctor);
        Patient patient = randombuilder.buildRandomPatient();
        patient = crud.createPatient(patient);
        Diagnosis diagnosis = randombuilder.buildRandomDiagnosis();
        diagnosis.setPatientConserne(patient);
        diagnosis = crud.createDiagnosis(diagnosis);
        Appointement appointement = randombuilder.buildRandomAppointement();
        appointement.setMedecinAffecte(doctor);
        appointement.setDiag(diagnosis);
        appointement.setMalade(patient);
        appointement.setLieu(patient.getLieuDeVie());
        Appointement appointement2 = new Appointement();
        appointement2.setMedecinAffecte(doctor);
        appointement2.setDiag(diagnosis);
        appointement2.setMalade(patient);
        appointement2.setLieu(patient.getLieuDeVie());
        appointement2.setDate(appointement.getDate().minusDays(1));
        Appointement appointement3 = new Appointement();
        appointement3.setMedecinAffecte(doctor);
        appointement3.setDiag(diagnosis);
        appointement3.setMalade(patient);
        appointement3.setLieu(patient.getLieuDeVie());
        appointement3.setDate(appointement.getDate().plusDays(1));
        System.out.println("appointementDate  1 : " + appointement.getDate());
        appointement = crud.createAppointement(appointement);
        appointement2 = crud.createAppointement(appointement2);
        appointement3 = crud.createAppointement(appointement3);
        return Response.ok(appointement).build();
    }

    @GET
    @Path("findAppointementById/{id}")
    public Response findAppointementById(@PathParam("id") Long id) {
        Response response;
        Appointement appointement = crud.findAppointementById(id);
        response = (appointement != null) ? Response.ok(appointement).build()
                : Response.noContent().build();
        return response;
    }

    @DELETE
    @Path("suppressAppointement/{id}")
    public Response suppressAppointement(@PathParam("id") Long id) {
        Response response;
        Appointement appointement = crud.findAppointementById(id);

        response = (appointement != null) ? Response.ok(appointement).build()
                : Response.noContent().build();
        if (appointement != null) {
            crud.suppressAppointement(appointement);
        }

        return response;
    }

    @GET
    @Path("findAppointementByDoctor/{idDoctor}")
    public List<Appointement> findAppointementByDoctor(@PathParam("id") Long idDoctor) {

        Doctor doctor = crud.findDoctorById(idDoctor);
        if (doctor != null) {
            return crud.findAppointementByDoctor(doctor);
        }
        return null;
    }

    @GET
    @Path("findAllDiagnosis")
    public List<Diagnosis> findAllDiagnosis() {
        return crud.findAllDiagnosis();
    }

    @PUT
    @Path("createDiagnosis")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createDiagnosis(Diagnosis diagnosis) {
        return Response.ok(crud.createDiagnosis(diagnosis)).build();
    }

    @GET
    @Path("findDiagnosisById/{id}")
    public Response findDiagnosisById(@PathParam("id") Long id) {
        Response response;
        Diagnosis diagnosis = crud.findDiagnosisById(id);
        response =
                (diagnosis != null) ? Response.ok(diagnosis).build() : Response.noContent().build();
        return response;
    }



    @DELETE
    @Path("suppressDiagnosis/{id}")
    public Response suppressDiagnosis(@PathParam("id") Long id) {
        Response response;
        Diagnosis diagnosis = crud.findDiagnosisById(id);

        response =
                (diagnosis != null) ? Response.ok(diagnosis).build() : Response.noContent().build();
        if (diagnosis != null) {
            crud.suppressDiagnosis(diagnosis);
        }

        return response;

    }

    @DELETE
    @Path("suppressPosition/{id}")
    public Response suppressPosition(@PathParam("id") Long id) {
        Response response;
        Position position = crud.findPositionById(id);

        response =
                (position != null) ? Response.ok(position).build() : Response.noContent().build();
        if (position != null) {
            crud.suppressPosition(position);
        }

        return response;

    }

    @PUT
    @Path("createPosition")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPosition(Position newPosition) {
        return Response.ok(crud.createPosition(newPosition)).build();
    }

    @GET
    @Path("findPositionById/{id}")
    public Response findPositionById(@PathParam("id") Long id) {
        Response response;
        Position position = crud.findPositionById(id);
        response =
                (position != null) ? Response.ok(position).build() : Response.noContent().build();
        return response;
    }


}
