package common;


import java.security.InvalidParameterException;

import monopoly.Player;

public abstract class Carte{
	private String titre;
	private String desc;
	private String paquet;
	
	public abstract void action(Player joueur);
	
	public Carte(String titre, String desc)throws InvalidParameterException{
		setTitre(titre);
		setDesc(desc);
	}

	public String getPaquet() {
		return paquet;
	}

	public void setPaquet(String paquet) {
		this.paquet = paquet;
	}

	private void setTitre(String titre)throws InvalidParameterException{
		if(titre == null || titre == "")
			throw new InvalidParameterException("Carte.setTitre() - titre null ou vide");
		this.titre = titre;
	}

	private void setDesc(String desc)throws InvalidParameterException{
		if(desc == null)
			throw new InvalidParameterException("Carte.setDesc() - description null");
		this.desc = desc;
	}

	public String getTitre() {
		return titre;
	}

	public String getDesc() {
		return desc;
	}
}