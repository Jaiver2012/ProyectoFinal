package clienteServidormulti;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.Calendar;
import java.util.Scanner;

import javax.net.ssl.SSLSocketFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * Esta clase es impotanteisima porque sirve como la interfaz de cada cliente
 * @author Jaiver
 *
 */
public class Cliente extends JFrame implements ActionListener {

	public static final String TRUSTTORE_LOCATION = "C:/Program Files (x86)/Java/jre1.8.0_151/bin/keystore.jks";

	public final static String APOSTAR = "Apostar";
	public final static String EMPEZAR = "Empezar";
	public final static String ESCUCHAR="Escuchar";

	private PanelVisual vi;

	private JButton butApostar;
	private JButton butEscuchar;
	
	private JTextField txtCApostar;
	private JLabel labCApostar;

	private ElHilo hilo;

	private JTextField txtMApostar;
	private JLabel labMApostar;
	
	private JTextField txtUserApostar;
	private JLabel labUserApostar;
	
	
	BufferedReader lee;
	PrintWriter escribe;
	String apuesta;
	
	String caballoApostado="";

	public Cliente() {

		// abrir flujos
		System.setProperty("javax.net.ssl.trustStore", TRUSTTORE_LOCATION);
		SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();
		Socket cliente;

		try {
			// cliente = new Socket("localhost", 8030);
			cliente = sf.createSocket("localHost", 8030);
			lee = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			escribe = new PrintWriter(cliente.getOutputStream(), true);

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setSize(650, 200);
		setTitle("Carrera");

		butApostar = new JButton(APOSTAR);
		butApostar.addActionListener(this);

		butEscuchar= new JButton(ESCUCHAR);
		butEscuchar.addActionListener(this);
		
		//butEscuchar.setEnabled(false);
		
		labCApostar = new JLabel("Caballo a apostar: ");
		txtCApostar = new JTextField();

		labMApostar = new JLabel("Monto a apostar: ");
		txtMApostar = new JTextField();

		labUserApostar= new JLabel("Cedula");
		txtUserApostar= new JTextField();
		
		
		vi = new PanelVisual();

		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1, 8));

		
		p.add(labUserApostar);
		p.add(txtUserApostar);
		p.add(labCApostar);
		p.add(txtCApostar);
		p.add(labMApostar);
		p.add(txtMApostar);
		p.add(butApostar);
		p.add(butEscuchar);

		add(vi, BorderLayout.CENTER);
		add(p, BorderLayout.SOUTH);

		hilo = new ElHilo(vi);
		hilo.start();

	}

	public  void prenderAudioC() {
		AudioCUDP au = new AudioCUDP();
		au.start();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String evento = e.getActionCommand();
		if (evento.equals(APOSTAR)) {
			String apuesta = txtCApostar.getText() + "," + txtMApostar.getText();

			escribe.println(apuesta);

			
			
			txtCApostar.setEnabled(false);
			txtMApostar.setEnabled(false);
			
			
			try {
				File file= new File("BD/usuarios.txt");
				//File file2= new File("BD/cc.txt");
				
				FileWriter fw;
				//FileWriter fw2;
				
				fw = new FileWriter(file,true);
				//fw2= new FileWriter(file2,false);
				//BufferedWriter lector = new BufferedWriter(fw2);
				
				
				BufferedWriter bw= new BufferedWriter(fw);
				//BufferedWriter bw2= new BufferedWriter(fw2);
				
				Calendar c= Calendar.getInstance();
				String dia=Integer.toString(c.get(Calendar.DATE));
				String mes=Integer.toString(c.get(Calendar.MONTH)+1);
				String anio=Integer.toString(c.get(Calendar.YEAR));
				
				String info=txtUserApostar.getText()+","+txtCApostar.getText()+","+txtMApostar.getText()+","+dia+":"+mes+":"+anio+"\n";
				
				fw.write(info);
				//fw2.write(txtUserApostar.getText());
				//lector.newLine();
				
				
				fw.close();
				bw.close();
			
				//fw2.close();
				//bw2.close();
				
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			try {
				String x=lee.readLine();
				//System.out.println(x);
				vi.setCaballoA(x);
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		else if(evento.equals(ESCUCHAR)) {
			prenderAudioC();
		}
		

	}
	
	
	public void habilitarEscucha() {

		butEscuchar.setEnabled(true);
		
		
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cliente ventana = new Cliente();
		ventana.setVisible(true);

	}

}