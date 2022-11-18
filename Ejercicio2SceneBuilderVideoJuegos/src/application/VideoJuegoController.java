package application;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.scene.control.ChoiceBox;

public class VideoJuegoController {
	@FXML
	private TextField txtNombre;
	@FXML
	private TextField txtPrecio;
	@FXML
	private ChoiceBox opcionConsola;
	@FXML
	private ChoiceBox opcionPegi;
	@FXML
	private Button btnAñadir;
	
	
	/*ArrayList<String> arrayConsola = new ArrayList<>(
		   	Arrays.asList("PS5", "XBOX", "PS4"));
		ObservableList<String> elementos = FXCollections.observableArrayList(arrayConsola);
	@		
	opcionConsola.setItems(elementos);*/
}
