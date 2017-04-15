package common;

import java.util.ArrayList;
import java.util.Collections;
import java.lang.NullPointerException;

import common.Carte;

public class Paquet{
	private ArrayList<Carte> pile = new ArrayList<Carte>();
	
	public Paquet(){
		pile = new ArrayList<Carte>();
	}
	
	public void add(Carte carte) throws NullPointerException{
		if(carte == null)
			throw new NullPointerException();
	
		pile.add(carte);
	}
	
	public Carte tirer() throws IndexOutOfBoundsException{
		Carte res = pile.get(0);
		pile.remove(0);
		return res;
	}
	
	public void shuffle(){
		Collections.shuffle(pile);
	}
}