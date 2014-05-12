
package metodos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import BD.Conectar;
import clases.Incidencia;
import clases.Mensaje;
import frames.Login;
import frames.Principal;

public class Eventos implements ActionListener {
	Principal gui;
	Conectar c;
	Statement st;
	ResultSet rs;

	public Eventos(Principal in){
		gui = in;
	}
	public void llenarlistaMensajes(ArrayList<Mensaje> mns,int id_com){
		
		gui.ListaMensajes.clear();
		for (int i = gui.model.getRowCount() - 1; i > -1; i--) {
	        gui.model.removeRow(i);
	    }
		for (int i = 0; i < mns.size(); i++) {
			if(mns.get(i).getId_comunidad()==id_com){
				gui.ListaMensajes.add(mns.get(i));
			}
		}
		
		Object[] fila = new Object[gui.model.getColumnCount()];
		for (int i = 0; i < gui.ListaMensajes.size(); i++) {
			if(gui.ListaMensajes.get(i).getId_comunidad()==id_com){
				fila[0]=gui.ListaMensajes.get(i).getId_usuario();
				fila[1]=gui.ListaMensajes.get(i).getId_destinatario();
				fila[2]=gui.ListaMensajes.get(i).getAsunto();
				fila[3]=gui.ListaMensajes.get(i).getMensaje();
				gui.model.addRow(fila);
				gui.tablamensajes.setModel(gui.model);
				gui.tablamensajes.validate();
				gui.tablamensajes.repaint();
			}
			
		}
		
	}
	
	public void llenarlistaIncidencias(ArrayList<Incidencia> mns,int id_com){
		
		gui.ListaIncidencias.clear();
		for (int i = gui.modelo.getRowCount() - 1; i > -1; i--) {
	        gui.modelo.removeRow(i);
	    }
		for (int i = 0; i < mns.size(); i++) {
			if(mns.get(i).getId_comunidad()==id_com){
				gui.ListaIncidencias.add(mns.get(i));
			}
		}
		
		Object[] fila = new Object[gui.modelo.getColumnCount()];
		for (int i = 0; i < gui.ListaIncidencias.size(); i++) {
			if(gui.ListaIncidencias.get(i).getId_comunidad()==id_com){
				fila[0]=gui.ListaIncidencias.get(i).getId_mensaje();
				fila[1]=gui.ListaIncidencias.get(i).getTitulo();
				fila[2]=gui.ListaIncidencias.get(i).getEstado();
				fila[3]=gui.ListaIncidencias.get(i).getFecha_creacion();
				gui.modelo.addRow(fila);
				gui.tablaincidencias.setModel(gui.modelo);
				gui.tablaincidencias.validate();
				gui.tablaincidencias.repaint();
			}
			
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