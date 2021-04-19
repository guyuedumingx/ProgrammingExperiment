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
    PriorityQueue<pair> helper = new PriorityQueue<pair>((o1, o2) -> o1.fre - o2.fre);
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

//    public void makeTree() {
//        for (Map.Entry entry:cnt.entrySet()) {
//            helper.add(new pair(entry.getKey(), entry.getValue()));
//        }
//        while (!helper.isEmpty()) {
//
//        }
//    }

}

class pair{
    Character key;
    Integer fre;
    pair(Object k, Object f) {
        this.key = (Character) k;
        this.fre = (Integer) f;
    }
}