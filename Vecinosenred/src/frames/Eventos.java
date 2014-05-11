package frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Eventos implements ActionListener {
	Principal gui;

	public Eventos(Principal in){
		gui = in;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==gui.mntmLogin){
			final Login login= new Login();
			login.setVisible(true);
		}
		
	}
}
