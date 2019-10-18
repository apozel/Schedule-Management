package base;

/**
 * Patient
 */
public class Patient {
  
  private int PatientID;
  private Position LieuRDZ;
  private int Criticité;
  private String Description;

  /**
   * 
   * @param PatientID
   * @param LieuRDZ
   * @param Criticité
   * @param Description
   */
  public Patient(int PatientID,Position LieuRDZ,int Criticité,String Description) {
    this.PatientID = PatientID;
    this.LieuRDZ = LieuRDZ;
    this.Criticité = Criticité;
    this.Description = Description;
  }

  public int getPatientID() {
    return PatientID;
  }

  public void setPatientID(int patientID) {
    PatientID = patientID;
  }

  public Position getLieuRDZ() {
    return LieuRDZ;
  }

  public void setLieuRDZ(Position lieuRDZ) {
    LieuRDZ = lieuRDZ;
  }

  public int getCriticité() {
    return Criticité;
  }

  public void setCriticité(int criticité) {
    Criticité = criticité;
  }

  public String getDescription() {
    return Description;
  }

  public void setDescription(String description) {
    Description = description;
  }
  
}