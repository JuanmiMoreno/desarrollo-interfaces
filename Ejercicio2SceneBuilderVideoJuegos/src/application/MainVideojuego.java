package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


public class MainVideojuego extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Pane root = (Pane)FXMLLoader.load(getClass().getResource("VideoJuego.fxml"));
			Scene scene = new Scene(root,600,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Videojuegos");
			primaryStage.getIcons().add(new Image("/icono/iconoVideoJuego.png"));
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
