package metodos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import BD.Conectar;

public class ComprobarUsuario {
	
	private String usuario,pass;
	private Statement st;
	private ResultSet rs;
	
	public ComprobarUsuario(String usuario,String pass){
		this.usuario=usuario;
		this.pass=pass;
	}
	
	public int comprobar(){
		int tipo=0;
		
		if(compUsuario()==true){
			if(compPass()==true){
				tipo=1;
			}
		}
		
		return tipo;
	}
	
	private boolean compUsuario(){
		boolean existe=false;
		Conectar c= new Conectar();
		try {
			c.conectar();
			
			st =c.getConexion().createStatement();
			rs=st.executeQuery("SELECT LOGUSU_ID FROM LOGUSU_LOGIN_USUARIOS");
			String check;
			
			while(rs.next()){
				check=rs.getString(1);
				if(check.equals(usuario)){					
						existe=true;
				}
			}			

			c.desconectar();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return existe;
		
	}
	
	private boolean compPass(){
		boolean existe=false;
		Conectar c= new Conectar();
		try {
			c.conectar();
			
			st =c.getConexion().createStatement();
			rs=st.executeQuery("SELECT LOGUSU_PASSWORD FROM LOGUSU_LOGIN_USUARIOS WHERE LOGUSU_ID='"+usuario+"'");
			rs.next();
			String check=rs.getString(1);
			if(check.equals(pass)){
				existe=true;
			}			

			c.desconectar();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return existe;
		
	}

}
