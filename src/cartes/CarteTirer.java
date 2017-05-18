package cartes;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import common.Carte;
import common.Case;
import common.Paquet;
import fenetre.Affichage;
import monopoly.Player;

public class CarteTirer extends Carte {
	Paquet autre;

	//========================
	//===== Constructeur =====
	//========================
	public CarteTirer(boolean special, String titre, String desc, Paquet autre) throws InvalidParameterException {
		super(special, titre, desc);
		if(autre == null)
			throw new InvalidParameterException("CarteTirer constructeur -> autre paquet null");
		
		this.autre = autre;
	}
	
	//=============================
	//===== Fonction speciale =====
	//=============================
	
	@Override
	public void action(Player joueur, Affichage sys, ArrayList<Case> terrain) {
		super.action(joueur, sys, terrain);
		
		Carte carte = autre.tirer();
		if(carte.getTitre().equals(Carte.PRISON_TITRE))
			joueur.addInv(carte);
		else
			//on a tiré la carte, ne pas oublier de la remettre dans le paquet
			autre.add(carte);
		
		carte.action(joueur, sys, terrain);
	}
	
	//=====================
	//===== Get & Set =====
	//=====================
	
	//aucun a part ceux de la classe superieure "Carte"
	
	//=============================
	//===== equals & toString =====
	//=============================

	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(obj == this)
			return true;
		if(!(obj instanceof CarteTirer))
			return false;
		CarteTirer o = (CarteTirer) obj;
		return this.getTitre().equals(o.getTitre())
				&& this.getDesc().equals(o.getDesc())
				&& this.isSpecial() == o.isSpecial()
				&& this.autre.equals(o.autre);
	}

	//toString de la classe superieure Carte
}
