
package frames;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextMeasurer;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import metodos.ObtenerFechaActual;
import BD.Introducir;
import clases.Usuario;

public class VentanaMensaje extends JDialog {
	private JTextField textAsunto;
	private JTextField textRemitente;
	private JTextArea txtareaMensaje;
	private JTextField textdestinatario;
	private JComboBox<String> comboBox;
	private ArrayList<Usuario> usuarios;
	private int id_com;
	private Usuario usu;

	/**
	 * @wbp.parser.constructor
	 */
	public VentanaMensaje(int id_comunidad,Usuario usuario,ArrayList<Usuario> usua) {
		this.id_com=id_comunidad;
		this.usu=usuario;
		this.usuarios=usua;
		getContentPane().setBackground(Color.WHITE);
		setResizable(false);
		setTitle("Mensaje");
		setBackground(Color.GRAY);
		setSize(500, 400);
		setBounds(400, 300, 500, 400);
		getContentPane().setLayout(null);

		JLabel lblDe = new JLabel("DE:");
		lblDe.setBounds(10, 11, 46, 14);
		getContentPane().add(lblDe);

		JLabel lblA = new JLabel("A:");
		lblA.setBounds(10, 36, 46, 14);
		getContentPane().add(lblA);

		JLabel lblAsunto = new JLabel("Asunto:");
		lblAsunto.setBounds(10, 61, 46, 14);
		getContentPane().add(lblAsunto);

		JLabel lblMensaje = new JLabel("Mensaje:");
		lblMensaje.setBounds(10, 86, 46, 14);
		getContentPane().add(lblMensaje);

		txtareaMensaje = new JTextArea();
		txtareaMensaje.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtareaMensaje.setBounds(10, 111, 474, 150);
		getContentPane().add(txtareaMensaje);
		comboBox = new JComboBox<String>();
		System.out.println(usuarios.size());
		for (int i = 0; i < usuarios.size(); i++) {
			if(usuarios.get(i).getId_comunidad()==id_com){
				comboBox.addItem(usuarios.get(i).getNombre());
			}
		}
		comboBox.setBounds(66, 32, 418, 22);
		getContentPane().add(comboBox);

		textAsunto = new JTextField();
		textAsunto.setBorder(new LineBorder(new Color(171, 173, 179)));
		textAsunto.setBounds(66, 58, 418, 20);
		getContentPane().add(textAsunto);
		textAsunto.setColumns(10);

		textRemitente = new JTextField();
		textRemitente.setText("Ti");
		textRemitente.setBorder(new LineBorder(new Color(171, 173, 179)));
		textRemitente.setBounds(66, 8, 418, 20);
		getContentPane().add(textRemitente);
		textRemitente.setColumns(10);
		{

		}

		JButton btnEnviarMensaje = new JButton("Enviar Mensaje");
		btnEnviarMensaje.setBounds(379, 272, 105, 23);
		getContentPane().add(btnEnviarMensaje);
		btnEnviarMensaje.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					enviarMensaje(usu.getUsuario(), id_com,usuarios.get(comboBox.getSelectedIndex()).getUsuario(), 
							textAsunto.getText(), txtareaMensaje.getText());
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});

	}

	public VentanaMensaje(String Remitente, String Destinatario, String Asunto,
			String Mensaje) {
		getContentPane().setBackground(Color.WHITE);
		setResizable(false);
		setTitle("Mensaje");
		setBackground(Color.GRAY);
		setSize(500, 300);
		setBounds(400, 300, 500, 300);
		getContentPane().setLayout(null);

		JLabel lblDe = new JLabel("DE:");
		lblDe.setBounds(10, 11, 46, 14);
		getContentPane().add(lblDe);

		JLabel lblA = new JLabel("A:");
		lblA.setBounds(10, 36, 46, 14);
		getContentPane().add(lblA);

		JLabel lblAsunto = new JLabel("Asunto:");
		lblAsunto.setBounds(10, 61, 46, 14);
		getContentPane().add(lblAsunto);

		JLabel lblMensaje = new JLabel("Mensaje:");
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

		textdestinatario = new JTextField();
		textdestinatario.setBorder(new LineBorder(new Color(171, 173, 179)));
		textdestinatario.setBounds(66, 33, 418, 20);
		getContentPane().add(textdestinatario);
		textdestinatario.setColumns(10);

		textRemitente = new JTextField();
		textRemitente.setBorder(new LineBorder(new Color(171, 173, 179)));
		textRemitente.setBounds(66, 8, 418, 20);
		getContentPane().add(textRemitente);
		textRemitente.setColumns(10);
		{

		}
		textAsunto.setText(Asunto);
		textAsunto.setEditable(false);
		textdestinatario.setText(Destinatario);
		textdestinatario.setEditable(false);
		textRemitente.setText(Remitente);
		textRemitente.setEditable(false);
		txtareaMensaje.setText(Mensaje);
		txtareaMensaje.setEditable(false);
	}
	
	private void enviarMensaje(String id_usuario,int id_comunidad,String id_destinatario,String asunto,String mensaje) 
			throws ClassNotFoundException, SQLException{

		ObtenerFechaActual ofa= new ObtenerFechaActual();
		
		Introducir introducir=new Introducir();
		
		introducir.introducir("INSERT INTO MENS_MENSAJES"
				+ "(MENS_ID_USUARIO,MENS_ID_COMUNIDAD,MENS_ID_DESTINATARIO,MENS_ASUNTO,MENS_MENSAJE,MENS_FECHA)"
				+ "VALUES('"+id_usuario+"','"+id_comunidad+"','"+id_destinatario+"','"+asunto+"','"+mensaje+"','"+ofa.obtenerFecha()+"')");
		
	}
}
