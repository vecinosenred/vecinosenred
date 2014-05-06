package frames;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TablonAnuncios extends JPanel {
	private JTable table;
	private JScrollPane scrollpane;

	/**
	 * Create the panel.
	 */
	public TablonAnuncios() {
		setLayout(null);
		scrollpane.setBounds(0, 0, 450, 300);
		
		table = new JTable();
		table.setBounds(0, 0,scrollpane.getWidth(), scrollpane.getHeight());
		
		
		
		scrollpane = new JScrollPane(table);
		scrollpane.setSize(450, 300);
		add(scrollpane);
		
	}
}
