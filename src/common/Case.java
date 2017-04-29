package common;

import monopoly.Player;

public interface Case{
	abstract void action(Player joueur, int scoreDe);
	public void init();
}