package common;

import common.Carte;

public class Player{
	private String nom;
	private int position = 0;
	private boolean perdu = false;
	
	private Carte inv[];//inventaire
	
	public Player(String nom){
		setNom(nom);
	}
	
	public void setNom(String nom){
		if(nom == null || nom == "")
			System.exit(1);
		this.nom = nom;
	}
}