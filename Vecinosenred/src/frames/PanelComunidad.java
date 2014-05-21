package frames;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import BD.Recuperar;
import clases.Movimiento;

public class PanelComunidad extends JPanel implements TreeSelectionListener{

	public JTree arbol;
	private ArrayList<Movimiento> movimientos=new ArrayList<Movimiento>();
	/**
	 * Create the panel.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public PanelComunidad(ArrayList<Movimiento> movimientos) throws ClassNotFoundException, SQLException {
		this.movimientos=movimientos;
		
		setLayout(null);
		setBounds(0, 0, 790, 450);
		
		crearArbol();
		arbol.setBounds(0, 0, 790, 450);
		add(arbol);
		

	}
	
	public void crearArbol() throws ClassNotFoundException, SQLException{
		
		ArrayList<Movimiento> ingresosOrdinarios=new ArrayList<Movimiento>();
		ArrayList<Movimiento> ingresosExtraordinarios=new ArrayList<Movimiento>();
		ArrayList<Movimiento> ingresosAnticipados=new ArrayList<Movimiento>();
		ArrayList<Movimiento> ingresosPendientes=new ArrayList<Movimiento>();
		ArrayList<Movimiento> gastosOrdinarios=new ArrayList<Movimiento>();
		ArrayList<Movimiento> gastosExtraordinarios=new ArrayList<Movimiento>();
		ArrayList<Movimiento> gastosPendientes=new ArrayList<Movimiento>();
		
		double totalIngOrd=0.00,totalIngExt=0.00,totalIngAnt=0.00,totalIngPen=0.00,totalGasOrd=0.00,totalGasExt=0.00,totalGasPen=0.00;
		
		for (int i = 0; i < movimientos.size(); i++) {			
			switch (movimientos.get(i).getTipo_movimiento()) {
			case 1:
				ingresosOrdinarios.add(movimientos.get(i));
				totalIngOrd=totalIngOrd+movimientos.get(i).getCantidad();
				break;
				
			case 2:
				ingresosExtraordinarios.add(movimientos.get(i));
				totalIngExt=totalIngExt+movimientos.get(i).getCantidad();
				break;
				
			case 3:
				ingresosAnticipados.add(movimientos.get(i));
				totalIngAnt=totalIngAnt+movimientos.get(i).getCantidad();
				break;
				
			case 4:
				ingresosPendientes.add(movimientos.get(i));
				totalIngPen=totalIngPen+movimientos.get(i).getCantidad();
				break;
				
			case 5:
				gastosOrdinarios.add(movimientos.get(i));
				totalGasOrd=totalGasOrd+movimientos.get(i).getCantidad();
				break;
				
			case 6:
				gastosExtraordinarios.add(movimientos.get(i));
				totalGasExt=totalGasExt+movimientos.get(i).getCantidad();
				break;
				
			case 7:
				gastosPendientes.add(movimientos.get(i));
				totalGasPen=totalGasPen+movimientos.get(i).getCantidad();
				break;
			}
		}
		
		
		
		/**Construimos los nodos del arbol que seran ramas u hojas*/
		/**Definimos cual será el directorio principal o la raiz de nuestro arbol*/
		  DefaultMutableTreeNode carpetaRaiz = new DefaultMutableTreeNode("RESUMEN FINANCIERO");
		 /**Definimos el modelo donde se agregaran los nodos*/
		  DefaultTreeModel modelo = new DefaultTreeModel(carpetaRaiz);
		 /**agregamos el modelo al arbol, donde previamente establecimos la raiz*/
		 arbol = new JTree(modelo);
		 /**definimos los eventos*/
		 arbol.getSelectionModel().addTreeSelectionListener(this);
		         
		 /**Definimos mas nodos del arbol y se lo agregamos al modelo*/
		 DefaultMutableTreeNode ingOrd = new DefaultMutableTreeNode("Ingresos Ordinarios: "+totalIngOrd);
		 DefaultMutableTreeNode ingExt = new DefaultMutableTreeNode("Ingresos Extraordinarios: "+totalIngExt);
		 DefaultMutableTreeNode ingAnt = new DefaultMutableTreeNode("Ingresos Anticipados: "+totalIngAnt);
		 DefaultMutableTreeNode ingPen = new DefaultMutableTreeNode("Ingresos Pendientes: "+totalIngPen);
		 DefaultMutableTreeNode gasOrd = new DefaultMutableTreeNode("Gastos Ordinarios: "+totalGasOrd);
		 DefaultMutableTreeNode gasExt = new DefaultMutableTreeNode("Gastos Extraordinarios: "+totalGasExt);
		 DefaultMutableTreeNode gasPen = new DefaultMutableTreeNode("Gastos Pendientes: "+totalGasPen);
		 /**Definimos donde se agrega el nodo, dentro de que rama y que posicion*/
		 modelo.insertNodeInto(ingOrd, carpetaRaiz, 0);
		 modelo.insertNodeInto(ingExt, carpetaRaiz, 1);
		 modelo.insertNodeInto(ingAnt, carpetaRaiz, 2);
		 modelo.insertNodeInto(ingPen, carpetaRaiz, 3);
		 modelo.insertNodeInto(gasOrd, carpetaRaiz, 4);
		 modelo.insertNodeInto(gasExt, carpetaRaiz, 5);
		 modelo.insertNodeInto(gasPen, carpetaRaiz, 6);
		 
		 for (int j = 0; j < ingresosOrdinarios.size(); j++) {
			 System.out.println(ingresosOrdinarios.size());
			 DefaultMutableTreeNode ingreso = 
					 new DefaultMutableTreeNode("Vecino: "+ingresosOrdinarios.get(j).getId_usuario()+" Fecha: "+ingresosOrdinarios.get(j).getFecha()
							 +" Motivo: "+ingresosOrdinarios.get(j).getMotivo()+" Cantidad: "+ingresosOrdinarios.get(j).getCantidad());
			 modelo.insertNodeInto(ingreso, ingOrd, j);
		}
		 
		 for (int j = 0; j < ingresosExtraordinarios.size(); j++) {
			 DefaultMutableTreeNode ingreso = 
					 new DefaultMutableTreeNode("Vecino: "+ingresosExtraordinarios.get(j).getId_usuario()+" Fecha: "+ingresosExtraordinarios.get(j).getFecha()
							 +" Motivo: "+ingresosExtraordinarios.get(j).getMotivo()+" Cantidad: "+ingresosExtraordinarios.get(j).getCantidad());
			 modelo.insertNodeInto(ingreso, ingExt, j);
		}
		 
		 for (int j = 0; j < ingresosAnticipados.size(); j++) {
			 DefaultMutableTreeNode ingreso = 
					 new DefaultMutableTreeNode("Vecino: "+ingresosAnticipados.get(j).getId_usuario()+" Fecha: "+ingresosAnticipados.get(j).getFecha()
							 +" Motivo: "+ingresosAnticipados.get(j).getMotivo()+" Cantidad: "+ingresosAnticipados.get(j).getCantidad());
			 modelo.insertNodeInto(ingreso, ingAnt, j);
		}
		 
		 for (int j = 0; j < ingresosPendientes.size(); j++) {
			 DefaultMutableTreeNode ingreso = 
					 new DefaultMutableTreeNode("Vecino: "+ingresosPendientes.get(j).getId_usuario()+" Fecha: "+ingresosPendientes.get(j).getFecha()
							 +" Motivo: "+ingresosPendientes.get(j).getMotivo()+" Cantidad: "+ingresosPendientes.get(j).getCantidad());
			 modelo.insertNodeInto(ingreso, ingPen, j);
		}
		 
		 for (int j = 0; j < gastosOrdinarios.size(); j++) {
			 DefaultMutableTreeNode gasto = 
					 new DefaultMutableTreeNode("Vecino: "+gastosOrdinarios.get(j).getId_usuario()+" Fecha: "+gastosOrdinarios.get(j).getFecha()
							 +" Motivo: "+gastosOrdinarios.get(j).getMotivo()+" Cantidad: "+gastosOrdinarios.get(j).getCantidad());
			 modelo.insertNodeInto(gasto, gasOrd, j);
		}
		 
		 for (int j = 0; j < gastosExtraordinarios.size(); j++) {
			 DefaultMutableTreeNode gasto = 
					 new DefaultMutableTreeNode("Vecino: "+gastosExtraordinarios.get(j).getId_usuario()+" Fecha: "+gastosExtraordinarios.get(j).getFecha()
							 +" Motivo: "+gastosExtraordinarios.get(j).getMotivo()+" Cantidad: "+gastosExtraordinarios.get(j).getCantidad());
			 modelo.insertNodeInto(gasto, gasExt, j);
		}
		 
		 for (int j = 0; j < gastosPendientes.size(); j++) {
			 DefaultMutableTreeNode ingreso = 
					 new DefaultMutableTreeNode("Vecino: "+gastosPendientes.get(j).getId_usuario()+" Fecha: "+gastosPendientes.get(j).getFecha()
							 +" Motivo: "+gastosPendientes.get(j).getMotivo()+" Cantidad: "+gastosPendientes.get(j).getCantidad());
			 modelo.insertNodeInto(ingreso, gasPen, j);
		}
		 
		 arbol.expandRow(0);
	}

	@Override
	public void valueChanged(TreeSelectionEvent arg0) {
		
		
		
	}
}
