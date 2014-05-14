package BD;

import java.sql.SQLException;
import java.sql.Statement;

public class Eliminar {
	
	private Statement st;
	
	public void eliminarAnuncio(String query) throws ClassNotFoundException, SQLException{
		
		Conectar c=new Conectar();
		c.conectar();		

		st = c.getConexion().createStatement();		
		st.executeUpdate(query);
		c.desconectar();
	}

}
