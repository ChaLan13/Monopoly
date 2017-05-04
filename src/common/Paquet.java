package common;

import java.util.ArrayList;
import java.util.Collections;
import java.lang.NullPointerException;
import java.security.InvalidParameterException;

import common.Carte;

public class Paquet{
	private String name;
	private ArrayList<Carte> pile = new ArrayList<Carte>();
	
	public Paquet(String name) throws InvalidParameterException{
		if(name == null)
			throw new InvalidParameterException("Paquet constructeur -> name null");
		if(name.equals(""))
			throw new InvalidParameterException("Paquet constructeur -> name vide");
		
		this.name = name;
		pile = new ArrayList<Carte>();
	}
	
	public String getName(){
		return name;
	}
	
	public void add(Carte carte) throws NullPointerException{
		if(carte == null)
			throw new NullPointerException();
	
		pile.add(carte);
		carte.setPaquet(this);
	}
	
	public Carte tirer() throws IndexOutOfBoundsException{
		Carte res = pile.get(0);
		pile.remove(0);
		return res;
	}
	
	public void shuffle(){
		Collections.shuffle(pile);
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Paquet))
			return false;
		return this.toString().equals(obj.toString());
	}

	@Override
	public String toString() {
		return name;
	}
	
	
}