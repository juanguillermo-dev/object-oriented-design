package clasesAplicación;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import clasesAdmin.Temporada;
import clasesUsuario.EquipoFantasia;
import clasesUsuario.Jugador;
import clasesUsuario.Usuario;

public class Historial {
	
	
	private static HashMap<String, Usuario> usuarios = new HashMap<String, Usuario>();
	private static HashMap<String, String> registroCuentasUser = new HashMap<String, String>();
	private static HashMap<String, String> registroCuentasAdmin = new HashMap<String, String>();
	private static HashMap<String, Temporada> historialTemporadas = new HashMap<String, Temporada>();
	
	
	
	public static void adminDePrueba() {
		registroCuentasAdmin.put("Juan", "qwe123");
	}
	

	
	
	public static void addUser(Usuario usuario, String nombreUsuario, String contraseña) {
		usuarios.put(nombreUsuario, usuario);
		registroCuentasUser.put(nombreUsuario, contraseña);
	}
	
	public static void addTemporada(Temporada temporada) {
		historialTemporadas.put(temporada.getName(), temporada);
	}
	
	
	public static HashMap<String, Usuario> getUsuarios() {
		return usuarios;
	}
	
	public static HashMap<String, String> getCuentasUser() {
		return registroCuentasUser;
	}
	
	
	
	
	public static HashMap<String, String> getCuentasAdmin() {
		return registroCuentasAdmin;
	}
	
	public static HashMap<String, Temporada> getHistorialTemporadas() {
		return historialTemporadas; 
	}
	
	public static void recrearTemporada(Temporada temporada, Usuario usuario) {
		
		ArrayList<String> historico = historialTemporadas.get(temporada.getName()).getHistorico();
		for (int i = 0; i < historico.size(); i++) {
			String[] suceso = historico.get(i).split(",");
			if ( historico.get(i).contains("Reporte") || historico.get(i).contains(usuario.getNombreUsuario()) ) {
				System.out.println(suceso[1]);
			}
		}
	
		
	}
	
	
	
	
	
	
	

}
