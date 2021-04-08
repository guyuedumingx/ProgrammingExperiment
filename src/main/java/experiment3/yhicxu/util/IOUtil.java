package experiment3.yhicxu.util;

import experiment3.yhicxu.test.TestHuffman;

import java.io.*;
import java.net.URL;

/**
 * <p><b>类名：</b>{@code IOUtil}</p>
 * <p><b>功能：</b></p><br>IO流相关工具包
 * <p><b>方法：</b></p>
 * <br> {@link #readFileText(String)}读取将指定路径中文本
 *
 * @author 60rzvvbj
 * @date 2021/4/8
 */
public class IOUtil {

    /**
     * <p><b>方法名：</b>{@code readFileText}</p>
     * <p><b>功能：</b></p><br>读取将指定路径中文本
     *
     * @param path 路径
     * @return 文本
     * @author 60rzvvbj
     * @date 2021/4/8
     */
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
