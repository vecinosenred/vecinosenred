package clases;

import java.sql.Date;

public class Reserva {
	private int id_reserva,id_instalacion,horainicio,horafin;
	Date fecha;
	
	public Reserva(int id_reserva,int id_instalacion,Date fecha,int horainicio,int horafin){
		this.id_reserva=id_reserva;
		this.id_instalacion=id_instalacion;
		this.fecha=fecha;
		this.horainicio=horainicio;
		this.horafin=horafin;
	}

	public int getId_reserva() {
		return id_reserva;
	}

	public void setId_reserva(int id_reserva) {
		this.id_reserva = id_reserva;
	}

	public int getId_instalacion() {
		return id_instalacion;
	}

	public void setId_instalacion(int id_instalacion) {
		this.id_instalacion = id_instalacion;
	}

	public int getHorainicio() {
		return horainicio;
	}

	public void setHorainicio(int horainicio) {
		this.horainicio = horainicio;
	}

	public int getHorafin() {
		return horafin;
	}

	public void setHorafin(int horafin) {
		this.horafin = horafin;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}

