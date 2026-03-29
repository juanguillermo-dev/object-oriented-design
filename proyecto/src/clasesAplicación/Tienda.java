package clasesAplicación;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import clasesAdmin.Temporada;
import clasesUsuario.Usuario;
import clasesUsuario.Jugador;


public class Tienda {
	
	private static Temporada temporadaSeleccionada = null;
	static SimpleDateFormat dateForm = new SimpleDateFormat("MM/dd/Y HH:mm");
	private static HashMap<String, ArrayList<Jugador>> equiposTemporada = null;
	private static ArrayList<Jugador> jugadoresTemporada = null;

	
	
	public HashMap<String, ArrayList<Jugador>> getEquiposTemporada(){
		return equiposTemporada;
	}
	
	public ArrayList<Jugador> getJugadoresTemporada(){
		return jugadoresTemporada;
	}
	
	
	
	public void setEquiposTemporada(HashMap<String, ArrayList<Jugador>> qpsTmprd) {
		equiposTemporada = qpsTmprd;
		jugadoresTemporada = new ArrayList<Jugador>();
		for (Map.Entry<String, ArrayList<Jugador>> entry: qpsTmprd.entrySet() ) {
			ArrayList<Jugador> jugadores = entry.getValue();
			for (int i = 0; i < jugadores.size(); i++) {
				jugadoresTemporada.add(jugadores.get(i));
			}
		}
	}
	
	
	

	
	
	public static void mostrarEquipos() {
		String texto = "";
		texto += "\n";
		try {
			texto += "Equipos de la temporada: ";
			texto += "\n";
			texto += "\n";
			for (Map.Entry<String, ArrayList<Jugador>> entry: equiposTemporada.entrySet() ) {
				texto += entry.getKey();
				texto += "\n";
				ArrayList<Jugador> jugadores = entry.getValue();
				for (int i = 0; i < jugadores.size(); i++) {
					texto += ("Nombre: " + jugadores.get(i).getNombre() ); 
					texto += ("Posición: " + jugadores.get(i).getPosición() ); 
					texto += ("Precio: " + jugadores.get(i).getPrecio() ); 
					texto += "\n";
				}
				texto += "\n";
				texto += "\n";
			}
		} catch (NumberFormatException e) {
			texto = "error";
		}	
	}
	
	
	public static void jugadoresPorEquipo() {
		
		try {
			while(true) {
				String equipo = Aplicación.input(("Ingrese el nombre de un equipo:\n"));
				if ( equiposTemporada.containsKey(equipo) == true) {
					ArrayList<Jugador> jugadores = equiposTemporada.get(equipo);
					for (int i = 0; i < jugadores.size(); i++) {
						System.out.println(""); 
						System.out.println("Nombre: " + jugadores.get(i).getNombre() ); 
						System.out.println("Posición: " + jugadores.get(i).getPosición() ); 
						System.out.println("Precio: " + jugadores.get(i).getPrecio() ); 
					}
					break;
				} else {
					System.out.println("El equipo ingresado no hace parte de esta temporada. Intentelo de nuevo.");
				}
			}
			System.out.println("");
			System.out.println("");
		} catch (NumberFormatException e) {
			System.out.println("\nParece que de momento no se han cargado equipos a la temporada.");
		}
		
	}
	
	
	public static void comprarJugador(Usuario User) {
	
		try {	
			boolean iterar = true;
			while(iterar) {
				String nombreJugador = Aplicación.input(("Ingrese el nombre del jugador que quiere comprar:\n"));
				boolean disponible = false;
				for (int i = 0; i < jugadoresTemporada.size(); i++) {
					if ( jugadoresTemporada.get(i).getNombre().equals(nombreJugador) ) {
						disponible = true;
						Jugador jugador = jugadoresTemporada.get(i);
						if ( User.getJugadores().get(jugador.getPosición()).contains(jugador) ) {
							System.out.println("El jugador ingresado ya hace parte de sus jugadores.");
						} else {
							if ( User.getEquipoFantasia().getPresupuesto() < jugador.getPrecio() ) {
								System.out.println("El presupuesto de su equipo no es suficiente para adquirir este jugador.");
							} else {
								User.modJugadores("compra", jugador);
								Date thisDate = new Date();
								temporadaSeleccionada.addHistorico( User.getNombreUsuario() + "," + dateForm.format(thisDate) + " --> Compró al jugador " + jugador.getNombre() + "." );
								iterar = false;
							}
						}
					}
				}
				if ( disponible == false ) {
					System.out.println("El jugador ingresado no hace parte de los jugadores de la temporada.");
				}
			}
		} catch (NumberFormatException e) {
			System.out.println("\nParece que de momento no existen jugadores en la temporada o en su cuenta.");
		}
	}

	
	
	
	public static void venderJugador(Usuario User) {
		
		try {
			boolean iterar = true;
			while(iterar) {
				String nombre = Aplicación.input(("Ingrese el nombre del jugador que quiere vender (tenga en cuenta que recuperará el 97% del valor del jugador):\n"));
				boolean disponible = false;
				HashMap<String, ArrayList<Jugador>> jugadores = User.getJugadores();
				for (Map.Entry<String, ArrayList<Jugador>> entry: jugadores.entrySet() ) {
					ArrayList<Jugador> posicion = entry.getValue();
					for (int a = 0; a < posicion.size(); a++) {
						if(posicion.get(a).getNombre().equals(nombre)) {
							disponible = true;
							Jugador jugador = posicion.get(a);
							User.modJugadores("venta", jugador);
							Date thisDate = new Date();
							temporadaSeleccionada.addHistorico( User.getNombreUsuario() + "," + dateForm.format(thisDate) + " --> Vendió al jugador " + jugador.getNombre() + "." );
							iterar = false;
							break;
						}
					}
				}
				if(disponible == false) {
					System.out.println("El jugador que pretende vender no se encuentra entre sus jugadores.");
				}
			}
		} catch (NumberFormatException e) {
			System.out.println("\nParece que de momento no existen jugadores en la temporada o en su cuenta.");
		}
	}
	
	
	

	
	public static void menuTienda(Usuario User, Temporada temp) throws IOException {
		
		temporadaSeleccionada = temp;
		
		boolean iterar = true;
		
		while (iterar) {
			mostrarEstado(User);
			System.out.println("");
			System.out.println("------------------------------");
			System.out.println("Menu de Tienda:");
			System.out.println("------------------------------");
			System.out.println("0: Volver atrás.");
			System.out.println("1: Mostrar equipos de la temporada.");
			System.out.println("2: Mostrar jugadores por equipo.");
			System.out.println("3: Comprar jugador.");
			System.out.println("4: Vender jugador.");
			System.out.println("");
			String opcion = Aplicación.input(("Ingrese una opción:\n"));
			
			switch (opcion) {
			
				case "0":
					System.out.println("Usted ha vuelto a la aplicación de usuario.");
					iterar = false;
					break;
					
				case "1":
					mostrarEquipos();
					break;
					
				case "2":
					jugadoresPorEquipo();
					break;
					
				case "3":
					comprarJugador(User);
					break;
					
				case "4":
					venderJugador(User);
					break;
					
				default:
					System.out.print("La opción seleccionada no es valida. Intentelo de nuevo.");
					System.out.println("");
					System.out.println("");
			}
		}
	}

}

