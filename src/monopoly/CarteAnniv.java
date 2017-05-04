package monopoly;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import common.Carte;
import common.Case;
import fenetre.Affichage;

public class CarteAnniv extends Carte {
	private ArrayList<Player> players;
	private int somme;
	
	public CarteAnniv(String titre, String desc, int somme, ArrayList<Player> players) throws InvalidParameterException {
		super(titre, desc);
		if(players == null)
			throw new InvalidParameterException("CarteAnniv constructeur -> players = null");
		if(somme == 0)
			throw new InvalidParameterException("CarteAnniv constructeur -> somme < 0");
			
		
		this.players = players;
		this.somme = somme;
	}

	@Override
	public void action(Player joueur, Affichage sys, ArrayList<Case> terrain) {
		super.action(joueur, sys, terrain);
		int total = 0;
		
		//on enleve 10 � tout le monde
		for(Player e : players){
			e.subMoney(somme);
			total+=somme;
		}
		
		//TODO affichage(carte anniv)FAIT EN CONSOLE
		sys.print(joueur.getName() 
				+ (total > 0? " gagne " : " paye ")
				+ (total-somme) + "�\n");
		
		//puis on ajoute le tout au joueur
		//(le joueur se paye lui meme 10, ce n'est pas grave)
		joueur.addMoney(total);
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof CarteAnniv))
			return false;
		return this.toString().equals(obj.toString());
	}
}
