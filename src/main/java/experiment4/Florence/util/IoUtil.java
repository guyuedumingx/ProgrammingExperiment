package experiment4.Florence.util;

import java.io.*;

/**
 * @author Florence
 */
public class IoUtil {
    private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    IoUtil(String fileName) throws FileNotFoundException { bufferedReader=new BufferedReader(new FileReader(fileName)); }
    public IoUtil(){ }


    public String readLine() throws IOException {
        return bufferedReader.readLine();
    }
}
