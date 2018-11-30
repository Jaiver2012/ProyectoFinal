package clienteServidormulti;
	
	
	import java.net.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
	import java.io.*;
	public class ClienteWeb implements Runnable{
		//private manejador m;
		private final Socket socket;

		public ClienteWeb(Socket socket)
		{
			this.socket =  socket;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("\nClientHandler Started for " + this.socket);
			handleRequest(this.socket);
			System.out.println("ClientHanlder Terminated for "+  this.socket + "\n");
		}
		
		
		public StringBuilder metodoHTML() {
			StringBuilder r=new StringBuilder();
			
			
			try {
				FileReader fr= new FileReader(new File("data/index.html"));
				BufferedReader br= new BufferedReader(fr);
				while(br.ready()) {
					r.append(br.readLine());
				}
				
			}catch(Exception e){
				
			}
			
			return r;
		}
		
		
		public void handleRequest(Socket socket)
		{
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String headerLine = in.readLine();
				// A tokenizer is a process that splits text into a series of tokens
				StringTokenizer tokenizer =  new StringTokenizer(headerLine);
				//The nextToken method will return the next available token
				String httpMethod = tokenizer.nextToken();
				// The next code sequence handles the GET method. A message is displayed on the
				// server side to indicate that a GET method is being processed
				if(httpMethod.equals("GET"))
				{
					System.out.println("Get method processed");
					String httpQueryString = tokenizer.nextToken();
					StringBuilder responseBuffer =  metodoHTML();

				sendResponse(socket, 200, responseBuffer.toString());				
				}
				else if(httpMethod.equals("POST")) {
					
					while(!headerLine.equals("")) {
						System.out.println(headerLine);
					
						headerLine=in.readLine();
					}
					
					headerLine=in.readLine();
					
					String id= headerLine.split("=")[1];
					
					manejador m= new manejador();
					if(m.consultar(id)) {
						
						
						ArrayList<String> n=m.llenarArreglo(id);
						
						
						StringBuilder responseBuffer =  new StringBuilder();
						responseBuffer
						.append("<html>")
						.append("<head>")
						.append("<style>")
						.append("body{")
						.append("}")
						.append("</style>")
						.append("<title>Informacion</title>")
						.append("</head>")
						.append("<body>")
						.append("<h1>carreras</h1>")
						.append("<table>")
						.append("<tr>")
						.append("<td><strong>Usuario</strong></td>")
						.append("<td><strong>Caballo</strong></td>")
						.append("<td><strong>Monto</strong></td>")
						.append("<td><strong>Fecha</strong></td>");
						agregarlista(n,responseBuffer, id);
						responseBuffer.append("<body>")
						.append("<table>")
						.append("<body>")
						.append("</html>");
						
						sendResponse(socket, 200, responseBuffer.toString());
						
						
						
						
					}
					
					
					System.out.println(headerLine);
				}
				
				else
				{
					System.out.println("The HTTP method is not recognized");
					sendResponse(socket, 405, "Method Not Allowed");
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
		
		private void agregarlista(ArrayList<String> n, StringBuilder responseBuffer, String id) {
			// TODO Auto-generated method stub
			for (int i = 0; i < n.size(); i++) {
				System.out.println("CHEQUEO LISTA :" + n.get(i));
				if(n.get(i).split(",")[0].compareToIgnoreCase(id)==0) {
					responseBuffer.append("<tr>");
					responseBuffer.append("<td>"+n.get(i).split(",")[0]+"</td>");
					responseBuffer.append("<td>"+n.get(i).split(",")[1]+"</td>");
					responseBuffer.append("<td>"+n.get(i).split(",")[2]+"</td>");
					responseBuffer.append("<td>"+n.get(i).split(",")[3]+"</td>");
				
					responseBuffer.append("<tr>");
				}
				
			}
			
			
		}

		public void sendResponse(Socket socket, int statusCode, String responseString)
		{
			String statusLine;
			String serverHeader = "Server: WebServer\r\n";
			String contentTypeHeader = "Content-Type: text/html\r\n";
			
			try {
				DataOutputStream out =  new DataOutputStream(socket.getOutputStream());
				if (statusCode == 200) 
				{
					statusLine = "HTTP/1.0 200 OK" + "\r\n";
					String contentLengthHeader = "Content-Length: "
					+ responseString.length() + "\r\n";
					out.writeBytes(statusLine);
					out.writeBytes(serverHeader);
					out.writeBytes(contentTypeHeader);
					out.writeBytes(contentLengthHeader);
					out.writeBytes("\r\n");
					out.writeBytes(responseString);
					} 
				else if (statusCode == 405) 
				{
					statusLine = "HTTP/1.0 405 Method Not Allowed" + "\r\n";
					out.writeBytes(statusLine);
					out.writeBytes("\r\n");
				} 
				else 
				{
					statusLine = "HTTP/1.0 404 Not Found" + "\r\n";
					out.writeBytes(statusLine);
					out.writeBytes("\r\n");
				}
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	


