package SPos;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;
import SIP.Transferencia;
public class ServidorPos{
	
	

	public static Date FechaMasActual (Date Fecha1, Date Fecha2){
	
	if (Fecha1.compareTo(Fecha2)==0){
		return Fecha1;
	}else if (Fecha1.compareTo(Fecha2)<0){
		return Fecha2;
	}
		return Fecha1;
    }//fin fechaActual

	public static String cuandoEsCliente(String datoRecibido, int puerto){ //codigo a ejecutarse cuando el serverPos actua de cliente para serverTelefonia
	Transferencia objetotrans= new Transferencia();
	String jsonPosicion= objetotrans.getDatosUDP(datoRecibido, puerto);
	return jsonPosicion;
		

	}//fin cuandoEscliente


	public static void main(String a[]){
        
        // Variables
        int puertoServidor = 2023;

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

//ACA ACTUA COMO CLIENTE PARA SERVERTELEFONIA HAY QUE MANDAR LOQ RECIBIO PARA OBTENER LA FECHA Y POSICION
		
		int puertoA=2026;
		int puertoB=2027;
	
		String PosA= cuandoEsCliente(datoRecibido, puertoA); //puerto es el puerto para conectarse a ServerTelefonia
		String PosB= cuandoEsCliente(datoRecibido, puertoB);
		//System.out.println("POSA["+PosA+"]");
		
		Gson gson = new Gson();
		//pasar mis objetos json POSA y POSB a ServerTelefonia
		JsonReader reader = new JsonReader(new StringReader(PosA));
		reader.setLenient(true);
		Posicion PosiA= gson.fromJson(reader, Posicion.class);	
	//ServerTelefonia PosiA= gson.fromJson(PosA, ServerTelefonia.class);
		System.out.println("LLEGA!!!");
		reader = new JsonReader(new StringReader(PosB));
		reader.setLenient(true);
		Posicion PosiB= gson.fromJson(reader, Posicion.class);
//		ServerTelefonia PosiB= gson.fromJson(PosB,ServerTelefonia.class);

	

		
		

                // Respondemos el mismo mensaje en Mayuscula
		Posicion respuesta = Posicion.masReciente(PosiA,PosiB);
		
		//System.out.println(respuesta);
		String json= gson.toJson(respuesta);
		System.out.println("jsonenviado: "+json);
                // Enviamos la respuesta inmediatamente a ese mismo cliente
                // Es no bloqueante
                sendData = json.getBytes();
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

