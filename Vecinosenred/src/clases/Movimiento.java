package clases;

import java.sql.Date;

public class Movimiento {
	
	private int id_cuenta,tipo_movimiento,saldo_anterior,saldo_final,id_movimiento,cantidad,id_comunidad;
	private String id_usuario,motivo;
	private Date fecha;

	public Movimiento(int id_movimiento, int id_cuenta, String id_usuario,int cantidad,int tipo_movimiento, Date fecha, String motivo,int saldo_anterior,int saldo_final,int id_com){
		this.id_movimiento=id_movimiento;
		this.id_cuenta=id_cuenta;
		this.id_usuario=id_usuario;
		this.tipo_movimiento=tipo_movimiento;
		this.motivo=motivo;
		this.saldo_anterior=saldo_anterior;
		this.saldo_final=saldo_final;
		this.id_comunidad=id_com;
		this.cantidad=cantidad;
		this.fecha=fecha;
	}

	public int getId_comunidad() {
		return id_comunidad;
	}

	public void setId_comunidad(int id_com) {
		this.id_comunidad = id_com;
	}

	public int getId_cuenta() {
		return id_cuenta;
	}

	public void setId_cuenta(int id_comunidad) {
		this.id_cuenta = id_comunidad;
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
	
	public int getId_movimiento() {
		return id_movimiento;
	}

	public void setId_movimiento(int id_movimiento) {
		this.id_movimiento = id_movimiento;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	

}
