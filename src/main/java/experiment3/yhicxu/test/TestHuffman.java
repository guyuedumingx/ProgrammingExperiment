package experiment3.yhicxu.test;

import experiment3.yhicxu.huffman.Huffman;
import experiment3.yhicxu.util.IOUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 用来运行main方法的测试类
public class TestHuffman {
    public static void main(String[] args) throws IOException {
        Huffman h = new Huffman();

        String s = IOUtil.readFileText("experiment3/Demo.txt");
        System.out.println("建树文本：");
        System.out.println(s);
        h.createTree(s);
        System.out.println("\n频率表：");
        h.showFrequencyTable();
        System.out.println("\n编码表：");
        h.showCodeTable();
        System.out.println("\n请输入要编码的字符串：");
        String text = new BufferedReader(new InputStreamReader(System.in)).readLine();
        String code = h.encode(text);
        System.out.println("\n编码后：");
        System.out.println(code);
        System.out.println("\n解码后：");
        System.out.println(h.decode(code));

        // 显示树相关
        /*
        h.createTree("aaaab\n\n\nbbc\r\rcd      aaabbcccdddeeefffgggh");
        Node<Character> root = h.getTree();
        TreeUtil.printTree(root);
        TreeUtil.buildXmind(root, "C:\\Users\\Administrator\\Desktop\\aaa\\huffman.xmind");
         */
    }
}
