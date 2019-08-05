package memo;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;


import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class memoTest extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("memoTest.fxml"));
	
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("NoName.txt");
		primaryStage.show();
	}
	

	public static void main(String[] args) {
		launch(args);
	}
}
