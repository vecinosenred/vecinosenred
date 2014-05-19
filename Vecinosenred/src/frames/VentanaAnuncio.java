
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

public class VentanaAnuncio extends JDialog {
	private JTextField textTitulo;
	private JTextField textRemitente;
	private JTextArea txtareaAnuncio;
	private ArrayList<Usuario> usuarios;
	private int id_com;
	private Usuario usu;
	private JTextField textFecha;

	
	public VentanaAnuncio(int id_comunidad,Usuario usuario,ArrayList<Usuario> usua) {
		this.id_com=id_comunidad;
		this.usu=usuario;
		this.usuarios=usua;
		getContentPane().setBackground(Color.WHITE);
		setResizable(false);
		setTitle("Mensaje");
		setBackground(Color.GRAY);
		setSize(500, 400);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JLabel lblDe = new JLabel("DE:");
		lblDe.setBounds(10, 11, 46, 14);
		getContentPane().add(lblDe);

		JLabel lblTitulo = new JLabel("Titulo:");
		lblTitulo.setBounds(10, 56, 46, 14);
		getContentPane().add(lblTitulo);

		JLabel lblMensaje = new JLabel("Anuncio:");
		lblMensaje.setBounds(10, 86, 70, 14);
		getContentPane().add(lblMensaje);

		txtareaAnuncio = new JTextArea();
		txtareaAnuncio.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtareaAnuncio.setBounds(10, 111, 474, 150);
		getContentPane().add(txtareaAnuncio);

		textTitulo = new JTextField();
		textTitulo.setBorder(new LineBorder(new Color(171, 173, 179)));
		textTitulo.setBounds(66, 53, 418, 20);
		getContentPane().add(textTitulo);
		textTitulo.setColumns(10);

		textRemitente = new JTextField();
		textRemitente.setText("Ti");
		textRemitente.setBorder(new LineBorder(new Color(171, 173, 179)));
		textRemitente.setBounds(66, 8, 418, 20);
		getContentPane().add(textRemitente);
		textRemitente.setColumns(10);
		{

		}

		JButton btnEnviarMensaje = new JButton("Enviar Anuncio");
		btnEnviarMensaje.setBounds(367, 272, 117, 23);
		getContentPane().add(btnEnviarMensaje);
		btnEnviarMensaje.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					enviarAnuncio(id_com, usu.getUsuario(), textTitulo.getText(), txtareaAnuncio.getText());
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * @wbp.parser.constructor
	 */
	public VentanaAnuncio(String usuario, String titulo, String anuncio, String fecha) {
		getContentPane().setBackground(Color.WHITE);
		setResizable(false);
		setTitle("Mensaje");
		setBackground(Color.GRAY);
		setSize(500, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JLabel lblDe = new JLabel("DE:");
		lblDe.setBounds(10, 11, 46, 14);
		getContentPane().add(lblDe);

		JLabel lblTitulo = new JLabel("Titulo:");
		lblTitulo.setBounds(10, 41, 46, 14);
		getContentPane().add(lblTitulo);

		JLabel lblAnuncio = new JLabel("Anuncio:");
		lblAnuncio.setBounds(10, 100, 67, 14);
		getContentPane().add(lblAnuncio);

		txtareaAnuncio = new JTextArea();
		txtareaAnuncio.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtareaAnuncio.setBounds(10, 120, 474, 141);
		getContentPane().add(txtareaAnuncio);

		textTitulo = new JTextField();
		textTitulo.setBorder(new LineBorder(new Color(171, 173, 179)));
		textTitulo.setBounds(66, 38, 418, 20);
		getContentPane().add(textTitulo);
		textTitulo.setColumns(10);

		textRemitente = new JTextField();
		textRemitente.setBorder(new LineBorder(new Color(171, 173, 179)));
		textRemitente.setBounds(66, 8, 418, 20);
		getContentPane().add(textRemitente);
		textRemitente.setColumns(10);
		{

		}
		textTitulo.setText(titulo);
		textTitulo.setEditable(false);
		textRemitente.setText(usuario);
		textRemitente.setEditable(false);
		txtareaAnuncio.setText(anuncio);
		txtareaAnuncio.setEditable(false);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(10, 71, 56, 16);
		getContentPane().add(lblFecha);
		
		textFecha = new JTextField();
		textFecha.setBounds(66, 68, 418, 22);
		textFecha.setText(fecha);
		textFecha.setEditable(false);
		getContentPane().add(textFecha);
		textFecha.setColumns(10);
	}
	
	private void enviarAnuncio(int id_comunidad,String id_usuario,String titulo,String anuncio) 
			throws ClassNotFoundException, SQLException{

		ObtenerFechaActual ofa= new ObtenerFechaActual();
		
		Introducir introducir=new Introducir();
		
		introducir.introducir("INSERT INTO TABANU_TABLON_ANUNCIOS"
				+ "(TABANU_ID_COMUNIDAD,TABANU_ID_USUARIO,TABANU_TITULO,TABANU_MENSAJE,TABANU_FECHA_CREACION)"
				+ "VALUES('"+id_comunidad+"','"+id_usuario+"','"+titulo+"','"+anuncio+"','"+ofa.obtenerFecha()+"')");
		
	}
}
