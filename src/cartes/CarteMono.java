package cartes;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import common.Carte;
import common.Case;
import fenetre.Affichage;
import monopoly.Player;
import terrain.Compagnie;
import terrain.Gare;
import terrain.Parc;

public class CarteMono extends Carte {
	private int gain;
	private int deplacement;
	private boolean caseCible;
	private Parc parc;
	
	//=========================
	//===== Constructeurs =====
	//=========================
	
	//plusieurs dispo (valeurs par defaut)
	public CarteMono(boolean special, String titre, String desc, Parc parc, int gain, int deplacement, boolean caseCible) throws InvalidParameterException {
		super(special, titre, desc);
		if(parc == null)
			throw new InvalidParameterException("CarteMono constructeur - parc null");
		
		this.parc = parc;
		this.gain = gain;
		this.deplacement = deplacement;
		this.caseCible = caseCible;
	}
	//seulement deplacement
	public CarteMono(String titre, String desc, Parc parc, int deplacement, boolean caseCible) throws InvalidParameterException {
		this(false, titre, desc, parc, 0, deplacement, caseCible);
	}
	//seulement un gain ou une taxe
	public CarteMono(String titre, String desc, Parc parc, int gain) throws InvalidParameterException {
		this(false, titre, desc, parc, gain, 0, false);
	}
	//deplacement mais une carte speciale
	public CarteMono(boolean special, String titre, String desc, Parc parc, int deplacement, boolean caseCible) throws InvalidParameterException {
		this(special, titre, desc, parc, 0, deplacement, caseCible);
	}
	//gain ou taxe mais carte speciale
	public CarteMono(boolean special, String titre, String desc, Parc parc, int gain) throws InvalidParameterException {
		this(special, titre, desc, parc, gain, 0, false);
	}
	
	//===============================
	//===== Fonctions speciales =====
	//===============================
	@Override
	public void action(Player joueur, Affichage sys, ArrayList<Case> terrain) throws InvalidParameterException {
		super.action(joueur, sys, terrain);
		
		calculGain(joueur, sys);
		
		calculDeplacement(joueur, sys, terrain);
	}
	
	private void calculGain(Player joueur, Affichage sys){
		if(gain < 0)
			parc.addMoney(-gain, sys);
			
		joueur.addMoney(gain);
	}
	
	private void calculDeplacement(Player joueur, Affichage sys, ArrayList<Case> terrain){
		if (caseCible) {//si on doit se deplacer jusque "deplacement"
			tpJoueur(joueur,terrain, sys);
		}
		else {//si on doit se deplacer de "deplacement" cases
			deplacerJoueur(joueur, terrain, sys);
		}
		
		
	}
	
	private void tpJoueur(Player joueur, ArrayList<Case> terrain, Affichage sys){
		if (getTitre().equals(Carte.PRISON_TITRE)) {
			joueur.tpto(deplacement, sys, terrain);
			joueur.setPrison(3);
		} else {
				int dpm = 0;
		
		if (this.getTitre().equals(Carte.GARE_PLUS_PROCHE_TITRE)) 
			dpm = rechercheGare(joueur, terrain);
		
		else if (this.getTitre().equals(Carte.COMPAGNIE_PLUS_PROCHE_TITRE)) 
			dpm = rechercheCompagnie(joueur, terrain);
		
		else 
			dpm = this.deplacement;
		
		// si il doit completer un tour pour arriver sur la case
		//-> passer par la case depart
		if (joueur.getPos() > dpm)
			joueur.addMoney(200);
		joueur.moveto(dpm, sys, terrain);
		terrain.get(joueur.getPos()).actionCarte(joueur, sys);
		}
		
		
	
	}
		
	private void deplacerJoueur(Player joueur, ArrayList<Case> terrain, Affichage sys){
		if (deplacement != 0) {
			int tmp = joueur.getPos() + deplacement;//resultat
			
			if (tmp > 39){//pas deplacer le plateau, case depart
				tmp -= 40;
				joueur.addMoney(200);
			}
			if (tmp < 0){//on peut aussi reculer
				tmp += 40;
			}
			joueur.moveto(tmp, sys, terrain);
			terrain.get(joueur.getPos()).actionCarte(joueur, sys);
		}
	}

	private int rechercheGare(Player joueur, ArrayList<Case> terrain){
		int tmp = joueur.getPos();
		int first = 0;
		for(int i = 0; i < 40; i++){
			if(terrain.get(i) instanceof Gare){
				if(tmp < i)
					return i;
				else if(first == 0)
					first = i;
			}			
		}
		return first;
	}

	private int rechercheCompagnie(Player joueur, ArrayList<Case> terrain){
		int tmp = joueur.getPos();
		int first = 0;
		for(int i = 0; i < 40; i++){
			if(terrain.get(i) instanceof Compagnie){
				if(tmp < i)
					return i;
				else if(first == 0)
					first = i;
			}			
		}
		return first;
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
		CarteMono o = (CarteMono) obj;
		return this.getTitre().equals(o.getTitre())
				&& this.getDesc().equals(o.getDesc())
				&& this.isSpecial() == o.isSpecial()
				&& this.gain == o.gain
				&& this.caseCible == o.caseCible
				&& this.deplacement == o.deplacement;
	}
	
	//toString de la classe superieure Carte
}
