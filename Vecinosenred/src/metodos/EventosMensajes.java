
package metodos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import frames.Principal;
import frames.VentanaMensaje;

public class EventosMensajes implements ActionListener, TableModelListener,
		MouseListener {
	Principal gui;
	int fila = -1;

	public EventosMensajes(Principal in) {
		gui = in;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == gui.anadirmensaje) {
			VentanaMensaje anadirmensaje = new VentanaMensaje();
			anadirmensaje.setVisible(true);
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		fila = gui.tablamensajes.rowAtPoint(arg0.getPoint());
		if (arg0.getClickCount() == 2) {
			if (fila != -1) {
				VentanaMensaje LeerMensaje = new VentanaMensaje(
						gui.tablamensajes.getValueAt(fila, 0).toString(),
						gui.tablamensajes.getValueAt(fila, 1).toString(),
						gui.tablamensajes.getValueAt(fila, 2).toString(),
						gui.tablamensajes.getValueAt(fila, 3).toString());
				LeerMensaje.setVisible(true);
			}
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
