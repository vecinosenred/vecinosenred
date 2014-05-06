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
		
		System.out.println(usuario+" "+pass);
		
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
				System.out.println(rs.getString(1));
				if(check.equals(usuario)){					
						existe=true;
						System.out.println(existe);
				}
			}			

			c.desconectar();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
				System.out.println(existe);
			}			

			c.desconectar();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return existe;
		
	}

}
