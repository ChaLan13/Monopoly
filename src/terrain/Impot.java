package terrain;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import common.Case;
import fenetre.Affichage;
import monopoly.Player;

public class Impot extends Case {
	Parc parc;
	int val;
	
	//========================
	//===== Constructeur =====
	//========================
	public Impot(String name,Parc parc, int val)throws InvalidParameterException{
		super(name);
		if(val < 0)
			throw new InvalidParameterException("Import constructeur -> val negatif");
		if(parc == null)
			throw new InvalidParameterException("Import constructeur -> parc null");
		
		this.val = val;
		this.parc = parc;
	}

	//=============================
	//===== Fonction speciale =====
	//=============================
	@Override
	public void action(Player joueur, int scoreDe, Affichage sys, ArrayList<Case> terrain) {
		sys.Impot(joueur, this);
		joueur.subMoney(val);
		if(parc != null){
			parc.addMoney(val, sys);
		}
	}
	
	//=====================
	//===== Get & Set =====
	//=====================
	
	public int getVal(){return val;}
	//aucun, seuls ceux de la classe superieure "Propriete"

	//=============================
	//===== equals & toString =====
	//=============================
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(obj == this)
			return true;
		if(!(obj instanceof Impot))
			return false;
		Impot o = (Impot) obj;
		return this.val == o.val
				&& this.getName().equals(o.getName())
				&& this.parc.equals(o.parc);
	}
	
	//toString de la classe superieure "Propriete"
}
