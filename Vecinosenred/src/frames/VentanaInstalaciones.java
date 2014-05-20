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
import BD.Actualizar;
import BD.Eliminar;
import BD.Introducir;
import clases.ComunidadUsuario;
import clases.Instalacion;
import clases.Usuario;

import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.xml.soap.Text;

public class VentanaInstalaciones extends JDialog {
	private JTextField textdestinatario;
	private JComboBox<String> comboBox;
	private ArrayList<Usuario> usuarios;
	private ArrayList<Instalacion> Instalaciones;
	private int id_com;
	private String tipo, Cuenta;
	private Usuario usu;
	public boolean administrador = false;
	public JTextField textIdInstalacion;
	public JTextField textNombre;
	public JTextField textcoste;
	public JTextField textComunidad;
	JButton btnAccion;
	JLabel lblInscombo;
	JCheckBox checkAdministrador, checkServicio;
	private JLabel lblDescripcion;
	public JTextField textDescripcion;
	JTextField textReglamento;

	/**
	 * @wbp.parser.constructor //INST_INSTALACIONES // INST_ID_INSTALACION`,
	 *                         `INST_ID_COMUNIDAD`, `INST_NOMBRE`, `INST_COSTE`,
	 *                         `INST_DESCRIPCION`, `INST_EN_SERVICIO`,
	 *                         `INST_REGLAMENTO`, `INST_MOROSOS`
	 */
	public VentanaInstalaciones(int id_comunidad, Usuario usuario,
			ArrayList<Usuario> usua, ArrayList<Instalacion> Instalacion,
			String Tipo) {
		this.usu = usuario;
		this.usuarios = usua;
		this.id_com = id_comunidad;
		this.Instalaciones = Instalacion;
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

		textDescripcion = new JTextField();
		textDescripcion.setBounds(125, 148, 359, 20);
		getContentPane().add(textDescripcion);
		textDescripcion.setColumns(10);

		comboBox = new JComboBox<String>();
		System.out.println(usua.size());
		for (int i = 0; i < Instalaciones.size(); i++) {
			if (Instalaciones.get(i).getId_comunidad() == id_com) {
				comboBox.addItem(Instalaciones.get(i).getNombre());
			}
		}
		comboBox.setBounds(125, 11, 359, 22);
		getContentPane().add(comboBox);
		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String seleccionado = comboBox.getSelectedItem().toString();
				for (int i = 0; i < Instalaciones.size(); i++) {
					if (seleccionado.equals(Instalaciones.get(i).getNombre())) {
						textComunidad.setText(""
								+ Instalaciones.get(i).getId_comunidad());
						textIdInstalacion.setText(""
								+ Instalaciones.get(i).getId_instalacion());
						textNombre.setText(seleccionado);
						textcoste.setText("" + Instalaciones.get(i).getCoste());
						textDescripcion.setText(Instalaciones.get(i)
								.getDescripcion());
						textReglamento.setText(Instalaciones.get(i)
								.getReglamento());
						if (Instalaciones.get(i).getMorosos() == 1) {
							checkAdministrador.setEnabled(true);
						} else {
							checkAdministrador.setEnabled(false);
						}
						if (Instalaciones.get(i).getEn_servicio() == 1) {
							checkServicio.setEnabled(true);
						} else {
							checkServicio.setEnabled(false);
						}
					}

				}
			}
		});

		lblInscombo = new JLabel("Usuario");
		lblInscombo.setBounds(10, 15, 105, 14);
		getContentPane().add(lblInscombo);
		if (tipo.equals("Alta")) {
			comboBox.setVisible(false);
			lblInscombo.setVisible(false);
			btnAccion.setText("Alta");
		}
		if (tipo.equals("Baja")) {
			btnAccion.setText("Baja");
		}
		if (tipo.equals("Modificacion")) {
			btnAccion.setText("Modificar");
		}

		JLabel lblinstalacion = new JLabel("Instalacion");
		lblinstalacion.setBounds(10, 51, 105, 14);
		getContentPane().add(lblinstalacion);

		JLabel lblreglamento = new JLabel("Reglamento:");
		lblreglamento.setBounds(10, 176, 105, 14);
		getContentPane().add(lblreglamento);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 101, 105, 14);
		getContentPane().add(lblNombre);

		textIdInstalacion = new JTextField();
		textIdInstalacion.setEditable(false);
		textIdInstalacion.setBounds(125, 48, 359, 20);
		getContentPane().add(textIdInstalacion);
		textIdInstalacion.setColumns(10);

		JLabel lblCoste = new JLabel("Coste:");
		lblCoste.setBounds(10, 126, 105, 14);
		getContentPane().add(lblCoste);

		textNombre = new JTextField();
		textNombre.setBounds(125, 98, 359, 20);
		getContentPane().add(textNombre);
		textNombre.setColumns(10);

		textcoste = new JTextField();
		textcoste.setBounds(125, 123, 359, 20);
		getContentPane().add(textcoste);
		textcoste.setColumns(10);

		JLabel lblComunidad = new JLabel("Comunidad");
		lblComunidad.setBounds(10, 73, 105, 14);
		getContentPane().add(lblComunidad);

		textComunidad = new JTextField();
		textComunidad.setEditable(false);
		textComunidad.setBounds(125, 73, 359, 20);
		getContentPane().add(textComunidad);
		textComunidad.setColumns(10);
		textComunidad.setText("" + id_com);

		JLabel lblmoroso = new JLabel("Morosos");
		lblmoroso.setBounds(10, 203, 105, 14);
		getContentPane().add(lblmoroso);

		checkAdministrador = new JCheckBox("");
		checkAdministrador.setBounds(125, 199, 21, 23);
		getContentPane().add(checkAdministrador);

		lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(10, 151, 105, 14);
		getContentPane().add(lblDescripcion);

		textReglamento = new JTextField();
		textReglamento.setBounds(125, 173, 359, 20);
		getContentPane().add(textReglamento);
		textReglamento.setColumns(10);

		checkServicio = new JCheckBox("");
		checkServicio.setBounds(125, 233, 21, 23);
		getContentPane().add(checkServicio);

		JLabel lblEnServicio = new JLabel("En Servicio");
		lblEnServicio.setBounds(10, 237, 105, 14);
		getContentPane().add(lblEnServicio);

		btnAccion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (tipo.equals("Alta")) {
					Introducir introducir = new Introducir();
					try {
						int admin = 0;
						int servicio = 0;
						if (checkAdministrador.isEnabled()) {
							admin = 1;
						}
						if (checkServicio.isEnabled()) {
							servicio = 1;
						}
						// INST_INSTALACIONES // INST_ID_INSTALACION`,
						// `INST_ID_COMUNIDAD`, `INST_NOMBRE`, `INST_COSTE`,
						// `INST_DESCRIPCION`, `INST_EN_SERVICIO`,
						// `INST_REGLAMENTO`, `INST_MOROSOS`
						introducir
								.introducir("INSERT INTO INST_INSTALACIONES (INST_ID_COMUNIDAD, INST_NOMBRE, INST_COSTE, INST_DESCRIPCION, INST_EN_SERVICIO, INST_REGLAMENTO, INST_MOROSOS) VALUES ('"
										+ textComunidad.getText()
										+ "','"
										+ textNombre.getText()
										+ "','"
										+ textcoste.getText()
										+ "','"
										+ textDescripcion.getText()
										+ "','"
										+ servicio
										+ "','"
										+ textReglamento.getText()
										+ "','"
										+ admin + "')");
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
						eliminar.eliminarAnuncio("DELETE FROM INST_INSTALACIONES WHERE INTS_ID_INSTALACION like '"
								+ textIdInstalacion.getText() + "'");

					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (tipo.equals("Modificacion")) {
					Actualizar actualizar = new Actualizar();
					try {
						int admin = 0;
						int servicio = 0;
						if (checkAdministrador.isEnabled()) {
							admin = 1;
						}
						if (checkServicio.isEnabled()) {
							servicio = 1;
						}//INST_ID_INSTALACION`,
						// `INST_ID_COMUNIDAD`, `INST_NOMBRE`, `INST_COSTE`,
						// `INST_DESCRIPCION`, `INST_EN_SERVICIO`,
						// `INST_REGLAMENTO`, `INST_MOROSOS`
						actualizar
								.actualizar("Update INST_INSTALACIONES set INST_NOMBRE="+ textNombre.getText()
										+ ",INST_COSTE="
										+ textcoste.getText()
										+ ",INST_DESCRIPCION="
										+ textDescripcion.getText()
										+ ",INST_EN_SERVICIO="
										+ servicio
										+ ",INST_REGLAMENTO="
										+ textReglamento.getText()
										+ ",INST_MOROSOS="
										+ admin +"where INST_ID_INSTALACION="+Integer.parseInt(textIdInstalacion.getText()));
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			setVisible(false);}
		});

	}
}
