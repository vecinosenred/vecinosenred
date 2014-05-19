
package frames;

import java.awt.Color;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import clases.ComunidadUsuario;
import clases.Movimiento;
import clases.Usuario;

public class VentanaMovimiento extends JDialog {
	private JComboBox<String> textTitular;
	private JTextField textCuenta;
	private JTextField textCantidad;
	private JTextArea txtareaMotivo;
	private JComboBox<String> comboTipoMov;
	
	private int id_com;
	private Usuario usu;
	private JTextField textFecha;
	private ObtenerFechaActual ofa;
	
	private ArrayList<Usuario> usuarios;
	private ArrayList<ComunidadUsuario> com_usu;

	/**
	 * @wbp.parser.constructor
	 */
	public VentanaMovimiento(int id_comunidad,Usuario usuario,ArrayList<Usuario> usu,ArrayList<ComunidadUsuario> comusu) {
		
		this.id_com=id_comunidad;
		this.usuarios=usu;
		this.com_usu=comusu;
		this.ofa=new ObtenerFechaActual();
		
		getContentPane().setBackground(Color.WHITE);
		setResizable(false);
		setTitle("Movimiento");
		setBackground(Color.GRAY);
		setSize(500, 400);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JLabel lblDe = new JLabel("Cuenta:");
		lblDe.setBounds(10, 11, 46, 14);
		getContentPane().add(lblDe);

		JLabel lblAsunto = new JLabel("Titular:");
		lblAsunto.setBounds(10, 41, 46, 14);
		getContentPane().add(lblAsunto);

		JLabel lblMensaje = new JLabel("Motivo:");
		lblMensaje.setBounds(10, 159, 46, 14);
		getContentPane().add(lblMensaje);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(10, 70, 46, 14);
		getContentPane().add(lblFecha);
		
		JLabel lblTipoMovimiento = new JLabel("Tipo movimiento:");
		lblTipoMovimiento.setBounds(10, 97, 106, 16);
		getContentPane().add(lblTipoMovimiento);

		txtareaMotivo = new JTextArea();
		txtareaMotivo.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtareaMotivo.setBounds(10, 186, 474, 86);
		getContentPane().add(txtareaMotivo);

		textTitular = new JComboBox<String>();
		for (int i = 0; i < usuarios.size(); i++) {
			textTitular.addItem(usuarios.get(i).getNombre());
		}
		textTitular.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int j = 0; j < com_usu.size(); j++) {
					if(com_usu.get(j).getId_usuario().equals(usuarios.get(textTitular.getSelectedIndex()).getUsuario()) && 
							com_usu.get(j).getId_comunidades()==id_com){
						textCuenta.setText(com_usu.get(j).getNum_cuenta());						
					}
				}
				
			}
		});
		textTitular.setBorder(new LineBorder(new Color(171, 173, 179)));
		textTitular.setBounds(128, 38, 356, 20);
		getContentPane().add(textTitular);

		textCuenta = new JTextField();
		textCuenta.setEditable(false);
		textCuenta.setBorder(new LineBorder(new Color(171, 173, 179)));
		textCuenta.setBounds(128, 8, 356, 20);
		getContentPane().add(textCuenta);
		
		textFecha = new JTextField();
		textFecha.setText(ofa.obtenerFecha());
		textFecha.setColumns(10);
		textFecha.setBorder(new LineBorder(new Color(171, 173, 179)));
		textFecha.setBounds(128, 67, 356, 20);
		
		comboTipoMov = new JComboBox();
		comboTipoMov.setBounds(128, 94, 356, 22);
		getContentPane().add(comboTipoMov);
		{

		}

		JButton btnEnviarMensaje = new JButton("Enviar Movimiento");
		btnEnviarMensaje.setBounds(346, 283, 138, 23);
		getContentPane().add(btnEnviarMensaje);
		getContentPane().add(textFecha);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setBounds(10, 129, 56, 16);
		getContentPane().add(lblCantidad);
		
		textCantidad = new JTextField();
		textCantidad.setBounds(128, 126, 356, 22);
		getContentPane().add(textCantidad);
		textCantidad.setColumns(10);
		btnEnviarMensaje.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					enviarMovimiento(Integer.parseInt(textCuenta.getText()), (String)textTitular.getSelectedItem(),Double.parseDouble(textCuenta.getText()), comboTipoMov.getSelectedIndex(), txtareaMotivo.getText());
					setVisible(false);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});

	}

	public VentanaMovimiento(String cuenta, String titular, double cantidad,String fecha, String motivo) {
		getContentPane().setBackground(Color.WHITE);
		setResizable(false);
		setTitle("Incidencia");
		setBackground(Color.GRAY);
		setSize(500, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JLabel lblCuenta = new JLabel("Cuenta:");
		lblCuenta.setBounds(10, 11, 46, 14);
		getContentPane().add(lblCuenta);

		JLabel lblTitular = new JLabel("Titular:");
		lblTitular.setBounds(10, 61, 46, 14);
		getContentPane().add(lblTitular);

		JLabel lblMotivo = new JLabel("Motivo:");
		lblMotivo.setBounds(10, 86, 46, 14);
		getContentPane().add(lblMotivo);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(10, 70, 46, 14);
		getContentPane().add(lblFecha);

		txtareaMotivo = new JTextArea();
		txtareaMotivo.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtareaMotivo.setBounds(10, 111, 474, 150);
		getContentPane().add(txtareaMotivo);

		JTextField txtTitular = new JTextField();
		txtTitular.setBorder(new LineBorder(new Color(171, 173, 179)));
		txtTitular.setBounds(66, 58, 418, 20);
		getContentPane().add(txtTitular);
		txtTitular.setColumns(10);

		textCuenta = new JTextField();
		textCuenta.setBorder(new LineBorder(new Color(171, 173, 179)));
		textCuenta.setBounds(66, 8, 418, 20);
		getContentPane().add(textCuenta);
		textCuenta.setColumns(10);
		
		txtTitular.setText(titular);
		txtTitular.setEditable(false);
		textCuenta.setText(cuenta);
		textCuenta.setEditable(false);
		textFecha.setText(fecha);
		textFecha.setEditable(false);
		txtareaMotivo.setText(motivo);
		txtareaMotivo.setEditable(false);
	}
	
	private void enviarMovimiento(int id_cuenta,String id_usuario,double cantidad,int tipo_mov,String motivo) 
			throws ClassNotFoundException, SQLException{

		ObtenerFechaActual ofa= new ObtenerFechaActual();
		
		Introducir introducir=new Introducir();
		
		introducir.introducir("INSERT INTO MOVCUE_MOVIENTOS_CUENTAS"
				+ "(MOVCUE_ID_CUENTA, MOVCUE_ID_USUARIO, MOVCUE_CANTIDAD,MOVCUE_TIPO_MOVIMIENTO,"
				+ "MOVCUE_FECHA_MOVIMIENTO,MOVCUE_MOTIVO)"
				+ "VALUES('"+id_cuenta+"','"+id_usuario+"','"+cantidad+"','"+tipo_mov+"','"+ofa.obtenerFecha()+"','"+motivo+"')");
		
	}
}
