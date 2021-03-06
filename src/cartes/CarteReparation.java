package cartes;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import common.Carte;
import common.Case;
import fenetre.Affichage;
import monopoly.Player;
import terrain.Parc;
import terrain.Terrain;

public class CarteReparation extends Carte {
	private int prixMaison;
	private int prixHotel;
	private Parc parc;
	
	//========================
	//===== Constructeur =====
	//========================
	public CarteReparation(String titre, String desc,Parc parc, int prixMaison, int prixHotel) throws InvalidParameterException {
		super(titre, desc);
		if(prixMaison < 0)
			throw new InvalidParameterException("CarteReparation constructeur -> prixMaison negatif");
		if(prixHotel < 0)
			throw new InvalidParameterException("CarteReparation constructeur -> prixHotel negatif");
		if(parc == null)
			throw new InvalidParameterException("CarteReparation constructeur -> parc null");
		
		this.parc = parc;
		this.prixMaison = prixMaison;
		this.prixHotel = prixHotel;
	}
	
	//=============================
	//===== Fonction speciale =====
	//=============================
	@Override
	public void action(Player joueur, Affichage sys, ArrayList<Case> terrain)throws InvalidParameterException {
		super.action(joueur, sys, terrain);
		
		int somme = 0;
		for(Case e : joueur.getPossession()){
			if(e instanceof Terrain){
				int tmp = ((Terrain) e).getNbrMaison();
				if(tmp < 5)
					somme += prixMaison*tmp;
				else
					somme += prixHotel;
			}
		}
		
		sys.AfficherReparation(joueur, somme);
		
		joueur.subMoney(somme);
		parc.addMoney(somme, sys);
	}
	
	//=====================
	//===== Get & Set =====
	//=====================
	
	//aucun a part ceux de la classe superieure Carte
	
	//=============================
	//===== equals & toString =====
	//=============================
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(obj == this)
			return true;
		if(!(obj instanceof CarteMono))
			return false;
		CarteReparation o = (CarteReparation) obj;
		return this.getTitre().equals(o.getTitre())
				&& this.getDesc().equals(o.getDesc())
				&& this.prixHotel == o.prixHotel
				&& this.prixMaison == o.prixMaison;
	}
	
	//toString de la classe superieure Cartes
}
