package clienteServidormulti;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class ElHilo extends Thread {

	PanelVisual vi;
	
	
	public ElHilo(PanelVisual v) {
		// TODO Auto-generated constructor stub
		vi=v;
		
		
	}
	
	
	
	public void run() {
		
		
		
		while(true) {
			MulticastSocket socket;
			
			try {
				socket = new MulticastSocket(10000);
				InetAddress a= InetAddress.getByName("228.5.6.7");
				socket.joinGroup(a);
				
				byte[] FF = new byte[10000];
		
				//System.out.println("ELHILO");
				
				DatagramPacket packet = new DatagramPacket(FF, FF.length);
				socket.receive(packet);
				//System.out.println("RECIBIO");
				
				String m= new String(packet.getData(), 0, packet.getLength());
				//System.out.println(m);
				String[] mm=m.split(",");
				
				//System.out.println(mm.toString());
				vi.setARR(mm);
				
				
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		
		
	}

}
