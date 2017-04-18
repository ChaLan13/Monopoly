//imports
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
	
	//création des cases etc
	//NE PAS OUBLIER
	//de lier le parc aux cartes monopoli (cagnote)
	//lier les joueurs à la carte anniversaire
	//lier les terrains entre eux
	//lier les gares
	//les compagnies
}