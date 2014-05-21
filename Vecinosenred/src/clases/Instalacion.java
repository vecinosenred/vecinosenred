package clases;

/**
 * Clase que define el tipo Instalacion
 * @author JonB
 *
 */
public class Instalacion {
	
	private int id_instalacion,id_comunidad,coste,en_servicio,morosos;
	private String nombre,descripcion,reglamento;
	
	public Instalacion(int id_instalacion,int id_comunidad,String nombre,
			int coste,String descripcion,int en_servicio,String reglamento,int morosos){
		
		this.id_instalacion=id_instalacion;
		this.id_comunidad=id_comunidad;
		this.nombre=nombre;
		this.coste=coste;
		this.descripcion=descripcion;
		this.en_servicio=en_servicio;
		this.reglamento=reglamento;
		this.morosos=morosos;
		
	}

	public int getId_instalacion() {
		return id_instalacion;
	}

	public void setId_instalacion(int id_instalacion) {
		this.id_instalacion = id_instalacion;
	}

	public int getId_comunidad() {
		return id_comunidad;
	}

	public void setId_comunidad(int id_comunidad) {
		this.id_comunidad = id_comunidad;
	}

	public int getCoste() {
		return coste;
	}

	public void setCoste(int coste) {
		this.coste = coste;
	}

	public int getEn_servicio() {
		return en_servicio;
	}

	public void setEn_servicio(int en_servicio) {
		this.en_servicio = en_servicio;
	}

	public int getMorosos() {
		return morosos;
	}

	public void setMorosos(int morosos) {
		this.morosos = morosos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getReglamento() {
		return reglamento;
	}

	public void setReglamento(String reglamento) {
		this.reglamento = reglamento;
	}

}
