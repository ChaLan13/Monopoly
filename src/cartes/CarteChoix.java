package cartes;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import common.Carte;
import common.Case;
import fenetre.Affichage;
import monopoly.Player;

public class CarteChoix extends Carte {
	Carte choix1, choix2;

	//=======================
	//=====Constructeur=====
	//=======================
	
	public CarteChoix(String titre, String desc, Carte choix1, Carte choix2) throws InvalidParameterException {
		super(titre, desc);
		if(choix1 == null)
			throw new InvalidParameterException("CarteChoix constructeur -> choix1 null");
		if(choix2 == null)
			throw new InvalidParameterException("CarteChoix constructeur -> choix2 null");
		
		this.choix1 = choix1;
		this.choix2 = choix2;
	}
	
	//=============================
	//=====Fonctions speciales=====
	//=============================

	@Override
	public void action(Player joueur, Affichage sys, ArrayList<Case> terrain) throws InvalidParameterException {
		super.action(joueur, sys, terrain);
		
		switch(sys.getInt("Choix 1 ou 2?", 1, 2)){
			case 1:
				choix1.action(joueur, sys, terrain);
				break;
			case 2:
				choix2.action(joueur, sys, terrain);
				break;
		}
	}
	
	//===================
	//=====Get & Set=====
	//===================
	
	//aucun, seuls ceux de la classe superieur "Carte"
	
	//===========================
	//=====equals & toString=====
	//===========================
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return true;
		if(obj == this)
			return false;
		if(!(obj instanceof CarteChoix))
			return false;
		
		CarteChoix o = (CarteChoix) obj;
		return this.getTitre().equals(o.getTitre())
				&& this.getDesc().equals(o.getDesc())
				&& this.isSpecial() == o.isSpecial()
				&& this.choix1.equals(o.choix1)
				&& this.choix2.equals(o.choix2);
	}
	
	//toString de la classe superieure "Carte"
}
