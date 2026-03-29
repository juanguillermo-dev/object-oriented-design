package clasesAdmin;

import java.util.ArrayList;
import java.util.HashMap;

import clasesAplicación.Tienda;
import clasesUsuario.EquipoFantasia;
import clasesUsuario.Jugador;

public class Temporada {
	
	public String nombre;
	public double presupuesto;
	public Tienda tienda;
	public HashMap<String, EquipoFantasia> equiposFantasia;
	public ArrayList<String> historico;
	
	
	public Temporada(String nombre) {
		this.nombre = nombre;
		presupuesto = 0;
		tienda = new Tienda();
		equiposFantasia = new HashMap<String, EquipoFantasia>();
		historico = new ArrayList<String>();
	}
	
	

	
	public String getName() {
		return nombre;
	}
	
	public void setPresupuesto(double pres) {
		presupuesto = pres;
	}
	
	public double getPresupuesto() {
		return presupuesto;
	}
	
	public Tienda getTienda() {
		return tienda;
	}
	
	
	public void addEquipoFantasia( String nombreUsuario, EquipoFantasia qpFnts ){
		equiposFantasia.put(nombreUsuario, qpFnts);
	}
	
	public HashMap<String, EquipoFantasia> getEquiposFantasia(){
		return equiposFantasia;
	}
	
	
	public void addHistorico(String suceso) {
		historico.add(suceso);
	}
	
	public ArrayList<String> getHistorico() {
		return historico;
	}
	
	
	
	
	
	
}

