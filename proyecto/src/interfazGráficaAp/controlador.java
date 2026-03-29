package interfazGráficaAp;

import java.io.IOException;

import clasesAplicación.Aplicación;
import clasesAplicación.AppAdmin;
import clasesAplicación.AppUsuario;
import clasesAplicación.Tienda;

public class controlador {
	
	
	
	public static String  checkCredenciales(String v1, String v2, String n, String p) throws IOException {
		return Aplicación.checkCredenciales(v1, v2, n, p);
	}
	
	public static String  checkTemporada(String v, String t) throws IOException {
		return Aplicación.checkTemporada(v, t);
	}
	
	
	
	
	
	
	
	public static String  cargarEquiposTemporada(String nombre) throws IOException {
		return AppAdmin.cargarEquiposTemporada(nombre);
	}
	
	public static String  designarPresupuestoTemporada(String cantidad) throws IOException {
		return AppAdmin.designarPresupuestoTemporada(cantidad);
	}
	
	public static String  cargarReportePartido(String fecha) throws IOException {
		return AppAdmin.cargarReportePartido(fecha);
	}
	
	
	
	
	
	
	public static String  getJugadoresUsuario() throws IOException {
		return AppUsuario.mostrarJugadores();
	}
	
	
	
	
	
	
	
	
}