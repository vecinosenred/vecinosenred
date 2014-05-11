package frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

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

		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		fila = gui.tablamensajes.rowAtPoint(arg0.getPoint());
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
