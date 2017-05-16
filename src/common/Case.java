package common;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import fenetre.Affichage;
import monopoly.Player;

public abstract class Case{
	private String name;
	
	//======================
	//=====Constructeur=====
	//======================
	
	public Case(String name)throws InvalidParameterException {
		setName(name);
	}

	//=============================
	//=====Fonctions speciales=====
	//=============================
	
	//permettera d'initialiser des variables en debut de parties
	public void init(){}
	
	//l'action quand le joueur tombe sur la case
	abstract public void action(Player joueur, int scoreDe, Affichage sys, ArrayList<Case> terrain);
	public void action(Player joueur, int scoreDe, Affichage sys){
		this.action(joueur, scoreDe, sys, null);
	}
	
	//une action speciale, utilisée seulement par les cartes
	//(gare 2x le prix, compagnie jet de de x10)
	public void actionCarte(Player joueur, Affichage sys) {
		this.action(joueur, 0, sys);
	};
	
	//===================
	//=====Get & Set=====
	//===================
	
	private void setName(String name)throws InvalidParameterException{
		if(name == null)
			throw new InvalidParameterException("Propriete.setName() -> name null");
		if(name.equals(""))
			throw new InvalidParameterException("Propriete.setName() -> name vide");
		
		this.name = name;
	}
	
	public String getName(){return name;}
	
	//===========================
	//=====equals & toString=====
	//===========================
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(obj == this)
			return true;
		if(!(obj instanceof Case))
			return false;
		
		Case o = (Case) obj;
		
		return this.name.equals(o.name);
	}

	@Override
	public String toString() {
		return name;
	}
}