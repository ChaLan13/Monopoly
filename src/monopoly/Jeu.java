package monopoly;

import common.Case;
import common.De;
import common.Carte;
import monopoly.Player;
import terrain.Propriete;
import terrain.Terrain;
import fenetre.Affichage;

import java.security.InvalidParameterException;

import java.util.ArrayList;
import java.util.Collections;

public class Jeu{
	//TODO cheats
	public static boolean CHEAT_DE = true;
	public static boolean CHEAT_MONEY = true;
	public static boolean CHEAT_TOUR_INFINI = true;
	private ArrayList<Case> terrain;
	private ArrayList<Player> players;
	private De de;
	private Affichage sys;
	
	//passage parametre entre plusieurs fonctions
	private int nbrDouble;
	private boolean recommence;
	private int money;
	private ArrayList<Propriete> echj1;
	private ArrayList<Propriete> echj2;
	private ArrayList<Carte> echcj1;
	private ArrayList<Carte> echcj2;
	
	//========================
	//===== Constructeur =====
	//========================
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
	
	//===============================
	//===== Fonctions speciales =====
	//===============================
	
	//Lancement d'une partie
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
			sys.print("Le joueur " +players.get(i).getName() +" a fait un lancer egal a " +tmp+ "\n" );
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

	//boucle jusqu'a la fin du jeu
	//et affiche la victoire
	private void boucleJeu(){
		do{
			for(int i=0; i<players.size(); i++)
				this.tour(i);
		}while(!this.finish());
		
		//TODO affichage(fin d'une partie) FAIT EN CONSOLE
		sys.print("La partie est finie!!!\n"
				+"Le gagnant est: " + Gagnant().getName() + "\n\n");
	}

	//calcule la fin d'une partie
	public boolean finish(){
		int reste = players.size();
		for(Player e : players){
			if(e.aPerdu())
				reste--;
		}
		return reste == 1;
	}
	
	//return le joueur gagnant
	private Player Gagnant(){
		for(Player e : players){
			if(!e.aPerdu())
				return e;
		}
		return null;
	}
	
	//=================================
	//===== Deroulement d'un tour =====
	//=================================
	private void tour(int num)throws InvalidParameterException{
		if(num > players.size()-1 || num < 0)
			throw new InvalidParameterException("Jeu.tour() - numero du joueur invalide");
		
		Player joueur = players.get(num);
		recommence = false;
		nbrDouble = 0;
		
		// TODO affichage(Debut du tour)FAIT EN CONSOLE
		sys.print("\n\n\n====================================");
		sys.print("C'est au tour de " + joueur.getName() + " (" + joueur.getMoney() + "$)\n");
		
		if(!joueur.aPerdu()){
			do{
				if(recommence){
					//TODO affichage(recommencer tour) FAIT EN CONSOLE
					sys.print("\n\n\n====================================");
					sys.print("Double! (" + nbrDouble + "e) " + joueur.getName() + " rejoue!\n");
					recommence = false;
				}
				else
					joueur.prisonMoins(sys);

				partiePrison(joueur);

				int lance = partieJetDe(joueur);

				partieDeplacement(joueur, lance);				
				
				menu(joueur);

			}while(recommence);
			if(joueur.getMoney() < 0){
				joueur.gameOver();
			}
		}
	}
	
	private void partiePrison(Player joueur){
		// si le joueur est en prison
		// soit il a la carte sortie de prison
		// soit il peux payer 50
		// soit il DOIT payer 50
		if (joueur.getPrison() > 0) {
			int tmp = joueur.searchInv(Carte.SORTIE_PRISON_TITRE);
			boolean rep;
			if (tmp > -1) {

				// TODO affichage(demande au joueur d'utiliser sa
				// carte)FAIT EN CONSOLE
				rep = sys.getBool("Voulez-vous utiliser votre carte de sortie de prison ?\n");
				if (rep) {
					Carte tmp2 = joueur.popInv(tmp);
					tmp2.returnPaquet();
					joueur.setPrison(0);
				}
			}
			if (joueur.getPrison() > 1) {
				// TODO affichage(demande au joueur de payer 50 pour
				// sortir) FAIT EN CONSOLE
				rep = sys.getBool("Voulez-vous payer 50$ pour sortir de prison ?");
				;
				if (rep/* payer 50 */) {
					joueur.addMoney(-50);
					joueur.setPrison(0);
				}
			} else if (joueur.getPrison() == 1) {
				// TODO affichage (popup "vous etes oblige de payer 50
				// pour sortir" "ok") FAIT EN CONSOLE
				sys.print("Vous etes oblige de payer 50$ pour sortir de prison. \n");
				joueur.addMoney(-50);
				joueur.setPrison(0);
			}
		}
	}
	private int partieJetDe(Player joueur){
		// lance de de et deplacement apres les problemes de prison
		// TODO affichage(lance de de du joueur num)FAIT EN CONSOLE
		int lance1 = de.jet();
		int lance2 = de.jet();
		
		//TODO cheat de
		if(Jeu.CHEAT_TOUR_INFINI && Jeu.CHEAT_DE){
			lance1 = sys.getInt("Avancer de ?");
			lance2 = 0;
		}
		else if(Jeu.CHEAT_DE){
			lance1 = sys.getInt("Premier de ?");
			lance2 = sys.getInt("Deuxieme de?");
		}
		
		
		sys.print(joueur.getName() + " lance les des et fait " + lance1 + " et " + lance2 + " (" + (lance1 + lance2) + ")"
				+ (lance1==lance2 ? "DOUBLE" : "") + "\n");
		if (lance1 == lance2) {
			nbrDouble++;
			//TODO cheat tour infini
			if (!(Jeu.CHEAT_TOUR_INFINI) && nbrDouble == 3) {
				// TODO affichage(3e double)FAIT EN CONSOLE
				sys.print("C'est le 3e double! Prison direct!");
				joueur.tpto(10, sys, terrain);
				joueur.setPrison(3);
			}
			else{
				recommence = true;
				joueur.setPrison(0);
			}
		}
		
		return lance1 + lance2;
	}
	private void partieDeplacement(Player joueur, int lance){
		if (joueur.getPrison() == 0) {
			int tmp = lance + joueur.getPos();
			if (tmp > 39) {// passer par la case depart
				// la derniere case est la num39 (case depart = 0)
				// donc si il est sur la 40 c'est donc qu'il est sur la
				// 0
				tmp = tmp - 40;
				joueur.addMoney(200);
				//TODO affichage(case depart)FAIT EN CONSOLE
				sys.print(joueur.getName() + " passe par la case depart!");
			}
			joueur.moveto(tmp, sys, terrain);

			// action quand il tombe sur une case
			terrain.get(joueur.getPos()).action(joueur, lance, sys, terrain);
		}
		
		if(joueur.getPrison()>0)//etre en prison stop le double
			recommence = false;
	}
	
	//===========================
	//===== Fonctions Menus =====
	//===========================
	private void menu(Player joueur){
		// TODO affichage FAIT EN CONSOLE
		int rep = 0;
		do{
			rep = sys.getInt("\n\nQue souhaitez vous faire ?\n"
					+ "(Il vous reste " + joueur.getMoney() + "e)\n"
					+ "1)Gerer les terrains ((de)construire des maison / hypothequer)\n"
					+ "2)Echanger avec un joueur\n"
					+ "3)Abandonner la partie\n"
					+ "4)Stop le tour\n"//TODO cheat tour infini
					+ "0)Finir le tour\n"
					+ "(Si vous finissez avec de l'argent negatif vous perdez)\n", 0, 3);
			switch (rep) {
			case 1:
				joueur.trierPossession(terrain);
				//permet d'afficher les possessions dans l'ordre du terrain
				//et pas l'ordre d'achat
				this.gererTerrain(joueur);
				break;
			case 2:
				joueur.trierPossession(terrain);
				//permet d'afficher les possessions dans l'ordre du terrain
				//et pas l'ordre d'achat
				this.echange(joueur);
				break;
			case 3 : 
				joueur.gameOver();
				rep=0;
				break;
			case 0:
				//TODO cheat tour infini
				if(Jeu.CHEAT_TOUR_INFINI)
					recommence = true;
				break;
			case 4:
				recommence = false;
				rep = 0;
				break;
			}
		}while(rep != 0);
	}
	
	private void gererTerrain(Player joueur){
		ArrayList<Propriete> tmp = joueur.getPossession();

		if (tmp.size() == 0) {
			// TODO affichage(gere LES terrains)FAIT EN CONSOLE
			sys.print("Vous ne possedez pas de terrain!");
		} else {
			String message = "Quel terrain voulez vous gerer?\n" 
				+ "(les - signifient que le terrain est hypotheque)\n";
			int it = 0;
			for (Propriete e : tmp) {
				it++;
				message += it + ")" + e.toString() + "\n";
			}
			message += "0) RETOUR\n";
			int rep = sys.getInt(message, 0, it);
			if (rep != 0)
				gerer(joueur, rep - 1);
		}
	}
	private void gerer(Player joueur, int num){
		//TODO affichage(gerer un terrain)FAIT EN CONSOLE
		Propriete tmp = joueur.getPossession(num);
		boolean estTerrain = tmp instanceof Terrain;
		//attention le 1e terrain est le num 0
		String message = "Que voulez-vous faire?\n"
				+ tmp.toString() + "\n"
				+"1) "
				+ (tmp.estHypo()? "Lever l'hypotheque (" + 11*tmp.getPrix()/20 +"$)"
						: "Hypothequer (" + tmp.getPrix()/2 + "$)" )
				+ "\n";
		
		if(estTerrain)
			message += "2) Construire une maison\n"
					+ "3) Deconstruire une maison\n";
		
		message += "0) RETOUR";
		
		switch(sys.getInt(message, 0, (estTerrain?3:1))){
		case 1:
			try{
				if(tmp.estHypo())
					tmp.leverHypo();
				else
					tmp.hypothequer();
				//Leve une hypotheque si pas de possesseur
				//ou deja hypotheque
				//(deja gere mais exception leve donc catch obligatoire)
			}
			catch(Exception e){
				e.printStackTrace();
			}
			break;
		case 2:
			try{
				((Terrain)tmp).construire();
			}
			catch(Exception e){
				sys.print(e.getMessage() + "\n");
			}
			break;
		case 3:
			try{
				((Terrain)tmp).deconstruire();
			}
			catch(Exception e){
				sys.print(e.getMessage() + "\n");
			}
			break;
		}
	}
	
	private void echange(Player joueur){
		int rep = 0;
		ArrayList<Player> TMLP = new ArrayList<Player>();
		String message = "Avec qui voulez-vous echanger?\n";
		int it = 0;
		for (Player e : players) {
			if (!e.aPerdu() && !e.equals(joueur)) {
				it++;
				message += it + ") " + e.getName() + " (" + e.getMoney() + "$)\n";
				TMLP.add(e);
			}
		}
		message += "0) RETOUR";
		rep = sys.getInt(message, 0, it);

		if (rep != 0)
			echangeJoueur(joueur, TMLP.get(rep-1));
	}
	
	private void echangeJoueur(Player joueur1, Player joueur2){
		int rep = 0;
		money = 0;
		echj1 = new ArrayList<Propriete>();
		echj2 = new ArrayList<Propriete>();
		echcj1 = new ArrayList<Carte>();
		echcj2 = new ArrayList<Carte>();
		
		do{
			String message = messageEchange(joueur1, joueur2);
			
			//TODO affichage(echange)FAIT EN CONSOLE
			sys.print(message);
			message = "\nQue voulez vous faire?\n"
					+"1)Gerer l'argent echange\n"
					+"2)Gerer l'inventaire que vous echangez\n"
					+"3)Gerer l'inventaire de l'autre joueur\n"
					+"4)Confirmer l'echange\n"
					+"0)RETOUR";
			
			rep = sys.getInt(message,0,4);
			switch(rep){
			case 1:
				//gerer l'argent
				money = sys.getInt("Combien d'argent voulez-vous echanger?\n"
						+ "(Neg si l'autre joueur doit vous passer de l'argent)"
						,-(joueur2.getMoney()),joueur1.getMoney());
				//min: l'argent de l'autre joueur
				//mais en negatif
				//max: l'argent du joueur actuel
				break;
			case 2:
				//gerer l'inv 1
				echangerquoi(joueur1, echj1, echcj1);
				break;
			case 3:
				//gerer l'inv 2
				echangerquoi(joueur2, echj2, echcj2);
				break;
			case 4:
				accepterEchange(joueur1, joueur2);
				rep = 0;
				break;
			}
		}while(rep != 0);
	}
	private void echangerquoi(Player joueur, ArrayList<Propriete> ech, ArrayList<Carte> echc){
		int rep = 0;
		do{
			String message = "Inventaire disponible:\n"
					+"(le x signifie qu'il est deja en echange,)\n"
					+"(re-selectionner pour l'enlever de l'echange)\n";
			int it = 0;
			for(Propriete e : joueur.getPossession()){
				it++;
				if(ech.contains(e))
					message+="(x) ";
				else
					message+="( ) ";
				
				message+= it + ")" + e.toString() + "\n";
			}
			int tmp = it;
			//sauvegarder quand on passe d'une propriete a une carte
			for(Carte e : joueur.getInv()){
				it++;
				if(echc.contains(e))
					message+="(x) ";
				else
					message+="( ) ";
				
				message+= it + ")" + e.toString() + "\n";
			}
			message += "    0) RETOUR (confirmer)";
			
			rep = sys.getInt(message, 0, it);
			if(rep != 0){
				if(rep <= tmp){//propriete
					Propriete TMP = joueur.getPossession(rep-1);
					if(ech.contains(TMP))
						ech.remove(TMP);
					else
						ech.add(TMP);
				}
				else{//carte
					Carte TMP = joueur.getInv(rep-tmp-1);
					if(echc.contains(TMP))
						echc.remove(TMP);
					else
						echc.add(TMP);
				}
			}
		}while(rep != 0);
	}
	private void accepterEchange(Player joueur1, Player joueur2){
		if(sys.getBool(joueur2.getName() + ", acceptez-vous l'echange?")){
			//gerer l'argent des joueur
			joueur1.subMoney(money);
			joueur2.addMoney(money);
			
			//gerer le passage de l'inventaire du joueur 1 au joueur 2
			for(Propriete e: echj1){
				boolean hyp;
				joueur1.popPossession(joueur1.searchPossession(e));
				hyp = e.estHypo();
				e.clear();
				if(hyp)
					e.hypothequer();
				joueur2.addPossession(e);
			}
			for(Carte e: echcj1){
				joueur1.popInv(joueur1.searchInv(e));
				joueur2.addInv(e);
			}
			
			//gerer le passage de l'inventaire du joueur2 au joueur 1
			for(Propriete e: echj2){
				boolean hyp;
				joueur2.popPossession(joueur2.searchPossession(e));
				hyp = e.estHypo();
				e.clear();
				if(hyp)
					e.hypothequer();
				joueur1.addPossession(e);
			}
			for(Carte e: echcj2){
				joueur2.popInv(joueur2.searchInv(e));
				joueur1.addInv(e);
			}
		}
	}
	private String messageEchange(Player joueur1, Player joueur2){
		//partie joueur1
		String message = "\n\n\n===================================\n"
				+ "Actuellement vous donnez:\n"
				+ "(Attention les maisons disparraissent a l'echange)";
		
		if(money <= 0 && echj1.size() == 0)
			message += "    rien\n";
		else{
			if(money > 0)
				message += "    " + money + "$\n";
			for(Propriete e : echj1)
				message += "    " + e.toString() + "\n";

			for(Carte e: echcj1)
				message += "    " + e.getTitre() + "\n";
		}
		
		//partie joueur2
		message += "\nEt " + joueur2.getName() + " vous donne:\n"
				+ "(Attention les maisons disparraissent a l'echange)";
		
		if(money >= 0 && echj2.size() == 0)
			message += "    rien\n";
		else{
			if(money < 0)
				message += "    " + (-money) + "$\n";
			
			for(Propriete e : echj2)
				message += "    " + e.toString() + "\n";

			for(Carte e: echcj1)
				message += "    " + e.getTitre() + "\n";
		}
		return message;
	}
}