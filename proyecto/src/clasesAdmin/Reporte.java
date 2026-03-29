package clasesAdmin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Reporte {
	
	String fecha;
	String marcador;
	HashMap<String, HashMap<String, Integer>> desempeños;
	String acontecimientos;
	
	public Reporte( String fecha, String marcador, HashMap<String, HashMap<String, Integer>> desempeños, String acontecimientos ) {
		
		this.fecha = fecha;
		this.marcador = marcador;
		this.desempeños = desempeños;
		this.acontecimientos = acontecimientos;
		
	}
	
	public HashMap<String, HashMap<String, Integer>> getDesempeños(){
		return desempeños;
	}
	
	
	public String getAcontecimientos() {
		return acontecimientos;
	}
	
	
	public void imprimir() {
		
		for (Map.Entry<String, HashMap<String, Integer>> entry1: desempeños.entrySet() ) {
			System.out.println(entry1.getKey());
			HashMap<String, Integer> desempeño = entry1.getValue();
			for (Map.Entry<String, Integer> entry2: desempeño.entrySet() ) {
				System.out.println( entry2.getKey() + String.valueOf(entry2.getValue()) );
			}
			System.out.println("");
		}
	}
	

	
}






