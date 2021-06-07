package experiment3.ww;

import util.CharUtil;
import util.treeUtil.HuffmanNode;
import util.treeUtil.TreeUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author yohoyes
 * @date 2021/4/17 2:20
 */
public class Huffman {
    private static Map<Character, String> encodingTable = new HashMap<>();

    public static void main(String[] args) {
        Map<Character, Integer> map = CharUtil.countForMap("experiment3/Demo.txt");
        Set<Character> characters = map.keySet();
        MyPriorityQueue<Node> queue = new MyPriorityQueue<>(map.size());
        for (Character character : characters) {
            Node node = new Node(character, map.get(character));
            queue.add(node);
        }
        while (queue.size() > 1) {
            Node min1 = queue.poll();
            Node min2 = queue.poll();
            Node parent = new Node(min1, min2);
            queue.add(parent);
        }
        Node root = queue.poll();
        buildCode(root, "");
        TreeUtil.buildXmind(root, "1.xmind");
//        TreeUtil.printTree(root);
        String abcd = encoding("are you ok");
        System.out.println(abcd);
        String decoding = decoding(abcd, root);
        System.out.println(decoding);
    }

    public static void buildCode(Node node, String code) {
        if (node == null) {
            return;
        }
        node.setCode(code);
        encodingTable.put(node.getKey(),code);
        buildCode(node.getLeft(), code + "0");
        buildCode(node.getRight(), code + "1");
    }

    public static String encoding(String str) {
        char[] chars = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(char ch : chars) {
            sb.append(encodingTable.get(ch));
        }
        return sb.toString();
    }

    public static String decoding(String str, Node root) {
        char[] chars = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        Node cur = root;
        for(char ch: chars) {
            if(ch == '0') {
                cur = cur.getLeft();
            }else if(ch == '1') {
                cur = cur.getRight();
            }
            if(cur.getLeft() == null && cur.getRight() == null) {
                sb.append(cur.getKey());
                cur = root;
            }
        }
        return sb.toString();
    }

}

class Node implements Comparable<Node>, HuffmanNode {
    private Character key;
    private Integer nums;
    private String code;
    private HuffmanNode left;
    private HuffmanNode right;

    public Node(Node left, Node right) {
        this.left = left;
        this.right = right;
        this.nums = left.nums + right.nums;
    }

    public Node(Character key, Integer nums) {
        this.key = key;
        this.nums = nums;
    }

    public Character getKey() {
        return key;
    }

    public void setKey(Character key) {
        this.key = key;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Node getLeft() {
        return (Node)left;
    }

    @Override
    public void setLeft(HuffmanNode left) {
        this.left = left;
    }

    public Node getRight() {
        return (Node)right;
    }

    @Override
    public void setData(Object object) {
    }

    @Override
    public String getData() {
        if ("".equals(code)) {
            return "root";
        }
        if (key == null) {
            return code;
        }
        if (key == '\n') {
            key = '↲';
        }
        if (key == '\r') {
            key = '↓';
        }
        if (key == ' ') {
            key = '凵';
        }
        return code + " " + key;
    }

    @Override
    public void setRight(HuffmanNode right) {
        this.right = right;
    }

    @Override
    public int compareTo(Node node) {
        if (nums > node.nums) {
            return 1;
        } else if (nums == node.nums) {
            return 0;
        }
        return -1;
    }

    @Override
    public String toString() {
        return "Node{" +
                "key=" + key +
                ", nums=" + nums +
                ", code='" + code + '\'' +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
