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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

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

    // test fonction :
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

    // APPOINTEMENT CRUD :

    // todo: rajouter une fonction pour avoir les rendez vous de la journee

    @POST
    @Path("Appointements")
    public Response postAppointement(@Context UriInfo ui) {
        MultivaluedMap<String, String> mapQueryParams = ui.getQueryParameters();
        Response response = Response.status(Status.BAD_REQUEST).build();
        if (mapQueryParams.containsKey("random")) {
            return createRandomAppointement();
        } else if (mapQueryParams.containsKey("diagnosisID")) {
            try {
                Diagnosis diagnosis = crud.findDiagnosisById(Long.valueOf(mapQueryParams.getFirst("diagnosisID")));
                if (diagnosis != null) {
                    return addAppointement(diagnosis.getId());
                } else {
                    response = Response.noContent().build();
                }
            } catch (Exception e) {
                response = Response.status(Status.BAD_REQUEST).entity(e).build();
            }
        }
        return response;
    }

    public Response addAppointement(Long idDiagnosis) {
        ResponseBuilder response = Response.status(Status.ACCEPTED);
        Diagnosis diagnosis = crud.findDiagnosisById(idDiagnosis);
        System.out.println("diagnosis add appointement : " + diagnosis);
        // on verifie que le diagnostic existe
        if (diagnosis != null) {
            if (crud.findAppointementByDiagnosis(diagnosis) == null) {
                System.out.println("il y a n'a pas deja de rendezvous");
                algo.addAppointementSchedule(new Request(diagnosis, diagnosis.getPatientConserne()));
                System.out.println(crud.findAppointementByDiagnosis(diagnosis));
                response = Response.ok(crud.findAppointementByDiagnosis(diagnosis));

            } else {
                response = Response.status(Status.CONFLICT);
            }
        } else {
            response = Response.status(Status.NOT_FOUND);
        }
        return response.build();

    }

    public Response createRandomAppointement() {
        RandomBuilder randombuilder = new RandomBuilder();
        Doctor doctor = randombuilder.buildRandomDoctor();
        doctor = crud.createDoctor(doctor);
        Patient patient = randombuilder.buildRandomPatient();
        patient = crud.createPatient(patient);
        Diagnosis diagnosis = randombuilder.buildRandomDiagnosis();
        diagnosis.setPatientConserne(patient);
        diagnosis = crud.createDiagnosis(diagnosis);
        System.out.println(diagnosis);
        System.out.println(crud.findDiagnosisById(diagnosis.getId()));
        return addAppointement(diagnosis.getId());
    }

    @GET
    @Path("/Appointements")
    public Response getAppointements(@Context UriInfo ui) {
        MultivaluedMap<String, String> mapQueryParams = ui.getQueryParameters();

        if (mapQueryParams.containsKey("id")) {
            try {
                Long id = Long.valueOf(mapQueryParams.getFirst("id"));
                return findAppointementById(id);
            } catch (Exception e) {
                return Response.status(Status.BAD_REQUEST).build();
            }

        } else if (mapQueryParams.containsKey("doctorID")) {
            try {
                Long id = Long.valueOf(mapQueryParams.getFirst("doctorID"));
                if (crud.findDoctorById(id) != null) {
                    return Response.ok(findAppointementByDoctor(id)).build();
                }

            } catch (Exception e) {
                return Response.status(Status.BAD_REQUEST).entity(e).build();
            }
        }
        return findAllAppointement();
    }

    public Response findAllAppointement() {
        Response response;
        List<Appointement> appointements = crud.findAllAppointement();
        response = (appointements != null) ? Response.ok(appointements).build() : Response.noContent().build();
        return response;
    }

    public Response findAppointementById(Long id) {
        Response response;
        Appointement appointement = crud.findAppointementById(id);
        response = (appointement != null) ? Response.ok(appointement).build() : Response.noContent().build();
        return response;
    }

    public List<Appointement> findAppointementByDoctor(Long idDoctor) {

        Doctor doctor = crud.findDoctorById(idDoctor);
        if (doctor != null) {
            return crud.findAppointementByDoctor(doctor);
        }
        return null;
    }

    @DELETE
    @Path("Appointements/{id}")
    public Response suppressAppointement(@PathParam("id") Long id) {
        Response response;
        Appointement appointement = crud.findAppointementById(id);

        response = (appointement != null) ? Response.ok(appointement).build() : Response.noContent().build();
        if (appointement != null) {
            try {
                crud.suppressAppointement(appointement);
                response = Response.ok(appointement).build();
            } catch (Exception e) {
                response = Response.status(Status.CONFLICT).entity("l'entité est utiliser par une autre ressource")
                        .build();
            }

        }

        return response;
    }

    // DOCTOR CRUD :

    public Response findAllDoctor() {
        Response response;
        List<Doctor> doctors = crud.findAllDoctor();
        System.out.println("docteurs : " + doctors);
        response = (doctors != null) ? Response.ok(doctors).build() : Response.noContent().build();
        return response;
    }

    @POST
    @Path("Doctors")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createDoctor(Doctor newDoc) {

        return Response.accepted(crud.createDoctor(newDoc)).build();
    }

    @GET
    @Path("/Doctors")
    public Response getDoctor(@Context UriInfo ui) {
        MultivaluedMap<String, String> mapQueryParams = ui.getQueryParameters();

        if (mapQueryParams.getFirst("id") != null) {
            try {
                Long id = Long.valueOf(mapQueryParams.getFirst("id"));
                return findDoctorById(id);
            } catch (Exception e) {
                return Response.status(Status.BAD_REQUEST).build();
            }

        } else if (mapQueryParams.getFirst("firstname") != null || mapQueryParams.getFirst("lastname") != null) {
            if (mapQueryParams.getFirst("firstname") != null && mapQueryParams.getFirst("lastname") != null) {
                String firstname = mapQueryParams.getFirst("firstname");
                String lastname = mapQueryParams.getFirst("lastname");
                return findDoctorByName(firstname, lastname);
            }
            return Response.status(Status.BAD_REQUEST).build();
        }
        return findAllDoctor();
    }

    public Response findDoctorById(Long id) {
        Response response;
        Doctor doctor = crud.findDoctorById(id);
        response = (doctor != null) ? Response.ok(doctor).build() : Response.noContent().build();
        return response;
    }

    public Response findDoctorByName(String firstName, String lastName) {
        Response response;
        System.out.println("firstname : " + firstName + " lastname : " + lastName);
        if (firstName != null && lastName != null) {
            Doctor doctor = crud.findDoctorByName(lastName, firstName);
            response = (doctor != null) ? Response.ok(doctor).build() : Response.noContent().build();
        } else {
            response = Response.status(Status.BAD_REQUEST).build();
        }

        return response;

    }

    @DELETE
    @Path("Doctors/{id}")
    public Response suppressDoctor(@PathParam("id") Long id) {
        Response response;
        Doctor doctor = crud.findDoctorById(id);

        response = (doctor != null) ? Response.ok(doctor).build() : Response.noContent().build();
        if (doctor != null) {
            try {
                crud.suppressDoctor(doctor);
                response = Response.ok(doctor).build();
            } catch (Exception e) {
                response = Response.status(Status.CONFLICT).entity("l'entité est utiliser par une autre ressource")
                        .build();
            }

        }

        return response;

    }

    // PATIENT CRUD :

    @GET
    @Path("/Patients")
    public Response getPatient(@Context UriInfo ui) {
        MultivaluedMap<String, String> mapQueryParams = ui.getQueryParameters();

        if (mapQueryParams.getFirst("id") != null) {
            try {
                Long id = Long.valueOf(mapQueryParams.getFirst("id"));
                return findPatientById(id);
            } catch (Exception e) {
                return Response.status(Status.BAD_REQUEST).build();
            }

        } else if (mapQueryParams.getFirst("firstname") != null || mapQueryParams.getFirst("lastname") != null) {
            if (mapQueryParams.getFirst("firstname") != null && mapQueryParams.getFirst("lastname") != null) {
                String firstname = mapQueryParams.getFirst("firstname");
                String lastname = mapQueryParams.getFirst("lastname");
                return findPatientByName(lastname, firstname);
            }
            return Response.status(Status.BAD_REQUEST).build();
        }
        return findAllPatient();
    }

    public Response findPatientById(Long id) {
        Response response;
        Patient patient = crud.findPatientById(id);
        response = (patient != null) ? Response.ok(patient).build() : Response.noContent().build();
        return response;
    }

    public Response findPatientByName(String lastname, String firstName) {
        Response response;

        Patient patient = crud.findPatientByName(lastname, firstName);
        response = (patient != null) ? Response.ok(patient).build() : Response.noContent().build();
        return response;
    }

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
        return Response.accepted(crud.createPatient(patient)).build();
    }

    @DELETE
    @Path("Patients/{id}")
    public Response suppressPatient(@PathParam("id") Long idPatientSuppress) {
        Response response;
        Patient patient = crud.findPatientById(idPatientSuppress);

        response = (patient != null) ? Response.ok(patient).build() : Response.noContent().build();
        if (patient != null) {
            try {
                crud.suppressPatient(patient);
                response = Response.ok(patient).build();
            } catch (Exception e) {
                response = Response.status(Status.CONFLICT).entity("l'entité est utiliser par une autre ressource")
                        .build();
            }

        }

        return response;
    }

    // DIAGNOSIS CRUD :

    @GET
    @Path("/Diagnosis")
    public Response getDiagnosis(@Context UriInfo ui) {
        MultivaluedMap<String, String> mapQueryParams = ui.getQueryParameters();

        if (mapQueryParams.getFirst("id") != null) {
            try {
                Long id = Long.valueOf(mapQueryParams.getFirst("id"));
                return findDiagnosisById(id);
            } catch (Exception e) {
                return Response.status(Status.BAD_REQUEST).build();
            }
        }
        return findAllDiagnosis();
    }

    public Response findAllDiagnosis() {
        Response response;
        List<Diagnosis> diagnosis = crud.findAllDiagnosis();
        response = (diagnosis != null) ? Response.ok(diagnosis).build() : Response.noContent().build();
        return response;
    }

    public Response findDiagnosisById(Long id) {
        Response response;
        Diagnosis diagnosis = crud.findDiagnosisById(id);
        response = (diagnosis != null) ? Response.ok(diagnosis).build() : Response.noContent().build();
        return response;
    }

    @POST
    @Path("/Diagnosis")
    public Response postDiagnosis(@Context UriInfo ui) {
        MultivaluedMap<String, String> mapQueryParams = ui.getQueryParameters();
        Response response = Response.status(Status.BAD_REQUEST).build();

        if (mapQueryParams.containsKey("random")) {
            RandomBuilder randombuilder = new RandomBuilder();
            Patient patient = randombuilder.buildRandomPatient();
            patient = crud.createPatient(patient);
            Diagnosis diagnosis = randombuilder.buildRandomDiagnosis();
            diagnosis.setPatientConserne(patient);
            diagnosis = crud.createDiagnosis(diagnosis);
            return Response.accepted(diagnosis).build();

        } else if (mapQueryParams.containsKey("criticality") && mapQueryParams.containsKey("patientID")
                && mapQueryParams.containsKey("description")) {
            try {
                Patient patient = crud.findPatientById(Long.valueOf(mapQueryParams.getFirst("patientID")));
                float criticality = Float.parseFloat(mapQueryParams.getFirst("criticality"));
                if (patient != null) {
                    Diagnosis diagnosis = new Diagnosis(criticality, mapQueryParams.getFirst("description"), patient);
                    response = Response.accepted(crud.createDiagnosis(diagnosis)).build();
                }
            } catch (Exception e) {
                response = Response.status(Status.BAD_REQUEST).entity(e).build();
            }
        }
        return response;
    }

    public Response createDiagnosis(Diagnosis diagnosis) {
        Response response;
        try {
            response = Response.ok(crud.createDiagnosis(diagnosis)).build();
        } catch (Exception e) {
            response = Response.notAcceptable(null).build();
        }
        return response;
    }

    @DELETE
    @Path("Diagnosis/{id}")
    public Response suppressDiagnosis(@PathParam("id") Long id) {
        Response response;
        Diagnosis diagnosis = crud.findDiagnosisById(id);

        response = (diagnosis != null) ? Response.ok(diagnosis).build() : Response.noContent().build();
        if (diagnosis != null) {
            try {
                crud.suppressDiagnosis(diagnosis);
                response = Response.ok(diagnosis).build();
            } catch (Exception e) {
                response = Response.status(Status.CONFLICT).entity("l'entité est utiliser par une autre ressource")
                        .build();
            }

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
            try {
                crud.suppressPosition(position);
                response = Response.ok(position).build();
            } catch (Exception e) {
                response = Response.status(Status.CONFLICT).entity("l'entité est utiliser par une autre ressource")
                        .build();
            }

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
        if (position == null)
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
