package frames;

import java.awt.Color;


import java.awt.Dimension;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Principal extends JFrame{
	String titulos[] = { "Producto", "Cantidad", "Precio" };
	String timodcli[] = { "Nombre", "Apellido1", "Apellido2", "telefono" };
	String tiprodven[] = { "Nombre", "Descripcion", "Codigo", "Precio Unidad" };
	String FamiliaProducto[] = { "Todo", "Torre", "PlacaBase", "Procesador", "Memoria RAM", "DiscoDuro", "TarjetaGrafica", "Raton", "Teclado", "Memoria USB", "--" };
	String TipoCliente[] = { "Normal", "Habitual", "Socio", "Empleado" };
	String titulomodprod[]={"Codigo","Nombre","Descripcion","Precio"};
	ArrayList<String> Clientes = new ArrayList<String>();
	JPanel panelventa, PanelInicio;
	DefaultTableModel model, tamodcli, tavenpro,tamodpro;
	JMenuItem mntmVenta, mntmSalir, mntmAltaC, mntmmodBajaC, mntmAltaP, mntmModbajaP;
	public Principal(){

		setResizable(false);
		getContentPane().setEnabled(false);
		setTitle("Tienda Informatica");
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

		mntmVenta = new JMenuItem("Venta/devolucion");
		mnInicio.add(mntmVenta);
		

		mntmSalir = new JMenuItem("Salir");
		mnInicio.add(mntmSalir);
		

		JMenu mnCliente = new JMenu("Cliente");
		menuBar.add(mnCliente);

		mntmAltaC = new JMenuItem("Alta Cliente");
		mnCliente.add(mntmAltaC);
		

		mntmmodBajaC = new JMenuItem("Baja/mod");
		mnCliente.add(mntmmodBajaC);
		

		JMenu mnProducto = new JMenu("Producto");
		menuBar.add(mnProducto);

		mntmAltaP = new JMenuItem("Alta Producto");
		mnProducto.add(mntmAltaP);


		mntmModbajaP = new JMenuItem("Mod/baja");
		mnProducto.add(mntmModbajaP);
		
		tamodpro = new DefaultTableModel(null, titulomodprod);
		tamodcli = new DefaultTableModel(null, timodcli);

		panelventa = new JPanel();
		panelventa.setBounds(0, 20, 794, 500);
		getContentPane().add(panelventa);
		panelventa.setOpaque(true);
		panelventa.setVisible(false);
		panelventa.setLayout(null);
		panelventa.setMinimumSize(new Dimension(0, 0));
		panelventa.setBorder(null);
		model = new DefaultTableModel(null, titulos);
		tavenpro = new DefaultTableModel(null, tiprodven);
		

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
