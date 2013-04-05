package SPos;
import com.google.gson.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;

public class Posicion {
	private String Fecha;
	private String Hora;
	private String Posi;

	public static Date deStringToDate(String fecha) {//convierte de String a Date
		SimpleDateFormat formatoDelTexto= new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date fechaEnviar= null;
		try{
		fechaEnviar= formatoDelTexto.parse(fecha);
		return fechaEnviar;

	    }catch (ParseException ex){
		ex.printStackTrace();
		return null;
	    }//fin del trycatch
	}//fin del deStringToDate


	public static Posicion masReciente(Posicion PosiA, Posicion PosiB){
		Date Fecha1= deStringToDate(PosiA.getFecha()+" "+PosiA.getHora());
		Date Fecha2= deStringToDate(PosiB.getFecha()+" "+PosiB.getHora());

		if (Fecha1.compareTo(Fecha2)==0){
			return PosiA;
		}else if (Fecha1.compareTo(Fecha2)<0){
			return PosiB;
		}else{
			return PosiA;
		}
	}

	public String getFecha(){
		return this.Fecha; 
	}
	public String getHora(){
		return this.Hora;
	}
	public String getPosi(){
		return this.Posi;
	}

	public Posicion(String Fecha, String Hora, String Posi){
		this.Fecha= Fecha;
		this.Hora= Hora;
		this.Posi= Posi; 
	}
}
	
	
