package experiment3.dqy;
import util.Resource;

import java.io.*;
import java.util.HashMap;
import java.util.PriorityQueue;

public class HuffmanTree {
    HashMap<Character, Integer> cnt = new HashMap<Character, Integer>();
    PriorityQueue<Integer> helper = new PriorityQueue<Integer>();
            public String getFrequency() throws IOException {
                String url = Resource.get("experiment3/Demo.txt");
                System.out.println();
                FileReader fileReader = new FileReader(url);
                BufferedReader br = new BufferedReader(fileReader);
                String s, ans = "";
                while ((s = br.readLine()) != null) {
                    ans = s.toString();
                    System.out.println(ans);
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        String s = new HuffmanTree().getFrequency();
    }
}
