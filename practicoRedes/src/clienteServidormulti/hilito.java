package clienteServidormulti;

import java.net.*;
import java.util.Scanner;


import java.io.*;
public class hilito implements Runnable
{
	Socket cliente;
	BufferedReader clienteI;
	PrintWriter clienteO;
	String datos;
	long tiempo;

	
	public hilito(Socket request, long t)
	{
		super();
		cliente = request;
		tiempo=t;
		
		
	}
	
	
	public void run()
	{		
		try 
		{
			clienteI = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			clienteO = new PrintWriter(cliente.getOutputStream(),true);
			
			long t1= System.currentTimeMillis();
			long t2=System.currentTimeMillis();
			
			
			while(((t2-t1)+tiempo)<30000) {
		    
			datos=clienteI.readLine();
				
			
			
			String [] datosA=datos.split(",");
			
			String numC= datosA[0];
			String mont= datosA[1];
			
			
			Servidor.setCaballos(Integer.parseInt(numC), Double.parseDouble(mont));
						
			//clienteO.println("se ha recibido la apuesta de $"+datosA[1]);
			clienteO.println(numC);
			
			t2= System.currentTimeMillis();
			//System.out.println(((t2-t1)+tiempo)<30000 );
			
			}
			clienteO.write("Apuesta terminada");;
			
			clienteI.close();
			clienteO.close();
			cliente.close();
			
		
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
