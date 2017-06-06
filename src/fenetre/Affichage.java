package fenetre;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import cartes.CarteChoix;
import common.Carte;
import common.De;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import monopoly.Player;
import terrain.Compagnie;
import terrain.Gare;
import terrain.Impot;

public class Affichage {
	BufferedReader sc;

	// ========================
	// ===== Constructeur =====
	// ========================
	public Affichage() {
		// scanner close a la fin du main
		sc = new BufferedReader(new InputStreamReader(System.in));
		
	}


	// ==============================
	// ===== Fonctions Basiques =====
	// ==============================
	public void print(String message) {
		System.out.println(message);
	}

	public String getString(String message) {
		try {
			this.print(message);
			String rep;
			rep = sc.readLine();
			return rep;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	// affiche le message puis demande un booleen
	// ne pas mettre de \n a la fin de message
	// plein de possibilite (o, O, oui, Oui, y, yes, Y, Yes ...)
	public boolean getBool(String message) {
		try {
			boolean suite = true;

			while (suite) {
				suite = false;

				this.print(message + "(o/n)");
				String rep = sc.readLine();
				switch (rep) {
				case "o":
				case "O":
				case "y":
				case "Y":
				case "yes":
				case "YES":
				case "oui":
				case "OUI":
				case "Oui":
				case "Yes":
					return true;

				case "n":
				case "N":
				case "no":
				case "NO":
				case "non":
				case "NON":
				case "Non":
				case "No":
					return false;

				default:
					print("La saisie est incorrecte.\n");
					suite = true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public int getInt(String message) {
		return getInt(message, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	public int getInt(String message, int min) {
		return getInt(message, min, Integer.MAX_VALUE);
	}

	public int getInt(String message, int min, int max) {
		try {
			boolean suite = true;
			String rep;
			int repint;

			while (suite) {
				suite = false;

				if (min != Integer.MIN_VALUE) {
					if (max != Integer.MAX_VALUE)
						message += "(entre " + min + " et " + max + ")";
					else
						message += "(min " + min + ")";
				} else {
					if (max != Integer.MAX_VALUE)
						message += "(max " + max + ")";
				}
				this.print(message);
				rep = sc.readLine();

				try {
					repint = Integer.parseInt(rep);
					if (repint < min || repint > max) {
						print("La saisie est incorrecte.\n");
						suite = true;
					} else
						return repint;
				} catch (NumberFormatException e) {
					print("La saisie est incorrecte.\n");
					suite = true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return min;
	}

	// ==============================
	// ===== Fonctions Precises =====
	// ==============================

	
	//===== Jeu =====
	
	// Jeu.ChoisirPremier
	public void LanceDeDebut(Player joueur, int score) {
		this.print("Le joueur " + joueur.getName() + " a fait un lancer egal a " + score + "\n");
	}

	// Jeu.Debut
	public void JoueurQuiCommence(Player joueur) {
		this.print("Le joueur " + joueur.getName() + " commence. \n");
	}

	// Jeu.PartiePrison

	public boolean DemandeCartePrison() {
		return this.getBool("Voulez-vous utiliser votre carte de sortie de prison ?\n");
	}

	public boolean DemandePayerPrison() {
		return this.getBool("Voulez-vous payer 50$ pour sortir de prison ?");
	}

	public void AfficherPayerPrison() {
		this.print("Vous etes oblige de payer 50$ pour sortir de prison. \n");
	}

	// Jeu.PartieJetDe
	public int JetDe1(De de) {
		return de.jet();
	}

	public int JetDe2(De de) {
		return de.jet();
	}

	public int CheatDe() {
		return this.getInt("Valeur du de? ", 1, 6);
	}

	public int CheatDeplacement() {
		return this.getInt("Avancer de combien? ");
	}

	public void AfficherLance(Player joueur, int lance1, int lance2) {
		this.print(joueur.getName() + " lance les des et fait " + lance1 + " et " + lance2 + " (" + (lance1 + lance2)
				+ ")" + (lance1 == lance2 ? "DOUBLE" : "") + "\n");
	}

	public void AfficherTriple() {
		this.print("C'est le 3e double! Prison direct!");
	}

	// Jeu.PartieDeplacement
	public void FinirTourPlateau(Player joueur) {
		this.print(joueur.getName() + " fait un tour de plateau et gagne 200$!");
	}

	
	//===== Carte =====
	
	// Carte.action
	public void AfficherCarte(Player joueur, Carte carte) {
		this.print(
				joueur.getName() + " tire une carte " + carte.getPaquet().getName() + "\n" + carte.toString() + "\n");
	}

	// CarteAnniv.action
	public void AfficherCarteAnniv(Player joueur, int somme) {
		this.print("Au total, " + joueur.getName() + (somme > 0 ? " gagne " : " paye ") + (somme) + "$\n");
	}

	// CarteReparation.action
	public void AfficherReparation(Player joueur, int somme) {
		this.print(joueur.getName() + " doit payer " + somme + "$ au total.\n");
	}

	// CaseDepart.action
	public void AfficherDepart(Player joueur) {
		this.print(joueur.getName() + " tombe sur la case depart.\n");
	}

	// CarteChoix.action
	public int AfficherChoixCarte(){
		return this.getInt("Choix 1 ou 2?", 1, 2);
	}
	
	
	//===== Terrain =====
	
	// Impot.action
	public void Impot(Player joueur, Impot _case) {
		this.print(_case.getName() + "!\n" + joueur.getName() + " doit payer " + _case.getVal() + "$.\n");
	}

	// Compagnie.actionCarte
	public int JetDe1Compagnie(De de) {
		return de.jet();
	}

	public int JetDe2Compagnie(De de) {
		return de.jet();
	}

	public void CompagnieCarte(Compagnie compagnie, Player joueur) {
		this.print(joueur.getName() + " tombe sur " + compagnie.getName() + " appartenant a " + compagnie.getPossesseur().getName() + ".\n");
	}

	// Gare.actionCarte
	public void GareCarte(Gare gare, Player joueur){
		this.print(joueur.getName() + " tombe sur " + gare.getName() + " appartenant a " + gare.getPossesseur().getName() + ".\n");
	}
	
	public void GareCarteHypotheque(){
		this.print("Le terrain est hypotheque!");
	}
	
	public void GareCartePayer(int montant){
		this.print("Le montant du loyer est: " + montant/2 + "x2 = " + montant + "$\n");
	}
}
