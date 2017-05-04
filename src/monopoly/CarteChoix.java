package monopoly;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import common.Carte;
import common.Case;
import fenetre.Affichage;

public class CarteChoix extends Carte {
	Carte choix1, choix2;

	public CarteChoix(String titre, String desc, Carte choix1, Carte choix2) throws InvalidParameterException {
		super(titre, desc);
		if(choix1 == null)
			throw new InvalidParameterException("CarteChoix constructeur -> choix1 null");
		if(choix2 == null)
			throw new InvalidParameterException("CarteChoix constructeur -> choix2 null");
		
		this.choix1 = choix1;
		this.choix2 = choix2;
	}

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

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof CarteChoix))
			return false;
		return this.toString().equals(obj.toString());
	}
}
