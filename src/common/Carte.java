package common;


import java.security.InvalidParameterException;
import java.util.ArrayList;

import fenetre.Affichage;
import monopoly.Player;

public class Carte{
	private String titre;
	private String desc;
	private Paquet paquet;
	private boolean special;
	
	
	public Carte(boolean special, String titre, String desc)throws InvalidParameterException{
		setTitre(titre);
		setDesc(desc);
		this.special = special;
	}
	public Carte(String titre, String desc)throws InvalidParameterException{
		this(false, titre, desc);
	}
	
	public void action(Player joueur, Affichage sys, ArrayList<Case> terrain) throws InvalidParameterException{
		if(joueur == null)
			throw new InvalidParameterException("Carte.action() -> joueur null");
		if(sys == null)
			throw new InvalidParameterException("Carte.action() -> affichage null");
		if(terrain == null)
			throw new InvalidParameterException("Carte.action() -> terrain null");
		
		//TODO affichage(afficher la carte et la description)FAIT EN CONSOLE
		sys.print(joueur.getName() + " tire une carte " + this.getPaquet().getName() + "\n"
				+ this.toString() + "\n");
	}

	public void returnPaquet(){
		paquet.add(this);
	}

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

	public Paquet getPaquet() {
		return paquet;
	}

	public void setPaquet(Paquet paquet) {
		this.paquet = paquet;
	}
	
	public String getTitre() {
		return titre;
	}

	public String getDesc() {
		return desc;
	}
	
	public boolean isntSpecial(){
		return !special;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Carte))
			return false;
		return (obj.toString() == this.toString()) && (this.getPaquet() == ((Carte) obj).getPaquet());
	}

	@Override
	public String toString() {
		return this.getTitre() + "\n" 
			+ this.getDesc();
	}
	
}