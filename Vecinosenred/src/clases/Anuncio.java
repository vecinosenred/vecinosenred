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

class RespuestaAnuncio{
	
	private Statement st;
	private ResultSet rs;
	private int id_respuesta,id_mensaje;
	private String respuesta,id_usuario;
	private Date fecha_respuesta;
	private ArrayList<RespuestaAnuncio> rean = new ArrayList<RespuestaAnuncio>();

	public int getId_respuesta() {
		return id_respuesta;
	}

	public void setId_respuesta(int id_respuesta) {
		this.id_respuesta = id_respuesta;
	}

	public int getId_mensaje() {
		return id_mensaje;
	}

	public void setId_mensaje(int id_mensaje) {
		this.id_mensaje = id_mensaje;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public String getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(String id_usuario) {
		this.id_usuario = id_usuario;
	}

	public Date getFecha_respuesta() {
		return fecha_respuesta;
	}

	public void setFecha_respuesta(Date fecha_respuesta) {
		this.fecha_respuesta = fecha_respuesta;
	}
	
	public ArrayList<RespuestaAnuncio> getRean() {
		return rean;
	}

	public void setRean(ArrayList<RespuestaAnuncio> rean) {
		this.rean = rean;
	}
	
	public RespuestaAnuncio(int id_mensaje){
		
		Conectar con = new Conectar();
		try {
			con.conectar();
			
			st=con.getConexion().createStatement();
			rs=st.executeQuery("SELECT * FROM RESPANU_RESPUESTAS_ANUNCIOS WHERE RESPANU_ID_MENSAJE="+id_mensaje);			
			while(rs.next()){
				this.id_respuesta=rs.getInt(1);
				this.id_mensaje=rs.getInt(2);
				this.respuesta=rs.getString(3);
				this.id_usuario=rs.getString(4);
				this.fecha_respuesta=rs.getDate(5);
				
				this.rean.add(this);
				
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
