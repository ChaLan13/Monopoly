package monopoly;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import common.Carte;
import common.Case;
import common.Paquet;
import fenetre.Affichage;

public class CarteTirer extends Carte {
	Paquet autre;

	public CarteTirer(boolean special, String titre, String desc, Paquet autre) throws InvalidParameterException {
		super(special, titre, desc);
		if(autre == null)
			throw new InvalidParameterException("CarteTirer constructeur -> autre paquet null");
		
		this.autre = autre;
	}
	
	public void add(Paquet autre)throws InvalidParameterException{
		if(autre == null)
			throw new InvalidParameterException("CarteTirer.add() -> autre paquet null");
		this.autre = autre;
	}

	@Override
	public void action(Player joueur, Affichage sys, ArrayList<Case> terrain) {
		if(this.isntSpecial())
			super.action(joueur, sys, terrain);
		Carte carte;
		carte = autre.tirer();
		if(carte.getTitre().equals("Sortie de prison"))
			joueur.addInv(carte);
		else
			//on a tiré la carte, ne pas oublier de la remettre dans le paquet
			autre.add(carte);
		
		carte.action(joueur, sys, terrain);
	}

}
