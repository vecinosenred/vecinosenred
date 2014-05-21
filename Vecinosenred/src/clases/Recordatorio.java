package clases;

import java.sql.Date;
import java.sql.Time;

/**
 * Clase que define el tipo Recordatorio
 * @author JonB
 *
 */
public class Recordatorio {
	
	private int id_marca,id_comunidad;
	private Date fecha;
	private Time hora;
	private String lugar,recordatorio;

	public Recordatorio(int id_marca,int id_com,Date fecha,Time hora,String lugar,String recordatorio){
		this.id_marca=id_marca;
		this.id_comunidad=id_com;
		this.fecha=fecha;
		this.hora=hora;
		this.lugar=lugar;
		this.recordatorio=recordatorio;
	}
	
	public int getId_marca() {
		return id_marca;
	}
	public void setId_marca(int id_marca) {
		this.id_marca = id_marca;
	}
	public int getId_comunidad() {
		return id_comunidad;
	}
	public void setId_comunidad(int id_comunidad) {
		this.id_comunidad = id_comunidad;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Time getHora() {
		return hora;
	}
	public void setHora(Time hora) {
		this.hora = hora;
	}
	public String getRecordatorio() {
		return recordatorio;
	}
	public void setRecordatorio(String recordatorio) {
		this.recordatorio = recordatorio;
	}
	
	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

}
