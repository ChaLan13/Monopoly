package terrain;

import java.util.ArrayList;

import common.Case;
import fenetre.Affichage;
import monopoly.Player;

public class Parc extends Case {
	private int somme;

	//========================
	//===== Constructeur =====
	//========================
	public Parc(String name) {
		super(name);
	}
	
	//===============================
	//===== Fonctions Speciales =====
	//===============================
	@Override
	public void init(){
		somme = 0;
	}
	
	@Override
	public void action(Player joueur, int scoreDe, Affichage sys, ArrayList<Case> terrain) {
		//TODO affichage(parc gratuit)FAIT EN CONSOLE
		sys.print(joueur.getName() + " tombe sur le parc gratuit et gagne " + somme + "€\n");
		joueur.addMoney(somme);
		somme = 0;
	}
	
	//=====================
	//===== Get & Set =====
	//=====================
	public void addMoney(int somme, Affichage sys){
		this.somme += somme;
		//TODO affichage(maj parc)FAIT EN CONSOLE
		sys.print("Il y a " + this.somme + "€ a trouver dans le parc!\n");
	}
	
	public int getSomme() {
		return somme;
	}
	
	//=============================
	//===== equals & toString =====
	//=============================
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(obj == this)
			return true;
		if(!(obj instanceof Parc))
			return false;
		Parc o = (Parc) obj;
		return this.getName().equals(o.getName())
				&& this.somme == o.somme;
	}

	@Override
	public String toString() {
		return this.getName() + "(" + somme + "$)";
	}
}
