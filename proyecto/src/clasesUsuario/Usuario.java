package clasesUsuario;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import clasesAdmin.Temporada;



public class Usuario {

	public String nombreUsuario;
	public float presupuesto;
	public HashMap<String,ArrayList<Jugador>> jugadores;
	public EquipoFantasia equipoFantasia;
	
	
	
	public Usuario (String nombreUsuario) {	
		this.nombreUsuario = nombreUsuario;
		jugadores = new HashMap<String,ArrayList<Jugador>>();
		jugadores.put("Portero", new ArrayList<Jugador>());
		jugadores.put("Defensa", new ArrayList<Jugador>());
		jugadores.put("Centrocampista", new ArrayList<Jugador>());
		jugadores.put("Delantero", new ArrayList<Jugador>());
		equipoFantasia = null;
	}
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	
	
	
	
	
	
	
	public HashMap<String,ArrayList<Jugador>> getJugadores(){
		return jugadores;
	}
	
	
	
	
	
	public void modJugadores(String accion, Jugador jugador){
		if (accion == "compra") {
			jugadores.get(jugador.getPosición()).add(jugador);
			equipoFantasia.puntosXjugador.put(jugador.getNombre(), 0);
			System.out.println("El jugador " + jugador.getNombre() + " se ha agregado correctamente a sus jugadores.");
			equipoFantasia.modPresupuesto("compra", jugador.getPrecio());
		} else {
			jugadores.get(jugador.getPosición()).remove(jugador);
			System.out.println("El jugador " + jugador.getNombre() + " se ha removido correctamente de su jugadores.");
			if (equipoFantasia.getEquipo().get(jugador.getPosición()).contains(jugador)) {
				equipoFantasia.getEquipo().get(jugador.getPosición()).remove(jugador);
				equipoFantasia.equipoVal = false;
				System.out.println("Usted ha removido un jugador de su equipo. Por lo tanto, este es inválido. Es bienvenido a editarlo.");
			}
			if (equipoFantasia.getAlineacion().get(jugador.getPosición()).contains(jugador)) {
				equipoFantasia.getAlineacion().get(jugador.getPosición()).remove(jugador);
				equipoFantasia.alineacionVal = false;
				System.out.println("Usted ha retirado un jugador de su alineación. Por lo tanto, esta es inválida. Es bienvenido a editarla.");
				if ( equipoFantasia.capitan == jugador ) {
					equipoFantasia.capitan = null;
					System.out.println("Usted ha retirado al capitán de su alineación. Por lo tanto, esta es inválida. Es bienvenido a editarla.");	
				}
			}
			equipoFantasia.modPresupuesto("venta", jugador.getPrecio());
		}
	}
	
	
	
	
	
	
	
	public EquipoFantasia getEquipoFantasia() {
		return equipoFantasia;
	}
	

}
