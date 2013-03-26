import com.google.gson.*;
import java.io.*;
import java.util.*;
class persona{
                String nombre;
                Integer ci;
		public persona(){
			this.nombre = "Julio";
			this.ci = 123;
		}
		public String toString(){
			return "nombre: "+this.nombre+" ci: "+ci;
		}
        }

public class test{
	public static String getInfo(String ci){
		String ret = "";
		FileInputStream fs = null;
		try {
		    fs = new FileInputStream("./BD/"+ci);
		    Scanner s = new Scanner(fs);
		 
		while(s.hasNextLine()){
		    ret += s.nextLine();}
		} catch (FileNotFoundException ex) {
			ret = null;
		} finally {
		    try {
		        fs.close();
		    } catch (IOException ex) {

		    }
		}	
		return ret;
	}
	public static void main(String arg[]){
		Gson gson = new Gson();
		persona p =  new persona();
		String json =  gson.toJson(p);
		//System.out.println(json);
		persona g = gson.fromJson(getInfo("123"),persona.class);

		System.out.println(g);
	}
}
