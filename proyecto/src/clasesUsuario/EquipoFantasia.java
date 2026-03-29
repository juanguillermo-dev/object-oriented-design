package clasesUsuario;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import clasesAdmin.Temporada;

public class EquipoFantasia {
	
	public String nombrePropietario;
	public String nombreEquipo;
	public Temporada temporada;
	private double presupuesto;
	public boolean equipoVal;
	public boolean alineacionVal;
	public static HashMap<String,ArrayList<Jugador>> equipo;
	public static HashMap<String,ArrayList<Jugador>> alineacion;
	HashMap<String, Integer> puntosXjugador;
	public int puntosTotales;
	public Jugador capitan;
	

	
	
	public EquipoFantasia (String nombreP, String nombreE, Temporada temp) {
		
		nombrePropietario = nombreP;
		nombreEquipo = nombreE;
		temporada = temp;
		presupuesto = temp.getPresupuesto();
		equipoVal = false;
		alineacionVal = false;
		equipo = new HashMap<String,ArrayList<Jugador>>();
		equipo.put("Portero", new ArrayList<Jugador>());
		equipo.put("Defensa", new ArrayList<Jugador>());
		equipo.put("Centrocampista", new ArrayList<Jugador>());
		equipo.put("Delantero", new ArrayList<Jugador>());
		alineacion = new HashMap<String,ArrayList<Jugador>>();
		alineacion.put("Portero", new ArrayList<Jugador>());
		alineacion.put("Defensa", new ArrayList<Jugador>());
		alineacion.put("Centrocampista", new ArrayList<Jugador>());
		alineacion.put("Delantero", new ArrayList<Jugador>());
		puntosXjugador = new HashMap<String, Integer>();
		puntosTotales = 0;
		capitan = null;	
	}
	
	public String getPropietario() {
		return nombrePropietario;
	}
	
	
	public String getCapitan() {
		Jugador capitan = this.capitan;
		String nombreCapitan = capitan.nombre;
		return nombreCapitan;
	}
	
	
	public double getPresupuesto() {
		return presupuesto;
	}
	
	public void modPresupuesto(String CoV, double precio) {
		if (CoV == "compra") {
			presupuesto -= precio;
		} else {
			presupuesto += (precio*0.97);
		}
	}
	
	
	public void modEquipo(String acto, Jugador jugador) {
		ArrayList<Jugador> listaE = equipo.get(jugador.getPosición());
		if (acto == "agregar") {
			listaE.add(jugador);
			equipo.replace(jugador.getPosición(), listaE);
			System.out.println("El jugador " + jugador.getNombre() + " ha sido agregado correctamente a su equipo.");
		} else {
			listaE.remove(jugador);
			equipo.replace(jugador.getPosición(), listaE);
			ArrayList<Jugador> listaA = equipo.get(jugador.getPosición());
			System.out.println("El jugador " + jugador.getNombre() + " ha sido removido de su equipo. Por lo tanto este es inválido. Es bienvenido a editarlo.");
			if (listaA.contains(jugador)) {
				modAlineacion("remover", jugador);
				System.out.println("El jugador " + jugador.getNombre() + " ha sido removido de su alineación. Por lo tanto esta es inválida. Es bienvenido a editarla.");
			}
		}
	}
	
	
	
	
	public void modAlineacion(String acto, Jugador jugador) {
		ArrayList<Jugador> lista = alineacion.get(jugador.getPosición());
		if (acto == "agregar") {
			lista.add(jugador);
			alineacion.replace(jugador.getPosición(), lista);
			System.out.println("El jugador " + jugador.getNombre() + " se ha agregado correctamente a su alineación.");
		} else {
			lista.remove(jugador);
			alineacion.replace(jugador.getPosición(), lista);
			System.out.println("El jugador " + jugador.getNombre() + " se ha removido correctamente de su alineación.");
		}
	}
	
	
	
	
	public HashMap<String,ArrayList<Jugador>> getEquipo() {
		return equipo;
	}	
	
	public HashMap<String,ArrayList<Jugador>> getAlineacion() {
		return alineacion;
	}
	

	
	public void sumarPuntos(String jugador, int sumados) {
		puntosXjugador.replace( jugador, ( puntosXjugador.get(jugador) + sumados ) );
		puntosTotales += sumados;
	}
	
	public HashMap<String, Integer> getPuntosXJ(){
		return puntosXjugador;
	}
	
	public int getPuntosTotales(){
		return puntosTotales;
	}
	
	
}	