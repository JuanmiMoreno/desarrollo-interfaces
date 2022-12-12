package application;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.TableView;

import javafx.scene.control.ChoiceBox;

import javafx.scene.control.TableColumn;

public class IndexController {
	@FXML
	private TextField campoNombre;
	@FXML
	private TextField campoDni;
	@FXML
	private ChoiceBox opcionEmpresa;
	@FXML
	private TextField campoApellido;
	@FXML
	private TextField campoCorreo;
	@FXML
	private Button btnAnadir;
	@FXML
	private Button btnBorrar;
	@FXML
	private TableView<Directivo> tableDirectivos;
	@FXML
	private TableColumn <Directivo, String>columnNombre;
	@FXML
	private TableColumn <Directivo, String> columnAppelido;
	@FXML
	private TableColumn <Directivo, String> columnDni;
	@FXML
	private TableColumn <Directivo, String> columnCorreo;
	@FXML
	private TableColumn <Directivo , Integer> columnEmpresaId;
	@FXML
	private ObservableList<Directivo> listaDirectivos = FXCollections
			.observableArrayList();
	@FXML
	public ObservableList<Integer> listaEmpresasId = FXCollections.observableArrayList(1, 2, 3);
	
	@FXML
	private void initialize() {

		opcionEmpresa.setItems(listaEmpresasId);

		columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		columnAppelido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
		columnDni.setCellValueFactory(new PropertyValueFactory<>("dni"));
		columnCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
		columnEmpresaId.setCellValueFactory(new PropertyValueFactory<>("empresa_id"));

		ObservableList ListaDirectivosBd= getDirectivosBD(); 
		
		tableDirectivos.setItems(ListaDirectivosBd);
	}
	
	private ObservableList<Directivo> getDirectivosBD(){
		
		ObservableList<Directivo> ListaDirectivosBd = FXCollections.observableArrayList();
		try {
		DatabaseConnection connectionDb = new DatabaseConnection();
		Connection connection  =connectionDb.getConnection();
		String query = "select * from directivos";
		PreparedStatement ps = connection.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			Directivo directivo = new Directivo(rs.getString("nombre"), rs.getString("apellido"), rs.getString("dni"), rs.getString("correo") ,rs.getInt("empresa_id"));
			ListaDirectivosBd.add(directivo);
		}
		
		connection.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return ListaDirectivosBd;
	}
	
	// Event Listener on Button[#btnAnadir].onAction
	@FXML
	public void anadirDirectivo(ActionEvent event) {
		
		if (!campoNombre.getText().isEmpty() && !opcionEmpresa.getSelectionModel().isEmpty()
				&& !campoApellido.getText().isEmpty() && !campoDni.getText().isEmpty() && !campoCorreo.getText().isEmpty()) {

			
				Directivo d = new Directivo(campoNombre.getText(), campoApellido.getText(),campoDni.getText(),campoCorreo.getText() ,
						Integer.parseInt(opcionEmpresa.getValue().toString()));

				listaDirectivos.add(d);
				campoNombre.clear();
				opcionEmpresa.getSelectionModel().clearSelection();
				campoApellido.clear();
				campoDni.clear();
				campoCorreo.clear();
				
				DatabaseConnection connectionDb = new DatabaseConnection();
				Connection connection  =connectionDb.getConnection();
				
				String query = "insert into directivos (nombre, apellido, dni, correo, empresa_id) values (?,?,?,?,?)";
				PreparedStatement ps;
				try {
					ps = connection.prepareStatement(query);
				
				ps.setString(1, d.getNombre() );
				ps.setString(2, d.getApellido());
				ps.setString(3, d.getDni());
				ps.setString(4, d.getCorreo());
				ps.setInt(5, d.getEmpresaId());
				ps.executeUpdate();
				
				connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ObservableList ListaDirectivosBd= getDirectivosBD(); 
				tableDirectivos.setItems(ListaDirectivosBd);
				
		} else {
			Alert alerta = new Alert(AlertType.WARNING);
			alerta.setTitle("Informacion incompleta");
			alerta.setHeaderText("Se debe introducion todos los campos");
			alerta.setContentText("Por favor introduzca datos en todos los campos");
			alerta.showAndWait();
		}
	}
	// Event Listener on Button[#btnBorrar].onAction
	@FXML
	public void borrarDirectivo(ActionEvent event) {
	int indiceSeleccionado = tableDirectivos.getSelectionModel().getSelectedIndex();
		
	if (indiceSeleccionado != -1) {

			tableDirectivos.getItems().remove(indiceSeleccionado);
			tableDirectivos.getSelectionModel().clearSelection();
		}else {
			
			Alert alerta = new Alert(AlertType.WARNING);
			alerta.setTitle("No se puede borrar");
			alerta.setHeaderText("Se debes elegir uno de los elementos de la tabla");
			alerta.setContentText("Por favor selecciona alguno de los elementos de la tabla");
			alerta.showAndWait();
		}
	}
}
