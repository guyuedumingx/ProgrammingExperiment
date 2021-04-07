package experiment3.yhicxu.util;

import experiment3.yhicxu.test.TestHuffman;

import java.io.*;
import java.net.URL;

public class IOUtil {
    public static String readFileText(String path) {
        URL resource = TestHuffman.class.getClassLoader().getResource(path);
        path = resource.getPath();
        Reader reader = null;
        char[] chars = new char[1024];
        int len;
        StringBuilder res = new StringBuilder();
        try {
            reader = new FileReader(path);
            while ((len = reader.read(chars)) != -1) {
                res.append(new String(chars, 0, len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return res.toString();
    }

}
