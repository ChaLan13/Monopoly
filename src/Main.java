//imports
import java.util.ArrayList;

import common.Carte;
import common.Case;
import monopoly.CarteChoix;
import monopoly.CarteMono;
import monopoly.CarteTirer;
import monopoly.Jeu;
import monopoly.Player;
import terrain.Depart;
import terrain.Gare;
import terrain.Impot;
import terrain.Parc;
import terrain.Pioche;
import terrain.Terrain;
import common.Paquet;
import fenetre.Affichage;
import fenetre.FenetrePrincipale;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		Affichage sys = new Affichage();
		Paquet chance, communaute;
		ArrayList<Case> terrain = new ArrayList<Case>();
		ArrayList<Player> players = new ArrayList<Player>();
		Parc parc;
		Jeu jeu;
		
		try {
			//new FenetrePrincipale(primaryStage);
			chance = new Paquet("chance");
			communaute = new Paquet("communaute");
			parc = new Parc("Parc Gratuit");
			CreationCarte(chance, communaute, parc);
			CreationCase(terrain, parc, chance, communaute);
			CreationJoueur(players, sys);
			
			jeu = new Jeu(sys, terrain, players);
			
			boolean recommence;
			do{
				jeu.debut();
				recommence = sys.getBool("\n\n\nVoulez vous rejouer une partie?");
			}while(recommence);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		sys.close();
	}
	public static void main(String[] args) {
		launch(args);
	}
	
	private void CreationCarte(Paquet chance, Paquet communaute, Parc parc){
		try{
			communaute.add(new CarteChoix("", "Payez une amande de 10€ ou bien tirez une carte \"CHANCE\"", new CarteMono(true, "", "", parc, -10), new CarteTirer(true, "", "", chance)));
		}
		catch(Exception e){
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	private void CreationCase(ArrayList<Case> terrain, Parc parc, Paquet chance, Paquet communaute){
		try{
			Depart case0 = new Depart("Case Depart");
			Terrain case1 = new Terrain("Boulevard de Belleville", 60,new int[]{2, 10, 30, 90, 160, 250}, 50);
			Pioche case2 = new Pioche("Caisse de communaute", communaute);
			Terrain case3 = new Terrain("Rue Lecourbe", 60, new int[]{4, 20, 60, 180, 320, 450}, 50);
			Impot case4 = new Impot("Impot sur le revenu", 200);
			Gare case5 = new Gare("Gare Montparnasse");
		}
		catch(Exception e){
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	private void CreationJoueur(ArrayList<Player> players, Affichage sys){
		boolean rep;
		do{
			rep = sys.getBool("Voulez-vous ajouter un joueur?");
			if(rep){
				players.add(new Player(sys.getString("Saisissez le pseudo:")));
			}
		}while(rep);
	}
	//création des cases etc
	//NE PAS OUBLIER
	//de lier le parc aux cartes monopoli (cagnote)
	//lier les joueurs à la carte anniversaire
	//lier les terrains entre eux
	//lier les gares
	//les compagnies
}