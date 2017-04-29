package fenetre;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FenetrePrincipale {
	
	private Stage stage;
	public FenetrePrincipale(Stage stage) {
		this.stage = stage;
		Pane root = new Pane();
		Scene scene = new Scene(root,400,400);
		stage.setScene(scene);
		Image mono = new Image(FenetrePrincipale.class.getResourceAsStream("images/plateau.jpg"));
		ImageView iv1 = new ImageView(mono);
		iv1.setFitHeight(100);
		iv1.setPreserveRatio(true);
        root.getChildren().add(iv1);
        stage.show();
	}
	
	
	public Stage getStage() {
		return stage;
	}
}