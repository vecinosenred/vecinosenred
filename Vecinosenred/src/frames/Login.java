package frames;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import metodos.ComprobarUsuario;
import BD.Recuperar;


public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private int erroneo=0;
	private int tipoUsuario=0;
	private JPasswordField passwordField;
	JComboBox<String> jcb;
	Principal principal;

	public int getTipoUsuario() {
		return tipoUsuario;
	}

	/**
	 * Create the frame.
	 */
	public Login(Principal p) {
		
		principal=p;
				
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setSize(450, 342);
		setLocationRelativeTo(null);
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
		
		passwordField = new JPasswordField();
		passwordField.setBounds(102, 162, 242, 22);
		contentPane.add(passwordField);
		
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
				
				String pass="";
				for (int i = 0; i < passwordField.getPassword().length; i++) {
					pass=pass+passwordField.getPassword()[i];
				}
								
				ComprobarUsuario cu= new ComprobarUsuario(textField.getText(),pass);
				
				tipoUsuario=cu.comprobar();
				
				if(tipoUsuario==erroneo){
					lblElUsuarioO.setVisible(true);
				}else{
					
					try {
						principal.setRecuperar(new Recuperar(textField.getText()));
						principal.setLogueado(principal.recuperar.getUsuarioLog());
						ArrayList<String> nomCom=principal.recuperar.recuperarNomComunidad(textField.getText());
						for (int i = 0; i < nomCom.size(); i++) {
							principal.comboBox.addItem(nomCom.get(i));
						}						
						setVisible(false);
						principal.PanelInicio.setVisible(true);
						principal.mntmCambioContrase�a.setEnabled(true);
						
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
				}
				
			}
		});
		contentPane.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(22, 257, 97, 25);
		contentPane.add(btnCancelar);
	} 
}

