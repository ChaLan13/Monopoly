package terrain;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import common.Case;
import fenetre.Affichage;
import monopoly.Player;

public class Police extends Case {

	//========================
	//===== Constructeur =====
	//========================
	public Police(String name) throws InvalidParameterException {
		super(name);
	}

	//=============================
	//===== Fonction speciale =====
	//=============================
	@Override
	public void action(Player joueur, int scoreDe, Affichage sys, ArrayList<Case> terrain) {
		sys.print(joueur.getName() + " tombe sur la case Aller en prison!!!\n");
		joueur.tpto(10, sys, terrain);
		joueur.setPrison(3);
	}
	
	//=====================
	//===== Get & Set =====
	//=====================
	
	//aucun, seuls ceux de la classe superieure "Case"
	
	//=============================
	//===== equals & toString =====
	//=============================
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj)
				&& obj instanceof Police;
	}

	//toString de la classe superieure "Case"
}
