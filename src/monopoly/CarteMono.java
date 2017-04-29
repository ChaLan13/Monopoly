package monopoly;

import java.security.InvalidParameterException;

import common.Carte;
import terrain.Parc;

public class CarteMono extends Carte {
	private int gain;
	private int deplacement;
	private boolean caseCible;
	private Parc parc;

	public CarteMono(String titre, String desc, Parc parc, int gain, int deplacement, boolean caseCible) throws InvalidParameterException {
		super(titre, desc);
		if(parc == null)
			throw new InvalidParameterException("CarteMono constructeur - parc null");
		
		this.parc = parc;
		this.gain = gain;
		this.deplacement = deplacement;
		this.caseCible = caseCible;
	}

	@Override
	public void action(Player joueur) {
		if(joueur == null)
			throw new InvalidParameterException("CarteMono.action() -> joueur null");
		
		//TODO affichage(afficher la carte et la description)
		
		if(gain < 0)
			parc.addMoney(-gain);
			
		joueur.addMoney(gain);
		
		if(caseCible)
			joueur.moveto(deplacement);
		else{
			int tmp = joueur.getPos() + deplacement;
			if(tmp > 39)
				tmp -= 40;
			if(tmp < 0)
				tmp +=40;
			joueur.moveto(tmp);
		}
	}

}
