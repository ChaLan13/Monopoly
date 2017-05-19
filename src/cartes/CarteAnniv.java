package cartes;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import common.Carte;
import common.Case;
import fenetre.Affichage;
import monopoly.Player;

/**
 * <b>Class Carte Anniv</b>
 * <p>Decrit l'action de la carte anniversaire : tous les joueurs doivent verser 10</p>
 * <p>@author LANUEL Charlotte, SCHWAB Lucas</p>
 * <p>@see Carte</p>
 *
 */

public class CarteAnniv extends Carte {
	private ArrayList<Player> players;
	private int somme;
	
	//========================
	//===== Constructeur =====
	//========================
	/**
	 * <p>Constructor</p>
	 * <p>Build a new CarteAnniv (the only one in the game)</p>
	 * <p>@param titre</p>
	 * <p>@param desc</p>
	 * <p>@param somme</p>
	 * <p>@param players</p>
	 * <p>@throws InvalidParameterException if titre or desc is null</p>
	 */
	public CarteAnniv(String titre, String desc, int somme, ArrayList<Player> players) throws InvalidParameterException {
		super(titre, desc);
		if(players == null)
			throw new InvalidParameterException("CarteAnniv constructeur -> players = null");
		if(somme == 0)
			throw new InvalidParameterException("CarteAnniv constructeur -> somme < 0");
			
		
		this.players = players;
		this.somme = somme;
	}

	//=============================
	//===== Fonction speciale =====
	//=============================
	/**
	 * <p>void action</p>
	 * <p>Defines the action done by the card : when you draw it, every players must pay you 10</p>
	 * <p>@param joueur, sys, terrain</p>
	 * <p>@author LANUEL Charlotte, SCWHAB Lucas</p>
	 * <p>@see Carte</p>
	 */
	@Override
	public void action(Player joueur, Affichage sys, ArrayList<Case> terrain) {
		super.action(joueur, sys, terrain);
		int total = 0;

		//on enleve la somme a tout le monde
		for(Player e : players){
			e.subMoney(somme);
			total+=somme;
		}
		
		//faut enlever le compte du joueur
		total-=somme;
		
		sys.AfficherCarteAnniv(joueur, total);
		
		//puis on ajoute le tout au joueur
		joueur.addMoney(total);
	}
	
	//=====================
	//===== Get & Set =====
	//=====================
	//aucun, seuls ceux de la classe superieure "Carte"
	
	//=============================
	//===== equals & toString =====
	//=============================
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj)
				&& obj instanceof Carte;
	}
	//equals et toString de la classe superieure "Carte"
}
