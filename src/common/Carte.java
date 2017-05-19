/**
  <b>class Carte</b>
 * <p>@see CarteAnniv, CarteChoix, CarteMono, CarteReparation, CarteTirer</p>
 * <p>@author SCHWAB Lucas, LANUEL Charlotte</p>
 *<p> @version final 1.0</p>
 * 
*/




package common;


import java.security.InvalidParameterException;
import java.util.ArrayList;

import fenetre.Affichage;
import monopoly.Player;

/** <b>Default constructor</b>
 * <p>Construit une carte par defaut</p>
 * @author SCHWAB Lucas, LANUEL Charlotte
 * @see other constructor Carte
 */
public class Carte{
	public static final String SORTIE_PRISON_TITRE = "VOUS ETES LIBERE DE PRISON";
	public static final String PRISON_TITRE = "Allez en prison.";
	public static final String GARE_PLUS_PROCHE_TITRE = "Avancez jusqu'a la gare la plus proche.";
	public static final String COMPAGNIE_PLUS_PROCHE_TITRE = "Avancez jusqu'a la Compagnie de service public al plus proche.";
	private String titre;
	private String desc;
	private Paquet paquet;
	private boolean special;//une carte speciale ne sera pas affichée
	
	//=========================
	//===== Constructeurs =====
	//=========================
	/**
	 * <b><p>Default constructor for special cards</p></b>
	 * <p>Build a card</p>
	 * <p>@param special, titre, desc</p>
	 * <p>@throws InvalidParameterException if titre or desc is null</p>
	 */
	public Carte(boolean special, String titre, String desc)throws InvalidParameterException{
		setTitre(titre);
		setDesc(desc);
		this.special = special;
	}

	public Carte(String titre, String desc)throws InvalidParameterException{
		this(false, titre, desc);
	}
	
	//===============================
	//===== Fonctions speciales =====
	//===============================
	/**
	 * <b>Void action</b>
	 * <p>Player draws a card and print the card's text</p>
	 *<p> @param joueur</p>
	 * <p>@param sys</p>
	 * <p>@param terrain</p>
	 * <p>@throws InvalidParameterException from joueur, affichage (sys), terrain if null</p>
	 */
	public void action(Player joueur, Affichage sys, ArrayList<Case> terrain) throws InvalidParameterException{
		if(joueur == null)
			throw new InvalidParameterException("Carte.action() -> joueur null");
		if(sys == null)
			throw new InvalidParameterException("Carte.action() -> affichage null");
		if(terrain == null)
			throw new InvalidParameterException("Carte.action() -> terrain null");
		
		//seulement si la carte n'est pas speciale
		if(this.isntSpecial())
			sys.AfficherCarte(joueur, this);
	}
	
	public void returnPaquet(){
		//remet la carte dans son paquet d'origine
		//pas d'exception si pas de paquet
		if(paquet != null)
			paquet.add(this);
	}

	//=====================
	//===== Get & Set =====
	//=====================
	
	private void setTitre(String titre)throws InvalidParameterException{
		if(titre == null)
			throw new InvalidParameterException("Carte.setTitre() - titre null");
		this.titre = titre;
	}

	private void setDesc(String desc)throws InvalidParameterException{
		if(desc == null)
			throw new InvalidParameterException("Carte.setDesc() - description null");
		this.desc = desc;
	}

	public Paquet getPaquet() {return paquet;}

	//pas de test null si on l'enleve du paquet
	public void setPaquet(Paquet paquet) {this.paquet = paquet;}
	
	public String getTitre() {return titre;}

	public String getDesc() {return desc;}
	
	public boolean isSpecial(){return special;}
	public boolean isntSpecial(){return !special;}
	
	//=============================
	//===== equals & toString =====
	//=============================

	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(obj == this)
			return true;
		if(!(obj instanceof Carte))
			return false;
		Carte o = (Carte) obj;
		return this.titre.equals(o.titre)
				&& this.desc.equals(o.desc)
				&& this.special == o.special;
	}

	@Override
	public String toString() {
		return (this.getTitre()=="" ? "" : this.getTitre() + "\n")	//Enleve un retour a la ligne inutile
			+ this.getDesc();										//Quand le titre est vide
	}
	
}