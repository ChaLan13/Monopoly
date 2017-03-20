package common;

import common.Carte;

public class Player{
	private String nom;
	//private Pion pion; //genre chapeau, bateau, bleu pour les chevaux
	private int position = 0;
	private boolean perdu = false;
	
	private Cartes inv[];//inventaire
	
	public Player(String nom){
		setNom(nom);
		inventaire = new 
	}
	
	public void setNom(String nom){
		if(nom = NULL || nom = "")
			system.exit(1);
		this.nom = nom;
	}
}