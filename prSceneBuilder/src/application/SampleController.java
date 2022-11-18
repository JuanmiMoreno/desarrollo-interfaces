package application;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;

public class SampleController {
	@FXML
	private TextField txtMensaje;
	@FXML
	private Button botonEnviar;
	@FXML
	private Label lblMensaje;

	// Event Listener on Button[#botonEnviar].onAction
	@FXML
	public void mostrarMensaje(ActionEvent event) {
		lblMensaje.setText(txtMensaje.getText());
	}
}
