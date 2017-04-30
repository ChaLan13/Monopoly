package monopoly;

import common.Carte;
import common.Case;
import fenetre.Affichage;
import terrain.Propriete;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;

public class Player{
	private String name;
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
	
	public void init(){
		pos = 0;
		money = 1500;
		prison = 0;
		inv.clear();
		possession.clear();
		perdu = false;
	}
	
	public void moveto(int num, Affichage sys, ArrayList<Case> terrain)throws InvalidParameterException{
		if(num < 0 || num > 39)
			throw new InvalidParameterException("Player.moveto() - Position inexisante");
		if(sys == null)
			throw new InvalidParameterException("Player.moveto() - affichage null");
		if(terrain == null)
			throw new InvalidParameterException("Player.moveto() - Terrain null");

		//TODO affichage(deplacement du joueur)FAIT EN CONSOLE
		sys.print(this.getName() + " se deplace jusque sur la case " + num + ", " + terrain.get(num).getName() + "\n");
		pos = num;
	}
	
	public void tpto(int num, Affichage sys, ArrayList<Case> terrain)throws InvalidParameterException{
		if(num < 0 || num > 39)
			throw new InvalidParameterException("Player.tpto() - Position inexisante");
		if(sys == null)
			throw new InvalidParameterException("Player.moveto() - affichage null");
		if(terrain == null)
			throw new InvalidParameterException("Player.moveto() - Terrain null");

		//TODO affichage(deplacement du joueur)FAIT EN CONSOLE
		//attention pas glisser le pion, le mettre directement sur la case
		sys.print(this.getName() + " va directement sur la case " + num + ", " + terrain.get(num).getName() + "\n");
		pos = num;
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
	
	public int searchPossession(Propriete search){
		//cherche dans les possession la propriete search
		//si elle n'est pas trouvé, retourne -1
		for(int i=0; i<inv.size(); i++){
			if(possession.get(i).equals(search))
				return i;
		}
		
		return -1;
	}
	
	public void clearInv(){
		for(Carte e : inv){
			e.returnPaquet();
		}
		inv.clear();
	}
	
	public void clearPossession(){
		for(Propriete e : getPossession()){
			e.clear();
		}
		possession.clear();
	}
	
	public void gameOver(){
		perdu = true;
		clearInv();
		clearPossession();
	}
	
	public void addPossession(Propriete prop)throws InvalidParameterException{
		if(prop == null)
			throw new InvalidParameterException("Player.addPossession() -> prop null");
		
		possession.add(prop);
	}
	
	public void trier(ArrayList<Case> terrain){
		int i = 0;
		for(Case e : terrain){
			if(e instanceof Propriete){
				int tmp = this.searchPossession((Propriete)e);
				if(tmp > i)
					Collections.swap(possession, i++, tmp);
			}
		}
	}

	public String getName() {
		return name;
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
	
	public void setPrison(int prison) {
		this.prison = prison;
	}
	
	public void prisonMoins(Affichage sys){
		if(prison > 0){
			//TODO affichage(Decrementation prison)FAIT EN CONSOLE
			prison--;
			sys.print("Il te reste " + prison + " tour" + (prison>1?"s":"") + " en prison.\n");
		}
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
		this.name = pseudo;
	}

	public boolean aPerdu() {
		return perdu;
	}

	public void addMoney(int somme){
		//ajoute de l'argent
		money = money + somme;
	}
	
	public void subMoney(int somme){
		//retire de l'argent
		money = money - somme;
	}

	public ArrayList<Carte> getInv() {
		return inv;
	}

	public ArrayList<Propriete> getPossession() {
		return possession;
	}

	@Override
	public boolean equals(Object obj) {
		return obj.toString() == this.toString();
	}

	@Override
	public String toString() {
		return this.getName();
	}
	
}