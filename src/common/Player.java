package common;

import common.Carte;

public class Player{
	private String nom;
	private int position = 0;
	
	private Carte inv[];//inventaire
	
	public Player(String nom){
		setNom(nom);
	}
	
	public void setNom(String nom){
		if(nom == null || nom == "")//TODO exception
			System.exit(1);
		this.nom = nom;
	}
}