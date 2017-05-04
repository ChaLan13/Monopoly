//imports
import java.util.ArrayList;

import common.Carte;
import common.Case;
import monopoly.CarteAnniv;
import monopoly.CarteChoix;
import monopoly.CarteMono;
import monopoly.CarteReparation;
import monopoly.CarteTirer;
import monopoly.Jeu;
import monopoly.Player;
import terrain.Compagnie;
import terrain.Depart;
import terrain.Gare;
import terrain.Impot;
import terrain.Parc;
import terrain.Pioche;
import terrain.Police;
import terrain.Prison;
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
			CreationCarte(chance, communaute, parc, players);
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
	
	private void CreationCarte(Paquet chance, Paquet communaute, Parc parc, ArrayList<Player> players){
		try{
			communaute.add(new CarteReparation("Vous devez faire des travaux sur vos proprietes:", "Versez 40€ pour chaque maison et 115€ pour chaque hotel que vous possedez.", parc, 40, 115));
			communaute.add(new CarteMono("", "Les impots vous remboursent 20€.", parc, 20));
			communaute.add(new CarteMono("", "Votre placement vous rapporte. Recevez 100€.", parc, 100));
			communaute.add(new CarteMono("", "Vous heritez de 100€.", parc, 100));
			communaute.add(new CarteMono("Visite chez le medecin:", "Payez 50€.", parc, -50));
			communaute.add(new CarteMono(Carte.PRISON_TITRE, "Allez tout droit en prison,\nNe passez pas par la case depart,\nNe recevez pas 200€.", parc, 10, true));
			communaute.add(new Carte(Carte.SORTIE_PRISON_TITRE, "Cette carte peut etre conservee jusqu'a ce qu'elle soit utilisee ou vendue."));
			communaute.add(new CarteMono("Frais de scolarite.","Payez 50€.", parc, -50));
			communaute.add(new CarteMono("", "La vente de votre stock vous rapporte 50€.", parc, 50));
			communaute.add(new CarteMono("Vous avez gagne le 2e prix de beaute.", "Recevez 10€.", parc, 10));
			communaute.add(new CarteAnniv("C'est votre anniversaire:", "Chaque joueur doit vous donner 10€.", 10, players));
			communaute.add(new CarteMono("Erreur de la banque en votre faveur.","Recevez 200€.", parc, 200));
			communaute.add(new CarteMono("Avancez jusqu'a la case depart","(Recevez 400€).", parc, 0, true));
			communaute.add(new CarteMono("Frais d'hospitalisation.","Payez 100€.", parc, -100));
			communaute.add(new CarteMono("Commission d'expert immobilier.","Recevez 25€.", parc, 25));
			communaute.add(new CarteMono("", "Votre assurance vie vous rapporte 100€.", parc, 100));
			
			chance.add(new CarteMono("", "Reculez de 3 cases.", parc, -3, false));
			chance.add(new CarteMono("Avancez au boulevard de la villette.", "Si vous apssez par la case depart, recevez 200€.", parc, 11, true));
			chance.add(new CarteMono("Rendez-vous a l'avenue Henri-Martin.", "Si vous apssez par la case depart, recevez 200€.", parc, 24, true));
			chance.add(new CarteMono(Carte.PRISON_TITRE, "Allez tout droit en prison,\nNe passez pas par la case depart,\nNe recevez pas 200€.", parc, 10, true));
			chance.add(new CarteMono("Avancez jusqu'a la case depart.", "(Recevez 200€).", parc, 0, true));
			chance.add(new CarteMono(Carte.GARE_PLUS_PROCHE_TITRE,"Si elle n'appartient a personne, vous poouvez l'acheter aupres de la banque.\nSi elle appartient deja a un autre joueur, vous devez lui payer deux fois le loyer demande.", parc, 0, true));
			chance.add(new CarteMono(Carte.COMPAGNIE_PLUS_PROCHE_TITRE, "Si elle n'appartient a personne, vous pouvez l'acheter aupres de la banque.\nSi elle appartient deja a un joueur, vous devez lui payer le montant du total de vos des multiplie par 10.", parc, 0, true));
			chance.add(new CarteMono(Carte.GARE_PLUS_PROCHE_TITRE,"Si elle n'appartient a personne, vous poouvez l'acheter aupres de la banque.\nSi elle appartient deja a un autre joueur, vous devez lui payer deux fois le loyer demande.", parc, 0, true));
			chance.add(new CarteMono("Votre immeuble et votre pret rapportent.","Touchez 150€.", parc, 150));
			chance.add(new CarteReparation("Vous faites des reparations sur toutes vos proprietes:","Versez 25€ pour chaque maison et 100€ pour chaque hotel que vous possedez.", parc, 24, 100));
			chance.add(new CarteMono("", "Rendez-vous a la rue de la paix.", parc, 39, true));
			chance.add(new CarteMono("Allez a la gare montparnasse.","Si vous passez par la case depart, recevez 200€.", parc, 5, true));
			chance.add(new CarteMono("", "La banque vous verse un dividende de 50€.", parc, 50));
			chance.add(new CarteMono("Amande pour exces de vitesse:","Payez 15€", parc, -15));
			chance.add(new CarteAnniv("Vous avez ete elu president du conseil d'administration.","Versez 50 a chaque joueur.", -50, players));
			chance.add(new Carte(Carte.SORTIE_PRISON_TITRE,"Cette carte peut etre conservee jusqu'a ce qu'elle soit utilisee ou vendue."));
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
			Impot case4 = new Impot("Impot sur le revenu",parc, 200);
			Gare case5 = new Gare("Gare Montparnasse");
			Terrain case6 = new Terrain("Rue de Vaugirard", 100, new int[]{6, 30, 90, 270, 400, 550}, 50);
			Pioche case7 = new Pioche("Chance", chance);
			Terrain case8 = new Terrain("Rue de Courcelles", 100, new int[]{6, 30, 90, 270, 400, 550}, 50);
			Terrain case9 = new Terrain("Avenue de la Republique", 120, new int[]{8, 40, 100, 300, 450, 600}, 50);
			
			case1.add(case3);
			
			case6.add(case8, case9);
			
			Prison case10 = new Prison("Simple Visite de prison");
			Terrain case11 = new Terrain("Boulevard de la Villette", 140, new int[]{10, 50, 150, 450, 625, 750}, 100);
			Compagnie case12 = new Compagnie("Compagnie de distribution d'electricite");
			Terrain case13 = new Terrain("Avenue de Neuilly", 140, new int[]{10, 50, 150, 450, 625, 750}, 100);
			Terrain case14 = new Terrain("Rue de Paradis", 160, new int[]{12, 60, 180, 500, 700, 900}, 100);
			Gare case15 = new Gare("Gare de Lyon");
			Terrain case16 = new Terrain("Avenue de Mozart", 180, new int[]{14, 70, 200, 550, 750, 950}, 100);
			Pioche case17 = new Pioche("Caisse de communaute", communaute);
			Terrain case18 = new Terrain("Boulevard St-Michel", 180, new int[]{14, 70, 200, 550, 750, 950}, 100);
			Terrain case19 = new Terrain("Place Pigalle", 200, new int[]{16, 80, 220, 600, 800, 1000}, 100);
			
			case11.add(case13, case14);
			
			case16.add(case18, case19);
			
			Parc case20 = parc;
			Terrain case21 = new Terrain("Avenue Matignon", 220, new int[]{1, 90, 250, 700, 875, 1050}, 150);
			Pioche case22 = new Pioche("Chance", chance);
			Terrain case23 = new Terrain("Boulevard Malesherbes", 220, new int[]{18, 90, 250, 700, 875, 1050}, 150);
			Terrain case24 = new Terrain("Avenue Henri-Martin", 240, new int[]{20, 100, 300, 750, 925, 1100}, 150);
			Gare case25 = new Gare("Gare du nord");
			Terrain case26 = new Terrain("Faubourg St-Honore", 260, new int[]{22, 110, 330, 800, 975, 1150}, 150);
			Terrain case27 = new Terrain("Place de la Bourse", 260, new int[]{22, 110, 330, 800, 975, 1150}, 150);
			Compagnie case28 = new Compagnie("Compagnie de distribution des eaux");
			Terrain case29 = new Terrain("Rue la Fayette", 280, new int[]{24, 120, 360, 850, 1025, 1200}, 150);
			
			case21.add(case23, case24);
			
			case26.add(case27, case29);
			
			case28.add(case12);
			
			Police case30 = new Police("Allez en Prison");
			Terrain case31 = new Terrain("Avenue de Breteuil", 300, new int[]{26, 130, 390, 900, 1100, 1275}, 200);
			Terrain case32 = new Terrain("Avenue Foch", 300, new int[]{26, 130, 390, 900, 1100, 1275}, 200);
			Pioche case33 = new Pioche("Caisse de communaute", communaute);
			Terrain case34 = new Terrain("Boulevard des Capucines", 320, new int[]{28, 150, 450, 1000, 1200, 1400}, 200);
			Gare case35 = new Gare("Gare Saint-Lazare");
			Pioche case36 = new Pioche("Chance", chance);
			Terrain case37 = new Terrain("Avenue des Champs-Elysees", 350, new int[]{35, 175, 500, 1100, 1300, 1500}, 200);
			Impot case38 = new Impot("Taxe de luxe",parc, 100);
			Terrain case39 = new Terrain("Rue de la Paix", 400, new int[]{50, 200, 600, 1400, 1700, 2000}, 200);
			
			case31.add(case32, case34);
			
			case39.add(case39);
			
			case5.add(case15, case25, case35);
			
			terrain.add(case0);
			terrain.add(case1);
			terrain.add(case2);
			terrain.add(case3);
			terrain.add(case4);
			terrain.add(case5);
			terrain.add(case6);
			terrain.add(case7);
			terrain.add(case8);
			terrain.add(case9);
			terrain.add(case10);
			terrain.add(case11);
			terrain.add(case12);
			terrain.add(case13);
			terrain.add(case14);
			terrain.add(case15);
			terrain.add(case16);
			terrain.add(case17);
			terrain.add(case18);
			terrain.add(case19);
			terrain.add(case20);
			terrain.add(case21);
			terrain.add(case22);
			terrain.add(case23);
			terrain.add(case24);
			terrain.add(case25);
			terrain.add(case26);
			terrain.add(case27);
			terrain.add(case28);
			terrain.add(case29);
			terrain.add(case30);
			terrain.add(case31);
			terrain.add(case32);
			terrain.add(case33);
			terrain.add(case34);
			terrain.add(case35);
			terrain.add(case36);
			terrain.add(case37);
			terrain.add(case38);
			terrain.add(case39);
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