package Marchant.Moteur;

/**
 * 
 * @author Arnaud.VDR
 * @date 5 d�c. 2019
 * @project ISEN_Marchant
 * @package Moteur
 *
 */
public class Crossover {
  
  /**
   * M�lange deux individus, prend la premi�re moiti� du premier individu,
   * puis la deuxi�me moiti� sur second, sauf si le point existe d�j� dans le nouvel individu,
   * alors prend le point du premier individu.
   * @param paths
   * @return Noeud[]
   */
  public Noeud[] geneticCrossover(Noeud[][] paths) {
    int taille = paths[0].length;
    boolean exist = false;
    Noeud[] newPath = new Noeud[taille];
    
    for(int i=0;i<taille;i++) {
      if(i%2 == 0) {
        newPath[i] = paths[0][i];
      } else {
        int cmp = 0;
        for(int j=0;j<taille;j++) {
          if(paths[1][i] != newPath[j]) {
            cmp++;
          } else {
            exist = false;
          }
        }
        if(cmp == taille) {
          exist = true;
        }
        if(exist) {
          newPath[i] = paths[1][i];
        } else {
          newPath[i] = paths[0][i];
        }
      }
    }
    return newPath;
  }
  
  public Noeud[][] CX2(Noeud[] n1,Noeud[] n2) {
    Noeud[] p1 = n1;
    Noeud[] p2 = n2;
    
    Noeud[] o1 = new Noeud[n1.length];
    Noeud[] o2 = new Noeud[n2.length];
    
    int st1 = 0, st2 = 0;
    int ind1 = 0, ind2 = 0;
    
    o1[st1]=p2[st1];
    while(st1<n1.length) {
      ind1 = find(p1,o1[st1]);
      o2[st2] = p2[ind1];
      st1 = st1 + 1;
      ind2 = find(p2,o2[st2]);
      o1[st1] = p1[ind2];
      st2 = st2 + 1;
    }
    Noeud[][] ret = {o1,o2};
    return ret;
  }  
  
  /**
   * trouve l'indice dans le tableau n1 de la valeur de n2
   * @param n1
   * @param n2
   * @return -1 si ERROR
   */
  public int find(Noeud[] n1, Noeud n2) {
    for(int i = 0;i<n1.length;i++) {
      if(n2 == n1[i]) return i;
    }
    return -1;
  }
}
