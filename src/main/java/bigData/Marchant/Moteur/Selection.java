package Marchant.Moteur;

/**
 * 
 * @author Arnaud.VDR
 * @date 22 oct. 2019
 * @project ISEN_Marchant
 * @package Moteur
 *
 */
public class Selection {

  /**
   * ------------ Selection ------------ <br/>
   * 1. Si la nouvelle liste a une distance plus petite <br/>
   * 2. return NouveauChemin <br/>
   * @param AncienNoeuds
   * @param NouveauNoeuds
   * @return Noeud[]
   */
  public Noeud[] geneticSelection(Noeud[] AncienNoeuds, Noeud[] NouveauNoeuds) {
//    System.out.println("-------- SELECTION -----------");
    Lien l = new Lien();
    double tailleAncienChemin = l.tailleChemin(AncienNoeuds);
    
    double tailleNouveauChemin = l.tailleChemin(NouveauNoeuds);

//    System.out.print("------- passage : ");
    if(tailleNouveauChemin < tailleAncienChemin) {
//      System.out.println("IF");
      return NouveauNoeuds;
    }
//    System.out.println("ELSE");
    return AncienNoeuds;
  }
}
