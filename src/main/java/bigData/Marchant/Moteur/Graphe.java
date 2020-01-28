package Marchant.Moteur;

/**
 * 
 * @author Arnaud.VDR
 * @date 1 oct. 2019
 * @project Marchant
 * @package Moteur
 *
 */
public class Graphe {
  
  /**
   * generation des noeuds
   * @param nb
   * @return listeNoeuds
   */
  public Noeud[] generationNoeud(int nb) {
    Noeud[] Noeuds = new Noeud[nb];
    for(int i = 0;i<nb;i++) {
      Noeuds[i] = new Noeud(i,Math.random()*400,Math.random()*500);
    }
    return Noeuds;
  }
}
