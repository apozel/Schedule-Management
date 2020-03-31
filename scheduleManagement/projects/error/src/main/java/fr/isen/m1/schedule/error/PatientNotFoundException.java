package Algorithm.Schedule.error;

/**
 * PatientNotFound
 */
public class PatientNotFoundException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 3361902791368912502L;

    public PatientNotFoundException() {
       System.out.println("le patient desirer n'est pas trouvable dans la base de donnees");
    }



}