package frames;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import BD.Eliminar;
import BD.Introducir;
import clases.ComunidadUsuario;
import clases.Usuario;

import javax.swing.JPasswordField;
import javax.swing.JCheckBox;

public class VentanaUsuario extends JDialog {
	private JTextField textdestinatario;
	private JComboBox<String> comboBox;
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
	public JTextField textComunidad;
	JButton btnAccion;
	JLabel lblUsucombo;
	JCheckBox checkAdministrador;
	private JLabel lblNumeroCuenta;
	public JTextField textCuenta;

	/**
	 * @wbp.parser.constructor
	 */
	public VentanaUsuario(int id_comunidad, Usuario usuario,
			ArrayList<Usuario> usua, ArrayList<ComunidadUsuario> comusu,
			String Tipo) {
		this.usu = usuario;
		this.usuarios = usua;
		this.id_com = id_comunidad;
		this.Com_usu = comusu;
		this.tipo = Tipo;
		getContentPane().setBackground(Color.WHITE);
		setResizable(false);
		setTitle("USUARIOS");
		setBackground(Color.GRAY);
		setSize(500, 400);
		setBounds(400, 300, 500, 400);
		getContentPane().setLayout(null);
		btnAccion = new JButton("boton correspondiente");
		btnAccion.setBounds(345, 272, 105, 23);
		getContentPane().add(btnAccion);

		textCuenta = new JTextField();
		textCuenta.setBounds(125, 173, 359, 20);
		getContentPane().add(textCuenta);
		textCuenta.setColumns(10);

		comboBox = new JComboBox<String>();
		System.out.println(usua.size());
		for (int i = 0; i < usuarios.size(); i++) {
			if (usuarios.get(i).getId_comunidad() == id_com) {
				comboBox.addItem(usuarios.get(i).getNombre());
			}
		}
		comboBox.setBounds(125, 11, 359, 22);
		getContentPane().add(comboBox);
		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String seleccionado = comboBox.getSelectedItem().toString();
				for (int i = 0; i < usuarios.size(); i++) {
					if (usuarios.get(i).getNombre().equals(seleccionado)) {
						for (int h = 0; h < Com_usu.size(); h++) {
							if (usuarios.get(i).getUsuario()
									.equals(Com_usu.get(h).getId_usuario())) {
								Cuenta = Com_usu.get(h).getNum_cuenta();
								if (Com_usu.get(h).getAdministrador() == 1 && Com_usu.get(h).getId_comunidades()==Integer.parseInt((textComunidad.getText()))) {
									administrador = true;
								}else{administrador = false;}
							}
						}
						textIdUsuario.setText(usuarios.get(i).getUsuario());
						textNombre.setText(usuarios.get(i).getNombre());
						textComunidad.setText("" + id_com);
						textDireccion.setText(usuarios.get(i).getDomicilio());
						passwordField.setText(usuarios.get(i).getPass());
						textCuenta.setText(Cuenta);
						checkAdministrador.setSelected(administrador);
					}
				}
			}
		});

		lblUsucombo = new JLabel("Usuario");
		lblUsucombo.setBounds(10, 15, 105, 14);
		getContentPane().add(lblUsucombo);
		if (tipo.equals("Alta")) {
			comboBox.setVisible(false);
			lblUsucombo.setVisible(false);
			btnAccion.setText("Alta");
		}
		if (tipo.equals("Baja")) {

			btnAccion.setText("Baja");
		}
		if (tipo.equals("Modificacion")) {
			btnAccion.setText("Modificar");
		}

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(10, 51, 105, 14);
		getContentPane().add(lblUsuario);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setBounds(10, 76, 105, 14);
		getContentPane().add(lblContrasea);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 101, 105, 14);
		getContentPane().add(lblNombre);

		textIdUsuario = new JTextField();
		textIdUsuario.setBounds(125, 48, 359, 20);
		getContentPane().add(textIdUsuario);
		textIdUsuario.setColumns(10);

		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setBounds(10, 126, 105, 14);
		getContentPane().add(lblDireccion);

		textNombre = new JTextField();
		textNombre.setBounds(125, 98, 359, 20);
		getContentPane().add(textNombre);
		textNombre.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(125, 73, 359, 22);
		getContentPane().add(passwordField);

		textDireccion = new JTextField();
		textDireccion.setBounds(125, 123, 359, 20);
		getContentPane().add(textDireccion);
		textDireccion.setColumns(10);

		JLabel lblComunidad = new JLabel("Comunidad");
		lblComunidad.setBounds(10, 151, 105, 14);
		getContentPane().add(lblComunidad);

		textComunidad = new JTextField();
		textComunidad.setEditable(false);
		textComunidad.setBounds(125, 148, 359, 20);
		getContentPane().add(textComunidad);
		textComunidad.setColumns(10);
		textComunidad.setText("" + id_com);

		JLabel lblAdministrador = new JLabel("Administrador");
		lblAdministrador.setBounds(10, 203, 105, 14);
		getContentPane().add(lblAdministrador);

		checkAdministrador = new JCheckBox("");
		checkAdministrador.setBounds(125, 199, 21, 23);
		getContentPane().add(checkAdministrador);

		lblNumeroCuenta = new JLabel("Numero Cuenta");
		lblNumeroCuenta.setBounds(10, 176, 105, 14);
		getContentPane().add(lblNumeroCuenta);

		btnAccion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (tipo.equals("Alta")) {
					Introducir introducir = new Introducir();
					try {
						int admin = 0;
						if (checkAdministrador.isEnabled()) {
							admin = 1;
						}
						introducir
								.introducir("INSERT INTO LOGUSU_LOGIN_USUARIOS (LOGUSU_ID, LOGUSU_PASSWORD, LOGUSU_NOMBRE, LOGUSU_DOMICILIO) VALUES ('"
										+ textIdUsuario.getText()
										+ "','"
										+ passwordField.getText()
										+ "','"
										+ textNombre.getText()
										+ "','"
										+ textDireccion.getText() + "')");
						introducir
								.introducir("insert into COMUSU_COMUNIDAD_USUARIO (COMUSU_ID_USUARIO,COMUSU_ID_COMUNIDADES,COMUSU_ADMINISTRADOR,COMUSU_NUM_CUENTA,COMUSU_PISO) VALUES ('"
										+ textIdUsuario.getText()
										+ "','"
										+ id_com
										+ "','"
										+ admin
										+ "','"
										+ textCuenta.getText()
										+ "','"
										+ textDireccion.getText() + "')");
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (tipo.equals("Baja")) {
					Eliminar eliminar = new Eliminar();
					try {
						eliminar.eliminarAnuncio("DELETE FROM COMUSU_COMUNIDAD_USUARIO WHERE COMUSU_ID_USUARIO like '"
								+ textIdUsuario.getText() + "'");
						eliminar.eliminarAnuncio("DELETE FROM LOGUSU_LOGIN_USUARIOS WHERE LOGUSU_ID like '"
								+ textIdUsuario.getText() + "'");

					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (tipo.equals("Modificacion")) {
				}
			}
		});

	}
}
