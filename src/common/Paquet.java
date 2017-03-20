package common;

import common.Carte;

public class Paquet{
	private Carte cartes[];
	private int max = 1;
	private int nbr = 0;
	
	public Paquet(int max){
		if(max < 1){
			system.exit(2);
		}
		this.max = max;
		cartes = new Carte[max];
	};
	
	public boolean add(Carte carte){
		if(nbr < max && carte != null){
			this.cartes[nbr] = carte;
			nbr++;
			return true;
		}
		else
			return false;
	}
	
	public Carte tirer(){
		int tmp = Math.random() * max;
		return cartes[tmp];
	}
}

//A REFAIRE AVEC UNE FIFO
// -> une carte tirée est supprimée du paquets
// dans le main ne pas oublier de les remettre (add)

// fonction melanger