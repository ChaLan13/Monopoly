package terrain;

import java.security.InvalidParameterException;

import common.Case;
import common.Paquet;
import common.Carte;
import monopoly.Player;

public class Pioche implements Case {
	private Paquet paquet;
	
	
	
	public Pioche(Paquet paquet)throws InvalidParameterException {
		if(paquet == null)
			throw new InvalidParameterException("Pioche constructeur - paquet null");
		this.paquet = paquet;
	}

	@Override
	public void action(Player joueur, int scoreDe) {
		Carte carte;
		carte = paquet.tirer();
		if(carte.getTitre().equals("Sortie de prison"))
			joueur.addInv(carte);
		else
			//on a tiré la carte, ne pas oublier de la remettre dans le paquet
			paquet.add(carte);
		
		carte.action(joueur);
	}

	@Override
	public void init() {}

}
