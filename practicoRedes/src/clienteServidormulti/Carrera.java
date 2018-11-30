package clienteServidormulti;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.Buffer;

public  class Carrera extends Thread{

	MulticastSocket s; InetAddress n;
	static caballo[] c;
	public final static int META = 400;

	
	static boolean empezo;
	private boolean termino;

	public Carrera(MulticastSocket a, InetAddress w) {
		// TODO Auto-generated constructor stub

		c = new caballo[6];
		empezo = false;
		termino = false;
		s=a;
		n=w;

	}

	public void empezarCarrera() {

		if (empezo == true) {
			for (int i = 0; i < c.length; i++) {
				c[i].start();

			}

		}

	}
	
	public void run() {
	//	System.out.println("a ver");
		pintarCa();
	};

	public void pintarCa() {

		//System.out.println("a ver");
		while (true) {
			String x = "";

			for (int i = 0; i < c.length; i++) {
				x += c[i].getX() + ",";
			}

			byte[] FF = x.getBytes();

			DatagramPacket packet = new DatagramPacket(FF, FF.length, n, 10000);
		//	System.out.println("entro2");
			try {
				s.send(packet);
				//System.out.println("envio");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public caballo[] getC() {
		return c;
	}

	public void setC(caballo[] c) {
		this.c = c;
	}

	public boolean isEmpezo() {
		return empezo;
	}

	public void setEmpezo(boolean empezo) {
		this.empezo = empezo;
	}

	public boolean isTermino() {
		return termino;
	}

	public void setTermino(boolean termino) {
		this.termino = termino;
	}

}
