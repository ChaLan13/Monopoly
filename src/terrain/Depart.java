package terrain;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import common.Case;
import fenetre.Affichage;
import monopoly.Player;

public class Depart extends Case {

	//======================
	//=====Constructeur=====
	//======================
	public Depart(String name) throws InvalidParameterException {
		super(name);
	}

	//=============================
	//=====Fonctions speciales=====
	//=============================
	@Override
	public void action(Player joueur, int scoreDe, Affichage sys, ArrayList<Case> terrain) {
		//TODO affichage(case depart)FAIT EN CONSOLE
		sys.print(joueur.getName() + " tombe sur la case depart.\n");
		//on gagne 2x plus d'argent en tombant pile sur la case départ
		//200 dans jeu, quand on passe de 39 à 0
		//et 200 ici
		joueur.addMoney(200);
	}
	
	//===================
	//=====Get & Set=====
	//===================
	
	//aucun, seuls ceux de la classe superieur "Case"

	// ===========================
	// =====equals & toString=====
	// ===========================
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof Depart))
			return false;

		Depart o = (Depart) obj;
		return this.getName().equals(o.getName());
	}
	
	//toString de la classe superieure Case
}
