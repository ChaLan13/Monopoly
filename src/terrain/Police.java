package terrain;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import common.Case;
import fenetre.Affichage;
import monopoly.Player;

public class Police extends Case {

	public Police(String name) throws InvalidParameterException {
		super(name);
	}

	@Override
	public void action(Player joueur, int scoreDe, Affichage sys, ArrayList<Case> terrain) {
		sys.print(joueur.getName() + " tombe sur la case Aller en prison!!!\n");
		joueur.tpto(10, sys, terrain);
		
	}


	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Police))
			return false;
		return this.toString().equals(obj.toString());
	}
}
