package experiment3.ww;

import util.CharUtil;
import util.TreeNode;
import util.TreeUtil;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author yohoyes
 * @date 2021/4/17 2:20
 */
public class Huffman {

    public static void main(String[] args) {
        Map<Character, Integer> map = CharUtil.countForMap("experiment3/Demo.txt");
        Set<Character> characters = map.keySet();
        PriorityQueue<Node> queue = new PriorityQueue<>();
        for(Character character : characters) {
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
        TreeUtil.buildXmind(root, "1");
        new TreeUtil().printTree(root);
    }

    public static void buildCode(Node node, String code) {
        if (node == null) {
            return;
        }
        node.setCode(code);
        buildCode(node.getLeft(), code+"0");
        buildCode(node.getRight(), code+"1");
    }
}

class Node implements Comparable<Node>, TreeNode {
    private Character key;
    private Integer nums;
    private String code;
    private Node left;
    private Node right;

    public Node(Node left, Node right) {
        this.left = left;
        this.right = right;
        this.nums = left.nums + right.nums;
    }

    public Node(Character key, Integer nums){
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

    @Override
    public void setLeft() {
        this.left = left;
    }

    @Override
    public void setRight() {
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    @Override
    public void setData(Object object) {
    }

    @Override
    public String getData() {
        if("".equals(code)) {
            return "root";
        }
        if(key == null) {
            return code;
        }
        if(key == '\n'){
            key = '↲';
        }
        if(key == '\r') {
            key = '↓';
        }
        if(key == ' ') {
            key = '凵';
        }
        return code + " " + key;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public int compareTo(Node node) {
        if(nums > node.nums){
            return 1;
        }else if(nums == node.nums) {
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
