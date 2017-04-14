package common;

import common.Carte;
import javax.naming.InvalidNameException;

public class Player{
	private String pseudo;
	private int pos = 0;
	private int money;
	
	private Carte inv[];//inventaire
	
	public Player(String pseudo, int money)throws InvalidNameException{
		setNom(pseudo);
		this.money = money;
	}
	
	private void setNom(String pseudo) throws InvalidNameException{
		if(pseudo == null || pseudo == "")
			throw new InvalidNameException();
		this.pseudo = pseudo;
	}
}