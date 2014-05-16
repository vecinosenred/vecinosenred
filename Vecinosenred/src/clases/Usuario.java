package clases;

public class Usuario {
	
	private String usuario,pass,nombre,domicilio;
	private int id_comunidad;
	
	public Usuario(String usuario,String pass,String nombre,String domicilio,int id_com){
		this.usuario=usuario;
		this.pass=pass;
		this.nombre=nombre;
		this.domicilio=domicilio;
		this.id_comunidad=id_com;
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

	public int getId_comunidad() {
		return id_comunidad;
	}

	public void setId_comunidad(int id_comunidad) {
		this.id_comunidad = id_comunidad;
	}
	
	

}
