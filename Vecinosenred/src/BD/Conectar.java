package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conectar{	

	private Connection conexion;
	public boolean runn=false;
	
	public Connection getConexion(){
		return conexion;
	}
	
	public void conectar() throws ClassNotFoundException{
		
		try
	    {
	    Class.forName("com.mysql.jdbc.Driver");
	    conexion = DriverManager.getConnection("jdbc:mysql://db4free.net/vecinos","vecinos" , "vecinos");
	    System.out.println(" conectat");   
	    
	    
	    }catch(SQLException e){
	    	System.out.println("Error al conectar");
	    }
		
	}
	
	public void desconectar() throws SQLException{
		
		conexion.close();
		System.out.println("desconectado");
		
	}


}
