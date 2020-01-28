package Marchant.Moteur;

import Schedule.utilities.Position;

/**
 * 
 * @author Arnaud.VDR
 * @date 30 sept. 2019
 * @project Marchant
 * @package Moteur
 *
 */
public class Noeud {
  /**
   * Identifiant du noeud
   */
  private int id;
  /**
   * position en X du noeud
   */
  private double x;
  /**
   * position en Y du noeud
   */
  private double y;

  public Noeud(int id, double x, double y) {
    this.id = id;
    this.x = x;
    this.y = y;
  }

  public Noeud(int id, Position pos){
    this.id = id;
    this.x = pos.getX();
    this.y = pos.getY();
  }

  public int getId() {
    return id;
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setX(double x) {
    this.x = x;
  }

  public void setY(double y) {
    this.y = y;
  }

  public void setIdXY(int id, double x, double y) {
    this.id = id;
    this.x = x;
    this.y = y;
  }

  @Override
  public String toString() {
    return "Noeud [id=" + id + ", x=" + x + ", y=" + y + "]";
  }

}
