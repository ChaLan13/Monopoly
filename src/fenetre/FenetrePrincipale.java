package fenetre;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FenetrePrincipale{
	
	private Stage stage;
	
	public FenetrePrincipale(Stage stage) {
		try{
			this.stage = stage;
		
		final URL url= getClass().getResource("monopolyPaneVersion.fxml");
		final FXMLLoader loader = new FXMLLoader(url);
		final Pane root = (Pane) loader.load();
		Scene scene = new Scene(root,1000,1000);
		stage.setScene(scene);
		
        stage.show();
		}
		catch(IOException ex){
			System.err.println("Erreur de chargement");
		}
    
	}
	
	/* public GridPane createGridPane(){
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(0, 0, 0, 0));
		
		
		ImageView depart = new ImageView(new Image("images/Depart.jpg"));
		grid.add(depart, 10, 10);
		ImageView belleville = new ImageView(new Image("images/Belleville.jpg"));
		grid.add(belleville, 9, 10);
		ImageView caisse1 = new ImageView(new Image("images/caisse1.jpg"));
		grid.add(caisse1, 8, 10);
		ImageView lecourbe = new ImageView(new Image("images/Lecourbe.jpg"));
		grid.add(lecourbe, 7, 10);
		ImageView impot = new ImageView(new Image("images/Impot.jpg"));
		grid.add(impot, 6, 10);
		ImageView montparnasse = new ImageView(new Image("images/Montparnasse.jpg"));
		grid.add(montparnasse, 5, 10);
		ImageView vaugirard = new ImageView(new Image("images/Vaugirard.jpg"));
		grid.add(vaugirard, 4, 10);
		ImageView chance1 = new ImageView(new Image("images/chance1.jpg"));
		grid.add(chance1, 3, 10);
		ImageView courcelles = new ImageView(new Image("images/Courcelles.jpg"));
		grid.add(courcelles, 2, 10);
		ImageView republique = new ImageView(new Image("images/Republique.jpg"));
		grid.add(republique, 1, 10);
		ImageView visitePrison = new ImageView(new Image("images/VisitePrison.jpg"));
		grid.add(visitePrison, 0, 10);
		ImageView villette = new ImageView(new Image("images/Villette.jpg"));
		grid.add(villette, 0, 9);
		
		return grid;
		
	}*/
	
	
	public Stage getStage() {
		return stage;
	}

}