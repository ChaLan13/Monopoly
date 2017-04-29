package terrain;

import common.Case;
import monopoly.Player;

public class Depart implements Case {

	@Override
	public void action(Player joueur, int scoreDe) {
		//on gagne 2x plus d'argent en tombant pile sur la case départ
		//200 dans jeu, quand on passe de 39 à 0
		//et 200 ici
		joueur.addMoney(200);
	}

	@Override
	public void init() {}

}
