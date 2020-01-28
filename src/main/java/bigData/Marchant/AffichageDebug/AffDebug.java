package Marchant.AffichageDebug;

import Marchant.Moteur.Lien;
import Marchant.Moteur.Noeud;

/**
 * 
 * @author Arnaud.VDR
 * @date 24 oct. 2019
 * @project ISEN_Marchant
 * @package AffichageDebug
 *
 */
public class AffDebug {

  /**
   * Affichage :
   * 1. de la taille du chemin
   * 2. de l'ordre des Noeuds
   * @param Noeuds
   * @param name
   */
  public void affichageTailleCheminEtOrderChemin(Noeud[] Noeuds, String name) {
    Lien l = new Lien();
    System.out.println("taille " + name + " = " + l.tailleChemin(Noeuds));
    for(int i = 0;i<Noeuds.length;i++) {
      System.out.print("."+Noeuds[i].getId());
    }
    System.out.println();
  }
  
  /**
   * affichage ensemble population Noeud[][]
   * @param Noeuds
   * @param name
   */
  public void affichagePopulationTailleCheminEtOrderChemin(Noeud[][] Noeuds, String name) {
    Lien l = new Lien();
    for(int i = 0;i<Noeuds.length;i++) {
      System.out.println("taille " + name +" "+ i + " = " + l.tailleChemin(Noeuds[i]));
      for(int j = 0;j<Noeuds[i].length;j++) {
        System.out.print("."+Noeuds[i][j].getId());
      }
      System.out.println();
    }
    System.out.println();
  }
  
}
