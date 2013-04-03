package SIP;

import java.net.*;
import java.io.*;

public class ServerIP{
	private Integer puerto;
	private ServerSocket socket;
	public ServerIP(Integer puerto){
		this.puerto = puerto;
	}
	public void Conectar(){
		try{		
			Boolean t=true;
			socket = new ServerSocket(this.puerto);
		
			while(t){
				new HiloS(socket.accept()).start();
			}
			socket.close();
		}catch(IOException e){
			System.err.println("No se puede iniciar el ServidorIP.");
            		System.exit(1);
				
		}
	}
	public static void main(String[] args){
		ServerIP servidor = new ServerIP(2019);
		servidor.Conectar();
	}
}
