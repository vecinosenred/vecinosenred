package frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import BD.Recuperar;

public class Principal extends JFrame{
	JPanel panelLogin, PanelInicio,Mensajes,Incidencias;
	JMenuItem mntmLogin, mntmSalir;
	Recuperar r;
	JComboBox<String> comboBox;
	JLabel labelbarrainferior;
	JPanel barrainferior,Calendario=new JPanel();
	Login log;
	JTabbedPane PanelPrincipal;	String titulosMensajes[] = { "Para", "Asunto", "Mensajes" };
	String titulosIncidencias[] = { "ticket","Titulo","Estado","Fecha Creada"};
	DefaultTableModel model,modelo;
	JTable tablamensajes,tablaincidencias;
	public JButton anadirmensaje,anadirincidencia;
	
	public Recuperar getR() {
		return r;
	}

	public void setR(Recuperar r) {
		this.r = r;
	}

	public Principal(){
		
		System.out.println("+++---+++");
		
		log=new Login(this);
		setResizable(false);
		getContentPane().setEnabled(false);
		setTitle("Vecinos en Red");
		setBackground(Color.GRAY);
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		Eventos principal = new Eventos(this);
		
		JPanel panelmenu = new JPanel();
		panelmenu.setBounds(0, 0, 794, 20);
		getContentPane().add(panelmenu);
		panelmenu.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setMargin(new Insets(0, 10, 0, 0));
		menuBar.setBounds(0, 0, 794, 20);
		panelmenu.add(menuBar);

		JMenu mnInicio = new JMenu("Inicio");
		menuBar.add(mnInicio);

		mntmLogin = new JMenuItem("Login");
		mntmLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				log.setVisible(true);
			}
		});
		mnInicio.add(mntmLogin);
		

		mntmSalir = new JMenuItem("Salir");
		mnInicio.add(mntmSalir);
		
		

		PanelInicio = new JPanel();
		PanelInicio.setBounds(0, 20, 794, 500);
		getContentPane().add(PanelInicio);
		PanelInicio.setMinimumSize(new Dimension(0, 0));
		PanelInicio.setBorder(null);
		PanelInicio.setLayout(null);
		
		PanelPrincipal = new JTabbedPane(JTabbedPane.TOP);
		PanelPrincipal.setBounds(0, 0, 794, 500);
		PanelInicio.add(PanelPrincipal);
		JPanel Anuncios = new JPanel();
		PanelPrincipal.addTab("Anuncios", null, Anuncios, "Anuncios");
		
		Incidencias = new JPanel();
		Incidencias.setBounds(0, 0, 794, 450);
		Incidencias.setLayout(null);
		Incidencias.setBorder(null);
		JScrollPane scrollPanel = new JScrollPane();
		scrollPanel.setBounds(0, 0, 790, 450);
		Incidencias.add(scrollPanel);
		modelo = new DefaultTableModel(null, titulosIncidencias);
		tablaincidencias = new JTable(modelo);
		scrollPanel.setViewportView(tablaincidencias);
		anadirincidencia = new JButton();
		anadirincidencia.setText("nuevo mensaje");
		anadirincidencia.setBounds(650, 450, 125, 20);
		Incidencias.add(anadirincidencia);
		PanelPrincipal.addTab("Incidencias", null, Incidencias, "Incidencias");
		
		Calendario.setBounds(0, 0, 794, 450);
		Calendario.setLayout(null);
		Calendario.setBorder(null);
		PanelPrincipal.addTab("Calendario", null, Calendario, "Calendario");
		
		JPanel Cuentas = new JPanel();
		PanelPrincipal.addTab("Cuentas", null, Cuentas, "Cuentas");
		
		JPanel Comunidad = new JPanel();
		PanelPrincipal.addTab("Comunidad", null, Comunidad, "Comunidad");
		
		JPanel Instalaciones = new JPanel();
		PanelPrincipal.addTab("Instalaciones", null, Instalaciones, "Instalaciones");
		
		JPanel Mensajes = new JPanel();
		PanelPrincipal.addTab( "Mensajes", null, Mensajes, "Mensajes");
				
		
		panelLogin = new JPanel();
		panelLogin.setBounds(0, 20, 794, 500);
		getContentPane().add(panelLogin);
		panelLogin.setOpaque(true);
		panelLogin.setVisible(false);
		panelLogin.setLayout(null);
		panelLogin.setMinimumSize(new Dimension(0, 0));
		panelLogin.setBorder(null);

		barrainferior = new JPanel();
		barrainferior.setBounds(0, 520, 794, 50);
		getContentPane().add(barrainferior);
		barrainferior.setLayout(null);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(645, 0, 149, 37);
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		barrainferior.add(comboBox);

		labelbarrainferior = new JLabel("");
		labelbarrainferior.setOpaque(true);
		labelbarrainferior.setBackground(Color.BLACK);
//		labelbarrainferior.setIcon(new ImageIcon(Principal.class.getResource("/resources/logobarra.jpg")));
		labelbarrainferior.setHorizontalAlignment(SwingConstants.CENTER);
		labelbarrainferior.setBounds(0, 0, 794, 50);
		barrainferior.add(labelbarrainferior);

		setVisible(true);
	}
	
	public void addTablePanes(){
		
		Calendario=(JPanel)new Calendario();
//		PanelPrincipal.getComponentAt(2).add(new Calendario());
//		PanelPrincipal.revalidate();
//		PanelPrincipal.repaint();
		
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
