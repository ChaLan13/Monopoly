package monopoly;

import common.Case;
import common.De;
import common.Carte;
import monopoly.Player;
import fenetre.Affichage;

import java.security.InvalidParameterException;

import java.util.ArrayList;
import java.util.Collections;

public class Jeu{
	private ArrayList<Case> terrain;
	private ArrayList<Player> players;
	private De de;
	private Affichage sys;
	
	public Jeu(Affichage sys, ArrayList<Case> terrain, ArrayList<Player> players)throws InvalidParameterException {
		if(sys == null)
			throw new InvalidParameterException("Jeu constructeur -> Affichage null");
		if(terrain == null)
			throw new InvalidParameterException("Jeu constructeur -> terrain null");
		if(players == null)
			throw new InvalidParameterException("Jeu constructeur -> players null");
		de = new De();
		this.sys = sys;
		this.players = players;
		this.terrain = terrain;
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
			sys.print("Le joueur" +players.get(i).getName() +" a fait un lancer egal a " +tmp+ "\n" );
			if(tmp > max){
				max = tmp;
				nbr = i;
			}
		}
		sys.print("Le joueur " +players.get(nbr).getName()+" commence. \n");
		Collections.swap(players, 0, nbr);
		
		for(Case e: terrain)
			e.init();
		for(Player e : players)
			e.init();
		
		this.boucleJeu();
	}	

	private void tour(int num)throws InvalidParameterException{
		if(num > players.size()-1 || num < 0)
			throw new InvalidParameterException("Jeu.tour() - numero du joueur invalide");
		
		int tmp, lance, lance1, lance2;
		Carte tmp2;
		Player joueur = players.get(num);
		boolean recommence = false;
		int nbrDouble = 0;
		
		if(!joueur.aPerdu()){
			do{
				recommence = false;
				// TODO affichage(Debut du tour)FAIT EN CONSOLE
				sys.print("C'est au tour de " + joueur.getName() + " (" + joueur.getMoney() + "€)\n");

				joueur.prisonMoins(sys);
				// si le joueur est en prison
				// soit il a la carte sortie de prison
				// soit il peux payer 50
				// soit il DOIT payer 50
				if (joueur.getPrison() > 0) {
					tmp = joueur.searchInv("Vous etes libere de prison.");
					boolean rep;
					if (tmp > -1) {

						// TODO affichage(demande au joueur d'utiliser sa
						// carte)FAIT EN CONSOLE
						rep = sys.getBool("Voulez-vous utiliser votre carte de sortie de prison ?\n");
						if (rep) {
							tmp2 = joueur.popInv(tmp);
							tmp2.returnPaquet();
							joueur.setPrison(0);
						}
					}
					if (joueur.getPrison() > 1) {
						// TODO affichage(demande au joueur de payer 50 pour
						// sortir) FAIT EN CONSOLE
						rep = sys.getBool("Voulez-vous payer 50 pour sortir de prison ?");
						;
						if (rep/* payer 50 */) {
							joueur.addMoney(-50);
							joueur.setPrison(0);
						}
					} else if (joueur.getPrison() == 1) {
						// TODO affichage (popup "vous etes oblige de payer 50
						// pour sortir" "ok") FAIT EN CONSOLE
						sys.print("Vous etes oblige de payer 50 pour sortir de prison. \n");
						joueur.addMoney(-50);
						joueur.setPrison(0);
					}
				}

				// lance de de et deplacement apres les problemes de prison
				// TODO affichage(lance de de du joueur num)FAIT EN CONSOLE
				lance1 = de.jet();
				lance2 = de.jet();
				sys.print(joueur.getName() + " lance les des et fait " + lance1 + " et " + lance2 + "\n");
				if (lance1 == lance2) {
					nbrDouble++;
					if (nbrDouble == 3) {
						// TODO affichage(3e double)FAIT EN CONSOLE
						sys.print("C'est le 3e double! Prison direct!");
						joueur.tpto(30, sys, terrain);
					}
					else
						recommence = true;
				}

				lance = lance1 + lance2;
				if (joueur.getPrison() == 0) {
					tmp = lance + joueur.getPos();
					if (tmp > 39) {// passer par la case depart
						// la derniere case est la num39 (case depart = 0)
						// donc si il est sur la 40 c'est donc qu'il est sur la
						// 0
						tmp = tmp - 40;
						joueur.addMoney(200);
						joueur.moveto(tmp, sys, terrain);

						// action quand il tombe sur une case
						terrain.get(joueur.getPos()).action(joueur, lance, sys);
					}
				}

				// TODO affichage
				// choix du joueur pour ajouter une maison ou echange etc
				// le joueur peut finir sont tour ici, mais attention
				// si son argent est negatif a la fin du tour, il a perdu

			}while(recommence);
			if(joueur.getMoney() < 0){
				joueur.gameOver();
			}
		}
	}
	
	private void boucleJeu(){
		do{
			for(int i=0; i<players.size(); i++)
				this.tour(i);
		}while(!this.finish());
		
		sys.print("La partie est finie!!!\n"
				+"Le gagnant est: " + Gagnant().getName() + "\n\n");
	}
	
	public boolean finish(){
		int reste = players.size();
		for(Player e : players){
			if(e.aPerdu())
				reste--;
		}
		return reste == 1;
	}
	
	private Player Gagnant(){
		for(Player e : players){
			if(!e.aPerdu())
				return e;
		}
		return null;
	}
}