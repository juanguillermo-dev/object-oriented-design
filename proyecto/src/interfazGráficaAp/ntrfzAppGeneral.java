package interfazGráficaAp;

import java.awt.Color;
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



public class ntrfzAppGeneral {
	
	
	
	public static void main(String[] args) {	
		
		JFrame frameAppGe = new JFrame();
		frameAppGe.setTitle("Aplicación General");
		frameAppGe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameAppGe.setResizable(false);
		frameAppGe.setSize(300,100);
		frameAppGe.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JLabel tituloFootFant = new JLabel();
		tituloFootFant.setText("Bienvenido a FootBall Fantasía. Ingresar como... ");
		
		JButton bUser = new JButton("Usuario");
		bUser.addActionListener( e -> {
			frameAppGe.dispose();
			crearOingresar();
		});
	
		
		JButton bAdmin = new JButton("Administrador");
		bAdmin.addActionListener( e -> {
			frameAppGe.dispose();
			displayLogIn("admin", null);
		});
		
		frameAppGe.add(tituloFootFant);
		frameAppGe.add(bUser);
		frameAppGe.add(bAdmin);		
		frameAppGe.setVisible(true);

	}
	
	
	
	
	
	
	
	
	public static void crearOingresar() {
		
		JFrame frame = new JFrame();
		frame.setTitle("Crear ó Ingresar?");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(300,100);
		frame.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JLabel pregunta = new JLabel();
		pregunta.setText("Como usuario, qué acción desea realizar? ");
		
		JButton bCrear = new JButton("Crear Cuenta");
		bCrear.addActionListener( e -> {
			frame.dispose();
			displayLogIn("user", "crear");
		});
	
		
		JButton bIngresar = new JButton("Ingresar");
		bIngresar.addActionListener( e -> {
			frame.dispose();
			displayLogIn("user", "ingresar");
		});
		
		frame.add(pregunta);
		frame.add(bCrear);
		frame.add(bIngresar);		
		frame.setVisible(true);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	@SuppressWarnings("deprecation")
	public static void displayLogIn(String v1, String v2) {
		
		JFrame frameLogIn = new JFrame();
		frameLogIn.setTitle("LogIn");
		frameLogIn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameLogIn.setResizable(false);
		frameLogIn.setSize(800,600);
		frameLogIn.setLocationRelativeTo(null);
		frameLogIn.setLayout(null);
		
		JLabel lblName = new JLabel("Nombre: ");
		lblName.setBounds(200,200,100,40);
		JLabel lblPassword = new JLabel("Contraseña: ");
		lblPassword.setBounds(200,250,100,40);
		
		JTextField name = new JTextField();
		name.setBounds(300,200,300,40);
		JPasswordField password = new JPasswordField();
		password.setBounds(300,250,300,40);
		
		JLabel mensajeError = new JLabel("");
		mensajeError.setBounds(300,380,300,40);
		
		
		
		JButton login = new JButton("LogIn");
		login.setBounds(300,300,100,40);
		login.addActionListener( e -> {
			String suceso = null;
			try {
				suceso = controlador.checkCredenciales(v1, v2, name.getText(), password.getText());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if ( suceso == "nulo" ) {
				mensajeError.setText("Recuerde que no puede dejar casillas vacías.");
			} else if ( suceso == "enUso" ) {
				mensajeError.setText("El nombre ingresado ya se encuentra en uso.");
			} else if ( suceso == "nameIncorrecto" ) {
				mensajeError.setText("El nombre ingresado no existe.");
			} else if ( suceso == "passIncorrecto" ) {
				mensajeError.setText("La contraseña ingresada es incorrecta.");
			} else {
				frameLogIn.dispose();
				tempSelect(v1);
			}
		});
		
		
		JButton volver = new JButton("Volver");
		volver.setBounds(450,300,100,40);
		volver.addActionListener( e -> {
			frameLogIn.dispose();
			main(null);
		});
		
		
		
		frameLogIn.add(lblName);
		frameLogIn.add(lblPassword);
		frameLogIn.add(name);
		frameLogIn.add(password);
		frameLogIn.add(login);
		frameLogIn.add(volver);
		frameLogIn.add(mensajeError);
		frameLogIn.setVisible(true);


	}
	
	
	
	
	
	
	public static void tempSelect(String v) {
		
		JFrame frameTempSelect = new JFrame();
		frameTempSelect.setTitle("Seleccionar Temporada");
		frameTempSelect.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameTempSelect.setResizable(false);
		frameTempSelect.setSize(800,600);
		frameTempSelect.setLocationRelativeTo(null);
		frameTempSelect.setLayout(null);
		
		JLabel lblTemp = new JLabel("Temporada: ");
		lblTemp.setBounds(200,200,100,40);
		JTextField temp = new JTextField();
		temp.setBounds(300,200,300,40);
		
		JLabel mensajeError = new JLabel("");
		mensajeError.setBounds(300,380,300,40);
		
		
		JButton select = new JButton("Select");
		select.setBounds(300,300,100,40);
		select.addActionListener( e -> {
			String suceso = null;
			try {
				suceso = controlador.checkTemporada(v, temp.getText());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if ( suceso == "inexistente" ) {
				mensajeError.setText("La temporada ingresada no existe.");
			} else {
				if ( v == "admin" ) {
					frameTempSelect.dispose();
					ntrfzAppAdministrador.displayMenuAdmin();
				} else {
					ntrfzAppUsuario.displayMenuUser();
				}
				
			}
		});
		
		
		JButton volver = new JButton("Volver");
		volver.setBounds(450,300,100,40);
		volver.addActionListener( e -> {
			frameTempSelect.dispose();
			main(null);
		});
		
		
		
		frameTempSelect.add(lblTemp);
		frameTempSelect.add(temp);
		frameTempSelect.add(select);
		frameTempSelect.add(volver);
		frameTempSelect.add(mensajeError);
		frameTempSelect.setVisible(true);


	}
	
	
	
	
	
	


}