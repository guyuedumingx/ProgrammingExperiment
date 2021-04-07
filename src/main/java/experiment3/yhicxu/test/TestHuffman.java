package experiment3.yhicxu.test;

import experiment3.yhicxu.huffman.Huffman;
import experiment3.yhicxu.huffman.Node;
import experiment3.yhicxu.util.IOUtil;

import java.io.IOException;

public class TestHuffman {
    public static void main(String[] args) throws IOException {
        String s = IOUtil.readFileText("experiment3/Demo.txt");
        Huffman h = new Huffman();
        h.init("aaaaaaabbaaaaacccccaaadddaaa");
        h.showCodeTable();
        String code = h.code("abacdac");
        System.out.println(code);
    }
}
