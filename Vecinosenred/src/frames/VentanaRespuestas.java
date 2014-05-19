package frames;

import java.awt.EventQueue;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BD.Introducir;
import metodos.ObtenerFechaActual;
import clases.Anuncio;
import javax.swing.JTextField;

public class VentanaRespuestas extends JFrame {

	private JPanel contentPane;
	private TextArea textArea;
	private ObtenerFechaActual ofa=new ObtenerFechaActual();
	private Introducir introducir=new Introducir();
	private JLabel lblUsuario;
	private JTextField textUsuario;
	private JTextField textFecha;


	/**
	 * Create the frame.
	 */
	public VentanaRespuestas(final int id_mensaje, final String id_usuario) {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textArea = new TextArea();
		textArea.setBounds(12, 61, 408, 146);
		contentPane.add(textArea);
		
		JLabel lblRespuesta = new JLabel("Respuesta:");
		lblRespuesta.setBounds(12, 39, 77, 16);
		contentPane.add(lblRespuesta);
		
		JButton btnResponder = new JButton("Responder");
		btnResponder.setBounds(323, 221, 97, 25);
		btnResponder.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {				
				try {
					System.out.println(id_mensaje);
					introducir.introducir("INSERT INTO RESPANU_RESPUESTAS_ANUNCIOS (RESPANU_ID_MENSAJE, RESPANU_RESPUESTA, RESPANU_ID_USUARIO, RESPANU_FECHA_RESPUESTA) "
							+ "VALUES ('"+ id_mensaje+"','"+textArea.getText()+"','"+id_usuario+"','"+ofa.obtenerFecha()+"')");
					setVisible(false);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		contentPane.add(btnResponder);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(12, 221, 97, 25);
		contentPane.add(btnCancelar);
	}
	
	
	/**
	 * @wbp.parser.constructor
	 */
	public VentanaRespuestas(String usuario,String fecha,String respuesta) {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textArea = new TextArea();
		textArea.setBounds(12, 97, 408, 146);
		textArea.setText(respuesta);
		textArea.setEditable(false);
		contentPane.add(textArea);
		
		JLabel lblRespuesta = new JLabel("Respuesta:");
		lblRespuesta.setBounds(12, 75, 77, 16);
		contentPane.add(lblRespuesta);
		
		lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(12, 13, 56, 16);
		contentPane.add(lblUsuario);
		
		textUsuario = new JTextField();
		textUsuario.setBounds(64, 10, 356, 22);
		textUsuario.setText(usuario);
		textUsuario.setEditable(false);
		contentPane.add(textUsuario);
		textUsuario.setColumns(10);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(12, 46, 56, 16);
		contentPane.add(lblFecha);
		
		textFecha = new JTextField();
		textFecha.setBounds(64, 43, 356, 22);
		textFecha.setText(fecha);
		textFecha.setEditable(false);
		contentPane.add(textFecha);
		textFecha.setColumns(10);
		
		
	}
}
