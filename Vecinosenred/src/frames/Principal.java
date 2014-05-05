package frames;

import java.awt.Color;


import java.awt.Dimension;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Principal extends JFrame{
	JPanel panelLogin, PanelInicio;
	JMenuItem mntmLogin, mntmSalir;
	public Principal(){

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
		JPanel Anuncios = new JPanel();
		PanelPrincipal.addTab("Ficha uno", null, Anuncios, "Anuncios");
		JPanel Calendario = new JPanel();
		PanelPrincipal.addTab("Ficha uno", null, Calendario, " Calendario");
		JPanel Incidencias = new JPanel();
		PanelPrincipal.addTab("Ficha uno", null, Incidencias, "Incidencias");
		JPanel Cuentas = new JPanel();
		PanelPrincipal.addTab("Ficha uno", null, Cuentas, "Cuentas");
		JPanel Comunidad = new JPanel();
		PanelPrincipal.addTab("Ficha uno", null, Comunidad, "Comunidad");
		JPanel Instalaciones = new JPanel();
		PanelPrincipal.addTab("Ficha uno", null, Instalaciones, "Instalaciones");
		JPanel Mensajes = new JPanel();
		PanelPrincipal.addTab("Ficha uno", null, Mensajes, "Mensajes");

		
		
		

		panelLogin = new JPanel();
		panelLogin.setBounds(0, 20, 794, 500);
		getContentPane().add(panelLogin);
		panelLogin.setOpaque(true);
		panelLogin.setVisible(false);
		panelLogin.setLayout(null);
		panelLogin.setMinimumSize(new Dimension(0, 0));
		panelLogin.setBorder(null);

		JPanel barrainferior = new JPanel();
		barrainferior.setBounds(0, 520, 794, 50);
		getContentPane().add(barrainferior);
		barrainferior.setLayout(null);

		JLabel labelbarrainferior = new JLabel("");
		labelbarrainferior.setOpaque(true);
		labelbarrainferior.setBackground(Color.BLACK);
		labelbarrainferior.setIcon(new ImageIcon(Principal.class.getResource("/resources/logobarra.jpg")));
		labelbarrainferior.setHorizontalAlignment(SwingConstants.CENTER);
		labelbarrainferior.setBounds(0, 0, 794, 50);
		barrainferior.add(labelbarrainferior);
		setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
