package BD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Actualizar extends Conectar{

	private Statement st;

	public void actualizar(String tabla,String columna,String partido,String filaString,int filaInt,int nDorsal) throws ClassNotFoundException, SQLException{
		
		int dato = 0;
		if(tabla!="JUGADORES"){
			Recuperar rc=new Recuperar("");
			ResultSet rs=rc.recuperarColumna("SELECT "+columna+" FROM "+tabla+" WHERE DORSAL="+filaInt);
			while(rs.next()){
				dato=rs.getInt(1);
			}
			dato++;
		}
		
		Conectar c=new Conectar();
		c.conectar();		

		st = c.getConexion().createStatement();	
		
		if(tabla=="ESTADISTICAS_JUGADORES"){
			st.executeUpdate("UPDATE ESTADISTICAS_JUGADORES " +
		    		"SET " +columna+"=" +dato+
		    		" WHERE DORSAL="+filaInt);
		}
		if(tabla=="ESTADISTICAS_PORTEROS"){
			st.executeUpdate("UPDATE ESTADISTICAS_PORTEROS " +
		    		"SET " +columna+"=" +dato+
		    		" WHERE DORSAL="+filaInt);
		}
		if(tabla=="ESTADISTICAS_PARTIDO"){
			st.executeUpdate("UPDATE ESTADISTICAS_PARTIDO " +
		    		"SET " +columna+"=" +dato+
		    		" WHERE PARTIDO='"+partido+"'");
		}
		if(tabla=="JUGADORES"){
			st.executeUpdate("UPDATE JUGADORES " +
		    		"SET DORSAL=" +nDorsal+
		    		" WHERE NOMBRE='"+filaString+"'");
		}
		
		c.desconectar();
		
	}
	
}
