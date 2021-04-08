package experiment3.yhicxu.test;

import experiment3.yhicxu.huffman.Huffman;
import experiment3.yhicxu.util.IOUtil;

import java.io.IOException;

// 用来运行main方法的测试类
public class TestHuffman {
    public static void main(String[] args) throws IOException {
        String s = IOUtil.readFileText("experiment3/Demo.txt");
        Huffman h = new Huffman();
        h.createTree("aaaaaaabbaaaaacccccaaadddaaa");
        h.showCodeTable();
        String code = h.code("abacdac");
        System.out.println(code);
        String message = h.decode(code);
        System.out.println(message);
    }
}
