package clases;

public class Movimiento {
	
	private int id_comunidad,tipo_movimiento,saldo_anterior,saldo_final;
	private String id_usuario,motivo;
	
	public Movimiento(int id_comunidad,String id_usuario,int tipo_movimiento,String motivo,int saldo_anterior,int saldo_final){
		this.id_comunidad=id_comunidad;
		this.id_usuario=id_usuario;
		this.tipo_movimiento=tipo_movimiento;
		this.motivo=motivo;
		this.saldo_anterior=saldo_anterior;
		this.saldo_final=saldo_final;
	}

	public int getId_comunidad() {
		return id_comunidad;
	}

	public void setId_comunidad(int id_comunidad) {
		this.id_comunidad = id_comunidad;
	}

	public int getTipo_movimiento() {
		return tipo_movimiento;
	}

	public void setTipo_movimiento(int tipo_movimiento) {
		this.tipo_movimiento = tipo_movimiento;
	}

	public int getSaldo_anterior() {
		return saldo_anterior;
	}

	public void setSaldo_anterior(int saldo_anterior) {
		this.saldo_anterior = saldo_anterior;
	}

	public int getSaldo_final() {
		return saldo_final;
	}

	public void setSaldo_final(int saldo_final) {
		this.saldo_final = saldo_final;
	}

	public String getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(String id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	
	

}
