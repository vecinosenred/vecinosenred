package clases;

import java.sql.Date;

public class Mensaje {
	
	private int id_comunidad,id_mensaje;
	private String id_usuario,id_destinatario,asunto,mensaje;
	private Date fecha;
	
	public Mensaje(int id_mensaje,String id_usuario,int id_comunidad,String id_destinatario,String asunto,String mensaje,Date fecha){
		this.id_mensaje=id_mensaje;
		this.id_usuario=id_usuario;
		this.id_comunidad=id_comunidad;
		this.id_destinatario=id_destinatario;
		this.asunto=asunto;
		this.mensaje=mensaje;
		this.fecha=fecha;
	}
	
	public int getId_mensaje() {
		return id_mensaje;
	}

	public void setId_mensaje(int id_mensaje) {
		this.id_mensaje = id_mensaje;
	}

	public int getId_comunidad() {
		return id_comunidad;
	}

	public void setId_comunidad(int id_comunidad) {
		this.id_comunidad = id_comunidad;
	}

	public String getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(String id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getId_destinatario() {
		return id_destinatario;
	}

	public void setId_destinatario(String id_destinatario) {
		this.id_destinatario = id_destinatario;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


}
