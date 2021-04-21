package experiment3.yhicxu.test;

import experiment3.yhicxu.huffman.Huffman;
import experiment3.yhicxu.huffman.Node;
import experiment3.yhicxu.util.IOUtil;
import util.TreeUtil;

import java.io.IOException;

// 用来运行main方法的测试类
public class TestHuffman {
    public static void main(String[] args) throws IOException {
        String s = IOUtil.readFileText("experiment3/Demo.txt");
        Huffman h = new Huffman();
        h.createTree("aaaab\n\n\nbbc\r\rcd      aaabbcccdddeeefffgggh");
//        h.createTree(s);
        Node<Character> root = h.getTree();
        TreeUtil.printTree(root);
        TreeUtil.buildXmind(root, "C:\\Users\\Administrator\\Desktop\\aaa\\huffman.xmind");
    }
}
