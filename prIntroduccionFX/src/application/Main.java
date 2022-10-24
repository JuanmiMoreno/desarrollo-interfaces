package application;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//STACK PANEL
			/*Button btn = new Button("Click aqui");
			btn.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
						System.out.println("hola mundo");
				}
			});
			Label lb = new Label("Hola mundo");
			StackPane panel = new StackPane();
			panel.setAlignment(lb, Pos.TOP_CENTER);
			panel.setAlignment(btn, Pos.CENTER);
			panel.getChildren().addAll(lb, btn);
			*/
			
			
			//VBOX (VERTICAL)
			/*Button btn1 = new Button("Boton 1");
			Button btn2 = new Button("Boton 2");
			Button btn3 = new Button("Boton 3");
			
			VBox panel = new VBox(15);
			panel.setPadding(new Insets(15));
			panel.getChildren().addAll(btn1, btn2, btn3);
			*/
			
			//HBOX (HORIZONTAL)
			/*Button btn1 = new Button("Boton 1");
			Button btn2 = new Button("Boton 2");
			Button btn3 = new Button("Boton 3");
			
			HBox panel = new HBox(15);
			panel.setPadding(new Insets(15));
			panel.getChildren().addAll(btn1, btn2, btn3);
			*/
			
			//BORDER PANEL
			/*Button btn1 = new Button("Boton 1");
			Button btn2 = new Button("Boton 2");
			Button btn3 = new Button("Boton 3");
			Button btn4 = new Button("Boton 4");
			
			VBox panelVertical = new VBox(15);
			panelVertical.setPadding(new Insets(15));
			panelVertical.getChildren().addAll(btn1, btn2, btn3);
			
			
			BorderPane panel = new BorderPane();
			panel.setRight(panelVertical);
		*/
			
			//GRID PANEL
			Button btn1 = new Button("Boton 1");
			Button btn2 = new Button("Boton 2");
			Button btn3 = new Button("Boton 3");
			Button btn4 = new Button("Boton 4");
			
			GridPane panel = new GridPane();
			panel.add(btn1, 0, 0);
			panel.add(btn2, 1, 0);
			panel.add(btn3, 0, 1);
			panel.add(btn4, 1, 1);
			
			panel.setVgap(15);
			panel.setHgap(15);
			panel.setPadding(new Insets(15));
			
			Scene scene = new Scene(panel,400,400);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Introduccion JavaFX");
			primaryStage.getIcons().add(new Image("/imagen/java.png"));
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
