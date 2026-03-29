package clasesAplicación;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import clasesAdmin.Administrador;
import clasesAdmin.Reporte;
import clasesAdmin.Temporada;
import clasesUsuario.Jugador;
import clasesUsuario.EquipoFantasia;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AppAdmin {
	
	static SimpleDateFormat dateForm = new SimpleDateFormat("MM/dd/Y HH:mm");
	public static Administrador admin = new Administrador();
	public static Temporada temporadaSeleccionada;
	
	
	public static void setTemporadaSeleccionada(Temporada temp) {
		temporadaSeleccionada = temp;
	}
	
	
	
	
	
	public static String cargarEquiposTemporada(String nombre) throws IOException {
		
		String resultado = "correcto";
		
		if ( nombre.equals("") ) {
			return "nulo";
		}
		
		try {
			temporadaSeleccionada.getTienda().setEquiposTemporada(admin.cargarTienda(temporadaSeleccionada, nombre));
		} catch(Exception e) {
			resultado = "error";
		}
		return resultado;
		
	}
	
	
	
	
	
	public static String designarPresupuestoTemporada(String cantidad) throws IOException {
		
		String resultado = null;
		
		if ( cantidad.equals("") ) {
			return "nulo";
		}
		
		try {
			double presupuesto = Double.parseDouble(cantidad);
			temporadaSeleccionada.setPresupuesto(presupuesto);
			resultado = "correcto";
		} catch(Exception e) {
			resultado = "error";
		}
		
		return resultado;
	
	}
	
	
	
	
	
	public static String cargarReportePartido(String fecha) throws IOException {
		
		String resultado = "correcto";
		
		if ( fecha.equals("") ) {
			return "nulo";
		}
		
		try {
			Reporte rprt = admin.cargarReporte(temporadaSeleccionada, fecha);
			temporadaSeleccionada.addHistorico( "Reporte," + rprt.getAcontecimientos() );
			actualizarPuntos(rprt, temporadaSeleccionada);
			resultado = "correcto";
		} catch(Exception e) {
			resultado = "error";
		}
		return resultado;
		
	}

	
	
	
	
	public static void actualizarPuntos( Reporte reporte, Temporada temporada) {
		
		HashMap<String, HashMap<String, Integer>> desempeños = reporte.getDesempeños();
		
		HashMap<String, EquipoFantasia> equiposFantasia = temporada.getEquiposFantasia();
		for (Map.Entry<String, EquipoFantasia> entry1: equiposFantasia.entrySet() ) {
			
			System.out.println(entry1.getKey());
			
			EquipoFantasia equipoFantasia = entry1.getValue();
			
			HashMap<String, ArrayList<Jugador>> alineacion = equipoFantasia.getAlineacion();
			for (Map.Entry<String, ArrayList<Jugador>> entry2: alineacion.entrySet() ) {
				
				ArrayList<Jugador> jugadores = entry2.getValue();
				for (int i = 0; i < jugadores.size(); i++) {
					
					Jugador jug = jugadores.get(i);
					String nombreJugador = jug.getNombre();
					
					if ( desempeños.containsKey(nombreJugador) ) {
						
						System.out.println(nombreJugador);
						
						HashMap<String, Integer> desempeño = desempeños.get(nombreJugador);
						for (Map.Entry<String, EquipoFantasia> entry3: equiposFantasia.entrySet() ) {
							System.out.println(entry3.getKey()+entry3.getValue());
						}
						
						int puntosSumados = 0;
						
						if ( desempeño.get("minutos") == 60 ) {
							puntosSumados += 1;
						} else if ( desempeño.get("minutos") > 60 ) {
							puntosSumados += 2;
						}
					
						if ( desempeño.get("goles&penales") != 0 ) {
							int pnts = desempeño.get("goles&penales");
						
							if ( jug.getPosición() == "Delantero" ) {
								puntosSumados += ( pnts * 4 );
							} else if ( jug.getPosición() == "Centrocampista" ) {
								puntosSumados += ( pnts * 5 );
							} else {
								puntosSumados += ( pnts * 6 );
							}
						}
					
						if ( desempeño.get("asistencias") != 0 ) {
							int pnts = desempeño.get("asistencias");
							puntosSumados += ( pnts * 3 );
						}
					
						if ( desempeño.get("recibidos") == 0 ) {
							if ( jug.getPosición() == "Portero" || jug.getPosición() == "Defensa" ) {
								puntosSumados += 4;
							}
						}
					
						if ( desempeño.get("fallidos") != 0 ) {
							int pnts = desempeño.get("fallidos");
							puntosSumados -= ( pnts * 2 );
						}
					
						if ( desempeño.get("amarillas") != 0 ) {
							int pnts = desempeño.get("amarillas");
							puntosSumados -= pnts;
						}
					
						if ( desempeño.get("rojas") != 0 ) {
							int pnts = desempeño.get("rojas");
							puntosSumados -= ( pnts * 3 );
						}
					
						if ( desempeño.get("autogoles") != 0 ) {
							int pnts = desempeño.get("autogoles");
							puntosSumados -= ( pnts * 2 );
						}
						
						equipoFantasia.sumarPuntos(nombreJugador, puntosSumados);
						
					}
					
				}
			}
		}
		
		
	
	}
	
	
	
	
	
	
	
	
		
			
	
	
	

	
	
	
}



	
	
	