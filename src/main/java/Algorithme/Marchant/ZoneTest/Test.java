package bigData.Marchant.ZoneTest;

import bigData.Marchant.AffichageDebug.AffDebug;
import bigData.Marchant.Moteur.Chemin;
import bigData.Marchant.Moteur.Graphe;

import bigData.Marchant.Moteur.Noeud;

/**
 * 
 * @author Arnaud.VDR
 * @date 22 oct. 2019
 * @project ISEN_Marchant
 * @package ZoneTest
 *
 */
public class Test {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    int nbNoeuds = 10;
    int iterationMutaSelect = 100000;
    Graphe gr = new Graphe();
    Chemin ch = new Chemin();
    AffDebug aff = new AffDebug();

    Noeud[] Noeuds = new Noeud[nbNoeuds];
    Noeuds = gr.generationNoeud(nbNoeuds);

    aff.affichageTailleCheminEtOrderChemin(Noeuds, "origin");

    Noeuds = ch.calculChemin(Noeuds, iterationMutaSelect);

    aff.affichageTailleCheminEtOrderChemin(Noeuds, "final");
  }

}
