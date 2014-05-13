package BD;

import java.sql.SQLException;
import java.sql.Statement;

public class Eliminar {
	
	private Statement st;
	
	public void eliminarAnuncio(int id) throws ClassNotFoundException, SQLException{
		
		Conectar c=new Conectar();
		c.conectar();		

		st = c.getConexion().createStatement();		
		st.executeUpdate("DELETE FROM INCI_INCIDENCIAS WHERE INCI_ID_INCIDENCIA="+id);
		c.desconectar();
	}

}
