package base;

/**
 * 
 * @author Arnaud.VDR
 * @date 18 oct. 2019
 * @project ISEN_Schedule_Management
 * @package base
 *
 */
public class Position {
  /**
   * X
   */
  private int X;
  /*
   * Y
   */
  private int Y;
  /*
   * Adresse
   */
  private String Adresse;
  /*
   * CodePostale
   */
  private String CodePostale;
  /*
   * Ville
   */
  private String Ville;
  
  /**
   * 
   * @param X
   * @param Y
   */
  public Position(int X, int Y) {
    this.X = X;
    this.Y = Y;
  }
  
  /**
   * 
   * @param Adresse
   * @param CodePostale
   * @param Ville
   */
  public Position(String Adresse, String CodePostale, String Ville) {
    this.Adresse = Adresse;
    this.CodePostale = CodePostale;
    this.Ville = Ville;
  }

  public int getX() {
    return X;
  }

  public void setX(int x) {
    X = x;
  }

  public int getY() {
    return Y;
  }

  public void setY(int y) {
    Y = y;
  }

  public String getAdresse() {
    return Adresse;
  }

  public void setAdresse(String adresse) {
    Adresse = adresse;
  }

  public String getCodePostale() {
    return CodePostale;
  }

  public void setCodePostale(String codePostale) {
    CodePostale = codePostale;
  }

  public String getVille() {
    return Ville;
  }

  public void setVille(String ville) {
    Ville = ville;
  }
}
