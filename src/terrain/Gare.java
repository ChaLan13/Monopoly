package terrain;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import fenetre.Affichage;
import monopoly.Player;

public class Gare extends Propriete {
	private ArrayList<Gare> autres = new ArrayList<Gare>();
	private int val[] = {25, 50, 100, 200};
	
	//======================
	//=====Constructeur=====
	//======================
	public Gare(String name) throws InvalidParameterException {
		super(name, 200);
	}
	
	//=============================
	//=====Fonctions speciales=====
	//=============================
	@Override
	int valeur(int scoreDe) {
		int i=0;
		for(Gare e: autres){
			if(this.getPossesseur() == e.getPossesseur())
				i++;
		}
		return val[i];
	}
	
	@Override
	public void actionCarte(Player joueur, Affichage sys) {
		if(joueur == null)
			throw new InvalidParameterException("Gare.actionCarte() - joueur null");
		
		
		if(this.getPossesseur() == null)
			this.achat(joueur, sys);
		else{
			//TODO affichage(tomber sur un terrain possédé)FAIT EN CONSOLE
			sys.print(joueur.getName() + " tombe sur " + this.getName() + " appartenant a " + this.getPossesseur().getName() + ".\n");
			if(this.estHypo()){
				sys.print("Le terrain est hypothéqué!\n");
			}
			else{
				int tmp = this.valeur(0) * 2;
				sys.print("Le montant du loyer est: " + tmp + "€\n");
				joueur.subMoney(tmp);
				this.getPossesseur().addMoney(tmp);
			}
		}
	}
	
	//===================
	//=====Get & Set=====
	//===================
	public void add(Gare autre1, Gare autre2, Gare autre3) throws InvalidParameterException {
		if(autre1 == null)
			throw new InvalidParameterException("Gare.add() -> Param NULL");
		if(autre2 == null)
			throw new InvalidParameterException("Gare.add() -> Param NULL");
		if(autre3 == null)
			throw new InvalidParameterException("Gare.add() -> Param NULL");
		
		this.autres.add(autre1);
		this.autres.add(autre2);
		this.autres.add(autre3);
	}

	// ===========================
	// =====equals & toString=====
	// ===========================
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof Compagnie))
			return false;

		Gare o = (Gare) obj;
		return this.getName().equals(o.getName())
				&& this.getPossesseur().equals(o.getPossesseur())
				&& this.estHypo() == o.estHypo() 
				&& this.getPrix() == o.getPrix();
		// comparaison de "autres" impossible
		// -> boucle infinie
	}

	// toString de la classe superieur Propriete
}
