package SPos;
import com.google.gson.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;


public class ServerTelefonia {
	
	private String Fecha;
	private String Hora;
	private String Posicion;
	public static String pathBd;//para la BD A o B


	public static boolean SaveFile(String FilePath, String FileContent, boolean CleanFileContent)
{
    	boolean Result = true;
    	

    	FileWriter file;
        BufferedWriter writer;
    	
    	try 
        {
            file = new FileWriter("SPos/"+pathBd+"/"+FilePath, !CleanFileContent);
            writer = new BufferedWriter(file);
            writer.write(FileContent,0,FileContent.length());
                    
            writer.close();
            file.close();
        } 
        catch (IOException ex) 
        {
        	Result = false;

            ex.printStackTrace();
        }
        finally
        {
        	return Result;
	}
}

	public static String getFechaActual() {

		Date ahora= new Date();
		SimpleDateFormat formateador= new SimpleDateFormat("dd-MM-yyyy");
		return formateador.format(ahora);

	}//fin getFechaActual

	public static String getHoraActual(){
		Date ahora= new Date();
		SimpleDateFormat formateador= new SimpleDateFormat("hh:mm:ss");
		return formateador.format(ahora);
	}//fin getHoraActual

	public static String getPos() {

		double x,y;
		String posicion;
		x=(Math.random()*100)%30;
		y=(Math.random()*100)%30;
		String a,b;
		a= String.valueOf(x);
		b= String.valueOf(y);
	
	posicion= ("("+a+","+b+")");

	return (posicion);
	}//fin getPos
	
	public ServerTelefonia(){
	this.Fecha= getFechaActual();
	this.Hora= getHoraActual();
	this.Posicion= getPos();
	}//fin constructor


	public static void main(String args[]) {
        
        // Variables
        int puertoServidor = Integer.parseInt(args[1]);
	
	pathBd=args[0]; //para bd

        try {
            //1) Creamos el socket Servidor de Datagramas (UDP)
            DatagramSocket serverSocket = new DatagramSocket(puertoServidor);

            //2) buffer de datos a enviar y recibir
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];


            //3) Servidor siempre esperando
            while (true) {

                receiveData = new byte[1024];

                DatagramPacket receivePacket =
                        new DatagramPacket(receiveData, receiveData.length);


                System.out.println("Esperando a algun cliente... ");

                // 4) Receive LLAMADA BLOQUEANTE
                serverSocket.receive(receivePacket);

                System.out.println("Aceptamos un paquete");

                // Datos recibidos e Identificamos quien nos envio
                String datoRecibido = new String(receivePacket.getData());
		
                InetAddress IPAddress = receivePacket.getAddress();

                int port = receivePacket.getPort();

                System.out.println("De : " + IPAddress + ":" + port);
                System.out.println("Mensaje : " + datoRecibido);
		
		Gson gson = new Gson();
		ServerTelefonia ubicacion= new ServerTelefonia();
		String json =  gson.toJson(ubicacion);
		//SaveFile((new Integer(datoRecibido)).toString(),json,true);
            	SaveFile(datoRecibido,json,true);

    		// Enviamos la respuesta inmediatamente a ese mismo cliente
                // Es no bloqueante
                sendData = json.getBytes();//nose si json loq tengo q mandar aca o ubicacion o que! ni como D: aaaah!!!
                DatagramPacket sendPacket =
                        new DatagramPacket(sendData, sendData.length, 							IPAddress,port);

                serverSocket.send(sendPacket);

            }//fin while

        } //fin try
	catch (Exception ex) {
		ex.printStackTrace();
            System.out.println("Puerto UDP " + puertoServidor +" esta ocupado.");
            System.exit(1);
        	} //fin catch	
 	}//fin main

 }//fin ServerTelefonia
