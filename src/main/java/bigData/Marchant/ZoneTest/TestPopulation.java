package Marchant.ZoneTest;

import java.util.Random;

import Marchant.AffichageDebug.AffDebug;
import Marchant.Moteur.Graphe;
import Marchant.Moteur.Lien;
import Marchant.Moteur.Mutation;
import Marchant.Moteur.Noeud;

public class TestPopulation {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    
    int nb = 10;
    int nbPop = 10;
    Graphe gr = new Graphe();
    AffDebug aff = new AffDebug();
    Mutation mu = new Mutation();
    Lien l = new Lien();
    
    /**
     * initialisation des points
     */
    Noeud[] ListeNoeuds = new Noeud[nb];
    ListeNoeuds = gr.generationNoeud(nb);
    
    /**
     * initialisation de la population
     */
    Noeud[][] Population = new Noeud[nbPop][nb];
    for(int i = 0;i<nbPop;i++) {
      Population[i] = ListeNoeuds = mu.geneticMutation(ListeNoeuds);
    }
    
    long startTime = System.currentTimeMillis();
    aff.affichagePopulationTailleCheminEtOrderChemin(Population, "Population");
    
    for(int iteration=0;iteration<10000;iteration++) {
    
      /**
       * Tri de la population
       */
      int min = 0;
      for(int i=0;i<Population.length;i++) {
        min = i; 
        for(int j=i;j<Population.length;j++) {
          if(l.tailleChemin(Population[j])<l.tailleChemin(Population[min])) {
            min = j;
          }
        }
        if(min!=i) {
          ListeNoeuds = Population[i];
          Population[i] = Population[min];
          Population[min] = ListeNoeuds;
        }
      }
      
      /**
       * Probabilist selection
       */
      double allPopulation = 0;
      for(int i=0;i<Population.length;i++) {
        allPopulation += 1/l.tailleChemin(Population[i]);
      }
      Random rand = new Random();
      double proba = rand.nextDouble()*allPopulation*10000;
      double tmpPop = 0.0;
      double lastTmpPop = 0.0;
      int a = 0;
      for(int i=0;i<Population.length;i++) {
        lastTmpPop = tmpPop;
        tmpPop += 1/l.tailleChemin(Population[i]);
        if(i==0) {
          if(proba >= 0.0 && proba <= tmpPop*10000) {
            a=i;
          }
        } else {
          if(proba > lastTmpPop*10000 && proba <= tmpPop*10000) {
            a=i;
          }
        }
      }
      
      /**
       * mutation de l'individu s�l�ctionn�
       */
      ListeNoeuds = Population[a];
      ListeNoeuds = mu.geneticMutation(ListeNoeuds);
      
      /**
       * Keep if tmp > pop[0], reject else;
       */
      if(l.tailleChemin(ListeNoeuds)<l.tailleChemin(Population[Population.length-1])) {
        Population[Population.length-1] = ListeNoeuds;
      }
    }
    aff.affichagePopulationTailleCheminEtOrderChemin(Population, "Population");
    
    long endTime = System.currentTimeMillis();
    
    System.out.println((endTime-startTime) + " ms");
    
  }

}
