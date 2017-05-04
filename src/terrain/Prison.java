package terrain;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import common.Case;
import fenetre.Affichage;
import monopoly.Player;

public class Prison extends Case {

	public Prison(String name)throws InvalidParameterException {
		super(name);
	}

	@Override
	public void action(Player joueur, int scoreDe, Affichage sys, ArrayList<Case> terrain) {
		if(joueur.getPrison() == 0)
			sys.print(joueur.getName() + " est en simple visite dans la prison.");
		else
			sys.print(joueur.getName() + " est en prison.");
	}


	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Prison))
			return false;
		return this.toString().equals(obj.toString());
	}
}
