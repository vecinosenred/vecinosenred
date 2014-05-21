package frames;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.font.TextMeasurer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import metodos.ObtenerFechaActual;
import BD.Actualizar;
import BD.Conectar;
import BD.Eliminar;
import BD.Introducir;
import clases.ComunidadUsuario;
import clases.Usuario;

import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.xml.soap.Text;

public class VentanaCreaCom extends JDialog {
	private JTextField textdestinatario;
	private ArrayList<Usuario> usuarios;
	private ArrayList<ComunidadUsuario> Com_usu;
	private int id_com;
	private String tipo, Cuenta;
	private Usuario usu;
	public boolean administrador = false;
	public JTextField textIdUsuario;
	public JTextField textNombre;
	public JPasswordField passwordField;
	public JTextField textDireccion;
	JButton btnAccion;
	String usuari;
	private JLabel lblNumeroCuentaUSU;
	public JTextField textCuentaUSU;
	private JTextField textCuentaCom;
	private JTextField textNombreCom;
	private JTextField textdireccionCom;
	private Statement st;
	private ResultSet rs;
	public int proximaid(){
		int id=0;
		Conectar c=new Conectar();
		try {
			st=c.getConexion().createStatement();
			
			rs=st.executeQuery("SELECT count(*) FROM COM_COMUNIDADES");		
			while(rs.next()){
				id=rs.getInt(0);
				
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	/**
	 * @wbp.parser.constructor
	 */
	public VentanaCreaCom(Usuario usuario) {
		this.usu=usuario;
		getContentPane().setBackground(Color.WHITE);
		setResizable(false);
		setTitle("Nueva Comunidad");
		setBackground(Color.GRAY);
		setSize(500, 400);
		setBounds(400, 300, 500, 400);
		getContentPane().setLayout(null);
		btnAccion = new JButton("Crear");
		btnAccion.setBounds(379, 338, 105, 23);
		getContentPane().add(btnAccion);
		
		
		textCuentaUSU = new JTextField();
		textCuentaUSU.setBounds(149, 277, 335, 20);
		getContentPane().add(textCuentaUSU);
		textCuentaUSU.setColumns(10);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(10, 171, 105, 14);
		getContentPane().add(lblUsuario);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setBounds(10, 196, 105, 14);
		getContentPane().add(lblContrasea);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 221, 105, 14);
		getContentPane().add(lblNombre);

		textIdUsuario = new JTextField();
		textIdUsuario.setBounds(125, 168, 359, 20);
		getContentPane().add(textIdUsuario);
		textIdUsuario.setColumns(10);

		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setBounds(10, 249, 105, 14);
		getContentPane().add(lblDireccion);

		textNombre = new JTextField();
		textNombre.setBounds(125, 218, 359, 20);
		getContentPane().add(textNombre);
		textNombre.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(125, 193, 359, 22);
		getContentPane().add(passwordField);

		textDireccion = new JTextField();
		textDireccion.setBounds(125, 246, 359, 20);
		getContentPane().add(textDireccion);
		textDireccion.setColumns(10);

		lblNumeroCuentaUSU = new JLabel("Numero Cuenta Usuario");
		lblNumeroCuentaUSU.setBounds(10, 280, 129, 14);
		getContentPane().add(lblNumeroCuentaUSU);

		textCuentaCom = new JTextField();
		textCuentaCom.setColumns(10);
		textCuentaCom.setBounds(165, 76, 319, 20);
		getContentPane().add(textCuentaCom);

		JLabel lblNumeroCuentaComunidad = new JLabel("Numero Cuenta Comunidad");
		lblNumeroCuentaComunidad.setBounds(10, 79, 145, 14);
		getContentPane().add(lblNumeroCuentaComunidad);

		JLabel label_1 = new JLabel("Nombre");
		label_1.setBounds(10, 22, 105, 14);
		getContentPane().add(label_1);

		textNombreCom = new JTextField();
		textNombreCom.setColumns(10);
		textNombreCom.setBounds(125, 19, 359, 20);
		getContentPane().add(textNombreCom);

		JLabel label_2 = new JLabel("Direccion:");
		label_2.setBounds(10, 50, 105, 14);
		getContentPane().add(label_2);

		textdireccionCom = new JTextField();
		textdireccionCom.setColumns(10);
		textdireccionCom.setBounds(125, 47, 359, 20);
		getContentPane().add(textdireccionCom);

		btnAccion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
					Introducir introducir = new Introducir();
					if (usu.getUsuario().equals("usuario")) {
						try {
							int admin = 1;
							introducir
									.introducir("INSERT INTO LOGUSU_LOGIN_USUARIOS (LOGUSU_ID, LOGUSU_PASSWORD, LOGUSU_NOMBRE, LOGUSU_DOMICILIO) VALUES ('"
											+ textIdUsuario.getText()
											+ "','"
											+ passwordField.getText()
											+ "','"
											+ textNombre.getText()
											+ "','"
											+ textDireccion.getText() + "')");
							introducir.introducir("INSERT INTO COM_COMUNIDADES (COM_NOMBRE,COM_DIRECCION,COM_NUM_CUENTA) VALUES ('"+textNombreCom.getText()+"','"+textdireccionCom.getText()+"','"+textCuentaCom.getText()+"')");
							
							introducir.introducir("insert into COMUSU_COMUNIDAD_USUARIO (COMUSU_ID_USUARIO,COMUSU_ID_COMUNIDADES,COMUSU_ADMINISTRADOR,COMUSU_NUM_CUENTA,COMUSU_PISO) VALUES ('"
											+ textIdUsuario.getText()
											+ "','"
											+proximaid()
											+ "','"
											+ admin
											+ "','"
											+ textCuentaUSU.getText()
											+ "','"
											+ textDireccion.getText() + "')");
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						setVisible(false);
					}else{
						try {
							int admin = 1;
							introducir.introducir("INSERT INTO COM_COMUNIDADES (COM_NOMBRE,COM_DIRECCION,COM_NUM_CUENTA) VALUES ('"+textNombreCom.getText()+"','"+textdireccionCom.getText()+"','"+textCuentaCom.getText()+"')");
							introducir.introducir("insert into COMUSU_COMUNIDAD_USUARIO (COMUSU_ID_USUARIO,COMUSU_ID_COMUNIDADES,COMUSU_ADMINISTRADOR,COMUSU_NUM_CUENTA,COMUSU_PISO) VALUES ('"
									+ usu.getUsuario()
									+ "','"
									+proximaid()
									+ "','"
									+ admin
									+ "','"
									+ textCuentaUSU.getText()
									+ "','"
									+ usu.getDomicilio() + "')");
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						setVisible(false);
					}
				}
			
		});

		if (!usu.getUsuario().equals("usuario")) {
			lblContrasea.setVisible(false);
			lblDireccion.setVisible(false);
			lblNombre.setVisible(false);
			lblUsuario.setVisible(false);
			textDireccion.setVisible(false);
			textIdUsuario.setVisible(false);
			passwordField.setVisible(false);
			textNombre.setVisible(false);

		}
	}
}
