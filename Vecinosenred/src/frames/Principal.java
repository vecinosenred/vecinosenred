package frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import clases.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Principal extends JFrame{
	JPanel panelLogin, PanelInicio,Mensajes,Incidencias;
	public JButton anadirmensaje,anadirincidencia;
	JMenuItem mntmLogin, mntmSalir;
	String titulosMensajes[] = { "Para", "Asunto", "Mensajes" };
	String titulosIncidencias[] = { "ticket","Titulo","Estado","Fecha Creada"};
	DefaultTableModel model,modelo;
	JTable tablamensajes,tablaincidencias;
	static Usuario logueado = new Usuario("usuario", "pass", "nombre", "domicilio");
	static 	ArrayList<Mensaje> ListaMensajes= new ArrayList<Mensaje>();
	static	ArrayList<Anuncio> ListaAnuncios= new ArrayList<Anuncio>();
	static	ArrayList<Incidencia> ListaIncidencias= new ArrayList<Incidencia>();
	public Principal(){
		
		setResizable(false);
		getContentPane().setEnabled(false);
		setTitle("Vecinos en Red");
		setBackground(Color.GRAY);
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		Eventos principal = new Eventos(this);
		EventosAnuncios Eveanuncios = new EventosAnuncios(this);
		EventosCalendario EveCalendario = new EventosCalendario(this);
		EventosComunidad EveComunidad = new EventosComunidad(this);
		EventosCuentas EveCuentas = new EventosCuentas(this);
		EventosIncidencias EveIncidencias = new EventosIncidencias(this);
		EventosInstalaciones EveInstalaciones = new EventosInstalaciones(this);
		EventosMensajes EveMensajes = new EventosMensajes(this);
		EventosLogin EveLogin = new EventosLogin(this);
		
		
		
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

		JLabel labelbarrainferior = new JLabel("");
		labelbarrainferior.setOpaque(true);
		labelbarrainferior.setBackground(Color.BLACK);
//		labelbarrainferior.setIcon(new ImageIcon(Principal.class.getResource("/resources/logobarra.jpg")));
		labelbarrainferior.setHorizontalAlignment(SwingConstants.CENTER);
		labelbarrainferior.setBounds(0, 0, 794, 50);
		barrainferior.add(labelbarrainferior);
		setVisible(true);
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
