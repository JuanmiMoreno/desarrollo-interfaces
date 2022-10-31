package application;
	
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {

			Rectangle r1 = new Rectangle();
			r1.setHeight(30);
			r1.setWidth(400);
			r1.setFill(Color.DARKGREEN);
			
			Rectangle r2 = new Rectangle();
			r2.setHeight(400);
			r2.setWidth(30);
			r2.setFill(Color.LIGHTGREEN);
			
			Rectangle r3 = new Rectangle();
			r3.setHeight(400);
			r3.setWidth(30);
			r3.setFill(Color.LIGHTBLUE);
		
			Rectangle r4 = new Rectangle();
			r4.setHeight(30);
			r4.setWidth(400);
			r4.setFill(Color.GREEN);;
			
			Rectangle r5 = new Rectangle();
			r5.setHeight(400);
			r5.setWidth(400);
			r5.setFill(Color.BLUE);;
			
			StackPane panel = new StackPane();
			
			
			panel.setAlignment(r1, Pos.TOP_CENTER);
			panel.setAlignment(r2,Pos.CENTER_LEFT);
			panel.setAlignment(r3, Pos.CENTER_RIGHT);
			panel.setAlignment(r4, Pos.BOTTOM_CENTER);
			panel.getChildren().addAll(r5,r3,r2,r1,r4);

			Scene scene = new Scene(panel, 400, 400);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Ejercicio 2");
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
