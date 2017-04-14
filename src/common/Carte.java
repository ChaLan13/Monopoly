package common;


import javax.naming.InvalidNameException;

public class Carte{
	private String titre;
	private String desc;
	private String paquet;
	
	public Carte(String titre, String desc)throws InvalidNameException{
		setTitre(titre);
		setDesc(desc);
	}

	public String getPaquet() {
		return paquet;
	}

	public void setPaquet(String paquet) {
		this.paquet = paquet;
	}

	private void setTitre(String titre)throws InvalidNameException{
		if(titre == null || titre == "")
			throw new InvalidNameException();
		this.titre = titre;
	}

	private void setDesc(String desc)throws InvalidNameException{
		if(desc == null)
			throw new InvalidNameException();
		this.desc = desc;
	}

	public String getTitre() {
		return titre;
	}

	public String getDesc() {
		return desc;
	}
}