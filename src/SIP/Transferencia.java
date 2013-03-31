package SIP;
import java.io.*;
import java.net.*;
public class Transferencia{
	public String getDatosUDP(String ci, int puerto){
        	String direccionServidor = "127.0.0.1";
		String ret = "";
        

        int puertoServidor = puerto;
        
        try {

            //BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

            DatagramSocket clientSocket = new DatagramSocket();

            InetAddress IPAddress = InetAddress.getByName(direccionServidor);
            //System.out.println("Intentando conectar a = " + IPAddress + ":" + puertoServidor +  " via UDP...");

            byte[] sendData = new byte[1024];
            byte[] receiveData = new byte[1024];

            //System.out.print("Ingrese el mensaje a enviar: ");
            String sentence = ci;
            sendData = sentence.getBytes();

            //System.out.println("Enviar " + sendData.length + " bytes al servidor.");
            DatagramPacket sendPacket =
                    new DatagramPacket(sendData, sendData.length, IPAddress, puertoServidor);

            clientSocket.send(sendPacket);

            DatagramPacket receivePacket =
                    new DatagramPacket(receiveData, receiveData.length);

            //System.out.println("Esperamos si viene el paquete");

            //Vamos a hacer una llamada BLOQUEANTE entonces establecemos un timeout maximo de espera
            clientSocket.setSoTimeout(10000);

            try {
                // ESPERAMOS LA RESPUESTA, BLOQUENTE
                clientSocket.receive(receivePacket);

                String modifiedSentence = new String(receivePacket.getData());
                InetAddress returnIPAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();

                //System.out.println("Respuesta desde =  " + returnIPAddress + ":" + port);
                //System.out.println("Mensaje: " + modifiedSentence);
		ret = modifiedSentence;

            } catch (SocketTimeoutException ste) {

                System.out.println("TimeOut: El paquete udp se asume perdido.");
            }
            clientSocket.close();
        } catch (UnknownHostException ex) {
            System.err.println(ex);
        } catch (IOException ex) {
            System.err.println(ex);
        }
	return ret;
	}
	public static void main(String []args){
		Transferencia t = new Transferencia();
		System.out.println(t.getDatos("3015205",2020));
		
	}
}
