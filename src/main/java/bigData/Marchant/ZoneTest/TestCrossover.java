package  Marchant.ZoneTest;

import  Marchant.AffichageDebug.AffDebug;
import  Marchant.Moteur.Crossover;
import  Marchant.Moteur.Graphe;
import  Marchant.Moteur.Mutation;
import  Marchant.Moteur.Noeud;

/**
 * 
 * @author Arnaud.VDR
 * @date 5 d�c. 2019
 * @project ISEN_Marchant
 * @package ZoneTest
 *
 */
public class TestCrossover {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    int nbNoeuds = 10;
    Graphe gr = new Graphe();
    AffDebug aff = new AffDebug();
    Crossover cr = new Crossover();
    Mutation mu = new Mutation();
    
    Noeud[] Noeuds = new Noeud[nbNoeuds];
    Noeuds = gr.generationNoeud(nbNoeuds);
    Noeud[][] population = new Noeud[2][nbNoeuds];
    population[0] = Noeuds = mu.geneticMutation(Noeuds);
    population[1] = Noeuds = mu.geneticMutation(Noeuds);
    
    aff.affichageTailleCheminEtOrderChemin(population[0],"pop0");
    aff.affichageTailleCheminEtOrderChemin(population[1],"pop1");
    
    Noeud[][] Noeudss = cr.CX2(population[0],population[1]);
    
    aff.affichageTailleCheminEtOrderChemin(Noeudss[0],"final");
    aff.affichageTailleCheminEtOrderChemin(Noeudss[1],"final");
  }

}
