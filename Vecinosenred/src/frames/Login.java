package frames;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 342);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(102, 65, 242, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setBounds(12, 36, 408, 16);
		contentPane.add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasea.setBounds(12, 112, 408, 16);
		contentPane.add(lblContrasea);
		
		textField_1 = new JTextField();
		textField_1.setBounds(102, 141, 242, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		final JLabel lblElUsuarioO = new JLabel("El usuario o contrase\u00F1a es incorrecto");
		lblElUsuarioO.setForeground(Color.RED);
		lblElUsuarioO.setHorizontalAlignment(SwingConstants.CENTER);
		lblElUsuarioO.setBounds(12, 224, 408, 16);
		lblElUsuarioO.setVisible(false);
		contentPane.add(lblElUsuarioO);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(308, 257, 97, 25);
		btnAceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(comprobarUsuario(textField.getText(), textField_1.getText())=="false"){
					lblElUsuarioO.setVisible(true);
				}
				
			}
		});
		contentPane.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(22, 257, 97, 25);
		contentPane.add(btnCancelar);
	}
	
	private String comprobarUsuario(String nom,String cont){
		
		String usuario=null;
		
		usuario="false";
		
		
		return usuario;
	}
	
}
