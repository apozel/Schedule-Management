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

@Path("API")
@Produces(MediaType.APPLICATION_JSON)

public class API  {

    @EJB(mappedName = "AlgorithmInterface")
    AlgorithmInterface algo;

    @EJB(mappedName = "CrudPuInterface")
    CrudPuInterface crud;

    @PUT
    @Path("addAppointement/{idDiagnosis}")
    public void addAppointement(@PathParam("idDiagnosis") Long idDiagnosis) {

    }

    @PUT
    @Path("putDoctorRandom")
    public void putDoctorRandom() {
        crud.createDoctor(new RandomBuilder().buildRandomDoctor());
    }

    @PUT
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

        return Response.ok().build();
    }

    @GET
    @Path(" findDoctorById/{id}")
    public Response findDoctorById(@PathParam("id") Long id) {

        return null;

    }

    @GET
    @Path("findDoctorByName/{name}")
    public Response findDoctorByName(@PathParam("name") String name) {

        return null;

    }

    @DELETE
    @Path("suppressDoctor/{id}")
    public Response suppressDoctor(@PathParam("id") Long doctor)  {
        //crud.suppressDoctor(doctor);
        return Response.ok().build();
    }

    @GET
    @Path("findAllPatient")
    public List<Patient> findAllPatient() {
        // TODO Auto-generated method stub
        return null;
    }

    @PUT
    @Path("createPatient")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPatient(Patient newDoc) {
        // TODO Auto-generated method stub
        return null;
    }

    @GET
    @Path("findPatientById/{id}")
    public Response findPatientById(@PathParam("id") Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @GET
    @Path("findPatientByName/{name}")
    public Response findPatientByName(@PathParam("name") String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @DELETE
    @Path("suppressPatient/{id}")
    public Response suppressPatient(@PathParam("id") Long idPatientSuppress) {
        // TODO Auto-generated method stub
        return null;

    }

    @GET
    @Path("findAllAppointement")
    public List<Appointement> findAllAppointement() {
        // TODO Auto-generated method stub
        return null;
    }

    @PUT
    @Path("createAppointement")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAppointement(Appointement newDoc) {
        // TODO Auto-generated method stub
        return null;
    }

    @GET
    @Path("findAppointementById/{id}")
    public Response findAppointementById(@PathParam("id") Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @DELETE
    @Path("suppressAppointement/{id}")
    public Response suppressAppointement(@PathParam("id") Long idSuppressAppointement) {
        // TODO Auto-generated method stub
        return null;

    }

    @GET
    @Path("findAppointementByDoctor/{idDoctor}")
    public List<Appointement> findAppointementByDoctor(@PathParam("id") Long idDoctor) {
        // TODO Auto-generated method stub
        return null;
    }

    @GET
    @Path("findAllDiagnosis")
    public List<Diagnosis> findAllDiagnosis() {
        // TODO Auto-generated method stub
        return null;
    }

    @PUT
    @Path("createDiagnosis")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createDiagnosis(Diagnosis newDoc) {
        // TODO Auto-generated method stub
        return null;
    }

    @GET
    @Path("findDiagnosisById/{id}")
    public Response findDiagnosisById(@PathParam("id") Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @GET
    @Path("findDiagnosisByName/{Patientname}")
    public Response findDiagnosisByName(@PathParam("name") String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @DELETE
    @Path("suppressDiagnosis/{id}")
    public Response suppressDiagnosis(@PathParam("id") Long idSuppressDiagnosis) {
        // TODO Auto-generated method stub
        return null;

    }

    @DELETE
    @Path("suppressPosition/{id}")
    public Response suppressPosition(@PathParam("id") Long idSuppressPosition) {
        // TODO Auto-generated method stub
        return null;

    }

    @PUT
    @Path("createPosition")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPosition(Position newPosition) {
        // TODO Auto-generated method stub
        return null;
    }

    @GET
    @Path("findPositionById/{id}")
    public Response findPositionById(@PathParam("id") Long id) {
        // TODO Auto-generated method stub
        return null;
    }


}