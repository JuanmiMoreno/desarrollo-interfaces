package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableView;

import javafx.scene.control.ChoiceBox;

import javafx.scene.control.TableColumn;

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
	@FXML
	private Button btnBorrar;
	@FXML
	private TableView<Videojuego> tableJuego;
	@FXML
	private TableColumn<Videojuego, String> columNombre;
	@FXML
	private TableColumn<Videojuego, Float> columPrecio;
	@FXML
	private TableColumn<Videojuego, String> columConsola;
	@FXML
	private TableColumn<Videojuego, String> columPegi;

	private ObservableList<Videojuego> listaVideojuegos = FXCollections
			.observableArrayList(new Videojuego("Fifa 23", 49, "PSP5", "PEGI 3"));

	public ObservableList<String> consolas = FXCollections.observableArrayList("PSP5", "PSP4", "Nintendo", "XBOX");
	public ObservableList<String> pegis = FXCollections.observableArrayList("PEGI 3", "PEGI 7", "PEGI 12", "PEGI 16",
			"PEGI 18");

	@FXML
	private void initialize() {
		opcionConsola.setItems(consolas);
		opcionPegi.setItems(pegis);
		columNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		columPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
		columConsola.setCellValueFactory(new PropertyValueFactory<>("consola"));
		columPegi.setCellValueFactory(new PropertyValueFactory<>("pegi"));

		tableJuego.setItems(listaVideojuegos);

	}

	@FXML
	public void aniadirLibro(ActionEvent event) {
		if (!txtNombre.getText().isEmpty() && !txtPrecio.getText().isEmpty()
				&& !opcionConsola.getSelectionModel().isEmpty() && !opcionPegi.getSelectionModel().isEmpty()) {
			if (esNumero(txtPrecio.getText())) {
				Videojuego juego1 = new Videojuego(txtNombre.getText(), Float.parseFloat(txtPrecio.getText()),
						opcionConsola.getValue().toString(), opcionPegi.getValue().toString());

				listaVideojuegos.add(juego1);

				txtNombre.clear();
				txtPrecio.clear();
				opcionConsola.getSelectionModel().clearSelection();
				opcionPegi.getSelectionModel().clearSelection();
			} else {
				Alert alerta = new Alert(AlertType.ERROR);
				alerta.setTitle("Error al insertar");
				alerta.setHeaderText("Se debe introducion un numero en el precio");
				alerta.setContentText("Por favor introduzca un numero en el precio puede tener decimales");
				alerta.showAndWait();
			}
		} else {
			Alert alerta = new Alert(AlertType.WARNING);
			alerta.setTitle("Informacion incompleta");
			alerta.setHeaderText("Se debe introducion todos los campos");
			alerta.setContentText("Por favor introduzca datos en todos los campos");
			alerta.showAndWait();
		}

	}
	
	public void borrarJuego(ActionEvent event) {

		int indiceSeleccionado = tableJuego.getSelectionModel().getSelectedIndex();
		if (indiceSeleccionado != -1) {

			tableJuego.getItems().remove(indiceSeleccionado);
		}else {
			
			Alert alerta = new Alert(AlertType.WARNING);
			alerta.setTitle("No se puede borrar");
			alerta.setHeaderText("Se debes elegir uno de los elementos de la tabla");
			alerta.setContentText("Por favor selecciona alguno de los elementos de la tabla");
			alerta.showAndWait();
		}
	}

	public boolean esNumero(String s) {
		try {
			Float.parseFloat(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
