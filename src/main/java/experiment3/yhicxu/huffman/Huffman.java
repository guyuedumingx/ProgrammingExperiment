package experiment3.yhicxu.huffman;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;

public class Huffman {

    private Node<Character> root;

    public String code(String str) {
        HashMap<Character, Integer> weight = getWeight(str);
        createHuffmanTree(weight);
        return null;
    }

    private HashMap<Character, Integer> getWeight(String str) {
        HashMap<Character, Integer> res = new HashMap<>();
        for (char ch : str.toCharArray()) {
            res.merge(ch, 1, Integer::sum);
        }
        return res;
    }

    private void createHuffmanTree(HashMap<Character, Integer> map) {
        Queue<Node<Character>> queue = new PriorityQueue<>(new Comparator<Node<Character>>() {
            @Override
            public int compare(Node<Character> o1, Node<Character> o2) {
                return o1.weight > o2.weight ? 1 : -1;
            }
        });
        for (char ch : map.keySet()) {
            Node<Character> t = new Node<>(ch, map.get(ch));
            queue.offer(t);
        }
        while (queue.size() > 1) {
            Node<Character> n1 = queue.poll();
            Node<Character> n2 = queue.poll();
            Node<Character> f = new Node<>();
            f.weight = n1.weight + n2.weight;
            f.left = n1;
            f.right = n2;
            queue.offer(f);
        }
        root = queue.poll();
    }

    public Node<Character> getTree() {
        return root;
    }

    public void decode(Node<Character> root) {

    }
}
