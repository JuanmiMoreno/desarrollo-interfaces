package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Directivo {
	
	 private SimpleStringProperty nombre;
	    private SimpleStringProperty apellido;
	    private SimpleStringProperty dni;
	    private SimpleStringProperty correo;
	    private SimpleIntegerProperty empresa_id;

	    public Directivo(String nombre, String apellido, String dni, String correo, int empresa_id) {

	        this.nombre = new SimpleStringProperty(nombre);
	        this.apellido = new SimpleStringProperty(apellido);
	        this.dni = new SimpleStringProperty(dni);
	        this.correo = new SimpleStringProperty(correo);
	        this.empresa_id = new SimpleIntegerProperty(empresa_id);
	    }

	    public String getNombre() {
	        return nombre.get();
	    }

	    public void setNombre(String nombre) {
	        this.nombre = new SimpleStringProperty(nombre);
	    }

	    public String getApellido() {
	        return apellido.get();
	    }

	    public void setApellido(String apellido) {
	        this.apellido = new SimpleStringProperty(apellido);
	    }

	    public String getDni(){
	        return dni.get();
	    }

	    public void setDni(String dni) {
	        this.dni = new SimpleStringProperty(dni);
	    }

	    public String getCorreo(){
	        return correo.get();
	    }

	    public void setCorreo(String correo) {
	        this.correo = new SimpleStringProperty(correo);
	    }
	    
	    public int getEmpresaId() {
	        return empresa_id.get();
	    }

	    public void setEmpresaId(int empresa_id) {
	        this.empresa_id = new SimpleIntegerProperty(empresa_id);
	    }


}
