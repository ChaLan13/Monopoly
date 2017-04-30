package terrain;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import common.Case;
import fenetre.Affichage;
import monopoly.Player;

public class Depart extends Case {

	public Depart(String name) throws InvalidParameterException {
		super(name);
	}

	@Override
	public void action(Player joueur, int scoreDe, Affichage sys, ArrayList<Case> terrain) {
		//TODO affichage(case depart)FAIT EN CONSOLE
		sys.print(joueur.getName() + " tombe sur la case depart.\n");
		//on gagne 2x plus d'argent en tombant pile sur la case départ
		//200 dans jeu, quand on passe de 39 à 0
		//et 200 ici
		joueur.addMoney(200);
	}
	

	@Override
	public void actionCarte(Player joueur, Affichage sys) {
		//TODO affichage(case depart carte)FAIT EN CONSOLE
		sys.print(joueur.getName() + " recois 400€.\n");
		//on gagne 2x plus d'argent en tombant pile sur la case départ
		//200 dans jeu, quand on passe de 39 à 0
		//et 200 ici
		joueur.addMoney(400);
	}
}
