package fr.isen.m1.schedule.web.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import fr.isen.m1.schedule.ejbs.implementation.AlgorithmBean;
import fr.isen.m1.schedule.ejbs.implementation.CrudPuBean;
import fr.isen.m1.schedule.random.RandomBuilder;
import fr.isen.m1.schedule.utilities.Appointement;
import fr.isen.m1.schedule.utilities.Diagnosis;
import fr.isen.m1.schedule.utilities.Doctor;
import fr.isen.m1.schedule.utilities.Patient;
import fr.isen.m1.schedule.utilities.Position;
import fr.isen.m1.schedule.utilities.Request;

@Path("API")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
/*
 * GET /entity for getting the entity, GET /entity/<id> for getting a single
 * product by id, POST /entity for creating a new product, PUT /entity/<id> for
 * updating a product by id, PATCH /entity/<id> for partially updating a product
 * by id, DELETE /entity/<id> for deleting a product by id.
 */

public class API {

    @EJB
    AlgorithmBean algo;

    @EJB
    CrudPuBean crud;

    // APPOINTEMENT CRUD :

    @POST
    @Path("addAppointement")
    public Response addAppointement(@QueryParam("x") Long idDiagnosis) {
        ResponseBuilder response = Response.status(Status.ACCEPTED);
        Diagnosis diagnosis = crud.findDiagnosisById(idDiagnosis);
        // on verifie que le diagnostic existe
        if (diagnosis != null) {
            if (crud.findAppointementByDiagnosis(diagnosis) == null) {
                algo.addAppointementSchedule(new Request(diagnosis, diagnosis.getPatientConserne()));
                response = Response.ok(crud.findAppointementByDiagnosis(diagnosis));

            } else {
                response = Response.status(Status.CONFLICT);
            }
        } else {
            response = Response.status(Status.NOT_FOUND);
        }
        return response.build();

    }

    @GET
    @Path("Appointements")
    public Response findAllAppointement() {
        Response response;
        List<Appointement> appointements = crud.findAllAppointement();
        response = (appointements != null) ? Response.ok(appointements).build() : Response.noContent().build();
        return response;
    }

    @POST
    @Path("Appointements")
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
    @Path("Appointements/{id}")
    public Response findAppointementById(@PathParam("id") Long id) {
        Response response;
        Appointement appointement = crud.findAppointementById(id);
        response = (appointement != null) ? Response.ok(appointement).build() : Response.noContent().build();
        return response;
    }

    @DELETE
    @Path("Appointements/{id}")
    public Response suppressAppointement(@PathParam("id") Long id) {
        Response response;
        Appointement appointement = crud.findAppointementById(id);

        response = (appointement != null) ? Response.ok(appointement).build() : Response.noContent().build();
        if (appointement != null) {
            crud.suppressAppointement(appointement);
        }

        return response;
    }

    @GET
    @Path("Appointements/Doctors/{id}")
    public List<Appointement> findAppointementByDoctor(@PathParam("id") Long idDoctor) {

        Doctor doctor = crud.findDoctorById(idDoctor);
        if (doctor != null) {
            return crud.findAppointementByDoctor(doctor);
        }
        return null;
    }

    // DOCTOR CRUD :

    @POST
    @Path("putDoctorRandom")
    public void putDoctorRandom() {
        crud.createDoctor(new RandomBuilder().buildRandomDoctor());
    }

    @POST
    @Path("putPatientRandom")
    public void putPatientRandom() {
        crud.createPatient(new RandomBuilder().buildRandomPatient());
    }

    @GET
    @Path("Doctors")
    public Response findAllDoctor() {
        Response response;
        List<Doctor> doctors = crud.findAllDoctor();
        response = (doctors != null) ? Response.ok(doctors).build() : Response.noContent().build();
        return response;
    }

    @POST
    @Path("Doctors")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createDoctor(Doctor newDoc) {
        crud.createDoctor(newDoc);
        return Response.ok().build();
    }

    @GET
    @Path("Doctors/{id}")
    public Response findDoctorById(@PathParam("id") Long id) {
        Response response;
        Doctor doctor = crud.findDoctorById(id);
        response = (doctor != null) ? Response.ok(doctor).build() : Response.noContent().build();
        return response;
    }

    @GET
    @Path("Doctors/{firstname}/{lastName}")
    public Response findDoctorByName(@PathParam("firstName") String firstName, @PathParam("lastName") String lastName) {
        Response response;
        Doctor doctor = crud.findDoctorByName(lastName, firstName);
        response = (doctor != null) ? Response.ok(doctor).build() : Response.noContent().build();
        return response;

    }

    @DELETE
    @Path("Doctors/{id}")
    public Response suppressDoctor(@PathParam("id") Long id) {
        Response response;
        Doctor doctor = crud.findDoctorById(id);

        response = (doctor != null) ? Response.ok(doctor).build() : Response.noContent().build();
        if (doctor != null) {
            crud.suppressDoctor(doctor);
        }

        return response;

    }

    // PATIENT CRUD :

    @GET
    @Path("Patients")
    public Response findAllPatient() {
        Response response;
        List<Patient> patients = crud.findAllPatient();
        response = (patients != null) ? Response.ok(patients).build() : Response.noContent().build();
        return response;
    }

    @POST
    @Path("Patients")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPatient(Patient patient) {
        return Response.ok(crud.createPatient(patient)).build();
    }

    @GET
    @Path("Patients/{id}")
    public Response findPatientById(@PathParam("id") Long id) {
        Response response;
        Patient patient = crud.findPatientById(id);
        response = (patient != null) ? Response.ok(patient).build() : Response.noContent().build();
        return response;
    }

    @GET
    @Path("Patients/{name}/{firstName}")
    public Response findPatientByName(@PathParam("name") String name, @PathParam("firstName") String firstName) {
        Response response;
        Patient patient = crud.findPatientByName(name);
        response = (patient != null) ? Response.ok(patient).build() : Response.noContent().build();
        return response;
    }

    @DELETE
    @Path("Patients/{id}")
    public Response suppressPatient(@PathParam("id") Long idPatientSuppress) {
        Response response;
        Patient patient = crud.findPatientById(idPatientSuppress);

        response = (patient != null) ? Response.ok(patient).build() : Response.noContent().build();
        if (patient != null) {
            crud.suppressPatient(patient);
        }

        return response;
    }

    // DIAGNOSIS CRUD :

    @GET
    @Path("Diagnosis")
    public Response findAllDiagnosis() {
        Response response;
        List<Diagnosis> diagnosis = crud.findAllDiagnosis();
        response = (diagnosis != null) ? Response.ok(diagnosis).build() : Response.noContent().build();
        return response;
    }

    @POST
    @Path("Diagnosis")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createDiagnosis(Diagnosis diagnosis) {
        return Response.ok(crud.createDiagnosis(diagnosis)).build();
    }

    @GET
    @Path("Diagnosis/{id}")
    public Response findDiagnosisById(@PathParam("id") Long id) {
        Response response;
        Diagnosis diagnosis = crud.findDiagnosisById(id);
        response = (diagnosis != null) ? Response.ok(diagnosis).build() : Response.noContent().build();
        return response;
    }

    @DELETE
    @Path("Diagnosis/{id}")
    public Response suppressDiagnosis(@PathParam("id") Long id) {
        Response response;
        Diagnosis diagnosis = crud.findDiagnosisById(id);

        response = (diagnosis != null) ? Response.ok(diagnosis).build() : Response.noContent().build();
        if (diagnosis != null) {
            crud.suppressDiagnosis(diagnosis);
        }

        return response;

    }

    // POSITIONS CRUD :

    @DELETE
    @Path("Positions/{id}")
    public Response suppressPosition(@PathParam("id") Long id) {
        Response response;
        Position position = crud.findPositionById(id);

        response = (position != null) ? Response.ok(position).build() : Response.noContent().build();
        if (position != null) {
            crud.suppressPosition(position);
        }

        return response;

    }

    @POST
    @Path("Positions")
    public Response createPosition(@QueryParam("x") Double x, @QueryParam("y") Double y) {
        if (x == null || y == null)
            return Response.status(Status.BAD_REQUEST).build();
        return Response.status(Status.CREATED).entity(crud.createPosition(new Position(x, y))).build();
    }

    @PUT
    @Path("Positions")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modifyPosition(Position position) {
        if (position == null )
            return Response.status(Status.BAD_REQUEST).build();
        if (crud.findPositionById(position.getId()) == null)
            return Response.status(Status.NO_CONTENT).build();
        crud.modifyPosition(position);
        return Response.ok(crud.findPositionById(position.getId())).build();
    }

    @GET
    @Path("Positions/{id}")
    public Response findPositionById(@PathParam("id") Long id) {
        Response response;
        Position position = crud.findPositionById(id);
        response = (position != null) ? Response.ok(position).build() : Response.noContent().build();
        return response;
    }

    @GET
    @Path("Positions")
    public Response findAllPosition() {
        Response response;
        List<Position> position = crud.findAllPosition();
        response = (position != null) ? Response.ok(position).build() : Response.noContent().build();
        return response;
    }

}
