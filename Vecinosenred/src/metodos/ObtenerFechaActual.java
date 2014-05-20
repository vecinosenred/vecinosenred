package metodos;

import java.util.Date;

public class ObtenerFechaActual {
	
	public String obtenerFecha(){
		String fechaTotal = String.valueOf(new Date());
		String fechaActual=fechaTotal.substring(fechaTotal.length() - 4, fechaTotal.length())+
				"-"+((new Date().getMonth()+1))+
				"-"+fechaTotal.substring(8,10);
		
		return fechaActual;
	}
	
	public String formatearFecha(Date fecha){

		String fechaTotal = String.valueOf(fecha);
		String fechaActual=fechaTotal.substring(fechaTotal.length() - 4, fechaTotal.length())+
				"-"+((new Date().getMonth()+1))+
				"-"+fechaTotal.substring(8,10);
		
		return fechaActual;
	}

}
