package terrain;

import java.util.ArrayList;

import common.Case;
import fenetre.Affichage;
import monopoly.Player;

public class Parc extends Case {
	private int somme;

	@Override
	public void action(Player joueur, int scoreDe, Affichage sys, ArrayList<Case> terrain) {
		//TODO affichage(parc gratuit)FAIT EN CONSOLE
		sys.print(joueur.getName() + " tombe sur le parc gratuit et gagne " + somme + "€\n");
		joueur.addMoney(somme);
		somme = 0;
	}
	
	public void addMoney(int somme, Affichage sys){
		this.somme += somme;
		//TODO affichage(maj parc)FAIT EN CONSOLE
		sys.print("Il y a " + this.somme + "€ a trouver dans le parc!\n");
	}
	
	@Override
	public void init(){
		somme = 0;
	}

	public Parc(String name) {
		super(name);
		init();
	}
	
	public int getSomme() {
		return somme;
	}
}
