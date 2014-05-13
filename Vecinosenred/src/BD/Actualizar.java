package BD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Actualizar{

	private Statement st;
	private ResultSet rs;

	public void actualizar(String query) throws ClassNotFoundException, SQLException{
		
		
		
		Conectar c=new Conectar();
		c.conectar();		

		st = c.getConexion().createStatement();	
		rs=st.executeQuery(query);		
		
		c.desconectar();
		
	}
	
}
