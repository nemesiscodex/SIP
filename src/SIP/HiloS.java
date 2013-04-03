package SIP;

import java.net.*;
import java.io.*;

public class HiloS extends Thread{
	private Socket socket;
	public HiloS(Socket socket){
		super("HiloS");	
		this.socket = socket;		
	}
	public void run(){
		Transferencia t = new Transferencia();
		String imgs[] = new String[3];
		try {
		    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		    BufferedReader in = new BufferedReader(
		            new InputStreamReader(
		            socket.getInputStream()));
		    out.println("Ingrese el Nro de CI:");
		    String inputLine, outputLine;
			
		    if ((inputLine = in.readLine()) != null) {
		        System.out.println("CI recibido: " + inputLine);
			out.println(t.getDatosUDP(inputLine,2020));
			imgs = t.getImagesTCPs(inputLine,2021);
			out.println(imgs[0]);
			out.println(imgs[1]);
			out.println(imgs[2]);
			out.println(t.getDatosUDP(inputLine,2023));
					        
		    }
		    out.close();
		    in.close();
		    socket.close();
		    System.out.println("Finalizando Hilo");

		} catch (IOException e) {
		    e.printStackTrace();
		}
	}

}
