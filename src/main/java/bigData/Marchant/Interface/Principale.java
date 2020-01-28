package Marchant.Interface;

import java.util.Random;

import Marchant.AffichageDebug.AffDebug;
import Marchant.Moteur.Chemin;
import Marchant.Moteur.Graphe;
import Marchant.Moteur.Lien;
import Marchant.Moteur.Mutation;
import Marchant.Moteur.Noeud;

public class Principale {

  public static void main(String[] args) {
    int nb = 10;
    Graphe gr = new Graphe();

    Noeud[] ListeNoeuds = new Noeud[nb];
    ListeNoeuds = gr.generationNoeud(nb);
    l1(ListeNoeuds);
    l2(ListeNoeuds);
  }

  /**
   * Ancienne version Pas de population multiple Pas de selection probabiliste Pas
   * de crossover Mutation Fitness Function Keep or Reject
   * 
   * @param ListeNoeuds
   */
  public static void l1(Noeud[] ListeNoeuds) {
    int iterationMutaSelect = 10000;
    Chemin ch = new Chemin();
    AffDebug aff = new AffDebug();

    long startTime = System.currentTimeMillis();
    aff.affichageTailleCheminEtOrderChemin(ListeNoeuds, "origin");

    ListeNoeuds = ch.calculChemin(ListeNoeuds, iterationMutaSelect);

    aff.affichageTailleCheminEtOrderChemin(ListeNoeuds, "final");

    long endTime = System.currentTimeMillis();

    System.out.println((endTime - startTime) + " ms");

    @SuppressWarnings("unused")
    Fenetre fen = new Fenetre(ListeNoeuds, "old version");
  }

  /**
   * Nouvelle version Population multiple Selection probabiliste Pas de crossover
   * Mutation Fitness Function Keep or Reject
   * 
   * @param ListeNoeuds
   */
  public static void l2(Noeud[] ListeNoeuds) {
    int nbPop = 10;
    AffDebug aff = new AffDebug();
    Mutation mu = new Mutation();
    Lien l = new Lien();

    /**
     * initialisation de la population
     */
    Noeud[][] Population = new Noeud[nbPop][ListeNoeuds.length];
    for (int i = 0; i < nbPop; i++) {
      Population[i] = ListeNoeuds = mu.geneticMutation(ListeNoeuds);
    }

    long startTime = System.currentTimeMillis();
    aff.affichagePopulationTailleCheminEtOrderChemin(Population, "Population");

    for (int iteration = 0; iteration < 10000; iteration++) {

      /**
       * Tri de la population
       */
      Noeud[] tmpTri = new Noeud[ListeNoeuds.length];
      int min = 0;
      for (int i = 0; i < Population.length; i++) {
        min = i;
        for (int j = i; j < Population.length; j++) {
          if (l.tailleChemin(Population[j]) < l.tailleChemin(Population[min])) {
            min = j;
          }
        }
        if (min != i) {
          tmpTri = Population[i];
          Population[i] = Population[min];
          Population[min] = tmpTri;
        }
      }

      /**
       * Probabilist selection
       */
      double allPopulation = 0;
      for (int i = 0; i < Population.length; i++) {
        allPopulation += 1 / l.tailleChemin(Population[i]);
      }
      Random rand = new Random();
      double probaA = rand.nextDouble() * allPopulation * 10000;
      double probaB = rand.nextDouble() * allPopulation * 10000;
      double tmpPop = 0.0;
      double lastTmpPop = 0.0;
      int a = 0;
      @SuppressWarnings("unused")
      int b = 0;
      for (int i = 0; i < Population.length; i++) {
        lastTmpPop = tmpPop;
        tmpPop += 1 / l.tailleChemin(Population[i]);
        if (i == 0) {
          if (probaA >= lastTmpPop && probaA <= tmpPop * 10000) {
            a = i;
          }
          if (probaB >= lastTmpPop && probaB <= tmpPop * 10000) {
            b = i;
          }
        } else {
          if (probaA > lastTmpPop * 10000 && probaA <= tmpPop * 10000) {
            a = i;
          }
          if (probaB > lastTmpPop * 10000 && probaB <= tmpPop * 10000) {
            b = i;
          }
        }
      }

      /**
       * Crossover entre les deux individus selectionn�s
       */

      /**
       * mutation de l'individu s�l�ctionn�
       */
      Noeud[] tmpMutaA = new Noeud[ListeNoeuds.length];
      Noeud[] tmpMutaB = new Noeud[ListeNoeuds.length];
      tmpMutaA = Population[a];
      tmpMutaA = mu.geneticMutation(tmpMutaA);
      tmpMutaB = Population[a];
      tmpMutaB = mu.geneticMutation(tmpMutaB);

      /**
       * Keep if tmp > pop[0], reject else;
       */
      if (l.tailleChemin(tmpMutaA) < l.tailleChemin(Population[Population.length - 1])) {
        Population[Population.length - 1] = tmpMutaA;
      }
      if (l.tailleChemin(tmpMutaB) < l.tailleChemin(Population[Population.length - 2])) {
        Population[Population.length - 2] = tmpMutaB;
      }
    }
    aff.affichagePopulationTailleCheminEtOrderChemin(Population, "Population");

    long endTime = System.currentTimeMillis();

    System.out.println((endTime - startTime) + " ms");

    @SuppressWarnings("unused")
    Fenetre fen = new Fenetre(Population[0], "new version");
  }
}
