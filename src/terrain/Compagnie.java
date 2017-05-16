package terrain;

import java.security.InvalidParameterException;

import common.De;
import fenetre.Affichage;
import monopoly.Player;

public class Compagnie extends Propriete {
	Compagnie autre;
	De de;
	
	//======================
	//=====Constructeur=====
	//======================
	public Compagnie(String name) throws InvalidParameterException {
		super(name, 150);
		de = new De(2,12);
	}
	
	//=============================
	//=====Fonctions speciales=====
	//=============================
	@Override
	public int valeur(int scoreDe) {
		if(this.getPossesseur() == autre.getPossesseur()){
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
	
	//===================
	//=====Get & Set=====
	//===================
	public void add(Compagnie autre)throws InvalidParameterException{
		if(autre == null)
			throw new InvalidParameterException("Compagnie.add() -> autre = null");
		this.autre = autre;
	}

	//===========================
	//=====equals & toString=====
	//===========================
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(obj == this)
			return true;
		if(!(obj instanceof Compagnie))
			return false;
		
		Compagnie o = (Compagnie) obj;
		return this.getName().equals(o.getName())
				&& this.getPossesseur().equals(o.getPossesseur())
				&& this.estHypo() == o.estHypo()
				&& this.getPrix() == o.getPrix();
		//comparaison de "autre" impossible
		//-> boucle infinie
	}
	
	//toString de la classe superieur Propriete
}
