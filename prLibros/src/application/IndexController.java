package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPanel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class IndexController {

	@FXML
	private TextField txtTitulo;

	@FXML
	private ChoiceBox cbEditorial;

	@FXML
	private TextField txtAutor;

	@FXML
	private TextField txtPaginas;

	@FXML
	private TableView<Libro> tableLibros;

	@FXML
	private TableColumn<Libro, String> columnTitulo;

	@FXML
	private TableColumn<Libro, String> columnEditorial;

	@FXML
	private TableColumn<Libro, String> columnAutor;

	@FXML
	private TableColumn<Libro, Integer> columnPaginas;

	@FXML
	private Button btnAnadir;
	@FXML
	private Button btnBorrar;

	private ObservableList<Libro> listaLibros = FXCollections
			.observableArrayList();

	public ObservableList<String> listaEditoriales = FXCollections.observableArrayList("Planeta","Altaya","kadokawa");

	@FXML
	private void initialize() {

		cbEditorial.setItems(listaEditoriales);

		columnTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
		columnEditorial.setCellValueFactory(new PropertyValueFactory<>("editorial"));
		columnAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
		columnPaginas.setCellValueFactory(new PropertyValueFactory<>("paginas"));

		ObservableList ListaLibrosBd= getLibrosBD(); 
		tableLibros.setItems(ListaLibrosBd);
	}
	
	//FUNCION PARA BUSCAR EN LA BASE DE DATOS
	private ObservableList<Libro> getLibrosBD(){
		
		ObservableList<Libro> listaLibrosBd = FXCollections.observableArrayList();
		try {
		DatabaseConnection connectionDb = new DatabaseConnection();
		Connection connection  =connectionDb.getConnection();
		String query = "select * from libros";
		PreparedStatement ps = connection.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			Libro libro = new Libro(rs.getInt("id"),rs.getString("titulo"), rs.getString("editorial"), rs.getString("autor"), rs.getInt("paginas"));
			listaLibrosBd.add(libro);
		}
		
		connection.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return listaLibrosBd;
	}

	@FXML
	public void anadirLibro(ActionEvent event) {
		if (!txtTitulo.getText().isEmpty() && !cbEditorial.getSelectionModel().isEmpty()
				&& !txtAutor.getText().isEmpty() && !txtPaginas.getText().isEmpty()) {

			if (esNumero(txtPaginas.getText())) {
				Libro l = new Libro(txtTitulo.getText(), cbEditorial.getValue().toString(), txtAutor.getText(),
						Integer.parseInt(txtPaginas.getText()));

				//AÑADIR A LA TABLA
				//listaLibros.add(l);
				//tableLibros.setItems(listaLibrosBd );
				
				
				txtTitulo.clear();
				cbEditorial.getSelectionModel().clearSelection();
				txtAutor.clear();
				txtPaginas.clear();
				
				//AÑADIR A LA BASE DE DATOS
				try {
				DatabaseConnection connectionDb = new DatabaseConnection();
				Connection connection  =connectionDb.getConnection();
				
				String query = "insert into libros (titulo, editorial, autor, paginas) VALUES (?,?,?,?)";
				PreparedStatement ps  = connection.prepareStatement(query);
				ps.setString(1, l.getTitulo() );
				ps.setString(2, l.getEditorial());
				ps.setString(3, l.getAutor());
				ps.setInt(4, l.getPaginas());
				ps.executeUpdate();
				
				connection.close();
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//DEPSUES DE INSERTA ACTUALIZAMOS LA TABLA
				ObservableList ListaLibrosBd= getLibrosBD(); 
				tableLibros.setItems(ListaLibrosBd);
				
			} else {
				Alert alerta = new Alert(AlertType.ERROR);
				alerta.setTitle("Error al insertar");
				alerta.setHeaderText("Se debe introducion un numero en las paginas");
				alerta.setContentText("Por favor introduzca un numero en las paginas");
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

	public void borrarLibro(ActionEvent event) {

		int indiceSeleccionado = tableLibros.getSelectionModel().getSelectedIndex();
		if (indiceSeleccionado != -1) {

			//tableLibros.getItems().remove(indiceSeleccionado);
			
			try {
				DatabaseConnection connectionDb = new DatabaseConnection();
				Connection connection  =connectionDb.getConnection();
				
				String query = "delete from libros where id =?";
				PreparedStatement ps  = connection.prepareStatement(query);
				Libro l =tableLibros.getSelectionModel().getSelectedItem();
				ps.setInt(1, l.getId());
				ps.executeUpdate();
				tableLibros.getSelectionModel().clearSelection();
				
				ObservableList ListaLibrosBd= getLibrosBD(); 
				tableLibros.setItems(ListaLibrosBd);
				connection.close();
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
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
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
