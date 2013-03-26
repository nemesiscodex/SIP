import java.io.*;
import java.util.*;

public class test2{
	public static void main(String[] args) {
        FileInputStream fs = null;
        try {
            fs = new FileInputStream("test.java");
            Scanner s = new Scanner(fs);
	 
	while(s.hasNextLine()){
            System.out.println(s.nextLine());}
        } catch (FileNotFoundException ex) {

        } finally {
            try {
                fs.close();
            } catch (IOException ex) {

            }
        }
    }
}
