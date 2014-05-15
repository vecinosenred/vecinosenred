package metodos;

import java.util.Date;

public class ObtenerFechaActual {
	
	public String obtenerFecha(){
		String fechaTotal = String.valueOf(new Date());
		String fechaActual=fechaTotal.substring(fechaTotal.length() - 4, fechaTotal.length())+
				"-"+new Date().getMonth()+
				"-"+fechaTotal.substring(8,10);
		
		return fechaActual;
	}

}
