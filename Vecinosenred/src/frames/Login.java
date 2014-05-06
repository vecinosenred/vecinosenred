package frames;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import metodos.ComprobarUsuario;


public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private int usuario=1;
	private int erroneo=0;
	private int tipoUsuario=0;
	private JPasswordField passwordField;
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

	public int getTipoUsuario() {
		return tipoUsuario;
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
		
		passwordField = new JPasswordField();
		passwordField.setBounds(102, 162, 242, 22);
		contentPane.add(passwordField);
		
		final JLabel lblElUsuarioO = new JLabel("El usuario o contrase\u00F1a es incorrecto");
		lblElUsuarioO.setForeground(Color.RED);
		lblElUsuarioO.setHorizontalAlignment(SwingConstants.CENTER);
		lblElUsuarioO.setBounds(12, 224, 408, 16);
		lblElUsuarioO.setVisible(false);
		contentPane.add(lblElUsuarioO);
		
		textField_1 = new JTextField();
		textField_1.setBounds(102, 197, 242, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(308, 257, 97, 25);
		btnAceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				String pass="";
				for (int i = 0; i < passwordField.getPassword().length; i++) {
					pass=pass+passwordField.getPassword()[i];
				}
				
				textField_1.setText(textField.getText()+" "+pass);
				
				ComprobarUsuario cu= new ComprobarUsuario(textField.getText(),pass);
				tipoUsuario=cu.comprobar();
				
				if(tipoUsuario==erroneo){
					lblElUsuarioO.setVisible(true);
				}else{
					Principal p= new Principal(textField.getText());
					p.setVisible(true);
					try {
						this.finalize();
					} catch (Throwable e) {
						// TODO Auto-generated catch block
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

