
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

import javax.swing.SwingConstants;
import javax.swing.JPasswordField;

import BD.Introducir;
import java.awt.Font;
public class VentanaContraseña extends JDialog {
	
	
	private int id_com;
	private Usuario usu;
	private JPasswordField passwordActual;
	private JPasswordField passwordNuevo;
	private JPasswordField passwordConfirmacion;
	JLabel lblContraseaActualIncorrecta;

	/**
	 * @wbp.parser.constructor
	 */
	public VentanaContraseña(Usuario usuario) {
		
		this.usu=usuario;
		
		getContentPane().setBackground(Color.WHITE);
		setResizable(false);
		setTitle("Cambio Contraseña");
		setBackground(Color.GRAY);
		setSize(500, 400);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JLabel lblContraseaActual = new JLabel("Contrase\u00F1a Actual");
		lblContraseaActual.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblContraseaActual.setHorizontalAlignment(SwingConstants.CENTER);
		lblContraseaActual.setBounds(100, 60, 300, 14);
		getContentPane().add(lblContraseaActual);
		
		JLabel lblNuevaContrasea = new JLabel("Nueva Contrase\u00F1a");
		lblNuevaContrasea.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNuevaContrasea.setHorizontalAlignment(SwingConstants.CENTER);
		lblNuevaContrasea.setBounds(100, 116, 300, 14);
		getContentPane().add(lblNuevaContrasea);
		
		JLabel lblConfirmacionNuevaContrasea = new JLabel("Confirmacion nueva Contrase\u00F1a");
		lblConfirmacionNuevaContrasea.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfirmacionNuevaContrasea.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblConfirmacionNuevaContrasea.setBounds(100, 175, 300, 14);
		getContentPane().add(lblConfirmacionNuevaContrasea);
		
		passwordActual = new JPasswordField();
		passwordActual.setBounds(100, 85, 300, 20);
		getContentPane().add(passwordActual);
		
		passwordNuevo = new JPasswordField();
		passwordNuevo.setBounds(100, 144, 300, 20);
		getContentPane().add(passwordNuevo);
		
		passwordConfirmacion = new JPasswordField();
		passwordConfirmacion.setBounds(100, 200, 300, 20);
		getContentPane().add(passwordConfirmacion);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(311, 231, 89, 23);
		getContentPane().add(btnAceptar);
		
		
		btnAceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
						System.out.println("click boton");	
					Confirmacion();
				
			}
		});
		
		lblContraseaActualIncorrecta = new JLabel("CONTRASE\u00D1A ACTUAL INCORRECTA");
		lblContraseaActualIncorrecta.setForeground(Color.RED);
		lblContraseaActualIncorrecta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblContraseaActualIncorrecta.setHorizontalAlignment(SwingConstants.CENTER);
		lblContraseaActualIncorrecta.setBounds(100, 324, 300, 14);
		getContentPane().add(lblContraseaActualIncorrecta);
		lblContraseaActualIncorrecta.setVisible(false);
		

	}
	
	private void Confirmacion(){
		System.out.println("hola");
		System.out.println(passwordActual.getText());
		if(passwordActual.getText().equals(usu.getPass())){
			System.out.println(usu.getPass());
			if(passwordNuevo.getText().equals(passwordConfirmacion.getText())){
				System.out.println("comprobado");
				usu.setPass(passwordNuevo.getText());
				System.out.println(usu.getPass());
				Introducir introducir=new Introducir();
				try {
					introducir.introducir("UPDATE LOGUSU_LOGIN_USUARIOS SET LOGUSU_PASSWORD='"+usu.getPass()+"' where LOGUSU_ID='"+usu.getUsuario()+"'");
					setVisible(false);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				
		}else{
			lblContraseaActualIncorrecta.setVisible(true);
		}
	}
}
