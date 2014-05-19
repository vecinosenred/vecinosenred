
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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import BD.Eliminar;
import clases.Anuncio;
import clases.Cuenta;
import clases.Incidencia;
import clases.Mensaje;
import clases.Movimiento;
import clases.Recordatorio;
import clases.Usuario;
import frames.Calendario;
import frames.Login;
import frames.Principal;
import frames.VentanaContraseña;
import frames.VentanaUsuario;

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
				fila[0]=gui.ListaMensajes.get(i).getId_usuario();
				fila[1]=gui.ListaMensajes.get(i).getId_destinatario();
				fila[2]=gui.ListaMensajes.get(i).getAsunto();
				fila[3]=gui.ListaMensajes.get(i).getMensaje();
				fila[4]="Eliminar";
				gui.modeloMensajes.addRow(fila);
			
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
		

		Action delete = new AbstractAction()
		{
			public void actionPerformed(ActionEvent e)
		    {
				System.out.println("hola capullo");
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
	
	public void llenarlistaCuentas(ArrayList<Movimiento> mns,int id_com){
		
		gui.ListaMovimientos.clear();
		for (int i = gui.modeloCuentas.getRowCount() - 1; i > -1; i--) {
	        gui.modeloCuentas.removeRow(i);
	    }
		for (int i = 0; i < mns.size(); i++) {
			if(mns.get(i).getId_comunidad()==id_com){
				gui.ListaMovimientos.add(mns.get(i));
			}
		}
		
		Usuario usuario=gui.logueado;
		Cuenta m = null;
		
		Object[] fila = new Object[gui.modeloCuentas.getColumnCount()];
		
		for (int i = 0; i < gui.ListaMovimientos.size(); i++) {
			int id_cuenta=gui.ListaMovimientos.get(i).getId_cuenta();
			for (int j = 0; j < gui.recuperar.getCuentas().size(); j++) {
				if(gui.recuperar.getCuentas().get(j).getId_cuenta()==id_cuenta &&
						gui.recuperar.getCuentas().get(j).getId_comunidad()==id_com){
					m=gui.recuperar.getCuentas().get(j);
					
					if(gui.ListaMovimientos.get(i).getId_usuario().equals(usuario.getUsuario())){
						fila[0]=gui.recuperar.getCuentas().get(gui.recuperar.getCuentas().indexOf(m)).getNum_cuenta();
						fila[1]=usuario.getNombre();
						fila[2]=gui.ListaMovimientos.get(i).getCantidad();
						fila[3]=gui.ListaMovimientos.get(i).getTipo_movimiento();
						fila[4]=gui.ListaMovimientos.get(i).getFecha();
						fila[5]=gui.ListaMovimientos.get(i).getMotivo();
						fila[6]=gui.ListaMovimientos.get(i).getSaldo_anterior();
						fila[7]=gui.ListaMovimientos.get(i).getSaldo_final();
						continue;
					}
				
				}
			}
			gui.modeloCuentas.addRow(fila);
			gui.tablaCuentas.setModel(gui.modeloCuentas);
			gui.tablaCuentas.validate();
			gui.tablaCuentas.repaint();
						
			
		}
	}
	
	public void llenarlistaComunidad(ArrayList<Movimiento> mns,int id_com) throws ClassNotFoundException, SQLException{
		
		gui.ListaMovimientos.clear();
		for (int i = gui.modeloComunidad.getRowCount() - 1; i > -1; i--) {
	        gui.modeloComunidad.removeRow(i);
	    }
		for (int i = 0; i < mns.size(); i++) {
			if(mns.get(i).getId_comunidad()==id_com){
				gui.ListaMovimientos.add(mns.get(i));
			}
		}
		
		Cuenta m = null;
		
		Object[] fila = new Object[gui.modeloComunidad.getColumnCount()];
		
		for (int i = 0; i < gui.ListaMovimientos.size(); i++) {
			int id_cuenta=gui.ListaMovimientos.get(i).getId_cuenta();
			for (int j = 0; j < gui.recuperar.getCuentas().size(); j++) {
				if(gui.recuperar.getCuentas().get(j).getId_cuenta()==id_cuenta &&
						gui.recuperar.getCuentas().get(j).getId_comunidad()==id_com){
					m=gui.recuperar.getCuentas().get(j);
					fila[0]=gui.recuperar.getCuentas().get(gui.recuperar.getCuentas().indexOf(m)).getNum_cuenta();
					fila[1]=gui.ListaMovimientos.get(i).getId_usuario();
					fila[2]=gui.ListaMovimientos.get(i).getCantidad();
					fila[3]=gui.ListaMovimientos.get(i).getTipo_movimiento();
					fila[4]=gui.ListaMovimientos.get(i).getFecha();
					fila[5]=gui.ListaMovimientos.get(i).getMotivo();
					fila[6]=gui.ListaMovimientos.get(i).getSaldo_anterior();
					fila[7]=gui.ListaMovimientos.get(i).getSaldo_final();				
					continue;
				}						
			}	
			gui.modeloComunidad.addRow(fila);
			gui.tablaComunidad.setModel(gui.modeloComunidad);
			gui.tablaComunidad.validate();
			gui.tablaComunidad.repaint();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==gui.mntmLogin){
			final Login login= new Login(gui);
			login.setVisible(true);
		}
		if(e.getSource()==gui.mntmSalir){
			System.exit(0);
		}
		if(e.getSource()==gui.mntmCambioContraseña){
			VentanaContraseña ventana=new VentanaContraseña(gui.getLogueado());
			ventana.setVisible(true);
		}
		if(e.getSource()==gui.mntmNuevaComunidad){
			
		}
		if(e.getSource()==gui.btnAnadirUsuario){
			VentanaUsuario ventana =new VentanaUsuario(gui.recuperar.getComunidades().get(gui.comboBox.getSelectedIndex()).getId(),gui.logueado,gui.recuperar.getUsuarios(),gui.recuperar.getComunidadesUsuarios(),"Alta");
			ventana.setVisible(true);
		}
		if(e.getSource()==gui.btnBorrarUsuario){
			VentanaUsuario ventana =new VentanaUsuario(gui.recuperar.getComunidades().get(gui.comboBox.getSelectedIndex()).getId(),gui.logueado,gui.recuperar.getUsuarios(),gui.recuperar.getComunidadesUsuarios(),"Baja");
			ventana.setVisible(true);
		}
		if(e.getSource()==gui.btnModificarUsuario){
			VentanaUsuario ventana =new VentanaUsuario(gui.recuperar.getComunidades().get(gui.comboBox.getSelectedIndex()).getId(),gui.logueado,gui.recuperar.getUsuarios(),gui.recuperar.getComunidadesUsuarios(),"Modificacion");
			ventana.setVisible(true);
		}
	}
}
