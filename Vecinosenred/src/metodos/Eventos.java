
package metodos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import BD.Conectar;
import BD.Eliminar;
import clases.Anuncio;
import clases.Incidencia;
import clases.Mensaje;
import clases.Recordatorio;
import frames.Calendario;
import frames.Login;
import frames.Principal;
import frames.VentanaMensaje;

public class Eventos implements ActionListener {
	Principal gui;
	Eliminar eliminar=new Eliminar();
	Statement st;
	ResultSet rs;
	String id_usuario;

	public Eventos(Principal in,String usuario){
		gui = in;
		id_usuario=usuario;
	}
	public void llenarlistaMensajes(ArrayList<Mensaje> mns,int id_com){
		
		gui.ListaMensajes.clear();
		for (int i = gui.modeloMensajes.getRowCount() - 1; i > -1; i--) {
	        gui.modeloMensajes.removeRow(i);
	    }
		for (int i = 0; i < mns.size(); i++) {
			if(mns.get(i).getId_comunidad()==id_com){
				gui.ListaMensajes.add(mns.get(i));
			}
		}
				
		Object[] fila = new Object[gui.modeloMensajes.getColumnCount()];
		for (int i = 0; i < gui.ListaMensajes.size(); i++) {
			if(gui.ListaMensajes.get(i).getId_comunidad()==id_com){
				fila[0]=gui.ListaMensajes.get(i).getId_usuario();
				fila[1]=gui.ListaMensajes.get(i).getId_destinatario();
				fila[2]=gui.ListaMensajes.get(i).getAsunto();
				fila[3]=gui.ListaMensajes.get(i).getMensaje();
				fila[4]="Eliminar";
				gui.modeloMensajes.addRow(fila);
			}
			
		}			

		gui.tablamensajes.setModel(gui.modeloMensajes);
		gui.tablamensajes.validate();
		gui.tablamensajes.repaint();	

		Action delete = new AbstractAction()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        try {
					eliminar.eliminarAnuncio("DELETE FROM MENS_MENSAJES WHERE MENS_ID_MENSAJE='"+
		        gui.ListaMensajes.get(Integer.valueOf( e.getActionCommand() )).getId_mensaje()+"'");
					gui.recuperar.RefrescarArray("mensajes");
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		        
		        JTable table = (JTable)e.getSource();
		        int modelRow = Integer.valueOf( e.getActionCommand() );
		        ((DefaultTableModel)table.getModel()).removeRow(modelRow);
		        
		    }
		};
		 
		ButtonColumn buttonColumn = new ButtonColumn(gui.tablamensajes, delete, 4);
		buttonColumn.setMnemonic(KeyEvent.VK_D);
		
	}
	
	public void llenarlistaIncidencias(ArrayList<Incidencia> mns,int id_com){
		
		gui.ListaIncidencias.clear();
		for (int i = gui.modeloIncidencias.getRowCount() - 1; i > -1; i--) {
	        gui.modeloIncidencias.removeRow(i);
	    }
		for (int i = 0; i < mns.size(); i++) {
			if(mns.get(i).getId_comunidad()==id_com){
				gui.ListaIncidencias.add(mns.get(i));
			}
		}
		
		Object[] fila = new Object[gui.modeloIncidencias.getColumnCount()];
		for (int i = 0; i < gui.ListaIncidencias.size(); i++) {
			if(gui.ListaIncidencias.get(i).getId_comunidad()==id_com){
				fila[0]=gui.ListaIncidencias.get(i).getId_mensaje();
				fila[1]=gui.ListaIncidencias.get(i).getTitulo();
				fila[2]=gui.ListaIncidencias.get(i).getEstado();
				fila[3]=gui.ListaIncidencias.get(i).getFecha_creacion();
				fila[4]="Eliminar";
				gui.modeloIncidencias.addRow(fila);
				gui.tablaincidencias.setModel(gui.modeloIncidencias);
				gui.tablaincidencias.validate();
				gui.tablaincidencias.repaint();
			}
			
		}	
		

		Action delete = new AbstractAction()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        try {
					eliminar.eliminarAnuncio("DELETE FROM INCI_INCIDENCIAS WHERE INCI_ID_INCIDENCIA='"+
		        gui.ListaIncidencias.get(Integer.valueOf( e.getActionCommand() )).getId_mensaje()+"'");
					gui.recuperar.RefrescarArray("incidencias");
					
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		        
		        JTable table = (JTable)e.getSource();
		        int modelRow = Integer.valueOf( e.getActionCommand() );
		        ((DefaultTableModel)table.getModel()).removeRow(modelRow);
		        
		    }
		};
		 
		ButtonColumn buttonColumn = new ButtonColumn(gui.tablaincidencias, delete, 4);
		buttonColumn.setMnemonic(KeyEvent.VK_D);
		
	}
	
	public void llenarlistaAnuncios(ArrayList<Anuncio> mns,int id_com) throws ClassNotFoundException, SQLException{
		
		gui.ListaAnuncios.clear();
		for (int i = gui.modeloAnuncios.getRowCount() - 1; i > -1; i--) {
	        gui.modeloAnuncios.removeRow(i);
	    }
		for (int i = 0; i < mns.size(); i++) {
			if(mns.get(i).getId_comunidad()==id_com){
				gui.ListaAnuncios.add(mns.get(i));
			}
		}
		
		Object[] fila = new Object[gui.modeloAnuncios.getColumnCount()];
		for (int i = 0; i < gui.ListaAnuncios.size(); i++) {
			if(gui.ListaAnuncios.get(i).getId_comunidad()==id_com){
				fila[0]=gui.ListaAnuncios.get(i).getId_mensaje();
				fila[1]=gui.ListaAnuncios.get(i).getTitulo();
				fila[2]=gui.ListaAnuncios.get(i).getMensaje();
				fila[3]=gui.ListaAnuncios.get(i).getFecha_creacion();
				fila[4]="Eliminar";								
				gui.modeloAnuncios.addRow(fila);
				gui.tablaAnuncios.setModel(gui.modeloAnuncios);
				gui.tablaAnuncios.validate();
				gui.tablaAnuncios.repaint();
			}			
			
		}	
		

		Action delete = new AbstractAction()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        try {
					eliminar.eliminarAnuncio("DELETE FROM TABANU_TABLON_ANUNCIOS WHERE TABANU_ID_MENSAJE='"+
		        gui.ListaAnuncios.get(Integer.valueOf( e.getActionCommand() )).getId_mensaje()+"'");
					gui.recuperar.RefrescarArray("anuncios");
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		        
		        JTable table = (JTable)e.getSource();
		        int modelRow = Integer.valueOf( e.getActionCommand() );
		        ((DefaultTableModel)table.getModel()).removeRow(modelRow);
		        
		    }
		};
		 
		ButtonColumn buttonColumn = new ButtonColumn(gui.tablaAnuncios, delete, 4);
		buttonColumn.setMnemonic(KeyEvent.VK_D);
		
	}
	
	public void mostrarCalendario(ArrayList<Recordatorio> rec,int id_com) throws ClassNotFoundException, SQLException{
		
		gui.Calendario.removeAll();
		gui.Calendario.add(new Calendario(rec,id_com));
		gui.Calendario.validate();
		gui.Calendario.repaint();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==gui.mntmLogin){
			final Login login= new Login(gui);
			login.setVisible(true);
		}
		
		if (e.getSource() == gui.anadirmensaje) {
			System.out.println("mensaje");
			VentanaMensaje anadirmensaje = new VentanaMensaje();
			anadirmensaje.setVisible(true);
		}
		
	}
}