package clases;

public class ComunidadUsuario {
	
	private int id_comunidades,administrador;
	private String id_usuario,num_cuenta,piso;
	
	public ComunidadUsuario(String id_usuario,int id_comunidades,int administrador,String num_cuenta,String piso){
		this.id_usuario=id_usuario;
		this.id_comunidades=id_comunidades;
		this.administrador=administrador;
		this.num_cuenta=num_cuenta;
		this.piso=piso;
	}

	public int getId_comunidades() {
		return id_comunidades;
	}

	public void setId_comunidades(int id_comunidades) {
		this.id_comunidades = id_comunidades;
	}

	public int getAdministrador() {
		return administrador;
	}

	public void setAdministrador(int administrador) {
		this.administrador = administrador;
	}

	public String getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(String id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getNum_cuenta() {
		return num_cuenta;
	}

	public void setNum_cuenta(String num_cuenta) {
		this.num_cuenta = num_cuenta;
	}

	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

}
