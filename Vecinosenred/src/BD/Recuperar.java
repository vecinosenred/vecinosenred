package BD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import clases.Anuncio;
import clases.Comunidad;
import clases.ComunidadUsuario;
import clases.Incidencia;
import clases.Instalacion;
import clases.Mensaje;
import clases.Movimiento;
import clases.Usuario;

public class Recuperar{
	
	private Statement st;
	private ResultSet rs;
	private ArrayList<ComunidadUsuario> com_usu=new ArrayList<ComunidadUsuario>();
	private ArrayList<Usuario> usuarios=new ArrayList<Usuario>();
	private ArrayList<Comunidad> comunidades=new ArrayList<Comunidad>();
	private ArrayList<Movimiento> movimientos=new ArrayList<Movimiento>();
	private ArrayList<Anuncio> anuncios=new ArrayList<Anuncio>();
	private ArrayList<Incidencia> incidencias=new ArrayList<Incidencia>();
	private ArrayList<Mensaje> mensajes=new ArrayList<Mensaje>();
	private ArrayList<Instalacion> instalaciones=new ArrayList<Instalacion>();	
	private Conectar c=new Conectar();
	
	public Recuperar(String id_usu) throws ClassNotFoundException, SQLException{
		c.conectar();
		st=c.getConexion().createStatement();
		
		rs=st.executeQuery("SELECT * FROM LOGUSU_LOGIN_USUARIOS");		
		while(rs.next()){
			Usuario usu=new Usuario(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
			usuarios.add(usu);
		}
		
		rs=st.executeQuery("SELECT * FROM COMUSU_COMUNIDAD_USUARIO WHERE COMUSU_ID_USUARIO='"+id_usu+"'");		
		while(rs.next()){
//			ComunidadUsuario comusu= new ComunidadUsuario(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5));
			ComunidadUsuario comusu= new ComunidadUsuario(rs.getString("COMUSU_ID_USUARIO"),rs.getInt("COMUSU_ID_COMUNIDADES"), 
					rs.getInt("COMUSU_ADMINISTRADOR"), rs.getString("COMUSU_NUM_CUENTA"), rs.getString("COMUSU_PISO"));

			com_usu.add(comusu);
		}		

		for (int i = 0; i < com_usu.size(); i++) {
			rs=st.executeQuery("SELECT * FROM COM_COMUNIDADES");		
			while(rs.next()){
				Comunidad com= new Comunidad(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				comunidades.add(com);
			}
		}
		
		rs=st.executeQuery("SELECT * FROM MOVCUE_MOVIMIENTOS_CUENTAS WHERE MOVCUE_ID_USUARIO='"+id_usu+"'");		
		while(rs.next()){
			Movimiento mov=new Movimiento(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), 
					rs.getInt(5), rs.getInt(6));
			movimientos.add(mov);
		}		
		
		for (int i = 0; i < com_usu.size(); i++) {
			rs=st.executeQuery("SELECT * FROM TABANU_TABLON_ANUNCIOS WHERE TABANU_ID_COMUNIDAD="+com_usu.get(i).getId_comunidades());		
			while(rs.next()){
				Anuncio anun= new Anuncio(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6));
				anuncios.add(anun);
			}			
		}
		
		for (int i = 0; i < com_usu.size(); i++) {
			rs=st.executeQuery("SELECT * FROM INCI_INCIDENCIAS WHERE INCI_ID_COMUNIDAD="+com_usu.get(i).getId_comunidades());		
			while(rs.next()){
				Incidencia inci= new Incidencia(rs.getInt(1), rs.getInt(2), rs.getString(3), 
						rs.getString(4), rs.getString(5),rs.getInt(6), rs.getDate(7));
				incidencias.add(inci);
			}			
		}
		
		rs=st.executeQuery("SELECT * FROM MENS_MENSAJES WHERE MENS_ID_USUARIO='"+id_usu+"'");		
		while(rs.next()){
			Mensaje men=new Mensaje(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getString(4), 
					rs.getString(5), rs.getDate(6));
			mensajes.add(men);
		}
		
		for (int i = 0; i < com_usu.size(); i++) {
			rs=st.executeQuery("SELECT * FROM INST_INSTALACIONES WHERE INST_ID_COMUNIDAD="+com_usu.get(i).getId_comunidades());		
			while(rs.next()){
				Instalacion inst= new Instalacion(rs.getInt(1), rs.getInt(2), rs.getString(3), 
						rs.getInt(4), rs.getString(5),rs.getInt(6), rs.getString(7),rs.getInt(8));
				instalaciones.add(inst);
			}			
		}
		
		c.desconectar();
	}
	
	public ArrayList<Instalacion> getInstalaciones() {
		return instalaciones;
	}

	public void setInstalaciones(ArrayList<Instalacion> instalaciones) {
		this.instalaciones = instalaciones;
	}

	public ArrayList<Usuario> getUsuarios(){
		return usuarios;
	}
	
	public ArrayList<Comunidad> getComunidades() {
		return comunidades;
	}

	public ArrayList<ComunidadUsuario> getCom_usu(){
		return com_usu;
	}
	
	public ArrayList<Movimiento> getMovimientos(){
		return movimientos;
	}
	
	public ArrayList<Incidencia> getIncidencias(){
		return incidencias;
	}
	
	public ArrayList<Anuncio> getAnuncios(){
		return anuncios;
	}
	
	public ArrayList<Mensaje> getMensajes(){
		return mensajes;
	}
	
	public ResultSet recuperarColumna(String query) throws ClassNotFoundException, SQLException{
		
		c.conectar();
		st=c.getConexion().createStatement();
		rs=st.executeQuery(query);
		c.desconectar();
		
		return rs;
	}
	
	public ResultSet recuperarTodo(String tabla) throws SQLException, ClassNotFoundException{
		
		c.conectar();
		st=c.getConexion().createStatement();
		rs=st.executeQuery("SELECT * FROM "+tabla);
		c.desconectar();
		
		return rs;
		
	} 
	

}
