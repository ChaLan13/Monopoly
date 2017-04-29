package terrain;

import common.Case;
import monopoly.Player;

public class Police implements Case {

	@Override
	public void action(Player joueur, int scoreDe) {
		joueur.tpto(10);

	}

	@Override
	public void init() {}

}
