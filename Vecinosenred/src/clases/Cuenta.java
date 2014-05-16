package clases;

public class Cuenta {

	int id_cuenta, id_comunidad;
	double saldo;
	String num_cuenta;
	public Cuenta(int id_cuenta,int id_comunidad,String num_cuenta,double saldo){
		this.id_cuenta=id_cuenta;
		this.id_comunidad=id_comunidad;
		this.num_cuenta=num_cuenta;
		this.saldo=saldo;
	}

	public int getId_cuenta() {
		return id_cuenta;
	}

	public void setId_cuenta(int id_cuenta) {
		this.id_cuenta = id_cuenta;
	}

	public int getId_comunidad() {
		return id_comunidad;
	}

	public void setId_comunidad(int id_comunidad) {
		this.id_comunidad = id_comunidad;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public String getUsuario() {
		return num_cuenta;
	}

	public void setUsuario(String usuario) {
		this.num_cuenta = usuario;
	}
	
	public String getNum_cuenta() {
		return num_cuenta;
	}

	public void setNum_cuenta(String num_cuenta) {
		this.num_cuenta = num_cuenta;
	}

	
}
