package experiment3.yhicxu.huffman;

import java.util.*;

/**
 * <p><b>类名：</b>{@code Huffman}</p>
 * <p><b>功能：</b></p><br>实现了哈夫曼编码的一系列功能
 * <p><b>方法：</b></p>
 * <br> {@link #createTree(String)}通过文本中字符出现频率构建哈弗曼树
 * <br> {@link #code(String)}将字符串进行编码
 * <br> {@link #decode(String)}将编码后的01字符串进行解码
 * <br> {@link #showFrequencyTable()}显示频率表
 * <br> {@link #showCodeTable()}显示编码表
 * <br> {@link #getTree()}获取哈弗曼树(根节点)
 * <br> {@link #setTree(Node)}设置哈弗曼树(根节点)
 *
 * @author 60rzvvbj
 * @date 2021/4/8
 */
public class Huffman {

    /**
     * 哈弗曼树根节点
     */
    private Node<Character> root;

    /**
     * 字符频率表
     */
    private HashMap<Character, Double> frequencyTable;

    /**
     * 字符编码表
     */
    private HashMap<Character, String> codeTable;

    /**
     * <p><b>方法名：</b>{@code createTree}</p>
     * <p><b>功能：</b></p><br>通过文本中字符出现频率构建哈弗曼树
     *
     * @param str 文本
     * @author 60rzvvbj
     * @date 2021/4/8
     */
    public void createTree(String str) {
        frequencyTable = getFrequencyTable(str);
        createHuffmanTree(frequencyTable);
//        createHuffmanTreeByLinkedList(frequencyTable);
        codeTable = new HashMap<>();
        updateCodeTable();
    }

    /**
     * <p><b>方法名：</b>{@code code}</p>
     * <p><b>功能：</b></p><br>将字符串进行编码
     *
     * @param message 要编码的信息
     * @return 编码后的01字符串
     * @author 60rzvvbj
     * @date 2021/4/8
     */
    public String code(String message) {
        StringBuilder res = new StringBuilder();
        for (char ch : message.toCharArray()) {
            res.append(codeTable.get(ch));
        }
        return res.toString();
    }

    /**
     * <p><b>方法名：</b>{@code decode}</p>
     * <p><b>功能：</b></p><br>将编码后的01字符串进行解码
     *
     * @param code 编码
     * @return 解码后的信息
     * @author 60rzvvbj
     * @date 2021/4/8
     */
    public String decode(String code) {
        Node<Character> now = root;
        StringBuilder message = new StringBuilder();
        for (char ch : code.toCharArray()) {
            if (ch == '0') {
                now = now.left;
            } else {
                now = now.right;
            }
            if (now.left == null && now.right == null) {
                message.append(now.getData());
                now = root;
            }
        }
        return message.toString();
    }

    /**
     * <p><b>方法名：</b>{@code showFrequencyTable}</p>
     * <p><b>功能：</b></p><br>显示频率表
     *
     * @author 60rzvvbj
     * @date 2021/4/8
     */
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

    /**
     * <p><b>方法名：</b>{@code showCodeTable}</p>
     * <p><b>功能：</b></p><br>显示编码表
     *
     * @author 60rzvvbj
     * @date 2021/4/8
     */
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

    /**
     * <p><b>方法名：</b>{@code getTree}</p>
     * <p><b>功能：</b></p><br>获取哈弗曼树(根节点)
     *
     * @return 根节点
     * @author 60rzvvbj
     * @date 2021/4/8
     */
    public Node<Character> getTree() {
        return root;
    }

    /**
     * <p><b>方法名：</b>{@code setTree}</p>
     * <p><b>功能：</b></p><br>设置哈弗曼树(根节点)
     *
     * @param root 哈弗曼树根节点
     * @author 60rzvvbj
     * @date 2021/4/8
     */
    public void setTree(Node<Character> root) {
        this.root = root;
        updateCodeTable();
    }

    /**
     * <p><b>方法名：</b>{@code updateCodeTable}</p>
     * <p><b>功能：</b></p><br>更新编码表
     *
     * @author 60rzvvbj
     * @date 2021/4/8
     */
    private void updateCodeTable() {
        dfs(root, "");
    }

    /**
     * <p><b>方法名：</b>{@code dfs}</p>
     * <p><b>功能：</b></p><br>通过前序遍历哈弗曼树的方法用来更新编码表的递归函数
     *
     * @param node 当前节点
     * @param code 当前编码
     * @author 60rzvvbj
     * @date 2021/4/8
     */
    private void dfs(Node node, String code) {
        if (node.left == null && node.right == null) {
            codeTable.put((Character) node.getData(), code);
        } else {
            dfs(node.left, code + "0");
            dfs(node.right, code + "1");
        }
    }

    /**
     * <p><b>方法名：</b>{@code getFrequencyTable}</p>
     * <p><b>功能：</b></p><br>通过文本中字符出现频率构建频率表
     *
     * @param str 文本
     * @return 频率表
     * @author 60rzvvbj
     * @date 2021/4/8
     */
    private HashMap<Character, Double> getFrequencyTable(String str) {
        HashMap<Character, Double> res = new HashMap<>();
        double len = str.length();
        for (char ch : str.toCharArray()) {
            res.merge(ch, 1.0, Double::sum);
        }
        res.replaceAll((c, v) -> res.get(c) / len);
        return res;
    }

    /**
     * <p><b>方法名：</b>{@code createHuffmanTree}</p>
     * <p><b>功能：</b></p><br>通过频率表创建哈弗曼树
     *
     * @param map 频率表
     * @author 60rzvvbj
     * @date 2021/4/8
     */
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

    class LinkedListNode<T> {
        public LinkedListNode<T> next;
        public T data;

        public LinkedListNode() {
        }

        public LinkedListNode(T data) {
            this.data = data;
        }

        public LinkedListNode(LinkedListNode<T> next, T data) {
            this.next = next;
            this.data = data;
        }
    }

    private void createHuffmanTreeByLinkedList(HashMap<Character, Double> map) {
        LinkedListNode<Node<Character>> head = new LinkedListNode<>();
        LinkedListNode<Node<Character>> now = null;
        for (char ch : map.keySet()) {
            Node<Character> t = new Node<>(ch, map.get(ch));
            if (now == null) {
                head.next = new LinkedListNode<>(t);
                now = head.next;
            } else {
                now.next = new LinkedListNode<>(t);
                now = now.next;
            }
        }
        now = head.next;
        while (now != null) {
            LinkedListNode<Node<Character>> node = head.next;
            while (node.next != null) {
                if (node.data.weight > node.next.data.weight) {
                    Node<Character> t = node.data;
                    node.data = node.next.data;
                    node.next.data = t;
                }
                node = node.next;
            }
            now = now.next;
        }
        now = head.next;

//        while (now != null) {
//            System.out.println(now.data.getData() + "  ");
//            now = now.next;
//        }

        while (now.next != null) {
            Node<Character> n1 = now.data;
            Node<Character> n2 = now.next.data;
            Node<Character> f = new Node<>();
            f.weight = n1.weight + n2.weight;
            f.left = n1;
            f.right = n2;
            now.data = f;
            now.next = now.next.next;
            LinkedListNode<Node<Character>> node = now;
            while (node.next != null) {
                if (node.data.weight > node.next.data.weight) {
                    Node<Character> t = node.data;
                    node.data = node.next.data;
                    node.next.data = t;
                }
                node = node.next;
            }
        }
        root = now.data;
    }

}
