package SIdP;
import com.google.gson.*;
import java.io.*;
import com.google.gson.*;
import java.util.*;
public class generarD{
	public static boolean SaveFile(String FilePath, String FileContent, boolean CleanFileContent)
{
    	boolean Result = true;
    	

    	FileWriter file;
        BufferedWriter writer;
    	
    	try 
        {
            file = new FileWriter("SIdP/BD/"+FilePath, !CleanFileContent);
            writer = new BufferedWriter(file);
            writer.write(FileContent,

                    0,
                    FileContent.length());
                    
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
    public static String nuevoEstado(){
        int n = (int)(Math.random()*10)%5;
        String ret = "";
        switch (n){
            case 0:
                ret = "buscado";
                break;
            case 1:
                ret = "profugo";
                break;
            case 2:
                ret = "preso";
                break;
            case 3:
                ret = "exconvicto";
                break;
            case 4:
                ret = "ninguno";
                break;
        }
        return ret;
    }
    public static String nuevaProfesion(){
        int n = (int)(Math.random()*10)%5;
        String ret = "";
        switch (n){
            case 0:
                ret = "estudiante";
                break;
            case 1:
                ret = "constructor";
                break;
            case 2:
                ret = "narco";
                break;
            case 3:
                ret = "empleado publico";
                break;
            case 4:
                ret = "vago";
                break;
        }
        return ret;
    }
    public static String nuevoEstadoCivil(){
        int n = (int)(Math.random()*10)%4;
        String ret = "";
        switch (n){
            case 0:
                ret = "Casad@";
                break;
            case 1:
                ret = "Solter@";
                break;
            case 2:
                ret = "Viud@";
                break;
            case 3:
                ret = "Divorciad@";
                break;
        }
        return ret;
    }
    public static String nuevaCuidad(){
        int n = (int)(Math.random()*10)%5;
        String ret = "";
        switch (n){
            case 0:
                ret = "Asuncion";
                break;
            case 1:
                ret = "Fdo. de la Mora";
                break;
            case 2:
                ret = "Lambare";
                break;
            case 3:
                ret = "Luque";
                break;
            case 4:
                ret = "San Lorenzo";
                break;
        }
        return ret;
    }
    public static int nuevaCi(){
        return (int) (Math.random()*10000000 + 500)%6000000;
    }
    public static String nuevaFecha(){
        int y,m,d;
        y = (int) (Math.random()*100 + 1900)+ (int) (Math.random()*10);
        m = ((int) (Math.random()*100))%12;
        d = ((int) (Math.random()*100))%28;
        return (d+"-"+m+"-"+y); 
    }
    public static void main(String[] args) {
	Gson gson = new Gson();
        for(int i = 1;i<101;i++){
        Identificacion n = new Identificacion(nuevaCi(),"nombre"+i,"apellido"+i,nuevoEstado(),nuevaFecha(),nuevoEstadoCivil(),nuevaCuidad(),nuevaCuidad(),nuevaProfesion());
	String json =  gson.toJson(n);
	SaveFile((new Integer(n.ci)).toString(),json,true);
	}
        
    }
}
