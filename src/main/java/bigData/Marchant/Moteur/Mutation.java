package Marchant.Moteur;

import java.util.Random;

/**
 * 
 * @author Arnaud.VDR
 * @date 22 oct. 2019
 * @project ISEN_Marchant
 * @package Moteur
 *
 */
public class Mutation {
  
  /**
   * ----------- Mutation ------------
   * 1. definition nombre de mutation, aleatoire entre 1 et 20 <br/>
   * 2. une mutation est une permutation entre 2 noeuds <br/>
   * 3. repetition de la permutation selon le nbMutation <br/>
   * La mise en place d'une variable buffer est indispensable for whatever reason
   * @param EntreeNoeuds
   * @return Noeud[]
   */
  public Noeud[] geneticMutation(Noeud[] EntreeNoeuds){
//    System.out.println("-------- MUTATION -----------");
    Random r = new Random();
    int nbMutation = 1+r.nextInt(20);
    
    Noeud[] Noeuds = new Noeud[EntreeNoeuds.length];
    for(int i = 0;i<EntreeNoeuds.length;i++) {
      Noeuds[i] = EntreeNoeuds[i];
    }
    
    for(int i = 0;i<nbMutation;i++) {
      Noeud tmpNoeud = new Noeud(0, 0, 0);
      int rand1 = 1+r.nextInt(Noeuds.length-1);
      int rand2 = 1+r.nextInt(Noeuds.length-1);
      tmpNoeud = Noeuds[rand1];
      Noeuds[rand1] = Noeuds[rand2];
      Noeuds[rand2] = tmpNoeud;
    }
    return Noeuds;
  }
}
