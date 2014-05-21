package BD;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author JonB
 * Clase utilizada para actualizar la BDD
 */
public class Actualizar{

	private Statement st;
	public void actualizar(String query) throws ClassNotFoundException, SQLException{
		
		
		
		Conectar c=new Conectar();
		c.conectar();		

		st = c.getConexion().createStatement();	
		st.executeQuery(query);		
		
		c.desconectar();
		
	}
	
}
