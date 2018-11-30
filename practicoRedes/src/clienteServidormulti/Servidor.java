package clienteServidormulti;

import java.io.*;
import java.net.*;

import javax.net.ssl.SSLServerSocketFactory;

public class Servidor {

	// C:\Program Files\Java\jdk-9.0.1\bin

	public static String KEYSTORE_LOCATION = "C:/Program Files (x86)/Java/jre1.8.0_151/bin/keystore.jks";
	public static String KEYSTORE_PASSWORD = "123456";

	private static Carrera ca;
	private static ServidorWeb Web;
	private static caballo[] caballos = new caballo[6];

	public static void main(String[] args) {

		System.setProperty("javax.net.ssl.keyStore", KEYSTORE_LOCATION);
		System.setProperty("javax.net.ssl.keyStorePassword", KEYSTORE_PASSWORD);

		SSLServerSocketFactory sslServer = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();

		MulticastSocket socket;
		
		try {
			socket = new MulticastSocket(10000);
			InetAddress a = InetAddress.getByName("228.5.6.7");
			socket.joinGroup(a);
			ca = new Carrera(socket, a);
			Web= new ServidorWeb();
			Web.start();
			// TODO Auto-generated method stub

			ServerSocket servidor;

			
			// llenar caballos

			llenarCaballos();

			// iniciar manjo de tiempo
			Long ini = System.currentTimeMillis();
			Long fin = System.currentTimeMillis();
			// ServerSocket servidor= sslServer.createServerSocket(8030);

			servidor = sslServer.createServerSocket(8030);

			System.out.println("escuchando");

			while ((fin - ini) < 20000) {

				Socket c = servidor.accept();

				// crea el hilo para esta solicitud
				Thread thread = new Thread(new hilito(c, (fin - ini)));

				thread.start();

				fin = System.currentTimeMillis();
			}
			//Aqui pum acaba el tiempo de apuesta y arranco con la carrera yyy prendo el audiode una 

			imprimir();
			ca.setEmpezo(true);
			ca.empezarCarrera();
			ca.start();
			
			AudioSUDP udp = new AudioSUDP();
			udp.start();

			//Cliente.prenderAudioC();

			//Cliente.habilitarEscucha();
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public static void imprimir() {
		for (int i = 0; i < ca.getC().length; i++) {
			System.out.println("El caballo #: " + ca.getC()[i].numero + " " + "Se le aposto: " + ca.getC()[i].monto);
		}
	}

	public static void setCaballos(int pos, double monto) {
		ca.getC()[pos - 1].monto += monto;
		ca.getC()[pos - 1].estado = true;

	}

	public static void llenarCaballos() {

		for (int i = 0; i < ca.getC().length; i++) {
			ca.getC()[i] = new caballo((i + 1), 0);
		}

	}

}