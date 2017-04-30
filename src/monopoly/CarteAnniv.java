package monopoly;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import common.Carte;
import common.Case;
import fenetre.Affichage;

public class CarteAnniv extends Carte {
	private ArrayList<Player> players;
	
	public CarteAnniv(String titre, String desc, ArrayList<Player> players) throws InvalidParameterException {
		super(titre, desc);
		if(players == null)
			throw new InvalidParameterException("CarteAnniv constructeur -> players = null");
		
		this.players = players;
	}

	@Override
	public void action(Player joueur, Affichage sys, ArrayList<Case> terrain) {
		super.action(joueur, sys, terrain);
		int somme = 0;
		
		//on enleve 10 à tout le monde
		for(Player e : players){
			e.subMoney(10);
			somme+=10;
		}
		
		//TODO affichage(carte anniv)FAIT EN CONSOLE
		sys.print(joueur.getName() + " gagne " + (somme-10) + "\n");
		
		//puis on ajoute le tout au joueur
		//(le joueur se paye lui meme 10, ce n'est pas grave)
		joueur.addMoney(somme);
	}

}
