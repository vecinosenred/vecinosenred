package clases;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import BD.Conectar;

public class Incidencia {
	
	private int id_incidencia,id_comunidad,estado;
	private String id_usuario,titulo,descripcion;
	private Date fecha_creacion;
	private ArrayList<RespuestaIncidencia> respanu= new ArrayList<RespuestaIncidencia>();
	
	public Incidencia(int id_incidencia,int id_comunidad,String id_usuario,String titulo,String descripcion,int estado,Date fecha){
		this.id_incidencia=id_incidencia;
		this.id_comunidad=id_comunidad;
		this.titulo=titulo;
		this.descripcion=descripcion;
		this.estado=estado;
		this.fecha_creacion=fecha;
		
		RespuestaIncidencia respuestas=new RespuestaIncidencia(this.id_incidencia);
		setRespanu(respuestas.getRean());
		
	}

	public int getId_mensaje() {
		return id_incidencia;
	}

	public void setId_mensaje(int id_mensaje) {
		this.id_incidencia = id_mensaje;
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
		return descripcion;
	}

	public void setMensaje(String mensaje) {
		this.descripcion = mensaje;
	}
	
	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Date getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
	
	public ArrayList<RespuestaIncidencia> getRespanu() {
		return respanu;
	}

	public void setRespanu(ArrayList<RespuestaIncidencia> respanu) {
		this.respanu = respanu;
	}

}

class RespuestaIncidencia{
	
	private Statement st;
	private ResultSet rs;
	private int id_respuesta,id_incidencia;
	private String respuesta,id_usuario;
	private Date fecha_respuesta;
	private ArrayList<RespuestaIncidencia> rein = new ArrayList<RespuestaIncidencia>();

	public int getId_respuesta() {
		return id_respuesta;
	}

	public void setId_respuesta(int id_respuesta) {
		this.id_respuesta = id_respuesta;
	}

	public int getId_mensaje() {
		return id_incidencia;
	}

	public void setId_mensaje(int id_mensaje) {
		this.id_incidencia = id_mensaje;
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
	
	public ArrayList<RespuestaIncidencia> getRean() {
		return rein;
	}

	public void setRean(ArrayList<RespuestaIncidencia> rean) {
		this.rein = rean;
	}
	
	public RespuestaIncidencia(int id_incidencia){
		
		Conectar con = new Conectar();
		try {
			con.conectar();
			
			st=con.getConexion().createStatement();
			rs=st.executeQuery("SELECT * FROM RESIN_RESPUESTAS_INCIDENCIAS WHERE RESIN_ID_INCIDENCIA="+id_incidencia);			
			while(rs.next()){
				
				this.id_incidencia=rs.getInt(1);
				this.id_respuesta=rs.getInt(2);
				this.respuesta=rs.getString(3);
				this.id_usuario=rs.getString(4);
				this.fecha_respuesta=rs.getDate(5);
				
				this.rein.add(this);
				
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
