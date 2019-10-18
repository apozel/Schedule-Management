package base;

/**
 * Patient
 */
public class Patient {
  
  private int PatientID;
  private Position LieuRDZ;
  private int Criticit�;
  private String Description;

  /**
   * 
   * @param PatientID
   * @param LieuRDZ
   * @param Criticit�
   * @param Description
   */
  public Patient(int PatientID,Position LieuRDZ,int Criticit�,String Description) {
    this.PatientID = PatientID;
    this.LieuRDZ = LieuRDZ;
    this.Criticit� = Criticit�;
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

  public int getCriticit�() {
    return Criticit�;
  }

  public void setCriticit�(int criticit�) {
    Criticit� = criticit�;
  }

  public String getDescription() {
    return Description;
  }

  public void setDescription(String description) {
    Description = description;
  }
  
}