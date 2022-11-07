package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		
			VBox panel = new VBox(15);
			
			Label usuario = new Label("Usuario");
			TextField campoUsuario = new TextField();
			Label contraseña = new Label("Contraseña");
			PasswordField campoContraseña = new PasswordField();
			Button botonEntrar = new Button("Entrar");
			Label mensaje = new Label();
			
			panel.getChildren().addAll(usuario,campoUsuario, contraseña, campoContraseña, botonEntrar, mensaje);
			
			botonEntrar.setOnAction(new EventHandler<ActionEvent>() {
				public void handle (ActionEvent event) {
					String usuario = campoUsuario.getText();
					String contraseña = campoContraseña.getText();
					mensaje.setText("Bienvenido, "+usuario );
					
					
				}
			});
		
			
		
			
			
			
			
			
		
			Scene scene = new Scene(panel,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
	
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
