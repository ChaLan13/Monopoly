//imports
import common.Carte;
import common.De;
import common.Player;
import monopoly.Jeu;
import common.Paquet;
import common.Plateau;

import fenetre.FenetrePrincipale;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			new FenetrePrincipale(primaryStage);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
}