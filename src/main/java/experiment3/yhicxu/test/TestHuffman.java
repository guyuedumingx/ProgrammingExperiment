package experiment3.yhicxu.test;

import experiment3.yhicxu.util.IOUtil;

import java.io.IOException;

public class TestHuffman {
    public static void main(String[] args) throws IOException {
        String s = IOUtil.readFileText("experiment3/Demo.txt");
        System.out.println(s);
    }
}
