package SIdP;

import java.io.*;
import java.net.*;
import java.util.*;

/**
* Servidor de Identificacion Personal - SIdP
*/
public class ServidorIdP{
	/**
	* getInfo: Obtiene de la base de datos la informacion personal
	* @param String el numero de cedula
	* @return Un String con la informacion personal
	*/
	public static String getInfo(String ci){
		String ret = "";
		FileInputStream fs = null;
		try {
		    //abre el directorio SIdP/BD y busca el archivo con el numero de cedula
		    fs = new FileInputStream("SIdP/BD/"+ci);
		    Scanner s = new Scanner(fs);
		while(s.hasNextLine()){
		    ret += s.nextLine();}
		} catch (Exception ex) {
			ret = "{}";
		} finally {
		    try {
			if(fs!=null)
		        fs.close();
		    } catch (IOException ex) {

		    }
		}
		return ret;
	}

	/**
	* Funcion main
	*/
	public static void main(String a[]){
        // Variables
        int puertoServidor = 2020;

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

                System.out.println("Recibimos un pedido");

                // Datos recibidos e Identificamos quien nos envio
                String datoRecibido = new String(receivePacket.getData());

                InetAddress IPAddress = receivePacket.getAddress();

                int port = receivePacket.getPort();

                System.out.println("De : " + IPAddress + ":" + port);
                System.out.println("CI pedido : " + datoRecibido);

                // Respondemos el mismo mensaje en Mayuscula
		String respuesta = getInfo(datoRecibido);
		//System.out.println(respuesta);


                // Enviamos la respuesta inmediatamente a ese mismo cliente
                // Es no bloqueante
                sendData = respuesta.getBytes();
                DatagramPacket sendPacket =
                        new DatagramPacket(sendData, sendData.length, IPAddress,
                        port);

                serverSocket.send(sendPacket);

            }

        } catch (Exception ex) {
		ex.printStackTrace();
            System.out.println("Puerto UDP " + puertoServidor +" esta ocupado.");
            System.exit(1);
        }

    }
}
