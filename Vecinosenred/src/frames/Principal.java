package frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
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
import clases.Anuncio;
import clases.Incidencia;
import clases.Mensaje;
import clases.Usuario;

import javax.swing.JComboBox;

public class Principal extends JFrame{
	JPanel panelLogin, PanelInicio,Mensajes,Incidencias;
	public JButton anadirmensaje,anadirincidencia;
	JMenuItem mntmLogin, mntmSalir;
	String titulosMensajes[] = { "De","Para", "Asunto", "Mensajes" };
	String titulosIncidencias[] = { "ticket","Titulo","Estado","Fecha Creada"};
	DefaultTableModel model,modelo;
	JTable tablamensajes,tablaincidencias;
	static Usuario logueado = new Usuario("usuario", "pass", "nombre", "domicilio");
	static 	ArrayList<Mensaje> ListaMensajes= new ArrayList<Mensaje>();
	static	ArrayList<Anuncio> ListaAnuncios= new ArrayList<Anuncio>();
	static	ArrayList<Incidencia> ListaIncidencias= new ArrayList<Incidencia>();
	Recuperar recuperar;
	JComboBox<String> comboBox;
	Eventos principal;
	EventosAnuncios Eveanuncios;
	EventosCalendario EveCalendario;
	EventosComunidad EveComunidad;
	EventosCuentas EveCuentas;
	EventosIncidencias EveIncidencias;
	EventosInstalaciones EveInstalaciones;
	EventosMensajes EveMensajes;
	EventosLogin EveLogin;
	
	public Recuperar getRecuperar() {
		return recuperar;
	}

	public void setRecuperar(Recuperar recuperar) {
		this.recuperar = recuperar;
	}

	public Principal(){
		
		setResizable(false);
		getContentPane().setEnabled(false);
		setTitle("Vecinos en Red");
		setBackground(Color.GRAY);
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		principal = new Eventos(this);
		Eveanuncios = new EventosAnuncios(this);
		EveCalendario = new EventosCalendario(this);
		EveComunidad = new EventosComunidad(this);
		EveCuentas = new EventosCuentas(this);
		EveIncidencias = new EventosIncidencias(this);
		EveInstalaciones = new EventosInstalaciones(this);
		EveMensajes = new EventosMensajes(this);
		EveLogin = new EventosLogin(this);
		
		
		
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
		mntmLogin.addActionListener(principal);
		mnInicio.add(mntmLogin);
		

		mntmSalir = new JMenuItem("Salir");
		mnInicio.add(mntmSalir);
		
		

		PanelInicio = new JPanel();
		PanelInicio.setBounds(0, 20, 794, 500);
		getContentPane().add(PanelInicio);
		PanelInicio.setMinimumSize(new Dimension(0, 0));
		PanelInicio.setBorder(null);
		PanelInicio.setLayout(null);
		
		JTabbedPane PanelPrincipal = new JTabbedPane(JTabbedPane.TOP);
		PanelPrincipal.setBounds(0, 0, 794, 500);
		PanelInicio.add(PanelPrincipal);
		//Panel Anuncios
		JPanel Anuncios = new JPanel();
		PanelPrincipal.addTab("Anuncios", null, Anuncios, "Anuncios");
		
		//Panel Calendario
		JPanel Calendario = new JPanel();
		PanelPrincipal.addTab("Calendario", null, Calendario, "Calendario");
		
		//Panel incidencias
		Incidencias = new JPanel();
		Incidencias.setBounds(0, 0, 794, 450);
		Incidencias.setLayout(null);
		Incidencias.setBorder(null);
		JScrollPane scrollPanel = new JScrollPane();
		scrollPanel.setBounds(0, 0, 790, 450);
		Incidencias.add(scrollPanel);
		tablaincidencias = new JTable(modelo);
		scrollPanel.setViewportView(tablaincidencias);
		anadirincidencia = new JButton();
		anadirincidencia.setText("nuevo mensaje");
		anadirincidencia.setBounds(650, 450, 125, 20);
		Incidencias.add(anadirincidencia);
		PanelPrincipal.addTab("Incidencias", null, Incidencias, "Incidencias");
		
		//Panel Cuentas
		JPanel Cuentas = new JPanel();
		PanelPrincipal.addTab("Cuentas", null, Cuentas, "Cuentas");
		
		//Panel Comunidad
		JPanel Comunidad = new JPanel();
		PanelPrincipal.addTab("Comunidad", null, Comunidad, "Comunidad");
		
		//Panel Instalaciones
		JPanel Instalaciones = new JPanel();
		PanelPrincipal.addTab("Instalaciones", null, Instalaciones, "Instalaciones");
		
		//Panel Mensajes
		Mensajes = new JPanel();
		Mensajes.setBounds(0, 0, 794, 450);
		Mensajes.setLayout(null);
		Mensajes.setBorder(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 790, 450);
		Mensajes.add(scrollPane);
		tablamensajes = new JTable(model);
		scrollPane.setViewportView(tablamensajes);
		anadirmensaje = new JButton();
		anadirmensaje.setText("nuevo mensaje");
		anadirmensaje.setBounds(650, 450, 125, 20);
		Mensajes.add(anadirmensaje);
		PanelPrincipal.addTab("Mensajes", null, Mensajes, "Mensajes");
		
		//Panel Login
		panelLogin = new JPanel();
		panelLogin.setBounds(0, 20, 794, 500);
		getContentPane().add(panelLogin);
		panelLogin.setOpaque(true);
		panelLogin.setVisible(false);
		panelLogin.setLayout(null);
		panelLogin.setMinimumSize(new Dimension(0, 0));
		panelLogin.setBorder(null);
		modelo = new DefaultTableModel(null, titulosIncidencias);
		model = new DefaultTableModel(null, titulosMensajes);

		JPanel barrainferior = new JPanel();
		barrainferior.setBounds(0, 520, 794, 50);
		getContentPane().add(barrainferior);
		barrainferior.setLayout(null);
		

		comboBox = new JComboBox<String>();
		comboBox.setBounds(637, 0, 157, 50);
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				principal.llenarlistaMensajes(recuperar.getMensajes(),
						recuperar.getComunidades().get(comboBox.getSelectedIndex()).getId());
				principal.llenarlistaIncidencias(recuperar.getIncidencias(),
						recuperar.getComunidades().get(comboBox.getSelectedIndex()).getId());
				
			}
		});
		barrainferior.add(comboBox);
		setVisible(true);

		JLabel labelbarrainferior = new JLabel("");
		labelbarrainferior.setOpaque(true);
		labelbarrainferior.setBackground(Color.BLACK);
//		labelbarrainferior.setIcon(new ImageIcon(Principal.class.getResource("/resources/logobarra.jpg")));
		labelbarrainferior.setHorizontalAlignment(SwingConstants.CENTER);
		labelbarrainferior.setBounds(0, 0, 794, 50);
		barrainferior.add(labelbarrainferior);
		
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
