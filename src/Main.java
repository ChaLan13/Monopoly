//imports
import java.util.ArrayList;

import common.Carte;
import common.Case;
import monopoly.Jeu;
import monopoly.Player;
import terrain.Parc;
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
			CreationCase(terrain, parc);
			CreationJoueur(players);
			
			jeu = new Jeu(sys, terrain, players);
			
			boolean recommence;
			do{
				jeu.debut();
				recommence = sys.getBool("Voulez vous rejouer une partie?");
			}while(recommence);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	public static void main(String[] args) {
		launch(args);
	}
	
	private void CreationCarte(Paquet chance, Paquet communaute, Parc parc){
		
	}
	
	private void CreationCase(ArrayList<Case> terrain, Parc parc){
		
	}
	
	private void CreationJoueur(ArrayList<Player> players){
		
	}
	//création des cases etc
	//NE PAS OUBLIER
	//de lier le parc aux cartes monopoli (cagnote)
	//lier les joueurs à la carte anniversaire
	//lier les terrains entre eux
	//lier les gares
	//les compagnies
}