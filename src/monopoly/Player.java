package monopoly;

import common.Carte;
import terrain.Propriete;

import java.security.InvalidParameterException;
import java.util.ArrayList;

public class Player{
	private String pseudo;
	private int pos;
	private int money;
	private int prison;
	private boolean perdu;
	
	private ArrayList<Carte> inv = new ArrayList<Carte>();//inventaire
	private ArrayList<Propriete> possession = new ArrayList<Propriete>();//terrains
		
	public Player(String pseudo)throws InvalidParameterException{
		setNom(pseudo);
		init();
	}
	
	public void addMoney(int somme){
		//ajoute de l'argent ou en retire si somme est négatif
		money = money + somme;
	}
	
	public void init(){
		pos = 0;
		money = 1500;
		prison = 0;
		inv.clear();
		possession.clear();
		perdu = false;
	}
	
	public void moveto(int num)throws InvalidParameterException{
		if(num < 0 || num > 39)
			throw new InvalidParameterException("Player.moveto() - Position inexisante");

		//TODO affichage(deplacement du joueur)
		pos = num;
	}
	
	public void gameOver(){
		perdu = true;
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
			if(inv.get(i).getTitre().equals(titre))
				return i;
		}
		
		return -1;
	}
	
	public void setPrison(int prison) {
		this.prison = prison;
	}
	
	public void prisonMoins(){
		if(prison > 0)
			prison--;
	}

	public void addInv(Carte carte)throws InvalidParameterException{
		if(carte == null)
			throw new InvalidParameterException("Player.addInv() - carte null");
		inv.add(carte);
	}
	
	public Carte popInv(int num){
		return inv.remove(num);
	}
	
	private void setNom(String pseudo) throws InvalidParameterException{
		if(pseudo == null || pseudo == "")
			throw new InvalidParameterException("Player.setNom() - Nom vide");
		this.pseudo = pseudo;
	}
}