package terrain;

import common.Case;
import monopoly.Player;

public class Parc implements Case {
	private int somme;

	@Override
	public void action(Player joueur, int scoreDe) {
		joueur.addMoney(somme);
		somme = 0;
	}
	
	public void addMoney(int somme){
		this.somme += somme;
	}
	
	@Override
	public void init(){
		somme = 0;
	}

	public Parc() {
		super();
		init();
	}
	
	public int getSomme() {
		return somme;
	}
}
