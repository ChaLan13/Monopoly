package terrain;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import common.Case;
import fenetre.Affichage;
import monopoly.Player;

public class Prison extends Case {

	//========================
	//===== Constructeur =====
	//========================
	public Prison(String name)throws InvalidParameterException {
		super(name);
	}

	//=============================
	//===== Fonction Speciale =====
	//=============================
	@Override
	public void action(Player joueur, int scoreDe, Affichage sys, ArrayList<Case> terrain) {
		if(joueur.getPrison() == 0)
			sys.print(joueur.getName() + " est en simple visite dans la prison.");
		else
			sys.print(joueur.getName() + " est en prison.");
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
				&& obj instanceof Prison;
	}

	//toString de la classe superieure "Case"
}
