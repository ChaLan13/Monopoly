package terrain;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import common.Case;
import fenetre.Affichage;
import monopoly.Player;

public abstract class Propriete extends Case {

	private boolean hypotheque;
	private Player possesseur;
	private int prix;
	
	public Propriete(String name, int prix)throws InvalidParameterException {
		super(name);
		setPrix(prix);
		init();
	}
	
	@Override
	public void init(){
		clear();
		hypotheque = false;
	}
	
	public void achat(Player joueur, Affichage sys){
		//TODO affichage(demande d'achat de la case)FAIT EN CONSOLE
		boolean tmp = sys.getBool("Voulez-vous acheter " + this.getName() + " pour " + this.getPrix() + "?");
		
		if(tmp){
			joueur.subMoney(prix);
			joueur.addPossession(this);
			//va set le possesseur
			//TODO affichage(confirmation d'achat)FAIT EN CONSOLE
			sys.print("Terrain achete avec succes");
		}
	}

	public void hypothequer(){
		//prix de l'hypothèque: prix/2
		if(!hypotheque){
			hypotheque = true;
			if(possesseur != null){
				possesseur.addMoney(prix/2);
			}
		}
	}
	
	public void leverHypo(){
		//prix pour lever l'hypotheque:
		//la moitié du prix + 10%
		//-> prix/2 + ((prix/2) / 100) * 10
		//-> prix/2 + (prix/200)*10
		//-> prix/2 + prix/20
		//-> 10*prix/20 + 1*prix/20
		//-> (11*prix/20)
		
		//On peut lever l'hypotheque d'un terrain a la banque
		//donc pas de possesseur obligatoire
		if(hypotheque){
			hypotheque = false;
			if(possesseur != null){
				possesseur.subMoney(11*prix/20);
			}
		}
	}
	
	public void action(Player joueur, int scoreDe, Affichage sys, ArrayList<Case> terrain)throws InvalidParameterException {
		if(joueur == null)
			throw new InvalidParameterException("Propriete.action() - joueur null");
		if(scoreDe < 0)
			throw new InvalidParameterException("Propriete.action() - score de invalide");
		
		
		if(possesseur == null)
			this.achat(joueur, sys);
		else{
			//TODO affichage(tomber sur un terrain possédé)FAIT EN CONSOLE
			sys.print(joueur.getName() + " tombe sur " + this.getName() + " appartenant a " + this.getPossesseur().getName() + ".\n");
			if(hypotheque){
				sys.print("Le terrain est hypothéqué!\n");
			}
			else{
				int tmp = this.valeur(scoreDe);
				sys.print("Le montant du loyer est: " + tmp + "€\n");
				joueur.subMoney(tmp);
				possesseur.addMoney(tmp);
			}
		}
	}
	
	abstract int valeur(int scoreDe);
	
	private void setPrix(int prix)throws InvalidParameterException {
		if(prix < 0){
			throw new InvalidParameterException("Propriete.action() - Prix negatif");
		}
		this.prix = prix;
	}

	public boolean estHypo() {
		return hypotheque;
	}

	public Player getPossesseur() {
		return possesseur;
	}
	
	public void clear(){
		possesseur = null;
		this.leverHypo();
	}

	public void setPossesseur(Player possesseur)throws InvalidParameterException{
		if(possesseur == null)
			throw new InvalidParameterException("Propriete.setPossesseur() -> possesseur null");
		this.possesseur = possesseur;
	}

	public int getPrix() {
		return prix;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Propriete))
			return false;
		return this.toString().equals(obj.toString());
	}

	@Override
	public String toString() {
		return (this.estHypo()? "- " : "  ") + this.getName();
	}
	
}
