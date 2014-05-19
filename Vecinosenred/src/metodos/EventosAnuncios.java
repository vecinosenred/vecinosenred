
package metodos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import frames.Principal;
import frames.VentanaAnuncio;
import frames.VentanaIncidencia;

public class EventosAnuncios implements ActionListener, TableModelListener,
MouseListener  {
	Principal gui;
	int fila = -1;
	
	public EventosAnuncios(Principal in){
		gui=in;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("mensajenuevo");
		if (e.getSource() == gui.anadirAnuncio) {
			VentanaAnuncio anadirmensaje = new VentanaAnuncio(gui.recuperar.getComunidades().get(gui.comboBox.getSelectedIndex()).getId(),
					gui.logueado,gui.recuperar.getUsuarios());
			anadirmensaje.setVisible(true);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		fila = gui.tablaAnuncios.rowAtPoint(arg0.getPoint());
		String titulo, anuncio,fecha;
		if(gui.tablaAnuncios.getValueAt(fila, 1)!=null){
			titulo=gui.tablaAnuncios.getValueAt(fila, 1).toString();
		}else{
			titulo="";
		}
		
		if(gui.tablaAnuncios.getValueAt(fila, 2)!=null){
			anuncio=gui.tablaAnuncios.getValueAt(fila, 2).toString();
		}else{
			anuncio="";
		}
		
		if(gui.tablaAnuncios.getValueAt(fila, 3)!=null){
			fecha=gui.tablaAnuncios.getValueAt(fila, 3).toString();
		}else{
			fecha="";
		}
		if (arg0.getClickCount() == 2 && fila!=-1) {
				VentanaAnuncio LeerAnuncio = new VentanaAnuncio(
						gui.logueado.getUsuario(),titulo,anuncio,fecha);
				LeerAnuncio.setVisible(true);
		}
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void tableChanged(TableModelEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}