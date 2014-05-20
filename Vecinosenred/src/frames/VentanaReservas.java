package frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import metodos.ObtenerFechaActual;
import BD.Introducir;
import BD.Recuperar;
import clases.Reserva;

import com.toedter.calendar.JCalendar;

public class VentanaReservas extends JFrame implements ActionListener{

	private JPanel contentPane;
	private String dia,mes,ano;
	private ObtenerFechaActual ofa=new ObtenerFechaActual();
	private JComboBox<Integer> comboHoraFin,comboHoraIni;
	private int id_instalacion;
	private JCalendar calendar;
	private ArrayList<Integer> horainicio=new ArrayList<Integer>();	
	private ArrayList<Reserva> reservas=new ArrayList<Reserva>();
	private Recuperar rec;
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public VentanaReservas(ArrayList<Reserva> reservas,int id_instalacion,Recuperar rec) throws ClassNotFoundException, SQLException {
		this.id_instalacion=id_instalacion;
		this.rec=rec;
		this.reservas=reservas;		
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("Reservar");
		setBounds(100, 100, 250, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		calendar = new JCalendar();
		calendar.setBounds(0, 0, 225, 155);
		calendar.addPropertyChangeListener(new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent arg0) {
				try {
					refrescarComboHoras();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}				
			}
		});
		contentPane.add(calendar);
		
		JButton btnReservar = new JButton("Reservar");
		btnReservar.setBounds(67, 235, 97, 25);
		btnReservar.addActionListener(this);
		contentPane.add(btnReservar);
		
		comboHoraIni = new JComboBox<Integer>();
		for (int i = 0; i < horainicio.size(); i++) {
			comboHoraIni.addItem(horainicio.get(i));
		}
		comboHoraIni.setBounds(12, 200, 213, 22);
		contentPane.add(comboHoraIni);	

		
		JLabel lblHoraInicio = new JLabel("Hora Inicio:");
		lblHoraInicio.setBounds(12, 171, 110, 16);
		contentPane.add(lblHoraInicio);
		
		refrescarComboHoras();
		
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		Introducir introducir=new Introducir();
		int horafin=(Integer)comboHoraIni.getSelectedItem()+1;
		try {
			introducir.introducir("INSERT INTO RESINS_RESERVAS_INSTALACIONES"
					+ "(RESINS_ID_INSTALACION,RESINS_DIA,RESINS_HORAINICIO,RESINS_HORAFIN)"
					+ "VALUES "
					+ "('"+id_instalacion+"','"+ofa.formatearFecha(calendar.getDate())+"','"+comboHoraIni.getSelectedItem()+"','"+horafin+"')");
			refrescarComboHoras();
			setVisible(false);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private void refrescarComboHoras() throws ClassNotFoundException, SQLException{
		
		rec.RefrescarArray("reservas");
		reservas=rec.getReservas();

		horainicio.clear();
		if (reservas.isEmpty()) {
			for (int i = 8; i < 23; i++) {
				horainicio.add(i);
			}
		}else{
			for (int j = 0; j < reservas.size(); j++) {
				System.out.println("primer for");
				System.out.println(String.valueOf(reservas.get(j).getFecha()));
				System.out.println(sdf.format(calendar.getDate()));
				if (String.valueOf(reservas.get(j).getFecha()).equals(sdf.format(calendar.getDate()))) {
					for (int i = 8; i < 23; i++) {
						System.out.println("segundo for");
						if(reservas.get(j).getId_instalacion()==id_instalacion && 
								reservas.get(j).getHorainicio()!=i){
							System.out.println("if");
							horainicio.add(i);
						}
					
					}
				}
			}
		}
//		comboHoraIni.updateUI();
		repaint();
	}
}

