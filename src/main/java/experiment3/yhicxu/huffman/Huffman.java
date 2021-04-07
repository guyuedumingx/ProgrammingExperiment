package experiment3.yhicxu.huffman;

import java.util.*;

public class Huffman {

    private Node<Character> root;
    private HashMap<Character, Double> frequencyTable;
    private HashMap<Character, String> codeTable;

    public void init(String str) {
        frequencyTable = getWeight(str);
        createHuffmanTree(frequencyTable);
        codeTable = new HashMap<>();
        updateCodeTable();
    }

    public String code(String message) {
        StringBuilder res = new StringBuilder();
        for (char ch : message.toCharArray()) {
            res.append(codeTable.get(ch));
        }
        return res.toString();
    }

    private void updateCodeTable() {
        dfs(root, "");
    }

    private void dfs(Node node, String code) {
        if (node.left == null && node.right == null) {
            codeTable.put((Character) node.getData(), code);
        } else {
            dfs(node.left, code + "0");
            dfs(node.right, code + "1");
        }
    }

    private HashMap<Character, Double> getWeight(String str) {
        HashMap<Character, Double> res = new HashMap<>();
        double len = str.length();
        for (char ch : str.toCharArray()) {
            res.merge(ch, 1.0, Double::sum);
        }
        for (char ch : res.keySet()) {
            res.put(ch, res.get(ch) / len);
        }
        return res;
    }

    private void createHuffmanTree(HashMap<Character, Double> map) {
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

    public void showFrequencyTable() {
        Set<Character> keySet = frequencyTable.keySet();
        Character[] characters = new Character[keySet.size()];
        keySet.toArray(characters);
        Arrays.sort(characters);
        for (char ch : characters) {
            String k = "" + ch;
            if (ch == 10) k = "\\n";
            if (ch == 13) k = "\\r";
            System.out.printf("%s:\t%.4f\n", k, frequencyTable.get(ch));
        }
    }

    public void showCodeTable() {
        Set<Character> keySet = codeTable.keySet();
        Character[] characters = new Character[keySet.size()];
        keySet.toArray(characters);
        Arrays.sort(characters);
        for (char ch : characters) {
            String k = "" + ch;
            if (ch == 10) k = "\\n";
            if (ch == 13) k = "\\r";
            System.out.println(k + ":\t" + codeTable.get(ch));
        }
    }

    public Node<Character> getTree() {
        return root;
    }

    public void setRoot(Node<Character> root) {
        this.root = root;
        updateCodeTable();
    }

    public String decode(String code) {
        Node<Character> now = root;
        String message = "";
        for (char ch : code.toCharArray()) {
            if (ch == '0') {
                now = now.left;
            } else {
                now = now.right;
            }
            if (now.left == null && now.right == null) {
                message += now.getData();
                now = root;
            }
        }
        return message;
    }
}
