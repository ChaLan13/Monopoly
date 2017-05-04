package terrain;

import java.security.InvalidParameterException;

import common.De;
import fenetre.Affichage;
import monopoly.Player;

public class Compagnie extends Propriete {
	Compagnie autre;
	De de;

	public Compagnie(String name) throws InvalidParameterException {
		super(name, 150);
		de = new De(2,12);
	}
	
	public void add(Compagnie autre)throws InvalidParameterException{
		if(autre == null)
			throw new InvalidParameterException("Compagnie.add() -> autre = null");
		this.autre = autre;
	}

	@Override
	public int valeur(int scoreDe) {
		if(this.getPossesseur().equals(autre.getPossesseur())){
			return scoreDe * 4;
		}
		else{
			return scoreDe * 10;
		}
	}
	
	@Override
	public void actionCarte(Player joueur, Affichage sys){
		if(this.getPossesseur() == null){
			this.achat(joueur, sys);
		}
		else{
			//TODO affichage(compagnie affichage carte)FAIT EN CONSOLE
			sys.print(joueur.getName() + " tombe sur " + this.getName() + " appartenant a " + this.getPossesseur().getName() + ".\n");
			if(this.estHypo()){
				sys.print("Le terrain est hypothéqué!\n");
			}
			else{
				int tmp = de.jet();
				//TODO affichage(compagnie affichage carte)FAIT EN CONSOLE
				sys.print("Il rejette donc les des et fait " + tmp + ".\n");
				tmp*=10;
				sys.print("Il va donc payer: " + tmp + "€\n");
				joueur.subMoney(tmp);
				this.getPossesseur().addMoney(tmp);
			}
		}
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Compagnie))
			return false;
		return this.toString().equals(obj.toString());
	}
	
}
