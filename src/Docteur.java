package base;

/**
 * docteur
 */
public class Docteur {

  private int DocteurID;
  private Position Pos;
  
  /**
   * 
   * @param DocteurID
   * @param Pos
   */
  public Docteur(int DocteurID,Position Pos) {
    this.DocteurID = DocteurID;
    this.Pos = Pos;
  }

  public int getDocteurID() {
    return DocteurID;
  }

  public void setDocteurID(int docteurID) {
    DocteurID = docteurID;
  }

  public Position getPos() {
    return Pos;
  }

  public void setPos(Position pos) {
    Pos = pos;
  }

}