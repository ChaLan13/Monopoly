package terrain;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import common.Case;
import fenetre.Affichage;
import monopoly.Player;

public class Impot extends Case {
	Parc parc;
	int val;
	

	public Impot(String name,Parc parc, int val)throws InvalidParameterException{
		super(name);
		if(val < 0)
			throw new InvalidParameterException("Import constructeur -> val negatif");
		if(parc == null)
			throw new InvalidParameterException("Import constructeur -> parc null");
		
		this.val = val;
		this.parc = parc;
	}

	@Override
	public void action(Player joueur, int scoreDe, Affichage sys, ArrayList<Case> terrain) {
		//TODO affichage(case impot)FAIIT EN CONSOLE
		
		sys.print(joueur.getName() + " tombe sur la case impot et doit payer " + this.val + "€.\n");
		joueur.subMoney(val);
		if(parc != null){
			parc.addMoney(val, sys);
		}
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Impot))
			return false;
		return this.toString().equals(obj.toString());
	}
}
