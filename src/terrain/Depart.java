package terrain;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import common.Case;
import fenetre.Affichage;
import monopoly.Player;

public class Depart extends Case {

	//========================
	//===== Constructeur =====
	//========================
	public Depart(String name) throws InvalidParameterException {
		super(name);
	}

	//=============================
	//===== Fonction speciale =====
	//=============================
	@Override
	public void action(Player joueur, int scoreDe, Affichage sys, ArrayList<Case> terrain) {
		sys.AfficherDepart(joueur);
		
		//on gagne 2x plus d'argent en tombant pile sur la case départ
		//200 dans jeu, quand on passe de 39 à 0
		//et 200 ici
		joueur.addMoney(200);
	}
	
	//=====================
	//===== Get & Set =====
	//=====================
	
	//aucun, seuls ceux de la classe superieur "Case"

	// =============================
	// ===== equals & toString =====
	// =============================
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj)
				&& obj instanceof Depart;
	}
	//equals et toString de la classe superieure Case
}
