package monopoly;

import common.Case;
import common.De;
import common.Carte;
import common.Paquet;
import monopoly.Player;
import monopoly.Affichage;

import java.security.InvalidParameterException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Jeu implements Affichage{
	private ArrayList<Case> terrain = new ArrayList<Case>();
	private ArrayList<Player> players = new ArrayList<Player>();
	private De de;
	private Paquet Chance, Communaute;
	
	public Jeu() {
		de = new De(2,12);
	}
	
	public void addPlayer(String name) throws InvalidParameterException{
		players.add(new Player(name));
	}

	public void debut(){
		//chaque joueurs lance un De pour determiner le 1e
		De desix = new De();
		int max = 0;
		int nbr = 0;
		int tmp;
		
		//TODO affichage (afficher le lanc� des d� pour chaque joueur)
		//le joueur i lance le d�
		for(int i=0; i<players.size(); i++){
			players.get(i).init();
			tmp = desix.jet();
			premierLancer(tmp,i+1);
			if(tmp > max){
				max = tmp;
				nbr = i;
			}
		}
		joueurQuiCommence(1+nbr, max);
		
		Collections.swap(players, 0, nbr);
	}	
	
	private void tour(int num)throws InvalidParameterException{
		if(num > players.size() || num < 1)
			throw new InvalidParameterException("Jeu.tour() - numero du joueur invalide");
		
		num--;//le joueur 1 est le numero 0
		int tmp, lance;
		Carte tmp2;
		Player joueur = players.get(num);
		
		//si le joueur est en prison
		// soit il a la carte sortie de prison
		// soit il peux payer 50
		// soit il DOIT payer 50
		if(joueur.getPrison() > 0){
			tmp = joueur.searchInv("Sortie de prison");
			if(tmp > -1){
				//TODO affichage(demande au joueur d'utiliser sa carte
				if(true/*utiliser la carte*/){
					tmp2 = joueur.popInv(tmp);
					if(tmp2.getPaquet() == "Chance")
						Chance.add(tmp2);
					else
						Communaute.add(tmp2);
					
					joueur.setPrison(0);
				}
			}
			if(joueur.getPrison() > 1){
				//TODO affichage(demande au joueur de payer 50 pour sortir)
				if(true/*payer 50*/){
					joueur.addMoney(-50);
					joueur.setPrison(0);
				}
			}
			else if(joueur.getPrison() == 1){
				//TODO affichage (popup "vous etes oblig� de payer 50 pour sortir" "ok")
				joueur.addMoney(-50);
				joueur.setPrison(0);
			}
		}

		
		//lanc� de d� et d�placement apr�s les probl�mes de prison
		lance = de.jet();//TODO affichage(lanc� de d� du joueur num)
		
		if(joueur.getPrison() == 0){
			tmp = lance + joueur.getPos();
			if(tmp > 39){//passer par la case d�part
				//la derniere case est la n�39 (case depart = 0)
				//donc si il est sur la 40 c'est donc qu'il est sur la 0
				tmp = tmp - 40;
				joueur.addMoney(200);
				joueur.moveto(tmp);
				
				//action quand il tombe sur une case
				terrain.get(joueur.getPos()).action(joueur, lance);
			}
		}
		
		
		//TODO affichage
		//choix du joueur pour ajouter une maison ou echange etc
		//le joueur peut finir son tour ici, mais attention
		//si son argent est n�gatif � la fin du tour, il a perdu

		//fin, enlever un nombre de tour de prison
		joueur.prisonMoins();
		
		//TODO faire perdre le joueur si son argent est negatif
		//-> enlever tous ses terrains
		//-> remettre l'inventaire dans les paquets
	}
	
	public int getNbrJoueur(){
		return players.size();
	}
	
	public void joueurQuiCommence(int i, int nb){
		System.out.println("C'est le joueur "+intToString(1+i)+" qui commence avec un lancer de dé égal à " +intToString(nb)+"\n");
	}
	
	public String intToString(int n){
		return (""+n);
	}

	public void premierLancer(int lancer, int i) {
		System.out.println("Le joueur " +intToString(i)+ " a fait un lancer égal à " +intToString(lancer));
		
	}
	
	public boolean demandeCarteSortiePrison(){
		System.out.println("Voulez-vous utiliser votre carte Sortie de prison ? \n ");
		boolean sortie=getBool();
		if(sortie)
			System.out.println("Vous utilisez votre carte de sortie de prison. \n"); 
		else
			System.out.println("Vous n'utilisez pas votre carte de sortie de prison. \n"); 
		return sortie;
			
	}
	
	public void print(String message){
		System.out.println(message);
	}

	public boolean getBool(){
		Scanner sc = new Scanner(System.in);
		boolean suite = true;
		while(suite){
			suite= false;
			String rep = sc.nextLine();
			switch(rep){
				case "o":
				case "O":
				case "y":
				case "Y":
				case "yes":
				case "YES":
				case "oui":
				case "OUI": return true;
				case "n":
				case "N":
				case "no":
				case "NO":
				case "non":
				case "NON":return false;	
				default : print("La saisie est incorrecte."); suite=true; 
			}	
		}
		return false;
	}
		
	public int getInt(){
		
	}
	
}