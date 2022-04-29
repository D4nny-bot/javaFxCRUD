package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.Parent;

import java.net.URI;
import java.nio.file.Paths;


public class MainCrud extends Application {
	
	//public static Stage primaryStage = null;
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		try {
			URI uri = Paths.get("src/view/Principal.fxml").toAbsolutePath().toUri();
			System.out.println("Uri-- " + uri.toString());
			Parent root = FXMLLoader.load(uri.toURL());
			Scene scene = new Scene(root);
			
			/*Parent root = FXMLLoader.load(getClass().getResource("/view/Principal.fxml"));
			primaryStage.initStyle(StageStyle.UNDECORATED);
			//primaryStage.setResizable(false);
	        primaryStage.setScene(new Scene(root, 329, 484));
	        this.primaryStage = primaryStage;
	        primaryStage.show();*/
			//BorderPane root = new BorderPane();
			//Scene scene = new Scene(root,400,400);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
