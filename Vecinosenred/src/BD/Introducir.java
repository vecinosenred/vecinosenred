package BD;

import java.sql.SQLException;
import java.sql.Statement;

public class Introducir {
	
	private Statement st;
	
	public void introducir(String tabla,String partido,int dorsal,String nombre,String posicion,String foto) throws SQLException, ClassNotFoundException{
				
		Conectar c=new Conectar();
		c.conectar();		

		st = c.getConexion().createStatement();	
		
		if(tabla=="partidos"){
			st.executeUpdate("INSERT INTO ESTADISTICAS_PARTIDO VALUES"+
		    		"('"+partido+"',0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0)");
		}
		if(tabla=="jugador"){
			st.executeUpdate("INSERT INTO JUGADORES VALUES"+
		    		"("+dorsal+",'"+nombre+"','"+posicion+"','"+foto+"')");
			System.out.println(posicion+"r"+foto);
			if(posicion.contentEquals("POR")){
				st.executeUpdate("INSERT INTO ESTADISTICAS_PORTEROS VALUES"+
			    		"("+dorsal+",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0)");
			}else{
				st.executeUpdate("INSERT INTO ESTADISTICAS_JUGADORES VALUES"+
		    		"("+dorsal+",0,0,0,0,0,0,0)");
			}
		}
		
		c.desconectar();
	}

}
