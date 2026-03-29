package interfazGráficaAp;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import clasesAplicación.Aplicación;


public class ntrfzTienda {
	
	
	public static void main(String[] args) throws IOException {
		displayTienda();
	}
	
	
	
	public static void displayTienda() throws IOException {
		
		
		JFrame frameJugadores = new JFrame();
		frameJugadores.setTitle("Sus Jugadores");
		frameJugadores.setResizable(false);
		frameJugadores.setSize(400,450);
		JPanel panelJug = new JPanel();
		panelJug.setVisible(true);
		JTextArea scrll = new JTextArea(23,35);
		scrll.setText(controlador.getJugadoresUsuario());
		scrll.setEditable(false);
		panelJug.add(scrll, BorderLayout.CENTER);
		panelJug.add(scrll);
		JScrollPane spnl = new JScrollPane(scrll, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		spnl.setBorder(new EmptyBorder(5,5,5,5));
		panelJug.add(spnl);
		frameJugadores.add(panelJug);
		frameJugadores.setVisible(true);
		
		
		
		JFrame frameTienda = new JFrame();
		frameTienda.setTitle("Tienda Jugadores");
		frameTienda.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameTienda.setResizable(false);
		frameTienda.setSize(400,600);
		
		JPanel panelA = new JPanel();
		panelA.setVisible(true);
		JTextArea scroll = new JTextArea(23,35);
		scroll.setEditable(false);
		panelA.add(scroll, BorderLayout.CENTER);
		panelA.add(scroll);
		JScrollPane sp = new JScrollPane(scroll, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp.setBorder(new EmptyBorder(5,5,5,5));
		panelA.add(sp);
		
		JLabel lblPres = new JLabel("Presupuesto: ");
		lblPres.setBounds(50,385,100,30);
		frameTienda.add(lblPres);

		JLabel lblJug = new JLabel("Jugador: ");
		lblJug.setBounds(50,425,100,30);
		frameTienda.add(lblJug);
		JTextField jug = new JTextField();
		jug.setBounds(130,425,200,30);
		frameTienda.add(jug);
		
		JLabel mensaje = new JLabel("mensaje");
		mensaje.setBounds(50,455,100,30);
		frameTienda.add(mensaje);
		
		JButton compra = new JButton("Comprar");
		compra.setBounds(20,500,100,50);
		compra.addActionListener( e -> {
			System.out.print("Boton compra");
		});
		frameTienda.add(compra);
		
		JButton venta = new JButton("Vender");
		venta.setBounds(140,500,100,50);
		venta.addActionListener( e -> {
			System.out.print("Boton venta");
		});
		frameTienda.add(venta);
		
		JButton volver = new JButton("Volver");
		volver.setBounds(260,500,100,50);
		volver.addActionListener( e -> {
			frameJugadores.dispose();
			frameTienda.dispose();
			ntrfzAppUsuario.displayMenuUser();
		});
		frameTienda.add(volver);
		
		
		
		
		frameTienda.add(panelA);
		frameTienda.setVisible(true);
		
		


	}
	
	
	
	
	
}






