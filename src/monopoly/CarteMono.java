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
			if(getTitre().equals(Carte.PRISON_TITRE)){
				joueur.tpto(deplacement, sys, terrain);
				joueur.setPrison(3);
			}
			else{
				int dpm = 0;
				if(this.getTitre().equals(Carte.GARE_PLUS_PROCHE_TITRE)){
					int tmp = joueur.getPos();
					if(tmp < 5)
						dpm = 5;
					else if(tmp < 15)
						dpm = 15;
					else if(tmp < 25)
						dpm = 25;
					else if(tmp < 35)
						dpm = 35;
					else
						dpm = 5;
				}
				else if(this.getTitre().equals(Carte.COMPAGNIE_PLUS_PROCHE_TITRE)){
					int tmp = joueur.getPos();
					if(tmp < 28 && tmp >= 12)//aller a la compagnie d'eau
						dpm = 28;
					else//aller a la compagnie elec
						dpm = 12;
				}
				else{
					dpm = this.deplacement;
				}
				//si il doit completer un tour pour arriver sur la case
				if(joueur.getPos() > dpm)
					joueur.addMoney(200);
				joueur.moveto(dpm, sys, terrain);
				terrain.get(joueur.getPos()).actionCarte(joueur, sys);
			}
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
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof CarteMono))
			return false;
		return this.toString().equals(obj.toString());
	}
	
}
