package common;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import fenetre.Affichage;
import monopoly.Player;

public abstract class Case{
	private String name;
	
	public Case(String name)throws InvalidParameterException {
		setName(name);
	}

	abstract public void action(Player joueur, int scoreDe, Affichage sys, ArrayList<Case> terrain);
	public void action(Player joueur, int scoreDe, Affichage sys){
		this.action(joueur, scoreDe, sys, null);
	}
	
	public void init(){}
	
	public void actionCarte(Player joueur, Affichage sys) {
		this.action(joueur, 0, sys);
	};
	
	private void setName(String name)throws InvalidParameterException{
		if(name == null)
			throw new InvalidParameterException("Propriete.setName() -> name null");
		if(name.equals(""))
			throw new InvalidParameterException("Propriete.setName() -> name vide");
		
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Case))
			return false;
		return this.toString().equals(obj.toString());
	}

	@Override
	public String toString() {
		return name;
	}
}