package fenetre;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class FenetrePrincipale {
	private Stage stage;
	public FenetrePrincipale(Stage stage) {
		this.stage = stage;
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root,400,400);
		//inutile -->
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
	public Stage getStage() {
		return stage;
	}
}