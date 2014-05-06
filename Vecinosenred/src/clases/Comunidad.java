package clases;

public class Comunidad {

	private int id;
	private String nombre,direccion,num_cuenta;
	
	public Comunidad(int id,String nombre,String direccion,String num_cuenta){
		this.id=id;
		this.nombre=nombre;
		this.direccion=direccion;
		this.num_cuenta=num_cuenta;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNum_cuenta() {
		return num_cuenta;
	}

	public void setNum_cuenta(String num_cuenta) {
		this.num_cuenta = num_cuenta;
	}
}
