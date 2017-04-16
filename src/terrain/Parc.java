package terrain;

import common.Case;
import monopoly.Player;

public class Parc implements Case {
	private int somme = 0;
	
	
	@Override
	public void action(Player joueur, int scoreDe) {
		joueur.addMoney(somme);
		somme = 0;
	}

}
