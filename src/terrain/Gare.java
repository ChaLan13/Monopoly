package terrain;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import fenetre.Affichage;
import monopoly.Player;

public class Gare extends Propriete {
	private ArrayList<Gare> autres = new ArrayList<Gare>();
	private int val[] = {25, 50, 100, 200};
	
	//========================
	//===== Constructeur =====
	//========================
	public Gare(String name) throws InvalidParameterException {
		super(name, 200);
	}
	
	//===============================
	//===== Fonctions speciales =====
	//===============================
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
			sys.GareCarte(this, joueur);
			if(this.estHypo()){
				sys.GareCarteHypotheque();
			}
			else{
				int tmp = this.valeur(0) * 2;
				sys.GareCartePayer(tmp);
				joueur.subMoney(tmp);
				this.getPossesseur().addMoney(tmp);
			}
		}
	}
	
	//=====================
	//===== Get & Set =====
	//=====================
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

	//=============================
	//===== equals & toString =====
	//=============================
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj)
				&& obj instanceof Gare;
	}
	//equals et toString de la classe superieur Propriete
}
