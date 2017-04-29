package monopoly;

import java.security.InvalidParameterException;

import common.Carte;

public class CarteAnniv extends Carte {
	private Jeu jeu;
	
	public CarteAnniv(String titre, String desc, Jeu jeu) throws InvalidParameterException {
		super(titre, desc);
		if(jeu == null)
			throw new InvalidParameterException("CarteAnniv constructeur -> jeu = null");
		
		this.jeu = jeu;
	}

	@Override
	public void action(Player joueur) {
		
	}

}
