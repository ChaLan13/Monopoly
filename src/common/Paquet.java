package common;

import java.util.ArrayList;
import java.util.Collections;
import java.lang.NullPointerException;
import java.security.InvalidParameterException;

import common.Carte;

public class Paquet{
	private String name;
	private ArrayList<Carte> pile = new ArrayList<Carte>();
	
	//=======================
	//=====Constructeur=====
	//=======================
	
	public Paquet(String name) throws InvalidParameterException{
		if(name == null)
			throw new InvalidParameterException("Paquet constructeur -> name null");
		if(name.equals(""))
			throw new InvalidParameterException("Paquet constructeur -> name vide");
		
		this.name = name;
		pile = new ArrayList<Carte>();
	}
	
	//=============================
	//=====Fonctions speciales=====
	//=============================
	
	public Carte tirer() throws IndexOutOfBoundsException{
		Carte res = pile.get(0);
		pile.remove(0);
		return res;
	}
	
	public void shuffle(){
		Collections.shuffle(pile);
	}
	
	//===================
	//=====Get & Set=====
	//===================
	
	public String getName(){return name;}
	
	public void add(Carte carte) throws NullPointerException{
		if(carte == null)
			throw new NullPointerException();
	
		pile.add(carte);
		carte.setPaquet(this);
	}

	//===========================
	//=====equals & toString=====
	//===========================
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(obj == this)
			return true;
		if(!(obj instanceof Paquet))
			return false;
		
		Paquet o = (Paquet) obj;
		return this.name.equals(o.name)
				&& this.pile.equals(o.pile);
	}

	@Override
	public String toString() {
		return name;
	}
	
	
}