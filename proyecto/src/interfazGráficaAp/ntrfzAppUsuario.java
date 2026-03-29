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


public class ntrfzAppUsuario {
	
	
	public static void main(String[] args) {
		displayMenuUser();
	}
	
	
	
	public static void displayMenuUser() {
		
		JFrame frameMenuUs = new JFrame();
		frameMenuUs.setTitle("Menú Usuario");
		frameMenuUs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameMenuUs.setResizable(false);
		frameMenuUs.setSize(800,600);
		frameMenuUs.setLocationRelativeTo(null);
		frameMenuUs.setLayout(null);
		
		JLabel mensaje = new JLabel("Seleccione una opción.");
		mensaje.setBounds(300,380,300,40);
		
		JButton button1 = new JButton("Acceder a la tienda.");
		button1.setBounds(250,100,300,50);
		button1.addActionListener( e -> {
			frameMenuUs.dispose();
			ntrfzTienda.displayTienda();
		});
		JButton button2 = new JButton("Editar equipo fantasía.");
		button2.setBounds(250,150,300,50);
		button2.addActionListener( e -> {
			frameMenuUs.dispose();
			ntrfzEditar.displayEditarEquipo();
		});
		JButton button3 = new JButton("Ver estadísticas de la Temporada.");
		button3.setBounds(250,200,300,50);
		button3.addActionListener( e -> {
			frameMenuUs.dispose();
			ntrfzEstadísticas.displayStats();
		});
		JButton button4 = new JButton("Ver historial de la temporada.");
		button4.setBounds(250,250,300,50);
		button4.addActionListener( e -> {
			frameMenuUs.dispose();
			//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
		});
		JButton volver = new JButton("Volver");
		volver.setBounds(250,300,300,50);
		volver.addActionListener( e -> {
			frameMenuUs.dispose();
			ntrfzAppGeneral.main(null);
		});
	
		frameMenuUs.add(button1);
		frameMenuUs.add(button2);
		frameMenuUs.add(button3);
		frameMenuUs.add(button4);
		frameMenuUs.add(volver);
		frameMenuUs.add(mensaje);
		frameMenuUs.setVisible(true);


	}
	
	
	
	

	
	
		

	


}