package frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

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

import metodos.Eventos;
import metodos.EventosAnuncios;
import metodos.EventosCalendario;
import metodos.EventosComunidad;
import metodos.EventosCuentas;
import metodos.EventosIncidencias;
import metodos.EventosInstalaciones;
import metodos.EventosLogin;
import metodos.EventosMensajes;
import BD.Recuperar;
import clases.Anuncio;
import clases.Incidencia;
import clases.Mensaje;
import clases.Movimiento;
import clases.Usuario;

public class Principal extends JFrame{
	public JPanel panelLogin, PanelInicio,Mensajes,Incidencias,Calendario,Cuentas,Comunidad,Instalaciones;
	public JButton anadirmensaje,anadirincidencia,anadirAnuncio;
	public JMenuItem mntmLogin;
	JMenuItem mntmSalir;
	String titulosMensajes[] = { "De","Para", "Asunto", "Mensajes","" };
	String titulosIncidencias[] = { "Ticket","Titulo","Estado","Fecha Creada",""};
	String titulosAnuncios[] = { "Ticket","Titulo","Anuncio","Fecha Creada",""};
	String titulosCuentas[] = { "Num. Cuenta","Titular","Cantidad","Tipo Movimiento",
			"Fecha","Motivo","Saldo Anterior","Saldo Nuevo"};
	String titulosComunidad[] = { "Num. Cuenta","Titular","Cantidad","Tipo Movimiento",
			"Fecha","Motivo","Saldo Anterior","Saldo Nuevo"};
	String titulosInstalaciones[] = { "Nombre","Coste","Descripción",""};
	public DefaultTableModel modeloMensajes,modeloIncidencias,modeloAnuncios,
							modeloCuentas,modeloComunidad,modeloInstalaciones;
	public JTable tablamensajes,tablaincidencias,tablaAnuncios,tablaCuentas,
				tablaComunidad,tablaInstalaciones;
	public static Usuario logueado = new Usuario("usuario", "pass", "nombre", "domicilio",0);
	public static 	ArrayList<Mensaje> ListaMensajes= new ArrayList<Mensaje>();
	public static	ArrayList<Anuncio> ListaAnuncios= new ArrayList<Anuncio>();
	public static	ArrayList<Incidencia> ListaIncidencias= new ArrayList<Incidencia>();
	public static	ArrayList<Movimiento> ListaMovimientos= new ArrayList<Movimiento>();
	public Recuperar recuperar;
	public JComboBox<String> comboBox;
	Eventos principal;
	EventosAnuncios Eveanuncios;
	EventosCalendario EveCalendario;
	EventosComunidad EveComunidad;
	EventosCuentas EveCuentas;
	EventosIncidencias EveIncidencias;
	EventosInstalaciones EveInstalaciones;
	public EventosMensajes EveMensajes;
	EventosLogin EveLogin;
	
	public Recuperar getRecuperar() {
		return recuperar;
	}

	public void setRecuperar(Recuperar recuperar) {
		this.recuperar = recuperar;
	}
	
	public static Usuario getLogueado() {
		return logueado;
	}

	public static void setLogueado(Usuario logueado) {
		Principal.logueado = logueado;
	}


	public Principal(){
		
		setResizable(false);
		getContentPane().setEnabled(false);
		setTitle("Vecinos en Red");
		setBackground(Color.GRAY);
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		principal = new Eventos(this,logueado.getUsuario());
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
		Anuncios.setBounds(0, 0, 794, 450);
		Anuncios.setLayout(null);
		Anuncios.setBorder(null);
		
		JScrollPane scrollAn = new JScrollPane();
		scrollAn.setBounds(0, 0, 790, 450);
		Anuncios.add(scrollAn);
		
		modeloAnuncios = new DefaultTableModel(null, titulosAnuncios);
		tablaAnuncios = new JTable(modeloAnuncios){
	        public boolean isCellEditable(int rowIndex, int vColIndex) {

	        	if(vColIndex<4){
		            return false;
	        	}else{
	        		return true;
	        	}
	        }};
		scrollAn.setViewportView(tablaAnuncios);
		
		anadirAnuncio = new JButton();
		anadirAnuncio.setText("nuevo mensaje");
		anadirAnuncio.setBounds(650, 450, 125, 20);
		Anuncios.add(anadirAnuncio);
		
		PanelPrincipal.addTab("Anuncios", null, Anuncios, "Anuncios");
		
		//Panel Calendario
		Calendario = new JPanel();		
		PanelPrincipal.addTab("Calendario", null, Calendario, "Calendario");
		
		//Panel incidencias
		Incidencias = new JPanel();
		Incidencias.setBounds(0, 0, 794, 450);
		Incidencias.setLayout(null);
		Incidencias.setBorder(null);
		
		JScrollPane scrollPanel = new JScrollPane();
		scrollPanel.setBounds(0, 0, 790, 450);
		Incidencias.add(scrollPanel);
		
		modeloIncidencias = new DefaultTableModel(null, titulosIncidencias);
		tablaincidencias = new JTable(modeloIncidencias){
	        public boolean isCellEditable(int rowIndex, int vColIndex) {
	        	
	        	if(vColIndex<4){
		            return false;
	        	}else{
	        		return true;
	        	}
	        	
	        }};
		tablaincidencias.addMouseListener(EveIncidencias);
		scrollPanel.setViewportView(tablaincidencias);
		
		anadirincidencia = new JButton();
		anadirincidencia.setText("nuevo mensaje");
		anadirincidencia.setBounds(650, 450, 125, 20);
		anadirincidencia.addActionListener(EveIncidencias);
		Incidencias.add(anadirincidencia);
		
		PanelPrincipal.addTab("Incidencias", null, Incidencias, "Incidencias");
		
		//Panel Cuentas
		Cuentas = new JPanel();
		Cuentas.setBounds(0, 0, 794, 450);
		Cuentas.setLayout(null);
		Cuentas.setBorder(null);
		
		JScrollPane scrollCuentas = new JScrollPane();
		scrollCuentas.setBounds(0, 0, 790, 450);
		Cuentas.add(scrollCuentas);
		
		modeloCuentas = new DefaultTableModel(null, titulosCuentas);
		modeloCuentas.addTableModelListener(EveCuentas);
		tablaCuentas = new JTable(modeloCuentas){
	        public boolean isCellEditable(int rowIndex, int vColIndex) {
	        		return false;
	        }};
		tablaCuentas.addMouseListener(EveCuentas);
		scrollCuentas.setViewportView(tablaCuentas);
		PanelPrincipal.addTab("Cuentas", null, Cuentas, "Cuentas");
		
		//Panel Comunidad
		Comunidad = new JPanel();
		Comunidad.setBounds(0, 0, 794, 450);
		Comunidad.setLayout(null);
		Comunidad.setBorder(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 790, 450);
		Comunidad.add(scrollPane);
		
		modeloComunidad = new DefaultTableModel(null, titulosComunidad);
		modeloComunidad.addTableModelListener(EveComunidad);
		tablaComunidad = new JTable(modeloComunidad){
	        public boolean isCellEditable(int rowIndex, int vColIndex) {
		            return false;
	        }};
		tablaComunidad.addMouseListener(EveComunidad);
		scrollPane.setViewportView(tablaComunidad);
		PanelPrincipal.addTab("Comunidad", null, Comunidad, "Comunidad");
		
		//Panel Instalaciones
		Instalaciones = new JPanel();
		Instalaciones.setBounds(0, 0, 794, 450);
		Instalaciones.setLayout(null);
		Instalaciones.setBorder(null);
		
		JScrollPane scrollInstalaciones = new JScrollPane();
		scrollInstalaciones.setBounds(0, 0, 790, 450);
		Instalaciones.add(scrollInstalaciones);
		
		modeloInstalaciones = new DefaultTableModel(null, titulosInstalaciones);
		modeloInstalaciones.addTableModelListener(EveInstalaciones);
		tablaInstalaciones = new JTable(modeloInstalaciones){
	        public boolean isCellEditable(int rowIndex, int vColIndex) {

	        	if(vColIndex<4){
		            return false;
	        	}else{
	        		return true;
	        	}
	        }};
		tablaInstalaciones.addMouseListener(EveInstalaciones);
		scrollInstalaciones.setViewportView(tablaInstalaciones);
		
		//Panel Mensajes
		Mensajes = new JPanel();
		Mensajes.setBounds(0, 0, 794, 450);
		Mensajes.setLayout(null);
		Mensajes.setBorder(null);
		
		JScrollPane scrollMensajes = new JScrollPane();
		scrollMensajes.setBounds(0, 0, 790, 450);
		Mensajes.add(scrollMensajes);
		
		modeloMensajes = new DefaultTableModel(null, titulosMensajes);
		modeloMensajes.addTableModelListener(EveMensajes);
		tablamensajes = new JTable(modeloMensajes){
	        public boolean isCellEditable(int rowIndex, int vColIndex) {

	        	if(vColIndex<4){
		            return false;
	        	}else{
	        		return true;
	        	}
	        }};
		tablamensajes.addMouseListener(EveMensajes);
		scrollMensajes.setViewportView(tablamensajes);
		
		anadirmensaje = new JButton();
		anadirmensaje.setText("nuevo mensaje");
		anadirmensaje.setBounds(650, 450, 125, 20);
		anadirmensaje.addActionListener(EveMensajes);
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
		modeloIncidencias = new DefaultTableModel(null, titulosIncidencias);
		modeloMensajes = new DefaultTableModel(null, titulosMensajes);

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
				principal.llenarlistaCuentas(recuperar.getMovimientos(),
						recuperar.getComunidades().get(comboBox.getSelectedIndex()).getId());
				try {
					principal.llenarlistaAnuncios(recuperar.getAnuncios(),
							recuperar.getComunidades().get(comboBox.getSelectedIndex()).getId());
					principal.mostrarCalendario(recuperar.getRecordatorios(), 
							recuperar.getComunidades().get(comboBox.getSelectedIndex()).getId());

				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
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
