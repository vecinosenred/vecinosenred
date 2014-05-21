package BD;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import clases.Anuncio;
import clases.Comunidad;
import clases.ComunidadUsuario;
import clases.Cuenta;
import clases.Incidencia;
import clases.Instalacion;
import clases.Mensaje;
import clases.Movimiento;
import clases.Recordatorio;
import clases.Reserva;
import clases.Usuario;

public class Recuperar implements Runnable{
	
	private Statement st;
	private ResultSet rs;
	private String id_usu;
	public ArrayList<ComunidadUsuario> com_usu=new ArrayList<ComunidadUsuario>();
	public ArrayList<ComunidadUsuario> comunidades_usuarios=new ArrayList<ComunidadUsuario>();
	public ArrayList<Usuario> usuarios=new ArrayList<Usuario>();
	public Usuario usuarioLog;
	public ArrayList<Comunidad> comunidades=new ArrayList<Comunidad>();
	public ArrayList<Movimiento> movimientos=new ArrayList<Movimiento>();
	public ArrayList<Movimiento> movimientosComunidad=new ArrayList<Movimiento>();
	public ArrayList<Anuncio> anuncios=new ArrayList<Anuncio>();
	public ArrayList<Anuncio> resp_anuncios=new ArrayList<Anuncio>();
	public ArrayList<Incidencia> incidencias=new ArrayList<Incidencia>();
	public ArrayList<Mensaje> mensajes=new ArrayList<Mensaje>();
	public ArrayList<Instalacion> instalaciones=new ArrayList<Instalacion>();
	public ArrayList<Reserva> reservas=new ArrayList<Reserva>();
	public ArrayList<Recordatorio> recordatorios=new ArrayList<Recordatorio>();
	public ArrayList<Cuenta> cuentas=new ArrayList<Cuenta>();
	public Conectar c=new Conectar();
	private Thread thread;
	
	public Recuperar(String id_usu) throws ClassNotFoundException, SQLException{
		
		thread=new Thread(this,"");
		thread.start();
		
		this.id_usu=id_usu;
		c.conectar();
		st=c.getConexion().createStatement();
				
		rs=st.executeQuery("SELECT * FROM LOGUSU_LOGIN_USUARIOS WHERE LOGUSU_ID='"+id_usu+"'");		
		while(rs.next()){
			usuarioLog=new Usuario(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),0);
		}		
		
		rs=st.executeQuery("SELECT * FROM COMUSU_COMUNIDAD_USUARIO WHERE COMUSU_ID_USUARIO='"+id_usu+"'");		// WHERE COMUSU_ID_USUARIO='"+id_usu+"'
		while(rs.next()){
//			ComunidadUsuario comusu= new ComunidadUsuario(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5));
			ComunidadUsuario comusu= new ComunidadUsuario(rs.getString("COMUSU_ID_USUARIO"),rs.getInt("COMUSU_ID_COMUNIDADES"), 
					rs.getInt("COMUSU_ADMINISTRADOR"), rs.getString("COMUSU_NUM_CUENTA"), rs.getString("COMUSU_PISO"));

			com_usu.add(comusu);
		}
		
		rs=st.executeQuery("SELECT * FROM COMUSU_COMUNIDAD_USUARIO");		// WHERE COMUSU_ID_USUARIO='"+id_usu+"'
		while(rs.next()){
//			ComunidadUsuario comusu= new ComunidadUsuario(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5));
			ComunidadUsuario comusu= new ComunidadUsuario(rs.getString("COMUSU_ID_USUARIO"),rs.getInt("COMUSU_ID_COMUNIDADES"), 
					rs.getInt("COMUSU_ADMINISTRADOR"), rs.getString("COMUSU_NUM_CUENTA"), rs.getString("COMUSU_PISO"));

			comunidades_usuarios.add(comusu);
		}

		for (int i = 0; i < com_usu.size(); i++) {
			rs=st.executeQuery("SELECT * FROM LOGUSU_LOGIN_USUARIOS WHERE LOGUSU_ID IN "
					+ "(SELECT COMUSU_ID_USUARIO FROM COMUSU_COMUNIDAD_USUARIO WHERE COMUSU_ID_COMUNIDADES="+com_usu.get(i).getId_comunidades()+")");		
			while(rs.next()){
				Usuario usu=new Usuario(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),com_usu.get(i).getId_comunidades());
				usuarios.add(usu);
			}
			
		}
		
		rs=st.executeQuery("SELECT * FROM COM_COMUNIDADES");		
		while(rs.next()){
			Comunidad com= new Comunidad(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
			comunidades.add(com);
		}
		
		for (int i = 0; i < com_usu.size(); i++) {
			rs=st.executeQuery("SELECT * FROM CUEN_CUENTAS WHERE CUEN_ID_COMUNIDAD="+com_usu.get(i).getId_comunidades());		
			while(rs.next()){
				Cuenta cue=new Cuenta(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDouble(4));
				cuentas.add(cue);
			}
			
		}
		
		for (int i = 0; i < com_usu.size(); i++) {
			rs=st.executeQuery("SELECT * FROM MOVCUE_MOVIMIENTOS_CUENTAS WHERE MOVCUE_ID_USUARIO IN "
					+ "(SELECT COMUSU_ID_USUARIO FROM COMUSU_COMUNIDAD_USUARIO WHERE COMUSU_ID_COMUNIDADES="+com_usu.get(i).getId_comunidades()+")");		
			while(rs.next()){
				Movimiento mov=new Movimiento(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDouble(4), rs.getInt(5), 
						rs.getDate(6), rs.getString(7), rs.getDouble(8), rs.getDouble(9),com_usu.get(i).getId_comunidades());
				movimientos.add(mov);

			}
		}
		
		for (int i = 0; i < cuentas.size(); i++) {
			rs=st.executeQuery("SELECT * FROM MOVCUE_MOVIMIENTOS_CUENTAS WHERE MOVCUE_ID_CUENTA="+cuentas.get(i).getId_cuenta());		
			while(rs.next()){
				Movimiento mov=new Movimiento(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDouble(4), rs.getInt(5), 
						rs.getDate(6), rs.getString(7), rs.getDouble(8), rs.getDouble(9),cuentas.get(i).getId_comunidad());
				movimientosComunidad.add(mov);

			}
		}
		
		for (int i = 0; i < com_usu.size(); i++) {
			rs=st.executeQuery("SELECT * FROM TABANU_TABLON_ANUNCIOS WHERE TABANU_ID_COMUNIDAD="+com_usu.get(i).getId_comunidades());		
			while(rs.next()){
				Anuncio anun= new Anuncio(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6));
				anuncios.add(anun);
			}			
		}
		
		for (int i = 0; i < com_usu.size(); i++) {
			rs=st.executeQuery("SELECT * FROM INCI_INCIDENCIAS WHERE INCI_ID_COMUNIDAD='"+com_usu.get(i).getId_comunidades()+"'");		
			while(rs.next()){
				Incidencia inci= new Incidencia(rs.getInt(1), rs.getInt(2), rs.getString(3), 
						rs.getString(4), rs.getString(5),rs.getInt(6), rs.getDate(7));
				incidencias.add(inci);
			}			
		}
		
		rs=st.executeQuery("SELECT * FROM MENS_MENSAJES WHERE MENS_ID_USUARIO='"+id_usu+"'");		
		while(rs.next()){
			Mensaje men=new Mensaje(rs.getInt(1),rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), 
					rs.getString(6), rs.getDate(7));
			mensajes.add(men);
		}
		
		for (int i = 0; i < com_usu.size(); i++) {
			rs=st.executeQuery("SELECT * FROM INST_INSTALACIONES WHERE INST_ID_COMUNIDAD='"+com_usu.get(i).getId_comunidades()+"'");		
			while(rs.next()){
				Instalacion inst= new Instalacion(rs.getInt(1), rs.getInt(2), rs.getString(3), 
						rs.getInt(4), rs.getString(5),rs.getInt(6), rs.getString(7),rs.getInt(8));
				instalaciones.add(inst);
			}			
		}
		
		for (int i = 0; i < instalaciones.size(); i++) {
			rs=st.executeQuery("SELECT * FROM RESINS_RESERVAS_INSTALACIONES WHERE RESINS_ID_INSTALACION="+instalaciones.get(i).getId_instalacion());		
			while(rs.next()){
				Reserva res= new Reserva(rs.getInt(1), rs.getInt(2), rs.getDate(3), 
						rs.getInt(4),rs.getInt(5));
				reservas.add(res);
			}
		}
		
		for (int i = 0; i < com_usu.size(); i++) {
			rs=st.executeQuery("SELECT * FROM MARC_MARCAS_CALENDARIO WHERE MARC_ID_COMUNIDAD="+com_usu.get(i).getId_comunidades());		
			while(rs.next()){
				Recordatorio rec= new Recordatorio(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getTime(4), rs.getString(5),rs.getString(6));
				recordatorios.add(rec);
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
	
	public ArrayList<Recordatorio> getRecordatorios(){
		return recordatorios;
	}
	
	public Usuario getUsuarioLog() {
		return usuarioLog;
	}

	public void setUsuarioLog(Usuario usuarioLog) {
		this.usuarioLog = usuarioLog;
	}
	
	public ArrayList<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(ArrayList<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}
	
	public ArrayList<ComunidadUsuario> getComunidadesUsuarios() {
		return comunidades_usuarios;
	}

	public void setComunidadesUsuarios(ArrayList<ComunidadUsuario> comunidadesusuarios) {
		this.comunidades_usuarios = comunidadesusuarios;
	}	
	
	public ArrayList<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(ArrayList<Reserva> reservas) {
		this.reservas = reservas;
	}	

	public ArrayList<Movimiento> getMovimientosComunidad() {
		return movimientosComunidad;
	}

	public void setMovimientosComunidad(ArrayList<Movimiento> movimientosComunidad) {
		this.movimientosComunidad = movimientosComunidad;
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
	
	public void RefrescarArray(String array) throws ClassNotFoundException, SQLException{
		c.conectar();
		st=c.getConexion().createStatement();
		
		switch (array) {
		case "usuarios":
			usuarios.clear();
			for (int i = 0; i < com_usu.size(); i++) {
				rs=st.executeQuery("SELECT * FROM LOGUSU_LOGIN_USUARIOS WHERE LOGUSU_ID IN "
						+ "(SELECT COMUSU_ID_USUARIO FROM COMUSU_COMUNIDAD_USUARIO WHERE COMUSU_ID_COMUNIDADES="+com_usu.get(i).getId_comunidades()+")");	
				while(rs.next()){
					Usuario usu=new Usuario(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),com_usu.get(i).getId_comunidades());
					usuarios.add(usu);
				}
				
			}			
			break;
			
		case "com_usu":
			com_usu.clear();
			rs=st.executeQuery("SELECT * FROM COMUSU_COMUNIDAD_USUARIO WHERE COMUSU_ID_USUARIO='"+id_usu+"'");		// WHERE COMUSU_ID_USUARIO='"+id_usu+"'
			while(rs.next()){
//				ComunidadUsuario comusu= new ComunidadUsuario(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5));
				ComunidadUsuario comusu= new ComunidadUsuario(rs.getString("COMUSU_ID_USUARIO"),rs.getInt("COMUSU_ID_COMUNIDADES"), 
						rs.getInt("COMUSU_ADMINISTRADOR"), rs.getString("COMUSU_NUM_CUENTA"), rs.getString("COMUSU_PISO"));

				com_usu.add(comusu);
			}		
			break;
			
		case "comunidades_usuarios":
			comunidades_usuarios.clear();
			rs=st.executeQuery("SELECT * FROM COMUSU_COMUNIDAD_USUARIO");		// WHERE COMUSU_ID_USUARIO='"+id_usu+"'
			while(rs.next()){
//				ComunidadUsuario comusu= new ComunidadUsuario(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5));
				ComunidadUsuario comusu= new ComunidadUsuario(rs.getString("COMUSU_ID_USUARIO"),rs.getInt("COMUSU_ID_COMUNIDADES"), 
						rs.getInt("COMUSU_ADMINISTRADOR"), rs.getString("COMUSU_NUM_CUENTA"), rs.getString("COMUSU_PISO"));

				comunidades_usuarios.add(comusu);
			}		
			break;
			
		case "comunidades":
			comunidades.clear();
			for (int i = 0; i < com_usu.size(); i++) {
				rs=st.executeQuery("SELECT * FROM COM_COMUNIDADES");		
				while(rs.next()){
					Comunidad com= new Comunidad(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
					comunidades.add(com);
				}
			}
			break;
			
		case "movimientos":
			movimientos.clear();
			for (int i = 0; i < com_usu.size(); i++) {
				rs=st.executeQuery("SELECT * FROM MOVCUE_MOVIMIENTOS_CUENTAS WHERE MOVCUE_ID_USUARIO IN "
						+ "(SELECT COMUSU_ID_USUARIO FROM COMUSU_COMUNIDAD_USUARIO WHERE COMUSU_ID_COMUNIDADES="+com_usu.get(i).getId_comunidades()+")");		
				while(rs.next()){
					Movimiento mov=new Movimiento(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDouble(4), rs.getInt(5), 
							rs.getDate(6), rs.getString(7), rs.getDouble(8), rs.getDouble(9),com_usu.get(i).getId_comunidades());
					movimientos.add(mov);
				}
			}
			
			for (int i = 0; i < cuentas.size(); i++) {
				rs=st.executeQuery("SELECT * FROM MOVCUE_MOVIMIENTOS_CUENTAS WHERE MOVCUE_ID_CUENTA="+cuentas.get(i).getId_cuenta());		
				while(rs.next()){
					Movimiento mov=new Movimiento(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDouble(4), rs.getInt(5), 
							rs.getDate(6), rs.getString(7), rs.getDouble(8), rs.getDouble(9),cuentas.get(i).getId_comunidad());
					movimientosComunidad.add(mov);

				}
			}
			break;
			
		case "cuentas":
			cuentas.clear();
			for (int i = 0; i < com_usu.size(); i++) {
				rs=st.executeQuery("SELECT * FROM CUEN_CUENTAS WHERE CUEN_ID_COMUNIDAD="+com_usu.get(i).getId_comunidades());		
				while(rs.next()){
					Cuenta cue=new Cuenta(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDouble(4));
					cuentas.add(cue);
				}
				
			}
			break;
		case "anuncios":
			anuncios.clear();
			for (int i = 0; i < com_usu.size(); i++) {
				rs=st.executeQuery("SELECT * FROM TABANU_TABLON_ANUNCIOS WHERE TABANU_ID_COMUNIDAD="+com_usu.get(i).getId_comunidades());		
				while(rs.next()){
					Anuncio anun= new Anuncio(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6));
					anuncios.add(anun);
				}			
			}
			break;
			
		case "incidencias":
			incidencias.clear();
			for (int i = 0; i < com_usu.size(); i++) {
				rs=st.executeQuery("SELECT * FROM INCI_INCIDENCIAS WHERE INCI_ID_COMUNIDAD="+com_usu.get(i).getId_comunidades());		
				while(rs.next()){
					Incidencia inci= new Incidencia(rs.getInt(1), rs.getInt(2), rs.getString(3), 
							rs.getString(4), rs.getString(5),rs.getInt(6), rs.getDate(7));
					incidencias.add(inci);
				}			
			}
			break;
			
		case "mensajes":
			mensajes.clear();
			rs=st.executeQuery("SELECT * FROM MENS_MENSAJES WHERE MENS_ID_USUARIO='"+id_usu+"'");		
			while(rs.next()){
				Mensaje men=new Mensaje(rs.getInt(1),rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), 
						rs.getString(6), rs.getDate(7));
				mensajes.add(men);
			}
			break;
			
		case "instalaciones":
			instalaciones.clear();
			for (int i = 0; i < com_usu.size(); i++) {
				rs=st.executeQuery("SELECT * FROM INST_INSTALACIONES WHERE INST_ID_COMUNIDAD="+com_usu.get(i).getId_comunidades());		
				while(rs.next()){
					Instalacion inst= new Instalacion(rs.getInt(1), rs.getInt(2), rs.getString(3), 
							rs.getInt(4), rs.getString(5),rs.getInt(6), rs.getString(7),rs.getInt(8));
					instalaciones.add(inst);
				}			
			}
			break;
			
		case "reservas":
			reservas.clear();
			for (int i = 0; i < instalaciones.size(); i++) {
				rs=st.executeQuery("SELECT * FROM RESINS_RESERVAS_INSTALACIONES WHERE RESINS_ID_INSTALACION="+instalaciones.get(i).getId_instalacion());		
				while(rs.next()){
					Reserva res= new Reserva(rs.getInt(1), rs.getInt(2), rs.getDate(3), 
							rs.getInt(4),rs.getInt(5));
					reservas.add(res);
				}
			}
			break;
			
		case "recordatorios":
			recordatorios.clear();
			for (int i = 0; i < com_usu.size(); i++) {
				rs=st.executeQuery("SELECT * FROM MARC_MARCAS_CALENDARIO WHERE MARC_ID_COMUNIDAD="+com_usu.get(i).getId_comunidades());		
				while(rs.next()){
					Recordatorio rec= new Recordatorio(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getTime(4), rs.getString(5),rs.getString(6));
					recordatorios.add(rec);
				}			
			}
			break;
		}		
		
		c.desconectar();
	}
	
	public void RefrescarArray() throws ClassNotFoundException, SQLException{
		c.conectar();
		st=c.getConexion().createStatement();
		
			usuarios.clear();
			for (int i = 0; i < com_usu.size(); i++) {

				rs=st.executeQuery("SELECT * FROM LOGUSU_LOGIN_USUARIOS WHERE LOGUSU_ID IN "
						+ "(SELECT COMUSU_ID_USUARIO FROM COMUSU_COMUNIDAD_USUARIO WHERE COMUSU_ID_COMUNIDADES="+com_usu.get(i).getId_comunidades()+")");
				while(rs.next()){
					Usuario usu=new Usuario(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),com_usu.get(i).getId_comunidades());
					usuarios.add(usu);
				}
				
			}
			
			com_usu.clear();
			rs=st.executeQuery("SELECT * FROM COMUSU_COMUNIDAD_USUARIO WHERE COMUSU_ID_USUARIO='"+id_usu+"'");		// WHERE COMUSU_ID_USUARIO='"+id_usu+"'
			while(rs.next()){
//				ComunidadUsuario comusu= new ComunidadUsuario(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5));
				ComunidadUsuario comusu= new ComunidadUsuario(rs.getString("COMUSU_ID_USUARIO"),rs.getInt("COMUSU_ID_COMUNIDADES"), 
						rs.getInt("COMUSU_ADMINISTRADOR"), rs.getString("COMUSU_NUM_CUENTA"), rs.getString("COMUSU_PISO"));

				com_usu.add(comusu);
			}		
			
			comunidades_usuarios.clear();
			rs=st.executeQuery("SELECT * FROM COMUSU_COMUNIDAD_USUARIO");		// WHERE COMUSU_ID_USUARIO='"+id_usu+"'
			while(rs.next()){
//				ComunidadUsuario comusu= new ComunidadUsuario(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5));
				ComunidadUsuario comusu= new ComunidadUsuario(rs.getString("COMUSU_ID_USUARIO"),rs.getInt("COMUSU_ID_COMUNIDADES"), 
						rs.getInt("COMUSU_ADMINISTRADOR"), rs.getString("COMUSU_NUM_CUENTA"), rs.getString("COMUSU_PISO"));

				comunidades_usuarios.add(comusu);
			}		
			
			comunidades.clear();
			for (int i = 0; i < com_usu.size(); i++) {
				rs=st.executeQuery("SELECT * FROM COM_COMUNIDADES");		
				while(rs.next()){
					Comunidad com= new Comunidad(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
					comunidades.add(com);
				}
			}
			
			movimientos.clear();
			for (int i = 0; i < com_usu.size(); i++) {
				rs=st.executeQuery("SELECT * FROM MOVCUE_MOVIMIENTOS_CUENTAS WHERE MOVCUE_ID_USUARIO IN "
						+ "(SELECT COMUSU_ID_USUARIO FROM COMUSU_COMUNIDAD_USUARIO WHERE COMUSU_ID_COMUNIDADES="+com_usu.get(i).getId_comunidades()+")");		
				while(rs.next()){
					Movimiento mov=new Movimiento(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDouble(4), rs.getInt(5), 
							rs.getDate(6), rs.getString(7), rs.getDouble(8), rs.getDouble(9),com_usu.get(i).getId_comunidades());
					movimientos.add(mov);
				}
			}
			
			cuentas.clear();
			for (int i = 0; i < com_usu.size(); i++) {
				rs=st.executeQuery("SELECT * FROM CUEN_CUENTAS WHERE CUEN_ID_COMUNIDAD="+com_usu.get(i).getId_comunidades());		
				while(rs.next()){
					Cuenta cue=new Cuenta(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDouble(4));
					cuentas.add(cue);
				}
				
			}
			
			movimientosComunidad.clear();
			for (int i = 0; i < cuentas.size(); i++) {
				rs=st.executeQuery("SELECT * FROM MOVCUE_MOVIMIENTOS_CUENTAS WHERE MOVCUE_ID_CUENTA="+cuentas.get(i).getId_cuenta());		
				while(rs.next()){
					Movimiento mov=new Movimiento(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDouble(4), rs.getInt(5), 
							rs.getDate(6), rs.getString(7), rs.getDouble(8), rs.getDouble(9),cuentas.get(i).getId_comunidad());
					movimientosComunidad.add(mov);

				}
			}
			
			anuncios.clear();
			for (int i = 0; i < com_usu.size(); i++) {
				rs=st.executeQuery("SELECT * FROM TABANU_TABLON_ANUNCIOS WHERE TABANU_ID_COMUNIDAD="+com_usu.get(i).getId_comunidades());		
				while(rs.next()){
					Anuncio anun= new Anuncio(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6));
					anuncios.add(anun);
				}			
			}
			
			incidencias.clear();
			for (int i = 0; i < com_usu.size(); i++) {
				rs=st.executeQuery("SELECT * FROM INCI_INCIDENCIAS WHERE INCI_ID_COMUNIDAD="+com_usu.get(i).getId_comunidades());		
				while(rs.next()){
					Incidencia inci= new Incidencia(rs.getInt(1), rs.getInt(2), rs.getString(3), 
							rs.getString(4), rs.getString(5),rs.getInt(6), rs.getDate(7));
					incidencias.add(inci);
				}			
			}
			
			mensajes.clear();
			rs=st.executeQuery("SELECT * FROM MENS_MENSAJES WHERE MENS_ID_USUARIO='"+id_usu+"'");		
			while(rs.next()){
				Mensaje men=new Mensaje(rs.getInt(1),rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), 
						rs.getString(6), rs.getDate(7));
				mensajes.add(men);
			}
			
			instalaciones.clear();
			for (int i = 0; i < com_usu.size(); i++) {
				rs=st.executeQuery("SELECT * FROM INST_INSTALACIONES WHERE INST_ID_COMUNIDAD="+com_usu.get(i).getId_comunidades());		
				while(rs.next()){
					Instalacion inst= new Instalacion(rs.getInt(1), rs.getInt(2), rs.getString(3), 
							rs.getInt(4), rs.getString(5),rs.getInt(6), rs.getString(7),rs.getInt(8));
					instalaciones.add(inst);
				}			
			}
			
			reservas.clear();
			for (int i = 0; i < instalaciones.size(); i++) {
				rs=st.executeQuery("SELECT * FROM RESINS_RESERVAS_INSTALACIONES WHERE RESINS_ID_INSTALACION="+instalaciones.get(i).getId_instalacion());		
				while(rs.next()){
					Reserva res= new Reserva(rs.getInt(1), rs.getInt(2), rs.getDate(3), 
							rs.getInt(4),rs.getInt(5));
					reservas.add(res);
				}
			}
			
			recordatorios.clear();
			for (int i = 0; i < com_usu.size(); i++) {
				rs=st.executeQuery("SELECT * FROM MARC_MARCAS_CALENDARIO WHERE MARC_ID_COMUNIDAD="+com_usu.get(i).getId_comunidades());		
				while(rs.next()){
					Recordatorio rec= new Recordatorio(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getTime(4), rs.getString(5),rs.getString(6));
					recordatorios.add(rec);
				}			
			}				
		
		c.desconectar();
	}
	
	public ArrayList<String> recuperarNomComunidad(String usuario){
		ArrayList<String> nomComunidad = new ArrayList<String>();
		String query="SELECT COM_NOMBRE FROM COM_COMUNIDADES WHERE COM_ID IN "
				+ "(SELECT COMUSU_ID_COMUNIDADES FROM COMUSU_COMUNIDAD_USUARIO WHERE COMUSU_ID_USUARIO='"+usuario+"')";
		
		try {
			c.conectar();
			st=c.getConexion().createStatement();		
			rs=st.executeQuery(query);
			
			while(rs.next()){
				nomComunidad.add(rs.getString(1));
			}

			c.desconectar();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return nomComunidad;
	}

	@Override
	public void run() {
		
		try {
			Thread.sleep(60000);
			RefrescarArray();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
