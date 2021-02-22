package compliance;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class testCMD {
public static void main(String[] args) {
	
	String str = "wmic product where name=\"OpenSCAP\" call uninstall"; 
//	String str = "wmic product get name,vendor,version"; 
    Runtime rt = Runtime.getRuntime();
    try{

        Process p = rt.exec(str);
        InputStream is =p.getInputStream();
      //  System.out.println(is.available());
        InputStreamReader in = new InputStreamReader(is);

        StringBuffer sb = new StringBuffer();
        BufferedReader buff = new BufferedReader(in);
        String line = buff.readLine();
        //System.out.println(line);
        while( line != null )
        {
            sb.append(line + "\n");
      //  System.out.println(line);
            line = buff.readLine();
        }
        System.out.println( sb );
        if ( sb.length() != 0 ){
            File f = new File("C:\\Users\\Administrador\\Desktop\\testCMD.txt");
            FileOutputStream fos = new FileOutputStream(f);
            fos.write(sb.toString().getBytes());
          
            String aux="test insercion";
            fos.write(aux.toString().getBytes());
            fos.close();
        }
    }catch( Exception ex )
    {
        ex.printStackTrace();
    }
	
	
	
}
}
