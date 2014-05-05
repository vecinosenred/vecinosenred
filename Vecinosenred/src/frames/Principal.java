package frames;

import java.awt.Color;


import java.awt.Dimension;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Principal extends JFrame{
	ArrayList<String> Clientes = new ArrayList<String>();
	JPanel panelventa, PanelInicio;
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
		mnInicio.add(mntmVenta);
		

		mntmSalir = new JMenuItem("Salir");
		mnInicio.add(mntmSalir);
		

		
		
		

		panelventa = new JPanel();
		panelventa.setBounds(0, 20, 794, 500);
		getContentPane().add(panelventa);
		panelventa.setOpaque(true);
		panelventa.setVisible(false);
		panelventa.setLayout(null);
		panelventa.setMinimumSize(new Dimension(0, 0));
		panelventa.setBorder(null);
		
		

		PanelInicio = new JPanel();
		PanelInicio.setBounds(0, 20, 794, 500);
		getContentPane().add(PanelInicio);
		PanelInicio.setMinimumSize(new Dimension(0, 0));
		PanelInicio.setBorder(null);
		PanelInicio.setLayout(null);

		JLabel imaini = new JLabel("");
		imaini.setHorizontalAlignment(SwingConstants.CENTER);
		imaini.setBounds(0, 0, 794, 500);
		imaini.setIcon(new ImageIcon(Principal.class.getResource("/resources/Imageninicio.jpg")));
		PanelInicio.add(imaini);

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
