package frames;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import metodos.ButtonColumn;
import metodos.EventosListaRespuestas;
import clases.Incidencia;
import clases.RespuestaAnuncio;

public class ListaRespuestas extends JFrame {

	private JPanel contentPane;
	public JTable table;
	private String titulosRespuestas[] = {"Usuario","Fecha","Respuesta"};
	private ArrayList<RespuestaAnuncio> respanu = new ArrayList<RespuestaAnuncio>();
	private DefaultTableModel modelo;
	private EventosListaRespuestas eveLista;

	/**
	 * Create the frame.
	 */
	public ListaRespuestas(ArrayList<RespuestaAnuncio> respuestas) {
		
		this.respanu=respuestas;
		eveLista=new EventosListaRespuestas(this);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JScrollPane scrollAn = new JScrollPane();
		scrollAn.setBounds(0, 0, 432, 253);
		contentPane.add(scrollAn);
		
		modelo = new DefaultTableModel(null, titulosRespuestas);
		modelo.addTableModelListener(eveLista);
		table = new JTable(modelo){
	        public boolean isCellEditable(int rowIndex, int vColIndex) {
		            return false;
	        }};  
		    
		llenarlista(respuestas); 
	        
	    table.addMouseListener(eveLista);
		scrollAn.setViewportView(table);
		
		
		
	}
	
	public void llenarlista(ArrayList<RespuestaAnuncio> mns){
		
		for (int i = modelo.getRowCount() - 1; i > -1; i--) {
	        modelo.removeRow(i);
	    }
		
		Object[] fila = new Object[modelo.getColumnCount()];
		for (int i = 0; i < mns.size(); i++) {
				fila[0]=mns.get(i).getId_usuario();
				fila[1]=mns.get(i).getFecha_respuesta();
				fila[2]=mns.get(i).getRespuesta();
				
				modelo.addRow(fila);
				table.setModel(modelo);
				table.validate();
				table.repaint();
		}	
		
	}
}
