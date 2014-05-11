package frames;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;

import metodos.ComprobarUsuario;

public class VentanaMensaje extends JDialog {
	private JTextField textAsunto;
	private JTextField textdestinatario;
	private JTextField textRemitente;
	private JTextArea txtareaMensaje;

	public VentanaMensaje() {
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

		JButton btnEnviarMensaje = new JButton("Enviar Mensaje");
		btnEnviarMensaje.setBounds(379, 272, 105, 23);
		getContentPane().add(btnEnviarMensaje);
		btnEnviarMensaje.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//añadir codigo para insertar mensaje en la base de datos
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
		textdestinatario.setText(Destinatario);
		textRemitente.setText(Remitente);
		txtareaMensaje.setText(Mensaje);
	}
}