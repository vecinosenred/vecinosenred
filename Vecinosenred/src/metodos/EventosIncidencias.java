package metodos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import frames.Principal;
import frames.VentanaIncidencia;
import frames.VentanaMensaje;

public class EventosIncidencias implements ActionListener, TableModelListener,
		MouseListener {
	Principal gui;
	int fila = -1;

	public EventosIncidencias(Principal in) {
		gui = in;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == gui.anadirincidencia) {
			VentanaIncidencia anadirincidencia = 
					new VentanaIncidencia(gui.recuperar.getComunidades().get(gui.comboBox.getSelectedIndex()).getId(),gui.logueado);
			anadirincidencia.setVisible(true);
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		fila = gui.tablaincidencias.rowAtPoint(arg0.getPoint());
		if (arg0.getClickCount() == 2) {
			if (fila != -1) {
				VentanaIncidencia LeerIncidencia = new VentanaIncidencia(
						gui.tablaincidencias.getValueAt(fila, 0).toString(),
						gui.tablaincidencias.getValueAt(fila, 1).toString(),
						gui.tablaincidencias.getValueAt(fila, 2).toString());
				LeerIncidencia.setVisible(true);
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}

	@Override
	public void tableChanged(TableModelEvent arg0) {

	}

}
