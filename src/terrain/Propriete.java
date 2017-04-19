package terrain;

import java.security.InvalidParameterException;

import common.Case;
import monopoly.Player;

public abstract class Propriete implements Case {

	private boolean hypotheque;
	private Player possesseur;
	private int prix;
	
	public void init(){
		clear();
		hypotheque = false;
	}
	
	public void achat(Player joueur){
		//TODO affichage(demande d'achat de la case)
		if(true/*Si le joueur achète*/){
			joueur.subMoney(prix);
			possesseur = joueur;
		}
	}

	public void hypothequer() throws Exception{
		//prix de l'hypothèque: prix/2
		if(hypotheque)
			throw new Exception("Terrain deja hypotheque");
		if(possesseur == null)
			throw new Exception("Pas de possesseur");
		
		hypotheque = true;
		possesseur.addMoney(prix/2);
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
	
	public void action(Player joueur, int scoreDe)throws InvalidParameterException {
		if(joueur == null || scoreDe <= 0)
			throw new InvalidParameterException("Propriete.action() - joueur null");
		if(scoreDe <= 0)
			throw new InvalidParameterException("Propriete.action() - score de invalide");
		
		
		if(possesseur == null)
			this.achat(joueur);
		else{
			if(!hypotheque){
				int tmp = this.valeur(scoreDe);
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

	public Propriete(int prix)throws InvalidParameterException {
		setPrix(prix);
		init();
	}

	public int getPrix() {
		return prix;
	}
}
