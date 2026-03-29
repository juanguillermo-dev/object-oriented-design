package clasesAdmin;

import java.io.BufferedReader;
//import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import clasesUsuario.Jugador;




//AAequiposReales
//06-26-2010 09-00



public class Administrador {
	
	
	
	
	public HashMap<String, ArrayList<Jugador>> cargarTienda(Temporada temporada, String fileName) throws IOException {
		
		HashMap<String, ArrayList<Jugador>> jugadoresTienda = new HashMap<String, ArrayList<Jugador>>();
		BufferedReader bufReader = new BufferedReader( new FileReader( "datosTemporadas/" + temporada.getName() + "/Equipos/" + fileName + ".txt" ));  
		String equipo = bufReader.readLine();
		System.out.println("Los equipos cargados a la tienda fueron... ");
		
		while (equipo != null) {	
			ArrayList<Jugador> listaJugadores = new ArrayList<Jugador>();
			BufferedReader lectorEquipo = new BufferedReader( new FileReader( "datosTemporadas/mundial2010/Equipos/" + equipo + ".txt" ));
			String jugador = lectorEquipo.readLine();
			jugador = lectorEquipo.readLine();
			
			while (jugador != null) {	
				String[] jugadorInfo = jugador.split("/");
				String nombre = jugadorInfo[1];
				String posición = jugadorInfo[2];
				double precio = Integer.parseInt(jugadorInfo[3]);
				Jugador jug = new Jugador(nombre, posición, equipo, precio);
				listaJugadores.add(jug);
				jugador = lectorEquipo.readLine();
			}
			lectorEquipo.close();
			jugadoresTienda.put(equipo, listaJugadores);
			System.out.println(equipo);
			equipo = bufReader.readLine(); 
		}
		bufReader.close();
		return jugadoresTienda;
		
	}
	
	
	
	
	
	public Reporte cargarReporte( Temporada tempObj, String fch ) throws IOException {
		
		String nombreTemporada = tempObj.getName();
		BufferedReader bufReader = new BufferedReader( new FileReader( "datosTemporadas/" + nombreTemporada + "/Fechas/" + fch + ".txt" ));
		String linea = bufReader.readLine();
		
		HashMap<String, HashMap<String, Integer>> desempeños = new HashMap<String, HashMap<String, Integer>>();
		while (true) {
			if ( linea.equals("*") ) {
				break;
			} else {
				HashMap<String, Integer> desempeño = new HashMap<String, Integer>();
				desempeño.put("minutos", 0);
				desempeño.put("goles&penales", 0);
				desempeño.put("autogoles", 0);
				desempeño.put("asistencias", 0);
				desempeño.put("recibidos", 0);
				desempeño.put("fallidos", 0);
				desempeño.put("amarillas", 0);
				desempeño.put("rojas", 0);
				desempeños.put(linea, desempeño);
				linea = bufReader.readLine(); 
			}
		}
		
		
		
		String acontecimientos = "\n";
		linea = bufReader.readLine();
		acontecimientos += ( linea + "\n" );
		
		String[] atributos = linea.split("-");
		String fecha = atributos[0];
		String mntsStr = atributos[1];
		int mntsInt = Integer.parseInt(mntsStr);
		for (Map.Entry<String, HashMap<String, Integer>> entry: desempeños.entrySet() ) {
			String jugador = entry.getKey();
			HashMap<String, Integer> des = desempeños.get(jugador);
			des.replace( "minutos", mntsInt );
			desempeños.replace( jugador, des );
			entry.getValue().replace("minutos", mntsInt);
		}
		
		HashMap<String, ArrayList<Jugador>> equipos = tempObj.tienda.getEquiposTemporada();
		String marcador = atributos[2];
		String[] eqs = marcador.split(" vs ");
		String eq1 = eqs[0].substring( 2, eqs[0].length() - 2 );
		String eq2 = eqs[1].substring( 0, eqs[1].length() - 4 );
		ArrayList<Jugador> equipo1 = equipos.get(eq1);
		ArrayList<Jugador> equipo2 = equipos.get(eq2);
		
		linea = bufReader.readLine();
		linea = bufReader.readLine();
		
		while (linea != null) {
			
			String[] acontecimiento = linea.split(":");
			
			if ( ( acontecimiento[1].equals("Gol") ) || ( acontecimiento[1].equals("Penal") ) ) {
				
				HashMap<String, Integer> des1 = desempeños.get(acontecimiento[2]);
				des1.replace( "goles&penales", ( des1.get("goles&penales") + 1 ) );
				desempeños.replace(acontecimiento[2], des1);
				
				if ( acontecimiento[3] != eq1 ) {
					int x = 0;
					while ( x < equipo1.size() ) {
						Jugador jug = equipo1.get(x);
						String nom = jug.getNombre();
						String pos = jug.getPosición();
						if ( ( pos == "portero" ) || ( pos == "defensa" ) ) {
							HashMap<String, Integer> des2 = desempeños.get(nom);
							des2.replace( "recibidos", ( des2.get("recibidos") + 1 ) );
							desempeños.replace(nom, des2);
						}
						x++;
					}
				} else {
					int x = 0;
					while ( x < equipo2.size() ) {
						Jugador jug = equipo2.get(x);
						String nom = jug.getNombre();
						String pos = jug.getPosición();
						if ( ( pos == "portero" ) || ( pos == "defensa" ) ) {
							HashMap<String, Integer> des2 = desempeños.get(nom);
							des2.replace( "recibidos", ( des2.get("recibidos") + 1 ) );
							desempeños.replace(nom, des2);
						}
						x++;
					}
				}
				
			} else if ( acontecimiento[1].equals("Fallido") ) {
				HashMap<String, Integer> des = desempeños.get(acontecimiento[2]);
				des.replace( "fallidos", ( des.get("fallidos") + 1 ) );
				desempeños.replace(acontecimiento[2], des);
				
			} else if ( acontecimiento[1].equals("Autogol") ) {
				HashMap<String, Integer> des = desempeños.get(acontecimiento[2]);
				des.replace( "autogoles", ( des.get("autogoles") + 1 ) );
				desempeños.replace(acontecimiento[2], des);
				
			} else if ( acontecimiento[1].equals("Amarilla") ) {
				HashMap<String, Integer> des = desempeños.get(acontecimiento[2]);
				des.replace( "amarillas", ( des.get("amarillas") + 1 ) );
				desempeños.replace(acontecimiento[2], des);
				
			} else if ( acontecimiento[1].equals("Roja") ) {
				HashMap<String, Integer> des = desempeños.get(acontecimiento[2]);
				des.replace( "rojas", ( des.get("rojas") + 1 ) );
				desempeños.replace(acontecimiento[2], des);
				
			} else if ( acontecimiento[1].equals("Asistencia") ) {
				HashMap<String, Integer> des = desempeños.get(acontecimiento[2]);
				des.replace( "asistencias", ( des.get("asistencias") + 1 ) );
				desempeños.replace(acontecimiento[2], des);
				
			} else if ( acontecimiento[1].equals("Sustitución") ) {
				
				String min = acontecimiento[0];
				int minuto = Integer.parseInt(min);
				
				String[] jgdrs = acontecimiento[2].split(",");
				HashMap<String, Integer> des1 = desempeños.get(jgdrs[0]);
				HashMap<String, Integer> des2 = desempeños.get(jgdrs[1]);
				
				des1.replace( "minutos", ( des1.get("minutos") - minuto ) );
				des2.replace( "minutos", ( des2.get("minutos") - ( mntsInt - minuto ) ) );
				
				desempeños.replace(acontecimiento[2], des1);
				desempeños.replace(acontecimiento[3], des2);
				
			} else {
				System.out.println("Esta fecha contiene acontecimientos invalidos.");
			}
			
			linea = bufReader.readLine();

		}
		
		Reporte rep = new Reporte( fecha, marcador, desempeños, acontecimientos );
		bufReader.close();
		return rep;
		

	}






	

}
	
	
	
	
	
	
	