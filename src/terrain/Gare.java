package terrain;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import fenetre.Affichage;
import monopoly.Player;

public class Gare extends Propriete {
	private ArrayList<Gare> autres = new ArrayList<Gare>();
	private int val[] = {25, 50, 100, 200};
	
	
	public Gare(String name) throws InvalidParameterException {
		super(name, 200);
	}
	
	public void add(Gare autre1, Gare autre2, Gare autre3) throws InvalidParameterException {
		if(autre1 == null)
			throw new InvalidParameterException("Gare.add() -> Param NULL");
		if(autre2 == null)
			throw new InvalidParameterException("Gare.add() -> Param NULL");
		if(autre3 == null)
			throw new InvalidParameterException("Gare.add() -> Param NULL");
		
		this.autres.add(autre1);
		autre1.add(this, autre2, autre3);
		this.autres.add(autre2);	
		autre2.add(this, autre1, autre3);
		this.autres.add(autre3);
		autre3.add(this, autre1, autre2);
	}

	@Override
	int valeur(int scoreDe) {
		int i=0;
		for(Gare e: autres){
			if( getPossesseur().equals(e.getPossesseur()))
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

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Gare))
			return false;
		return this.toString().equals(obj.toString());
	}
}
