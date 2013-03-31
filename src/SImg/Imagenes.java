package SImg;
import java.io.*;

public class Imagenes{
	public byte[] getImagen(Integer ci, String img) throws FileNotFoundException, IOException{
		byte[] imagen = null;
		try {

		    File file = new File("SImg/BD/"+ci+"/"+img);
		    
		    FileInputStream fis = new FileInputStream(file);
		    ByteArrayOutputStream bos = new ByteArrayOutputStream();
		    int totalBytes=0;
		    byte[] buf = new byte[2048];
		    try {
		        for (int readNum; (readNum = fis.read(buf)) != -1;) {
		            bos.write(buf, 0, readNum); 
		            totalBytes+=readNum;
		        }
		    } catch (IOException ex) {	
			System.out.println("Error al leer la imagen");
		    }
		    
		    //imagen es el ByteArray que retornamos
		    imagen = bos.toByteArray();
		   // Lanza los errores a setImagenes()
		} catch (FileNotFoundException e) {
				 throw e;
		} catch (IOException e) {
				 throw e;
		}
		return imagen;
	}
	public void guardar(byte []img,Integer ci,Integer tipo){
		
		SaveFile("SImg/BD/"+ci+"/"+tipo+".jpeg",img,true);
	}
	public static boolean SaveFile(String FilePath, byte []Content, boolean CleanFileContent)
{
    	boolean Result = true;
  
    	FileWriter file;
        BufferedWriter writer;
    	
    	try 
        {
            FileOutputStream fo = new FileOutputStream(FilePath);
	    fo.write(Content);
            fo.close();
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
	public static void main(String []args){
		Imagenes im = new Imagenes();
		try{
			byte img[] = im.getImagen(2966912,"images.jpeg");
			for(int i =0;i<img.length;i++){
				System.out.print(img[i]);
			}	
			System.out.println();
			System.out.println(img.length);
			im.guardar(img,2966912,1);
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
