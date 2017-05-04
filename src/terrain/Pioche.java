package terrain;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import common.Case;
import common.Paquet;
import fenetre.Affichage;
import common.Carte;
import monopoly.Player;

public class Pioche extends Case {
	private Paquet paquet;
	
	public Pioche(String name, Paquet paquet)throws InvalidParameterException {
		super(name);
		if(paquet == null)
			throw new InvalidParameterException("Pioche constructeur - paquet null");
		this.paquet = paquet;
	}

	@Override
	public void action(Player joueur, int scoreDe, Affichage sys, ArrayList<Case> terrain) {
		Carte carte;
		carte = paquet.tirer();
		if(carte.getTitre().equals(Carte.SORTIE_PRISON_TITRE))
			joueur.addInv(carte);
		else
			//on a tiré la carte, ne pas oublier de la remettre dans le paquet
			paquet.add(carte);
		
		carte.action(joueur, sys, terrain);
	}

	@Override
	public void init() {
		paquet.shuffle();
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Pioche))
			return false;
		return this.toString().equals(obj.toString());
	}
}
