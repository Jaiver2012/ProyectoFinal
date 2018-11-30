package clienteServidormulti;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class manejador {

	File file;
	FileWriter fw;
	BufferedWriter bw;
	BufferedReader br;
	FileReader fr;
	
	
	
	public manejador() {
		// TODO Auto-generated constructor stub
		try {
			
			
			file= new File("BD/usuarios.txt");
			fw= new FileWriter(file, true);
			bw= new BufferedWriter(fw);
			//br= new BufferedReader(fw);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	public ArrayList<String> llenarArreglo(String id) {
		ArrayList<String> arr=new ArrayList<String>();
		

		String usuario="";
		try {
			fr= new FileReader(file);
			br= new BufferedReader(fr);
			
			
			usuario= br.readLine();
			
			while(usuario!=null) {
				
				String ide=usuario.split(",")[0];
				
				if(id.equals(ide)) {
					arr.add(usuario);
				}
				usuario= br.readLine();
				
			}
			
			
			fr.close();
			br.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		
		
		
		
		
		
		return arr;
	}
	
	
	

	
	public boolean consultar(String id) {
		boolean esta=false;
		
		
		String usuario="";
		try {
			fr= new FileReader(file);
			br= new BufferedReader(fr);
			
			
			usuario= br.readLine();
			
			while(usuario!=null) {
				
				String ide=usuario.split(",")[0];
				
				if(id.equals(ide)) {
					esta=true;
				}
				usuario= br.readLine();
				
				
			}
			
			
			fr.close();
			br.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return esta;
	}
	
	
	
}
