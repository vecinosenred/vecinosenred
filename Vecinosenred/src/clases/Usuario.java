package clases;

public class Usuario {
	
	private String usuario,pass,nombre,domicilio;
	
	public Usuario(String usuario,String pass,String nombre,String domicilio){
		this.usuario=usuario;
		this.pass=pass;
		this.nombre=nombre;
		this.domicilio=domicilio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}
