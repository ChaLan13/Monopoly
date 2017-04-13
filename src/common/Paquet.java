package common;

import java.util.ArrayList;
import java.util.Collections;

import common.Carte;

public class Paquet{
	private ArrayList<Carte> pile;
	
	public Paquet(){
		pile = new ArrayList<Carte>();
	}
	
	public void add(Carte carte){//TODO throws
		if(carte != null){
			pile.add(carte);
		}
		else{
			//TODO exception carte null
		}
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