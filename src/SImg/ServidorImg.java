package SImg;

import com.google.gson.*;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class ServidorImg {

    private static int BUFF_SIZE = 2048;

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        boolean listening = true;
        try {
            serverSocket = new ServerSocket(2021);
        } catch (IOException e) {
            System.err.println("No se puede abrir el puerto: 2021.");
            System.exit(1);
        }
        System.out.println("Puerto abierto: 2021.");

        while (listening) {
            try {
                Socket socket = serverSocket.accept();
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                        socket.getInputStream()));
                String inputLine, outputLine;
		Gson gson = new Gson();

                if ((inputLine = in.readLine()) != null) {
                    System.out.println("ci recibido: " + inputLine);
                    Imagenes img = new Imagenes();
		    String imagen = "";
	 	    for(int i=1;i<=3;i++){
			try{
	            	imagen = gson.toJson(img.getImagen(Integer.parseInt(inputLine),i+".jpeg"));
			}catch(FileNotFoundException e){
				imagen = "[]";			
			}
			out.println(imagen);
		    }
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
