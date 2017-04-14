package common;

import common.Carte;
import javax.naming.InvalidNameException;
import java.util.ArrayList;

public class Player{
	private String pseudo;
	private int pos = 0;
	private int money = 1500;
	private int prison = 0;
	
	private ArrayList<Carte> inv;//inventaire
	
	public Player(String pseudo)throws InvalidNameException{
		setNom(pseudo);
	}
	
	public void addMoney(int somme){
		//ajoute de l'argent ou en retire si somme est négatif
		money = money + somme;
	}
	
	private void setNom(String pseudo) throws InvalidNameException{
		if(pseudo == null || pseudo == "")
			throw new InvalidNameException();
		this.pseudo = pseudo;
	}
	
	public void moveto(int num)throws IndexOutOfBoundsException{
		if(num < 0 || num > 39)
			throw new IndexOutOfBoundsException();
		
		pos = num;
	}

	public String getPseudo() {
		return pseudo;
	}

	public int getPos() {
		return pos;
	}

	public int getMoney() {
		return money;
	}

	public int getPrison() {
		return prison;
	}

	public int searchInv(String titre){
		//cherche dans l'inventaire la carte au titre "titre"
		//si elle n'est pas trouvé, retourne -1
		for(int i=0; i<inv.size(); i++){
			if(inv.get(i).getTitre() == titre)
				return i;
		}
		
		return -1;
	}
	
	public void setPrison(int prison) {
		this.prison = prison;
	}
	
	public void prisonMoin(){
		if(prison > 0)
			prison--;
	}

	public Carte popInv(int num){
		return inv.remove(num);
	}
}