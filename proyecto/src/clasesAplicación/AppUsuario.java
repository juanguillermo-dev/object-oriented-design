package clasesAplicación;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.text.SimpleDateFormat;
import java.util.Date;

import clasesAdmin.Temporada;
import clasesUsuario.Usuario;
import clasesUsuario.EquipoFantasia;
import clasesUsuario.Jugador;

public class AppUsuario {
	
	
	static SimpleDateFormat dateForm = new SimpleDateFormat("MM/dd/Y HH:mm");
	
	private static Temporada temporadaSeleccionada = null;
	public static Usuario usuarioSeleccionado = null;
	
	
	public static void setTemporadaSeleccionada(Temporada temp) {
		temporadaSeleccionada = temp;
	}
	
	public static void setUsuarioSeleccionado(Usuario user) {
		usuarioSeleccionado = user;
	}
	

	
	
	public static String mostrarJugadores() {
		String texto = "";
		texto += "\n";
		texto += "Recuerde que estos son sus jugadores...";
		texto += "\n";
		texto += "\n";
		HashMap<String, ArrayList<Jugador>> jugadores = usuarioSeleccionado.getJugadores();
		for (Map.Entry<String,ArrayList<Jugador>> entry: jugadores.entrySet() ) {
			texto += entry.getKey() + "s: ";
			ArrayList<Jugador> posicion = entry.getValue();
			for (int i = 0; i < posicion.size(); i++) {
				texto += (posicion.get(i).getNombre());
			}
			texto += "\n";
		}
		texto += "\n";
		return texto;
	}

	
	

	
	public static void crearEquipoFantasia(Usuario User, Temporada temporada) {
		String nombre = Aplicación.input(("Ingrese el nombre que tendrá su equipo de fantasía:\n"));
		User.equipoFantasia = new EquipoFantasia(User.getNombreUsuario(), nombre, temporada);
		temporadaSeleccionada.addEquipoFantasia(nombre, User.equipoFantasia);
		System.out.println("Se ha creado su equipo fantasía de nombre " + nombre + ".");
		System.out.println("En este momento, su equipo no tiene jugadores, por lo tanto es inválido. Es bienvenido a editarlo.");
	}
	
	
	
	
	public static void editarEquipoFantasia(Usuario User) {
	
		int porteros = User.getJugadores().get("Portero").size();
		int defensas = User.getJugadores().get("Defensa").size();
		int centros = User.getJugadores().get("Centrocampista").size();
		int delanteros = User.getJugadores().get("Delantero").size();
		if ( porteros < 2 || defensas < 5 || centros < 5 || delanteros < 3 ) {
			System.out.println("Con los jugadores que tiene actualemte, no es posible formar un equipo valido.");
			
		} else {
			System.out.println("Recuerde que su equipo debe tener las siguientes características...");
			System.out.println("15 Jugadores:");
			System.out.println("Porteros: 2");
			System.out.println("Defensas: 5");
			System.out.println("Centrocampistas: 5");
			System.out.println("Delanteros: 3");
			System.out.println("");
			
			int CantJugEqu = 0;
			while( CantJugEqu < 15 ) {
				
				HashMap<String,ArrayList<Jugador>> jugadores = User.getJugadores();
				HashMap<String,ArrayList<Jugador>> equipo = User.getEquipoFantasia().getEquipo();
				ArrayList<Jugador> disponibles = new ArrayList<Jugador>();
				
				int porterosEqu = equipo.get("Portero").size();
				int defensasEqu = equipo.get("Defensa").size();
				int centrosEqu = equipo.get("Centrocampista").size();
				int delanterosEqu = equipo.get("Delantero").size();
				CantJugEqu = ( porterosEqu + defensasEqu + centrosEqu + delanterosEqu );
				
				System.out.println("En este momento, su equipo está estructurado de la siguiente manera...");
				System.out.println("");
				for (Map.Entry<String,ArrayList<Jugador>> entry: jugadores.entrySet() ) {
					System.out.println(entry.getKey() + "s: ");
					ArrayList<Jugador> jugadoresPosicion = entry.getValue();
					for (int i = 0; i < jugadoresPosicion.size(); i++) {
						if ( equipo.get(entry.getKey()).contains(jugadoresPosicion.get(i)) ) {
							System.out.println(jugadoresPosicion.get(i).getNombre());
						} else {
							disponibles.add(jugadoresPosicion.get(i));
						}
					}
					System.out.println("");
				}
				System.out.println("");
				System.out.println("Sus jugadores disponibles son...");
				for (int i = 0; i < disponibles.size(); i++) {
					System.out.println(disponibles.get(i).getNombre());
				}
			
				System.out.println("Su equipo cuenta con " + String.valueOf(CantJugEqu) + " jugadores. Hay que agregar " + String.valueOf(15-CantJugEqu) + " más." );
				System.out.println("");
				String slct = Aplicación.input(("Ingrese la acción que quiere realizar junto al jugador seleccionado en el siguiente formato (agregar ó remover),(nombre del jugador):\n"));
				String[] select = slct.split(",");
				boolean existe = false;
				
				switch (select[0]) {
				
					case "agregar":
						for (int i = 0; i < disponibles.size(); i++) {
							if ( disponibles.get(i).getNombre().equals(select[1]) ) {
								existe = true;
								Jugador jugador = disponibles.get(i);
								ArrayList<Jugador> EquPosJug = equipo.get(jugador.getPosición());
								
								if ( EquPosJug.contains(jugador) ) {
									System.out.println("El jugador que pretende agregar ya se encuentra dentro de su equipo.");
									
								} else {
									
									switch ( jugador.getPosición() ) {
									
										case "Portero":
											if ( porterosEqu < 2 ) {
												User.equipoFantasia.modEquipo("agregar", jugador);
												CantJugEqu++;
											} else {
												System.out.println("El equipo ha llegado a su límite de porteros. Intente con un jugador de diferente posición.");
											}
											break;
											
										case "Defensa":
											if ( defensasEqu < 5 ) {
												User.equipoFantasia.modEquipo("agregar", jugador);
												CantJugEqu++;
											} else {
												System.out.println("El equipo ha llegado a su límite de defensas. Intente con un jugador de diferente posición.");
											}
											break;
											
										case "Centrocampista":
											if ( centrosEqu < 5 ) {
												User.equipoFantasia.modEquipo("agregar", jugador);
												CantJugEqu++;
											} else {
												System.out.println("El equipo ha llegado a su límite de centrocampistas. Intente con un jugador de diferente posición.");
											}
											break;
											
										case "Delantero":
											if ( delanterosEqu < 3 ) {
												User.equipoFantasia.modEquipo("agregar", jugador);
												CantJugEqu++;
											} else {
												System.out.println("El equipo ha llegado a su límite de delanteros. Intente con un jugador de diferente posición.");
											}
											break;
											
									}	
								}
							}
						}
						if (existe == false) {
							System.out.println("El jugador que quiere agregar no se encuentra entre sus jugadores disponibles.");
						}
						break;
						
					case "remover":
						for (Map.Entry<String,ArrayList<Jugador>> entry: equipo.entrySet() ) {
							ArrayList<Jugador> jugadoresPosicion = entry.getValue();
							for (int i = 0; i < jugadoresPosicion.size(); i++) {
								if ( jugadoresPosicion.get(i).getNombre().equals(select[1]) ) {
									existe = true;
									Jugador jugador = jugadoresPosicion.get(i);
									User.equipoFantasia.modEquipo("remover", jugador);
									CantJugEqu--;
								}
							}
						}
						if (existe == false) {
							System.out.println("El jugador que quiere remover no se encuentra dentro de su equipo.");
						}
						break;
					
					default:
						System.out.println("La acción seleccionada no es válida. Intentelo de nuevo.");
						System.out.println("");
				}
			}
			User.equipoFantasia.equipoVal = true;
			System.out.println("Su equipo es válido. Es bienvenido a editar su alineación.");
		}
	}







	public static void editarEquipoAlineacion(Usuario User) {
		
		System.out.println("Recuerde que su alineación debe tener las siguientes características...");
		System.out.println("11 Jugadores:");
		System.out.println("Porteros: 1");
		System.out.println("Defensas: 4");
		System.out.println("Centrocampistas: 4");
		System.out.println("Delanteros: 2");
		System.out.println("De entre los cuales uno debe ser designado como capitán.");
		System.out.println("");

		int CantJugAl = 0;
		boolean selectCap = false;
		while( CantJugAl < 11 || selectCap == false) {
			
			HashMap<String,ArrayList<Jugador>> equipo = User.getEquipoFantasia().getEquipo();
			HashMap<String,ArrayList<Jugador>> alineacion = User.getEquipoFantasia().getAlineacion();
			ArrayList<Jugador> disponibles = new ArrayList<Jugador>();
				
			int porterosAl = alineacion.get("Portero").size();
			int defensasAl = alineacion.get("Defensa").size();
			int centrosAl = alineacion.get("Centrocampista").size();
			int delanterosAl = alineacion.get("Delantero").size();
			CantJugAl = ( porterosAl + defensasAl + centrosAl + delanterosAl );
			
			System.out.println("En este momento, su alineación está estructurada de la siguiente manera...");
			System.out.println("");
			for (Map.Entry<String,ArrayList<Jugador>> entry: equipo.entrySet() ) {
				System.out.println(entry.getKey() + "s: ");
				ArrayList<Jugador> jugadoresPosicion = entry.getValue();
				for (int i = 0; i < jugadoresPosicion.size(); i++) {
					if ( alineacion.get(entry.getKey()).contains(jugadoresPosicion.get(i)) ) {
						System.out.println(jugadoresPosicion.get(i).getNombre());
					} else {
						disponibles.add(jugadoresPosicion.get(i));
					}
				}
				System.out.println("");
			}
			System.out.println("");
			System.out.println("Sus jugadores disponibles son...");
			for (int i = 0; i < disponibles.size(); i++) {
				System.out.println(disponibles.get(i).getNombre());
			}
			System.out.println("Su alineación cuenta con " + String.valueOf(CantJugAl) + " jugadores. Hay que agregar " + String.valueOf(11-CantJugAl) + " más." );
			if (selectCap) {
				System.out.println("Capitán selecctionado: " + User.getEquipoFantasia().getCapitan() );
			} else {
				System.out.println("De momento, no se la seleccionado un capitán dentro de la alineación.");
			}
			
			System.out.println("");
			String slct = Aplicación.input(("Ingrese la acción que quiere realizar junto al jugador seleccionado en el siguiente formato (agregar ó remover ó capitán),(nombre del jugador):\n"));
			String[] select = slct.split(",");
			boolean existe = false;
			
			switch (select[0]) {
			
				case "agregar":
					for (int i = 0; i < disponibles.size(); i++) {
						if ( disponibles.get(i).getNombre().equals(select[1]) ) {
							existe = true;
							Jugador jugador = disponibles.get(i);
							ArrayList<Jugador> AlPosJug = alineacion.get(jugador.getPosición());
							if ( AlPosJug.contains(jugador) ) {
								System.out.println("El jugador que pretende agregar ya se encuentra dentro de su alineación.");
							} else {
								
								switch ( jugador.getPosición() ) {
								
								case "Portero":
									if ( porterosAl < 1 ) {
										User.equipoFantasia.modAlineacion("agregar", jugador);
										CantJugAl++;
									} else {
										System.out.println("La alineación ha llegado a su límite de porteros. Intente con un jugador de diferente posición.");
									}
									break;
									
								case "Defensa":
									if ( defensasAl < 4 ) {
										User.equipoFantasia.modAlineacion("agregar", jugador);
										CantJugAl++;
									} else {
										System.out.println("La alineación ha llegado a su límite de defensas. Intente con un jugador de diferente posición.");
									}
									break;
									
								case "Centrocampista":
									if ( centrosAl < 4 ) {
										User.equipoFantasia.modAlineacion("agregar", jugador);
										CantJugAl++;
									} else {
										System.out.println("La alineación ha llegado a su límite de centrocampistas. Intente con un jugador de diferente posición.");
									}
									break;
									
								case "Delantero":
									if ( delanterosAl < 2 ) {
										User.equipoFantasia.modAlineacion("agregar", jugador);
										CantJugAl++;
									} else {
										System.out.println("La alineación ha llegado a su límite de delanteros. Intente con un jugador de diferente posición.");
									}
									break;
									
							}
							
							}
						}
					}
					if (existe == false) {
						System.out.println("El jugador que quiere agregar no se encuentra entre sus jugadores disponibles.");
					}
					break;
					
				case "remover":
					for (Map.Entry<String,ArrayList<Jugador>> entry: alineacion.entrySet() ) {
						ArrayList<Jugador> jugadoresPosicion = entry.getValue();
						for (int i = 0; i < jugadoresPosicion.size(); i++) {
							if ( jugadoresPosicion.get(i).getNombre().equals(select[1]) ) {
								existe = true;
								Jugador jugador = jugadoresPosicion.get(i);
								User.equipoFantasia.modAlineacion("remover", jugador);
								CantJugAl--;
							}
						}
					}
					if (existe == false) {
						System.out.println("El jugador que quiere remover no se encuentra dentro de su alineación.");
					}
					break;
				
				case "capitán":
					for (Map.Entry<String,ArrayList<Jugador>> entry: alineacion.entrySet() ) {
						ArrayList<Jugador> jugadoresPosicion = entry.getValue();
						for (int i = 0; i < jugadoresPosicion.size(); i++) {
							if ( jugadoresPosicion.get(i).getNombre().equals(select[1]) ) {
								existe = true;
								User.equipoFantasia.capitan = jugadoresPosicion.get(i);
								selectCap = true;
								System.out.println("El jugador " + jugadoresPosicion.get(i).getNombre() + " es ahora el nuevo capitán de su equipo.");
							}
						}
					}
					if (existe == false) {
						System.out.println("El jugador que quiere designar como capitán no se encuentra dentro de su alineación.");
					}
					break;
					
				default:
					System.out.println("La acción seleccionada no es válida. Intentelo de nuevo.");
					System.out.println("");
			}
		}
		User.equipoFantasia.alineacionVal = true;
		System.out.println("Su alineación es válida.");
	
	
	}
	
	
	
	
	
	
	
	
	
	public static void verPuntos(Usuario User) {
	
		int PT = User.getEquipoFantasia().getPuntosTotales();
		HashMap<String, Integer> PxJ = User.getEquipoFantasia().getPuntosXJ();
		System.out.println("Los puntos totales de su equipo fantasía son: " + String.valueOf(PT) );
		for (Map.Entry<String, Integer> entry: PxJ.entrySet() ) {
			if ( entry.getValue() != 0 ) {
				System.out.println( entry.getKey() + ": " + entry.getValue() );
			}	
		}
	}
	
	
	
	public static void verEstadisticas() {
		
		ArrayList<String> ranking = new ArrayList<String>();
		HashMap<String, EquipoFantasia> equiposFantasia = temporadaSeleccionada.getEquiposFantasia();
		for (Map.Entry<String, EquipoFantasia> entry: equiposFantasia.entrySet() ) {
			String agregar = entry.getKey() + ": " + String.valueOf(entry.getValue().getPuntosTotales());
			if ( ranking.size() == 0 ) {
				ranking.add(agregar);
			} else {
				String[] info1 = agregar.split(": ");
				boolean falta = true;
				for (int i = 0; i < ranking.size(); i++) {
					String[] info2 = ranking.get(i).split(": ");
					if ( Integer.parseInt(info1[1]) > Integer.parseInt(info2[1]) ) {
						ranking.add(i, agregar);
						falta = false;
						break;
					}
				}
				if (falta) {
					ranking.add(agregar);
				}
			}
		}
		System.out.println("Ranking Equipos Fantasía:");
		for (int i = 0; i < ranking.size(); i++) {
			System.out.println( String.valueOf(i) + ". " + ranking.get(i) );
		}
	}
	 
	
	
	
	
	
	public static void menuUsuario(Usuario User) throws IOException {
		
		checkTemporada();
		
		Date thsDt = new Date();
		temporadaSeleccionada.addHistorico( User.getNombreUsuario() + "," + dateForm.format(thsDt) + " --> Accedió a su cuenta." );
		
	
		boolean iterar = true;
		while (iterar) {
			
			boolean validar = true;
			if ( User.equipoFantasia != null && ( User.equipoFantasia.equipoVal == false || User.equipoFantasia.alineacionVal == false ) ) {
				System.out.println("Recuerde que de momento, su equipo o alineación son inválidos");
				validar = false;
			}
			System.out.println("------------------------------");
			System.out.println("Menu de Usuario:");
			System.out.println("------------------------------");
			System.out.println("0: Volver atrás.");
			System.out.println("1: Crear equipo fantasía.");
			System.out.println("2: Acceder a la tienda.");
			System.out.println("3: Editar equipo fantasía.");
			System.out.println("4: Editar alineación del equipo.");
			System.out.println("5: Ver puntos de mi equipo.");
			System.out.println("6: Ver ranking de puntos de la temporada.");
			System.out.println("7: Recrear temporada hasta ahora.");
			System.out.println("");
			String opcion = Aplicación.input(("Ingrese una opción:\n"));
			
			
			switch (opcion) {
			
				case "0":
					if ( validar == false ) {
						System.out.println("No puede desempeñar esta acción porque su equipo o su alineación son invalidos. Es bienvenido a editarlos.");
					} else {
						Date thisDate = new Date();
						temporadaSeleccionada.addHistorico( User.getNombreUsuario() + "," + dateForm.format(thisDate) + " --> Salió de su cuenta." );
						System.out.println("Usted ha vuelto al inicio.");
						iterar = false;
					}
					break;
					
					
					
					
				case "1":
					if ( User.getEquipoFantasia() == null ) {
						crearEquipoFantasia(User, temporadaSeleccionada);
						Date thisDate = new Date();
						temporadaSeleccionada.addHistorico( User.getNombreUsuario() + "," + dateForm.format(thisDate) + " --> Creó su equipo fantasía." );
						System.out.println("");
					} else {
						System.out.println("Usted ya cuenta con un equipo fantasia para esta temporada. Es bienvenido a editarlo");
						System.out.println("");
					}
					
					break;
					
					
					
				case "2":
					if ( User.getEquipoFantasia() == null ) {
						System.out.println("Para poder acceder a la tienda, usted debe crear primero un equipo de fantasía.");
					} else {
						System.out.println("Usted ha accedido a la tienda.");
						Tienda.menuTienda(User, temporadaSeleccionada);
						System.out.println("");
					}
					break;
					
					
				
				case "3":
					if ( User.equipoFantasia != null ) {
						editarEquipoFantasia(User);
						Date thisDate = new Date();
						temporadaSeleccionada.addHistorico( User.getNombreUsuario() + "," + dateForm.format(thisDate) + " --> Editó los jugadores de su equipo fantasía." );
						System.out.println("");
					} else {
						System.out.println("De momento, usted no cuenta con un equipo fantasía. Cree uno para poderlo editar.");
						System.out.println("");
					}
					break;
				
				case "4":
					if ( User.equipoFantasia != null ) {
						editarEquipoAlineacion(User);
						Date thisDate = new Date();
						temporadaSeleccionada.addHistorico( User.getNombreUsuario() + "," + dateForm.format(thisDate) + " --> Editó la alineación de su equipo fantasía." );
						System.out.println("");
					} else {
						System.out.println("De momento, usted no cuenta con un equipo fantasía. Cree uno para poder editar su alineación.");
						System.out.println("");
					}
					break;
					
					
				case "5":
					if ( User.equipoFantasia != null ) {
						verPuntos(User);
						System.out.println("");
					} else {
						System.out.println("De momento, usted no cuenta con un equipo fantasía. Por lo tanto.");
						System.out.println("");
					}
					break;
					
					
				case "6":
					verEstadisticas();
					break;
					
				case "7":
					Historial.recrearTemporada( temporadaSeleccionada, User );
					break;
					
			
				default:
					System.out.print("La opción seleccionada no es valida. Intentelo de nuevo.");
					System.out.println("");
					System.out.println("");
			}
			
			
		}
	

}







}









	
	
	


