package clasesAplicación;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import clasesAdmin.Temporada;
import clasesUsuario.Usuario;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Aplicación {
	

	static SimpleDateFormat dateForm = new SimpleDateFormat("MM/dd/Y HH:mm");
	



	public static String checkCredenciales(String v1, String v2, String n, String p) {
		
		if ( n.equals("") || p.equals("") ) {
			return "nulo";
		}
		
		String ret = null;
		
		if ( v1 == "user" ) {
			
			HashMap<String, String> usuarios = Historial.getCuentasUser();
			
			if ( v2 == "crear" ) {
				
				if (usuarios.containsKey(n)) {
					ret = "enUso";
				} else {
					Usuario User = new Usuario(n);
					Historial.addUser(User ,n, p);
					AppUsuario.setUsuarioSeleccionado(User);
					ret = "correcto";
				}
				
			} else {
				
				if (usuarios.containsKey(n)) {
					if (usuarios.get(n).equals(p)) {
						HashMap<String,Usuario> usuariosObj = Historial.getUsuarios();
						AppUsuario.setUsuarioSeleccionado(usuariosObj.get(n));
						ret = "correcto";
					} else {
						ret = "passIncorrecto";
					}
				} else {
					ret = "nameIncorrecto";
				}
				
			} 
			
		} else {
			
			Historial.adminDePrueba();
			HashMap<String, String> administradores = Historial.getCuentasAdmin();
			if (administradores.containsKey(n)) {
				if (administradores.get(n).equals(p)) {
					ret = "correcto";
				} else {
					ret = "passIncorrecto";
				}
			} else {
				ret = "nameIncorrecto";
			}
			
		}
		
		return ret;
		
		
	}
	
	
	
	
	public static String checkTemporada(String v, String t) {
		
		HashMap<String, Temporada> temporadas = Historial.getHistorialTemporadas();
		
		String ret = null;
		
		if ( v == "admin" ) {
			if (temporadas.containsKey(t)) {
				AppAdmin.setTemporadaSeleccionada(temporadas.get(t));
				ret = "existe";
			} else {
				temporadas.put(t, new Temporada(t));
				AppAdmin.setTemporadaSeleccionada(temporadas.get(t));
				ret = "existe";
			}
			
		} else {
			if (temporadas.containsKey(t)) {
				AppUsuario.setTemporadaSeleccionada(temporadas.get(t));
				ret = "existe";
			} else {
				ret = "inexistente";
			}
		}
		
		return ret;
	}
	


	
		
}

	
	

	
	
	
	



	
	
	

