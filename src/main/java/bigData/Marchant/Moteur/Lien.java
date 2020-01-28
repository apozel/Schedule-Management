package Marchant.Moteur;

/**
 * 
 * @author Arnaud.VDR
 * @date 14 oct. 2019
 * @project Marchant
 * @package Moteur
 *
 */
public class Lien {
  
  /**
   * 
   * @param EntreeNoeuds
   * @return double tailleChemin
   */
  public double tailleChemin(Noeud[] EntreeNoeuds) {
    double tailleChemin = -0.f;
    for(int i = 0;i<EntreeNoeuds.length;i++) {
      if(i==EntreeNoeuds.length-1) {
        tailleChemin+=calculLien(EntreeNoeuds[i], EntreeNoeuds[0]);
      } else {
        tailleChemin+=calculLien(EntreeNoeuds[i], EntreeNoeuds[i+1]);
      }
    }
    return tailleChemin;
  }
  
  /**
   * Calcul distance entre 2 noeuds
   * @param n1
   * @param n2
   * @return
   */
  private double calculLien(Noeud n1,Noeud n2) {
    return Math.sqrt(Math.pow(n1.getX()-n2.getX(), 2)+Math.pow(n1.getY()-n2.getY(), 2));
  }
}
