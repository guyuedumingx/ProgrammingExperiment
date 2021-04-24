package experiment3.dqy;
import util.Resource;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanTree {
    HashMap<Character, Integer> cnt = new HashMap<Character, Integer>();
    HashMap<String, Character> mp = new HashMap<String, Character>();
    PriorityQueue<HuffmanTreeNode> helper = new PriorityQueue<HuffmanTreeNode>((o1, o2) -> o1.getFre() - o2.getFre());
    public void getFrequency() throws IOException {
        String url = Resource.get("experiment3/Demo.txt");
        System.out.println();
        FileReader fileReader = new FileReader(url);
        BufferedReader br = new BufferedReader(fileReader);
        char read;
        while ((read = (char)br.read()) != -1) {
            if (cnt.containsKey(read)) {
                int cur = cnt.get(read);
                cnt.replace(read, cur + 1);
            } else {
                cnt.put(read, 1);
            }
        }
    }

    public void makeTree() {
        for (Map.Entry entry:cnt.entrySet()) {
            helper.add(new HuffmanTreeNode((int)entry.getValue(), "", (char)entry.getKey()));
        }
        while (helper.size() != 1) {
            HuffmanTreeNode cur1 = helper.poll();
            HuffmanTreeNode cur2= helper.poll();
            HuffmanTreeNode newTree = new HuffmanTreeNode(cur1.getFre() + cur2.getFre(), "", '*');
            newTree.size = cur1.size + cur2.size;
            newTree.setLeft(cur1.size > cur2.size ? cur2 : cur1);
            newTree.setRight(cur1.size > cur2.size ? cur1: cur2);
            helper.add(newTree);
        }
    }

    public void getCode() {

    }
}