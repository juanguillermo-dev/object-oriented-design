package interfazGráficaAp;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import clasesAplicación.Aplicación;


public class ntrfzAppAdministrador {
	
	
	public static Boolean equiposCargados = false;
	
	
	public static void main(String[] args) {
		displayMenuAdmin();
	}
	
	
	
	public static void displayMenuAdmin() {
		
		JFrame frameMenuAd = new JFrame();
		frameMenuAd.setTitle("Menú Administrador");
		frameMenuAd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameMenuAd.setResizable(false);
		frameMenuAd.setSize(800,600);
		frameMenuAd.setLocationRelativeTo(null);
		frameMenuAd.setLayout(null);
		
		JLabel mensaje = new JLabel("Seleccione una opción.");
		mensaje.setBounds(300,380,300,40);
		
		JButton button1 = new JButton("Cargar información de equipos.");
		button1.setBounds(250,100,300,50);
		button1.addActionListener( e -> {
			frameMenuAd.dispose();
			cargarInfoTemporada("equipos");
		});
		JButton button2 = new JButton("Designar presupuesto de equipos.");
		button2.setBounds(250,150,300,50);
		button2.addActionListener( e -> {
			frameMenuAd.dispose();
			cargarInfoTemporada("presupuesto");
		});
		JButton button3 = new JButton("Cargar reporte de partido.");
		button3.setBounds(250,200,300,50);
		button3.addActionListener( e -> {
			frameMenuAd.dispose();
			cargarInfoTemporada("reporte");
		});
		JButton button4 = new JButton("Cargar podio de la temporada.");
		button4.setBounds(250,250,300,50);
		button4.addActionListener( e -> {
			frameMenuAd.dispose();
			cargarInfoTemporada("podio");
		});
		JButton volver = new JButton("Volver");
		volver.setBounds(250,300,300,50);
		volver.addActionListener( e -> {
			frameMenuAd.dispose();
			ntrfzAppGeneral.main(null);
		});
	
		frameMenuAd.add(button1);
		frameMenuAd.add(button2);
		frameMenuAd.add(button3);
		frameMenuAd.add(button4);
		frameMenuAd.add(volver);
		frameMenuAd.add(mensaje);
		frameMenuAd.setVisible(true);


	}
	
	
	
	
	
	
	@SuppressWarnings("unused")
	public static void cargarInfoTemporada(String v) {
		
		JFrame frameCargar = new JFrame();
		frameCargar.setTitle("Cargar Información Temporada");
		frameCargar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameCargar.setResizable(false);
		frameCargar.setSize(800,600);
		frameCargar.setLocationRelativeTo(null);
		frameCargar.setLayout(null);
		
		JLabel label = new JLabel();
		label.setBounds(200,200,100,40);
		
		JTextField input = new JTextField();
		input.setBounds(300,200,300,40);
		
		JLabel mensaje = new JLabel();
		mensaje.setBounds(200,380,500,50);
		
		if ( v == "presupuesto" ) {
			label.setText("Presupuesto: ");
			mensaje.setText("Ingrese el presupuesto que desee establecer.");
		} else if ( v == "reporte" ) {
			label.setText("Fecha: ");
			mensaje.setText("Ingrese la fecha que desea cargar con el siguente formato (mes-día-año hora-minuto).");
		} else {
			label.setText("Archivo: ");
			if ( v == "equipos" ) {
				mensaje.setText("Ingrese el nombre del archivo con los nombres de los equipos de la temporada.");
			} else {
				mensaje.setText("Ingrese el nombre del archivo con el podio de la temporada.");
			}
		}
		
		JButton cargar = new JButton("Cargar");
		cargar.setBounds(300,300,100,40);
		cargar.addActionListener( e -> {
			
			String suceso = null;
			
			if ( v == "equipos" ) {
				if ( equiposCargados == true ) {
					mensaje.setText("Los equipos de la temporada ya fueron cargados.");
				} else {
					try {
						suceso = controlador.cargarEquiposTemporada(input.getText());
						if ( suceso == "nulo" ) {
							mensaje.setText("Recuerde que la casilla no puede estar vacía.");
						} else if ( suceso == "error" ) {
							mensaje.setText("El nombre del archivo no existe en el dispositivo.");
						} else {
							mensaje.setText("Los equipos de la temporada fueron cargados correctamente.");
							equiposCargados = true;
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				
				
			} else if ( v == "presupuesto" ) {
				try {
					suceso = controlador.designarPresupuestoTemporada(input.getText());
					if ( suceso == "nulo" ) {
						mensaje.setText("Recuerde que la casilla no puede estar vacía.");
					} else if ( suceso == "error" ) {
						mensaje.setText("Lo ingresado no tiene un valor numérico.");
					} else {
						mensaje.setText("El presupuesto fue asignado correctamente.");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			} else if ( v == "reporte" ) {
				if ( equiposCargados == false ) {
					mensaje.setText("Recuerde que no puede cargar fechas sin antes cargar los equipos.");
				} else {
					try {
						suceso = controlador.cargarReportePartido(input.getText());
						if ( suceso == "nulo" ) {
							mensaje.setText("Recuerde que la casilla no puede estar vacía.");
						} else if ( suceso == "error" ) {
							mensaje.setText("La fecha ingresada no existe en el dispositivo.");
						} else {
							mensaje.setText("El reporte de la fecha fue cargado correctamente.");
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			} else {
				// Aquí va codigo que aun no he programado.
			}
			
	
			
		
		});
		
		
		JButton volver = new JButton("Volver");
		volver.setBounds(450,300,100,40);
		volver.addActionListener( e -> {
			frameCargar.dispose();
			main(null);
		});
		
		
		
		frameCargar.add(label);
		frameCargar.add(input);
		frameCargar.add(cargar);
		frameCargar.add(volver);
		frameCargar.add(mensaje);
		frameCargar.setVisible(true);


	}
	
	
		

	


}