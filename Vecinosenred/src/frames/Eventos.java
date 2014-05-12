package frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import clases.*;

public class Eventos implements ActionListener {
	Principal gui;

	public Eventos(Principal in){
		gui = in;
	}
	public void llenarlistaMensajes(String usuario){
		try {
			String query = "Select * from Mensajes where ID_usuario like '"+usuario+"' OR ID_Destinatario like '"+usuario+"'";
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://db4free.net/vecinos","vecinos" , "vecinos");
			System.out.println("conectat");
			Statement bt = conexion.createStatement();
			ResultSet rs = bt.executeQuery(query);
			while (rs.next()) {
				Mensaje m =new Mensaje(rs.getString(0), rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5));
				gui.ListaMensajes.add(m);
				}
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState:" + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} catch (ClassNotFoundException ex) {
			
			ex.printStackTrace();
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==gui.mntmLogin){
			final Login login= new Login(gui);
			login.setVisible(true);
		}
		
	}
}
