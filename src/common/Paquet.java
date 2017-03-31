package common;

import java.util.ArrayList;
import java.util.Collections;

import common.Carte;

public class Paquet{
	private ArrayList<Carte> pile;
	private int max = 1;
	private int nbr = 0;
	
	public Paquet(int max){
		if(max < 1){
			System.exit(2);
		}
		this.max = max;
		pile = new ArrayList<Carte>();
	}
	
	public boolean add(Carte carte) throws IndexOutOfBoundsException{
		if(nbr < max && carte != null){
			nbr++;
			return pile.add(carte);
		}
		else
			throw new IndexOutOfBoundsException("Too much object");
	}
	
	public Carte tirer() throws IndexOutOfBoundsException{
		Carte res = pile.get(0);
		pile.remove(0);
		nbr--;
		return res;
	}
	
	public void shuffle(){
		Collections.shuffle(pile);
	}
}