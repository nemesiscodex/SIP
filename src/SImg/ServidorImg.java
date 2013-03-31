package SImg;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class ServidorImg {

    private static int BUFF_SIZE = 1024;

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        boolean listening = true;
        try {
            serverSocket = new ServerSocket(4444);
        } catch (IOException e) {
            System.err.println("No se puede abrir el puerto: 2021.");
            System.exit(1);
        }
        System.out.println("Puerto abierto: 4444.");

        while (listening) {
            try {
                Socket socket = serverSocket.accept();
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                        socket.getInputStream()));
                out.println("Bienvenido!");
                String inputLine, outputLine;

                if ((inputLine = in.readLine()) != null) {
                    System.out.println("ci recibido: " + inputLine);
                    Imagenes img = new Imagenes();
	            byte [] imagen1 = img.getImagen(Integer.parseInt(inputLine),"1.jpeg");
		    byte [] imagen2 = img.getImagen(Integer.parseInt(inputLine),"2.jpeg");
		    byte [] imagen3 = img.getImagen(Integer.parseInt(inputLine),"3.jpeg");
		    out.print(imagen1);
                   out.print(imagen2);
		out.print(imagen3);
                }
                out.close();
                in.close();
                socket.close();
                System.out.println("Finalizando transferencia.");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
