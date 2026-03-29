package clasesUsuario;

public class Jugador {
	
	public String equipoReal;
	public String posicion;
	public double precio;
	public String nombre;


	public Jugador (String nombre, String posición, String equipoReal, double precio) {
		this.equipoReal = equipoReal;
		this.posicion = posición;
		this.precio = precio;
		this.nombre = nombre;
		
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getPosición() {
		return posicion;
	}
	
	public double getPrecio() {
		return precio;
	}




	
	
	
	
}


