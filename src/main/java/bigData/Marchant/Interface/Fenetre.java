package Marchant.Interface;

import javax.swing.JFrame;

import Marchant.Moteur.Noeud;

@SuppressWarnings("serial")
public class Fenetre extends JFrame {
  public Fenetre(Noeud[] listeNoeuds,String fName){
    this.setTitle(fName);
    this.setSize(500, 600);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
    this.setContentPane(new Panneau(listeNoeuds));
    
    this.setVisible(true);
  }
}
