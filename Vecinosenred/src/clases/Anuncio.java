package clases;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import BD.Conectar;

public class Anuncio {
	
	private int id_mensaje,id_comunidad;
	private String id_usuario,titulo,mensaje;
	private Date fecha_creacion;
	private ArrayList<RespuestaAnuncio> respanu= new ArrayList<RespuestaAnuncio>();
	
	public Anuncio(int id_mensaje,int id_comunidad,String id_usuario,String titulo,String mensaje,Date fecha){
		this.id_mensaje=id_mensaje;
		this.id_comunidad=id_comunidad;
		this.id_usuario=id_usuario;
		this.titulo=titulo;
		this.mensaje=mensaje;
		this.fecha_creacion=fecha;
		
		RespuestaAnuncio respuestas=new RespuestaAnuncio(this.id_mensaje);
		this.respanu=respuestas.getRean();
		
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

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Date getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
	
	public ArrayList<RespuestaAnuncio> getRespanu() {
		return respanu;
	}

	public void setRespanu(ArrayList<RespuestaAnuncio> respanu) {
		this.respanu = respanu;
	}

}
	
