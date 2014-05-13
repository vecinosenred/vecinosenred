package BD;

import java.sql.SQLException;
import java.sql.Statement;

public class Introducir {
	
	private Statement st;
	
	public void introducir(String query) throws SQLException, ClassNotFoundException{
				
		Conectar c=new Conectar();
		c.conectar();		

		st = c.getConexion().createStatement();
		st.executeUpdate(query);		
		
		c.desconectar();
	}

}
