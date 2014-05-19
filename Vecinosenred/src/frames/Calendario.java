package frames;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import BD.Actualizar;
import BD.Introducir;
import BD.Recuperar;
import clases.Recordatorio;

public class Calendario extends JPanel implements ActionListener {

	private JLabel etiqueta, horaL, lugarL, actividadL;
	private JTextField mes, fecha, horaT, lugarT;
	private JButton anterior, siguiente, ir, recordatorio, guardar, cancelar;
	private DefaultTableModel tabla;
	private JTable table;
	private JFrame marco;
	private JTextArea actividadT;
	private ArrayList<Recordatorio> rec;
	private int id_com;
	private Actualizar actualizar=new Actualizar();
	private Introducir introducir=new Introducir();
	
	String dias[] = {"Lunes","Martes","Miércoles","Jueves","Viernes","Sábado","Domingo"};
	String di[] = {"Lun","Mar","Mie","Jue","Vie","Sab","Dom"};
	String meses[] = {"ENERO","FEBRERO","MARZO","ABRIL","MAYO","JUNIO","JULIO","AGOSTO","SEPTIEMBRE","OCTUBRE","NOVIEMBRE","DICIEMBRE"};
	String meses2[] = {"Ene","Feb","Mar","Abr","May","Jun","Jul","Ago","Set","Oct","Nov","Dic"};
	String datoSeleccionado = "";

	int months[] = {31,28,31,30,31,30,31,31,30,31,30,31}; // Cantidad maxima de los dias de cada mes
	int anoActual = 0;
	int mesActual = 0;
	int diaActual = 0;
	int anoTemporal = 0;
	int mesTemporal = 0;
	int columnaSeleccionada = 0;
	int filaSeleccionada = 0;
	int cantidadArchivos = 1;
	int buffer = 2048;
	

	public Calendario(ArrayList<Recordatorio> rec,int id_com) {

		this.rec=rec;
		this.id_com=id_com;

//		Container c = get
//		c.setLayout(new FlowLayout());

		etiqueta = new JLabel("Hora del Sistema: "+establecerHora());

		mes = new JTextField(10);
		mes.setEditable(false);

		fecha = new JTextField(10);
		fecha.setEditable(false);

		anterior = new JButton("<< Anterior");
		anterior.addActionListener(this);
		anterior.setMnemonic('A');
		anterior.setToolTipText("Mostrar el mes anterior");

		siguiente = new JButton("Siguiente >>");
		siguiente.addActionListener(this);
		siguiente.setMnemonic('S');
		siguiente.setToolTipText("Mostrar el mes siguiente");

		ir = new JButton("Ir a...");
		ir.addActionListener(this);
		ir.setMnemonic('I');
		ir.setToolTipText("Escoger un mes o ver el mes actual");

		recordatorio = new JButton("Recordatorio");
		recordatorio.addActionListener(this);
		recordatorio.setMnemonic('R');
		recordatorio.setToolTipText("Crear o ver un recordatorio");

		tabla = new DefaultTableModel();

		for(int i = 0; i < dias.length; i++)
		tabla.addColumn(dias[i]);

		for(int i = 0; i < 6; i++) {

			String rows[] = new String[7];
			tabla.addRow(rows);

		}// for

		table = new JTable(tabla);
		table.setPreferredScrollableViewportSize(new Dimension(500, 96));
		table.setSelectionMode(0);
		table.setCellSelectionEnabled(true);

		JPanel p1 = new JPanel(new GridLayout(1,1));
		p1.add(new JLabel("Mes Mostrado:"));
		p1.add(mes);

		JPanel p2 = new JPanel(new GridLayout(1,1));
		p2.add(new JLabel("Fecha Actual:"));
		p2.add(fecha);

		JPanel p3 = new JPanel(new BorderLayout());
		p3.add(p2, BorderLayout.EAST);
		p3.add(p1, BorderLayout.WEST);

		JPanel p4 = new JPanel(new GridLayout(1,4,3,3));
		p4.add(anterior);
		p4.add(ir);
		p4.add(recordatorio);
		p4.add(siguiente);

		JPanel p5 = new JPanel(new BorderLayout(3,3));
		p5.add(etiqueta, BorderLayout.WEST);

		JPanel p6 = new JPanel(new BorderLayout(10,10));
		p6.add(p3, BorderLayout.NORTH);
		p6.add(new JScrollPane(table), BorderLayout.CENTER);
		p6.add(p4, BorderLayout.SOUTH);

		JPanel p7 = new JPanel(new BorderLayout(5,5));
		p7.add(p6, BorderLayout.NORTH);
		p7.add(p5, BorderLayout.SOUTH);

		JPanel p8 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p8.add(p7);

		this.add(p8);

		establecerFechaActual();
		mostrarEnTabla(anoActual, mesActual);

		setSize(550,260);
		show();

	}// public calendario

	static { // Obtiene la apariencia de la interfaz del sistema y la muestra en el programa

	    try {

	      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

	    }// look and feel

	    catch (Exception e) {}

  	}// catch

  	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == anterior)
		mesAnterior();

		if(e.getSource() == siguiente)
		mesSiguiente();

		if(e.getSource() == ir)
		irA();

		if(e.getSource() == recordatorio)
		opcionRecordatorio();

		if(e.getSource() == cancelar) {

			marco.hide();
			marco.dispose();

		}// if

		if(e.getSource() == guardar){
			
			guardarRecordatorio();
		}
	}// actionPerformed

	public String establecerHora() { // Obtiene la hora del sistema

		Date horas = new Date();
		Date minutos = new Date();
		Date segundos = new Date();

		String h = String.valueOf(horas.getHours());
		String m = String.valueOf(minutos.getMinutes());
		String s = String.valueOf(segundos.getSeconds());

		String hora = laHora(h) + ":" + m + ":" + s + " " + meridiano(h);

		return(hora);

	}// establecerHora

	public String laHora(String ho) { // Convierte la hora militar

		int a = Integer.parseInt(ho);

		String horas[] = {"1","2","3","4","5","6","7","8","9","10","11"};
		String retorno = "";

		if(a == 0)
		retorno = "12";

		else if(a >= 13 && a <= 23)
		retorno = horas[a-13];

		else
		retorno = ho;

		return(retorno);

	}// hora

	public String meridiano(String ho) { // Establece si la hora es pm o am

		int b = Integer.parseInt(ho);

		String retorno = "";

		if(b >= 12 && b <= 23)
		retorno = "pm";

		else
		retorno = "am";

		return(retorno);

	}// meridiano

	public void establecerFechaActual() { // Obtiene del sistema la fecha actual

		String fechaTotal = String.valueOf(new Date());

		mes.setText(meses[month(fechaTotal.substring(4,7))] + " " + fechaTotal.substring(fechaTotal.length() - 4, fechaTotal.length()));

		fecha.setText(di[day(fechaTotal.substring(0,3))] + ", " + fechaTotal.substring(8,10) + " " + meses2[month(fechaTotal.substring(4,7))] + " " + fechaTotal.substring(fechaTotal.length() - 4, fechaTotal.length()));

		anoActual = Integer.parseInt(fechaTotal.substring(fechaTotal.length() - 4, fechaTotal.length()));

		mesActual = month(fechaTotal.substring(4,7));

		diaActual = Integer.parseInt(fechaTotal.substring(8,10));

		anoTemporal = anoActual;
		mesTemporal = mesActual;

	}// establecer

	public int month(String m) { // Retorna un numero entre 0 y 11 que corresponde al mes

		int mo = 0;

		if(m.equals("Ene") || m.equals("Jan"))
		mo = 0;

		else if(m.equals("Feb"))
		mo = 1;

		else if(m.equals("Mar"))
		mo = 2;

		else if(m.equals("Abr") || m.equals("Apr"))
		mo = 3;

		else if(m.equals("May"))
		mo = 4;

		else if(m.equals("Jun"))
		mo = 5;

		else if(m.equals("Jul"))
		mo = 6;

		else if(m.equals("Ago") || m.equals("Aug"))
		mo = 7;

		else if(m.equals("Sep") || m.equals("Set"))
		mo = 8;

		else if(m.equals("Oct"))
		mo = 9;

		else if(m.equals("Nov"))
		mo = 10;

		else
		mo = 11;

		return(mo);

	}// month

	public int day(String m) { // Retorna un numero entre 0 y 6 que corresponde a los dias de la semana

		int mo = 0;

		if(m.equals("Lun") || m.equals("Mon"))
		mo = 0;

		else if(m.equals("Mar") || m.equals("Tue"))
		mo = 1;

		else if(m.equals("Mie") || m.equals("Wed"))
		mo = 2;

		else if(m.equals("Jue") || m.equals("Thu"))
		mo = 3;

		else if(m.equals("Vie") || m.equals("Fri"))
		mo = 4;

		else if(m.equals("Sab") || m.equals("Sat"))
		mo = 5;

		else if(m.equals("Dom") || m.equals("Sun"))
		mo = 6;

		return(mo);

	}// day

	public boolean bisiesto(int a) { // Retorna true si el año es bisiesto, de lo contrario retorna false

		boolean retorno = false;

		if(a % 4 == 0 || a % 100 == 0 || a % 400 == 0)
		retorno = true;

		return(retorno);

	}// bisiesto

	public void mostrarEnTabla(int ac, int ma) { // Muestra en la tabla los dias del mes seleccionado

		int columna = primerDia(ac, ma);
		int fila = 0;

		mes.setText(meses[ma] + " " + ac);

		if(bisiesto(ac))
		months[1] = 29;

		else
		months[1] = 28;

		for(int i = 1; i <= months[ma]; i++) {

			if(chequearMarca(ac,ma,i)) {

				if(ac == anoActual && ma == mesActual && i == diaActual)
				table.setValueAt("<< "+marca(ac,ma,i)+" >>",fila,columna);

				else
				table.setValueAt(marca(ac,ma,i),fila,columna);

			}// if

			else {

				if(ac == anoActual && ma == mesActual && i == diaActual)
				table.setValueAt("<< "+String.valueOf(i)+" >>",fila,columna);

				else
				table.setValueAt(String.valueOf(i),fila,columna);

			}// else

			if(columna == 6) {

				columna = 0;
				fila++;

			}// if

			else
			columna++;

		}// for

	}// mostrarEnTabla

	public int primerDia(int a, int m) { // Retorna un numero entre 0 y 6 que corresponde al primer dia del mes

		String d = String.valueOf((new GregorianCalendar(a,m,1)).getTime());

		return(day(d.substring(0,3)));

	}// recorrido

	public void mesAnterior() { // Muestra en la tabla el mes anterior al mostrado

		limpiarTabla();

		if(mesTemporal == 0) {

			mesTemporal = 11;
			anoTemporal--;

		}// if

		else
		mesTemporal--;

		mostrarEnTabla(anoTemporal, mesTemporal);

	}// mesAnterior

	public void limpiarTabla() { // Limpia los datos de la tabla

		for(int i = 0; i < 6; i++) {

			for(int j = 0; j < 7; j++) {

				table.setValueAt("",i,j);

			}// for

		}// for

	}// limpiarTabla

	public void mesSiguiente() { // Muestra en la tabla el siguiente mes

		limpiarTabla();

		if(mesTemporal == 11) {

			mesTemporal = 0;
			anoTemporal++;

		}// if

		else
		mesTemporal++;

		mostrarEnTabla(anoTemporal, mesTemporal);

	}// mesSiguiente

	public void irA() { // Muestra la interfaz para la opcion "Ir a..."

		JLabel seleccion = new JLabel("Seleccione una opción: ");

		JRadioButton escoger = new JRadioButton("Escoger un mes", true);
		JRadioButton actual = new JRadioButton("Volver a la fecha actual");

		ButtonGroup mx = new ButtonGroup();
		mx.add(escoger);
		mx.add(actual);

		JPanel i1 = new JPanel(new GridLayout(3,1,3,3));
		i1.add(seleccion);
		i1.add(escoger);
		i1.add(actual);

		JOptionPane.showMessageDialog(null,i1,"Ir a...",JOptionPane.QUESTION_MESSAGE);

		if(escoger.isSelected())
		escogerMes();

		else
		fechaActual();


	}// irA

	public void fechaActual() { // Muestra en la tabla el mes actual

		anoTemporal = anoActual;
		mesTemporal = mesActual;

		limpiarTabla();

		mostrarEnTabla(anoActual, mesActual);

	}// fechaActual

	public void escogerMes() { // Establece la interfaz y muestra en la tabla el mes seleccionado por el usuario

		try {

			JLabel titulo1 = new JLabel("Digite el año y escoja el mes");
			JLabel titulo2 = new JLabel("al cual desea ir");

			JTextField aa = new JTextField(5);

			JComboBox me = new JComboBox();
			me.setMaximumRowCount(5);

			for(int i = 0; i < meses.length; i++)
			me.addItem(meses[i]);

			JPanel e1 = new JPanel(new GridLayout(2,1));
			e1.add(titulo1);
			e1.add(titulo2);

			JPanel e2 = new JPanel(new GridLayout(2,1,3,3));
			e2.add(new JLabel("Año: "));
			e2.add(aa);
			e2.add(new JLabel("Mes: "));
			e2.add(me);

			JPanel e3 = new JPanel(new BorderLayout(3,3));
			e3.add(e1, BorderLayout.NORTH);
			e3.add(e2, BorderLayout.SOUTH);

			JOptionPane.showMessageDialog(null,e3,"Selección",JOptionPane.QUESTION_MESSAGE);

			int a = Integer.parseInt(aa.getText());
			int m = me.getSelectedIndex();

			if(a > 0) {

				anoTemporal = a;
				mesTemporal = m;

				limpiarTabla();

				mostrarEnTabla(a,m);

			}// if

			else
			JOptionPane.showMessageDialog(null,
			"Debe digitar numeros enteros positivos en el espacio para el año","Error",
			JOptionPane.ERROR_MESSAGE);

		}// try

		catch(Exception e) {

			JOptionPane.showMessageDialog(null,
			"Debe digitar numeros enteros positivos en el espacio para el año","Error",
			JOptionPane.ERROR_MESSAGE);

		}// catch

	}// escogerMes

	public String obtenerDato(int fila, int columna) { // Obtiene el dato de una celda de la tabla

		String dato = "";

		dato = String.valueOf(table.getValueAt(fila,columna));

		return(dato);

	}// obtenerDato

	public void opcionRecordatorio() { // Muestra la interfaz para la opcion "Recordatorio"

		columnaSeleccionada = table.getSelectedColumn();
		filaSeleccionada = table.getSelectedRow();

		if(columnaSeleccionada != -1 && filaSeleccionada != -1) {

			datoSeleccionado = obtenerDato(filaSeleccionada, columnaSeleccionada);

			if(datoSeleccionado.equals("null") || datoSeleccionado.equals(""))
			JOptionPane.showMessageDialog(null,
			"Debe seleccionar una celda que no esté vacía","Error",
			JOptionPane.WARNING_MESSAGE);

			else {

				JLabel pregunta = new JLabel("¿Qué desea hacer?");

				JRadioButton crear = new JRadioButton("Crear un Recordatorio",true);
				JRadioButton ver = new JRadioButton("Ver un Recordatorio");

				ButtonGroup cv = new ButtonGroup();
				cv.add(crear);
				cv.add(ver);

				JPanel o1 = new JPanel(new GridLayout(3,1,3,3));
				o1.add(pregunta);
				o1.add(crear);
				o1.add(ver);

				JOptionPane.showMessageDialog(null,o1,"Selección",JOptionPane.QUESTION_MESSAGE);

				if(crear.isSelected())
				crearRecordatorioInterfaz();

				else
				verRecordatorio();

			}// else

		}// if

		else
		JOptionPane.showMessageDialog(null,"Primero debe seleccionar una celda de la cuadricula",
		"Error",JOptionPane.WARNING_MESSAGE);

	}// opcionRecordatorio

	public boolean fechaValida(int a1, int a2, int m1, int m2, int d1, int d2) { // Retorna true si la fecha 2 es mayor o igual a la fecha 1

		boolean retorno = false;

		if(a2 > a1)
		retorno = true;

		else if(a2 == a1) {

			if(m2 > m1)
			retorno = true;

			else if(m2 == m1) {

				if(d2 >= d1)
				retorno = true;

			}// else if

		}// else if

		return(retorno);

	}// fechaValida

	public void crearRecordatorioInterfaz() { // Muestra la interfaz para la opcion "Crear un recordatorio"

		int diaTemporal = Integer.parseInt(identificarDato(datoSeleccionado));

		if(fechaValida(anoActual,anoTemporal,mesActual,mesTemporal,diaActual,diaTemporal)) {

			marco = new JFrame("Crear un Recordatorio");

			marco.addWindowListener(
					new WindowAdapter() {
						public void windowClosing(WindowEvent e) {
							marco.hide();
							marco.dispose();
						}// windowClosing
					}// WindowAdapter
			);//WindowListener

			horaL = new JLabel("Hora:");
			lugarL = new JLabel("Lugar:");
			actividadL = new JLabel("Actividad:");

			horaT = new JTextField(5);
			lugarT = new JTextField(10);

			actividadT = new JTextArea(10,35);

			guardar = new JButton("Guardar");
			guardar.addActionListener(this);
			guardar.setMnemonic('G');
			guardar.setToolTipText("Guardar el recordatorio");

			cancelar = new JButton("Cancelar");
			cancelar.addActionListener(this);
			cancelar.setMnemonic('C');
			cancelar.setToolTipText("Cierra la ventana");

			JPanel c1 = new JPanel(new GridLayout(2,1));
			c1.add(horaL);
			c1.add(horaT);

			JPanel c2 = new JPanel(new GridLayout(2,1));
			c2.add(lugarL);
			c2.add(lugarT);

			JPanel c3 = new JPanel(new BorderLayout(1,1));
			c3.add(actividadL, BorderLayout.NORTH);
			c3.add(new JScrollPane(actividadT), BorderLayout.SOUTH);

			JPanel c4 = new JPanel(new BorderLayout(5,5));
			c4.add(c1, BorderLayout.NORTH);
			c4.add(c2, BorderLayout.CENTER);
			c4.add(c3, BorderLayout.SOUTH);

			JPanel c5 = new JPanel(new GridLayout(1,2,3,3));
			c5.add(guardar);
			c5.add(cancelar);

			JPanel c6 = new JPanel(new BorderLayout(5,5));
			c6.add(c4, BorderLayout.NORTH);
			c6.add(c5, BorderLayout.SOUTH);

			JPanel c7 = new JPanel(new FlowLayout(FlowLayout.CENTER));
			c7.add(c6);

			marco.getContentPane().add(c7);

			marco.setSize(300,375);
			marco.show();

		}// if

		else
		JOptionPane.showMessageDialog(null,"Solo se permite crear un recordatorio\n"+
		"con una fecha igual o superior a la fecha actual","Error",
		JOptionPane.ERROR_MESSAGE);

	}// crearRecordatorioInterfaz

	public boolean espaciosLlenos() { // Retorna false si alguno de los espacios de Hora, Lugar y Actividad estan vacios

		boolean retorno = false;

		if(!horaT.getText().equals("") && !lugarT.getText().equals("") && !actividadT.getText().equals(""))
		retorno = true;

		return(retorno);

	}// espaciosLlenos

	public String identificarDato(String d) { // Retorna el numero de dia de una celda de la tabla

		char dt[] = d.toCharArray();
		String retorno = "";
		boolean hay = true;

		for(int i = 0; i < dt.length; i++) {

			if(String.valueOf(dt[i]).equals("<") || String.valueOf(dt[i]).equals(">") || String.valueOf(dt[i]).equals("*") || String.valueOf(dt[i]).equals("#") || String.valueOf(dt[i]).equals("&") || String.valueOf(dt[i]).equals(" "))
			hay = false;

			else
			hay = true;

			if(hay)
			retorno += String.valueOf(dt[i]);

		}// for

		return(retorno);

	}// datoSeleccionado

	public void guardarRecordatorio() { // Guarda en archivo lo que se escribio en los espacios de Hora, Lugar y Actividad

		try {

			if(espaciosLlenos()) {
				
				 SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
				 Date fech = null;
				 try {

				     fech = formatoDelTexto.parse(String.valueOf(anoTemporal)+"-"+String.valueOf(mesTemporal)+"-"
				     +identificarDato(datoSeleccionado));

				 } catch (ParseException ex) {

				     ex.printStackTrace();

				 }
				 
				 SimpleDateFormat formatoDelTextoHora = new SimpleDateFormat("yyyy-MM-dd");
				 Time fecFormatoTime = null;
				 try {
				       formatoDelTextoHora = new SimpleDateFormat("HH:mm:ss", new Locale("es", "ES"));
				       fecFormatoTime = new Time(formatoDelTextoHora.parse(horaT.getText()).getTime());
				 } catch (Exception ex) {
					 ex.printStackTrace();
				}
				 System.out.println(formatoDelTextoHora.format(fecFormatoTime));
				introducir.introducir("INSERT INTO MARC_MARCAS_CALENDARIO (MARC_ID_COMUNIDAD, MARC_FECHA, MARC_HORA, MARC_LUGAR, MARC_EVENTO)"
						+ " VALUES('"+id_com+"','"+formatoDelTexto.format(fech)+"','"+formatoDelTextoHora.format(fecFormatoTime)+"','"+lugarT.getText()+"','"+actividadT.getText()+"')");			

				
				JOptionPane.showMessageDialog(null,"El recordatorio ha sido guardado con exito",
				"Recordatorio Guardado",JOptionPane.INFORMATION_MESSAGE);

				marco.hide();
				marco.dispose();

				establecerMarca();

				table.clearSelection();

			}// if

			else
			JOptionPane.showMessageDialog(null,
			"Debe llenar los espacios de Hora, Lugar y Actividad","Error",
			JOptionPane.ERROR_MESSAGE);

		}// try

		catch(Exception e) {

			JOptionPane.showMessageDialog(null,"Error en: "+e.toString(),"Error",
			JOptionPane.ERROR_MESSAGE);

		}// catch

	}// guardarRecordatorio

	public void establecerMarca() { // Establece la marca para el dia en que se acaba de crear un recordatorio

		mostrarEnTabla(anoTemporal, mesTemporal);

	}// establecerMarca

	
	public void verRecordatorio() { // Lee desde los archivos con recordatorios y extrae su informacion

		try {


			boolean existe = false;
			String hora = "";
			String lugar = "";
			String actividad = "";
			
			
			SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
			Date fech = null;
			try {
	
				fech = formatoDelTexto.parse(String.valueOf(anoTemporal)+"-"+String.valueOf(mesTemporal)+"-"
			    +identificarDato(datoSeleccionado));
	
			} catch (ParseException ex) {
	
			     ex.printStackTrace();
	
			}
			
			for (int i = 0; i < rec.size(); i++) {				
				if(formatoDelTexto.format(rec.get(i).getFecha()).equals(formatoDelTexto.format(fech)) && 
						rec.get(i).getId_comunidad()==id_com){
					hora = String.valueOf(rec.get(i).getHora());
					lugar = rec.get(i).getLugar();
					actividad = rec.get(i).getRecordatorio();

					verRecordatorioInterfaz(hora,lugar,actividad);

					hora = "";
					lugar = "";
					actividad = "";
					existe=true;
				}				
			}

			if(!existe)
			JOptionPane.showMessageDialog(null,"No existe un recordatorio guardado\n"+
			"para el "+identificarDato(datoSeleccionado)+" de "+meses[mesTemporal].toLowerCase()+" del año "+anoTemporal,
			"No existe",JOptionPane.INFORMATION_MESSAGE);

			table.clearSelection();

		}// try

		catch(Exception e) {

			JOptionPane.showMessageDialog(null,"Error en: "+e.toString(),"Error",
			JOptionPane.ERROR_MESSAGE);

		}// catch

	}// verRecordatorio

	public void verRecordatorioInterfaz(String h, String l, String a) { // Muestra la interfaz para la opcion "Ver un recordatorio"

		marco = new JFrame("Ver un Recordatorio");

			marco.addWindowListener(
					new WindowAdapter() {
						public void windowClosing(WindowEvent e) {
							marco.hide();
							marco.dispose();
						}// windowClosing
					}// WindowAdapter
			);//WindowListener

			horaL = new JLabel("Hora:");
			lugarL = new JLabel("Lugar:");
			actividadL = new JLabel("Actividad:");

			horaT = new JTextField(5);
			horaT.setEditable(false);

			lugarT = new JTextField(10);
			lugarT.setEditable(false);

			actividadT = new JTextArea(10,35);
			actividadT.setEditable(false);

			JPanel c1 = new JPanel(new GridLayout(2,1));
			c1.add(horaL);
			c1.add(horaT);

			JPanel c2 = new JPanel(new GridLayout(2,1));
			c2.add(lugarL);
			c2.add(lugarT);

			JPanel c3 = new JPanel(new BorderLayout(1,1));
			c3.add(actividadL, BorderLayout.NORTH);
			c3.add(new JScrollPane(actividadT), BorderLayout.SOUTH);

			JPanel c4 = new JPanel(new BorderLayout(5,5));
			c4.add(c1, BorderLayout.NORTH);
			c4.add(c2, BorderLayout.CENTER);
			c4.add(c3, BorderLayout.SOUTH);

			JPanel c5 = new JPanel(new FlowLayout(FlowLayout.CENTER));
			c5.add(c4);

			marco.getContentPane().add(c5);

			horaT.setText(h);
			lugarT.setText(l);
			actividadT.setText(a);

			marco.setSize(400,400);
			marco.setLocationRelativeTo(null);
			marco.show();

	}// verRecordatorioInterfaz
	

	public boolean chequearMarca(int a, int m, int d) { // Retorna true si el dia a mostrar en la tabla tiene un recordatorio

		boolean existe = false;

		try {

			SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
			 Date fech = null;
			 try {
			     fech = formatoDelTexto.parse(a+"-"+m+"-"+d);
			 } catch (ParseException ex) {
			     ex.printStackTrace();
			 }
			
			for (int i = 0; i < rec.size(); i++) {
				if(formatoDelTexto.format(rec.get(i).getFecha()).equals(formatoDelTexto.format(fech)) && 
						rec.get(i).getId_comunidad()==id_com){
					existe=true;
				}
			}

		}// try

		catch(Exception e) {

			JOptionPane.showMessageDialog(null,"Error en: "+e.toString(),"Error",
			JOptionPane.ERROR_MESSAGE);

		}// catch

		return(existe);

	}// chequearMarca

	public String marca(int a, int m, int d) { // Retorna las marcas para cualquier dia que tenga un recordatorio

		String retorno = "";

		if(a < anoActual)
		retorno = String.valueOf(d)+" &";

		else if(a > anoActual)
		retorno = String.valueOf(d)+" #";

		else {

			if(m < mesActual)
			retorno = String.valueOf(d)+" &";

			else if(m > mesActual)
			retorno = String.valueOf(d)+" #";

			else {

				if(d < diaActual)
				retorno = String.valueOf(d)+" &";

				else if(d > diaActual)
				retorno = String.valueOf(d)+" #";

				else
				retorno = String.valueOf(d)+" *";

			}// else

		}// else

		return(retorno);

	}// marca

}// class calendario