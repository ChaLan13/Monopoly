package monopoly;

import common.Case;
import common.De;
import common.Carte;
import common.Paquet;
import monopoly.Player;
import fenetre.Affichage;

import java.security.InvalidParameterException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Jeu{
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
		
		//TODO affichage (afficher le lance des de pour chaque joueur) FAIT EN CONSOLE
		//le joueur i lance le de
		for(int i=0; i<players.size(); i++){
			players.get(i).init();
			tmp = desix.jet();
			Affichage.print("Le joueur" +players.get(i).getPseudo() +" a fait un lancer egal a " +tmp+ "\n" );
			if(tmp > max){
				max = tmp;
				nbr = i;
			}
		}
		Affichage.print("Le joueur " +players.get(nbr).getPseudo()+" commence. \n");
		Collections.swap(players, 0, nbr);
	}	
	
	private void tour(int num)throws InvalidParameterException{
		if(num > players.size() || num < 1)
			throw new InvalidParameterException("Jeu.tour() - numero du joueur invalide");
		
		num--;//le joueur 1 est le numero 0
		int tmp, lance;
		Carte tmp2;
		Player joueur = players.get(num);
		
		if(!joueur.aPerdu()){
		
			//si le joueur est en prison
			// soit il a la carte sortie de prison
			// soit il peux payer 50
			// soit il DOIT payer 50
			if(joueur.getPrison() > 0){
				tmp = joueur.searchInv("Sortie de prison");
				boolean rep;
				if(tmp > -1){
					
					//TODO affichage(demande au joueur d'utiliser sa carte FAIT EN CONSOLE
					Affichage.print("Voulez-vous utiliser votre carte de sortie de prison ?\n");
					rep = Affichage.getBool();
					if(rep){
						Affichage.print("Vous utilisez votre carte sortie de prison. \n");
						tmp2 = joueur.popInv(tmp);
						if(tmp2.getPaquet() == "Chance")
							Chance.add(tmp2);
						else
							Communaute.add(tmp2);
						
						joueur.setPrison(0);
					}
					else 
						Affichage.print("Vous gardez votre carte sortie de prison. \n");
				}
				if(joueur.getPrison() > 1){
					//TODO affichage(demande au joueur de payer 50 pour sortir) FAIT EN CONSOLE
					Affichage.print("Voulez-vous payer 50 pour sortir de prison ? \n");
					rep= Affichage.getBool();;
					if(rep/*payer 50*/){
						joueur.addMoney(-50);
						joueur.setPrison(0);
					}
				}
				else if(joueur.getPrison() == 1){
					//TODO affichage (popup "vous etes oblige de payer 50 pour sortir" "ok") FAIT EN CONSOLE
					Affichage.print("Vous etes oblige de payer 50 pour sortir de prison. \n");
					joueur.addMoney(-50);
					joueur.setPrison(0);
				}
			}
			
			
			//lance de de et deplacement apres les problemes de prison
			lance = de.jet();//TODO affichage(lance de de du joueur num)
			
			if(joueur.getPrison() == 0){
				tmp = lance + joueur.getPos();
				if(tmp > 39){//passer par la case depart
					//la derniere case est la num39 (case depart = 0)
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
			//le joueur peut finir sont tour ici, mais attention
			//si son argent est negatif a la fin du tour, il a perdu

			//fin, enlever un nombre de tour de prison
			joueur.prisonMoins();
		
			if(joueur.getMoney() < 0){
				joueur.gameOver(Chance, Communaute);
			}
		}
	}
}