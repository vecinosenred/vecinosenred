package metodos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import frames.ListaRespuestas;
import frames.VentanaRespuestas;

public class EventosListaRespuestas implements ActionListener, TableModelListener,
MouseListener {
	
	ListaRespuestas gui;
	int fila = -1;

	public EventosListaRespuestas(ListaRespuestas in) {
		gui = in;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		fila = gui.table.rowAtPoint(arg0.getPoint());
		System.out.println("entra");
		if (arg0.getClickCount() == 2) {
			if (fila != -1) {
				VentanaRespuestas LeerRespuesta = new VentanaRespuestas(
						gui.table.getValueAt(fila, 0).toString(),
						gui.table.getValueAt(fila, 1).toString(),
						gui.table.getValueAt(fila, 2).toString());
				LeerRespuesta.setVisible(true);
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
