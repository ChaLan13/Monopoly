package monopoly;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import common.Carte;
import common.Case;
import fenetre.Affichage;
import terrain.Parc;

public class CarteMono extends Carte {
	private int gain;
	private int deplacement;
	private boolean caseCible;
	private Parc parc;

	public CarteMono(boolean special, String titre, String desc, Parc parc, int gain, int deplacement, boolean caseCible) throws InvalidParameterException {
		super(special, titre, desc);
		if(parc == null)
			throw new InvalidParameterException("CarteMono constructeur - parc null");
		
		this.parc = parc;
		this.gain = gain;
		this.deplacement = deplacement;
		this.caseCible = caseCible;
	}
	public CarteMono(String titre, String desc, Parc parc, int deplacement, boolean caseCible) throws InvalidParameterException {
		this(false, titre, desc, parc, 0, deplacement, caseCible);
	}
	public CarteMono(String titre, String desc, Parc parc, int gain) throws InvalidParameterException {
		this(false, titre, desc, parc, gain, 0, false);
	}
	public CarteMono(boolean special, String titre, String desc, Parc parc, int deplacement, boolean caseCible) throws InvalidParameterException {
		this(special, titre, desc, parc, 0, deplacement, caseCible);
	}
	public CarteMono(boolean special, String titre, String desc, Parc parc, int gain) throws InvalidParameterException {
		this(special, titre, desc, parc, gain, 0, false);
	}

	@Override
	public void action(Player joueur, Affichage sys, ArrayList<Case> terrain) throws InvalidParameterException {
		if(this.isntSpecial())
			super.action(joueur, sys, terrain);
		
		if(gain < 0)
			parc.addMoney(-gain, sys);
			
		joueur.addMoney(gain);
		
		if(caseCible){
			joueur.moveto(deplacement, sys, terrain);
			terrain.get(joueur.getPos()).actionCarte(joueur, sys);
		}
		else{
			if(deplacement != 0){
				int tmp = joueur.getPos() + deplacement;
				if(tmp > 39)
					tmp -= 40;
				if(tmp < 0)
					tmp +=40;
			joueur.moveto(tmp, sys, terrain);
			terrain.get(joueur.getPos()).actionCarte(joueur, sys);
			}
		}
	}

}
