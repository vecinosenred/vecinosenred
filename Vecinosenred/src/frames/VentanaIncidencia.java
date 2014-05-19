
package frames;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import metodos.ObtenerFechaActual;
import BD.Introducir;
import clases.Usuario;

public class VentanaIncidencia extends JDialog {
	private JTextField textAsunto;
	private JTextField textRemitente;
	private JTextArea txtareaMensaje;
	
	private int id_com;
	private Usuario usu;

	/**
	 * @wbp.parser.constructor
	 */
	public VentanaIncidencia(int id_comunidad,Usuario usuario) {
		
		this.id_com=id_comunidad;
		this.usu=usuario;
		
		getContentPane().setBackground(Color.WHITE);
		setResizable(false);
		setTitle("Incidencia");
		setBackground(Color.GRAY);
		setSize(500, 400);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JLabel lblDe = new JLabel("DE:");
		lblDe.setBounds(10, 11, 46, 14);
		getContentPane().add(lblDe);

		JLabel lblAsunto = new JLabel("Titulo:");
		lblAsunto.setBounds(10, 61, 46, 14);
		getContentPane().add(lblAsunto);

		JLabel lblMensaje = new JLabel("Descripción:");
		lblMensaje.setBounds(10, 86, 105, 14);
		getContentPane().add(lblMensaje);

		txtareaMensaje = new JTextArea();
		txtareaMensaje.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtareaMensaje.setBounds(10, 111, 474, 150);
		getContentPane().add(txtareaMensaje);

		textAsunto = new JTextField();
		textAsunto.setBorder(new LineBorder(new Color(171, 173, 179)));
		textAsunto.setBounds(66, 58, 418, 20);
		getContentPane().add(textAsunto);
		textAsunto.setColumns(10);

		textRemitente = new JTextField();
		textRemitente.setText(usu.getNombre());
		textRemitente.setBorder(new LineBorder(new Color(171, 173, 179)));
		textRemitente.setBounds(66, 8, 418, 20);
		getContentPane().add(textRemitente);
		textRemitente.setColumns(10);
		{

		}

		JButton btnEnviarMensaje = new JButton("Enviar Incidencia");
		btnEnviarMensaje.setBounds(346, 272, 138, 23);
		getContentPane().add(btnEnviarMensaje);
		btnEnviarMensaje.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					enviarIncidencia(id_com, usu.getUsuario(), textAsunto.getText(), txtareaMensaje.getText());
					setVisible(false);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});

	}

	public VentanaIncidencia(String Remitente, String Titulo, String Descripcion) {
		getContentPane().setBackground(Color.WHITE);
		setResizable(false);
		setTitle("Incidencia");
		setBackground(Color.GRAY);
		setSize(500, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JLabel lblDe = new JLabel("DE:");
		lblDe.setBounds(10, 11, 46, 14);
		getContentPane().add(lblDe);

		JLabel lblAsunto = new JLabel("Titulo:");
		lblAsunto.setBounds(10, 61, 46, 14);
		getContentPane().add(lblAsunto);

		JLabel lblMensaje = new JLabel("Descripción:");
		lblMensaje.setBounds(10, 86, 46, 14);
		getContentPane().add(lblMensaje);

		txtareaMensaje = new JTextArea();
		txtareaMensaje.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtareaMensaje.setBounds(10, 111, 474, 150);
		getContentPane().add(txtareaMensaje);

		textAsunto = new JTextField();
		textAsunto.setBorder(new LineBorder(new Color(171, 173, 179)));
		textAsunto.setBounds(66, 58, 418, 20);
		getContentPane().add(textAsunto);
		textAsunto.setColumns(10);

		textRemitente = new JTextField();
		textRemitente.setBorder(new LineBorder(new Color(171, 173, 179)));
		textRemitente.setBounds(66, 8, 418, 20);
		getContentPane().add(textRemitente);
		textRemitente.setColumns(10);
		textAsunto.setText(Titulo);
		textAsunto.setEditable(false);
		textRemitente.setText(Remitente);
		textRemitente.setEditable(false);
		txtareaMensaje.setText(Descripcion);
		txtareaMensaje.setEditable(false);
	}
	
	private void enviarIncidencia(int id_comunidad,String id_usuario,String titulo,String descripcion) 
			throws ClassNotFoundException, SQLException{

		ObtenerFechaActual ofa= new ObtenerFechaActual();
		
		Introducir introducir=new Introducir();
		
		introducir.introducir("INSERT INTO INCI_INCIDENCIAS"
				+ "(INCI_ID_COMUNIDAD,INCI_ID_USUARIO,INCI_TITULO,INCI_DESCRIPCION,INCI_ESTADO,INCI_FECHA_CREACION)"
				+ "VALUES('"+id_comunidad+"','"+id_usuario+"','"+titulo+"','"+descripcion+"','1','"+ofa.obtenerFecha()+"')");
		
	}
}
