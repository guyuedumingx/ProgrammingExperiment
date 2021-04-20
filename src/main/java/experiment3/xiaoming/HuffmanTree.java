package experiment3.xiaoming;

import java.util.*;

/**
 * @author cmy
 * @create 2021-04-20-14:18
 */
public class HuffmanTree {

    public static void main(String[] args) {
        String str = "i am iron man!   because i am \n good boy";
        String huffman = getHuffmanCodes(str);
        System.out.println(huffman);

        String string = docode(table, huffman);
        System.out.println(string);

    }

    private static Map<Character, String> table = new HashMap<>();

    /**
     * 根据传入的字符串，生成哈夫曼编码（0101010000101111）
     * @param s
     * @return
     */
    private static String getHuffmanCodes(String s) {
        List<Node> list = getList(s);
        Node root = createHuffmanTree(list);
        Map<Character, String> map = initCodesTable(root);
        System.out.println(map);
        table = map;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            stringBuilder.append(map.get(s.charAt(i)));
        }
        return stringBuilder.toString();
    }

    private static String docode(Map<Character, String> huffmanCodeTable, String huffmanCode) {
        //把哈夫曼编码表进行转换，反向查询
        Map<String, Character> map = new HashMap<>();
        for (Map.Entry<Character, String> entry : huffmanCodeTable.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        System.out.println(map);
        StringBuilder stringBuilder = new StringBuilder();
        //扫描哈夫曼编码串
        for (int i = 0; i < huffmanCode.length(); i++) {
            //计数器
            int count = 1;
            boolean flag = true;
            Character ch = null;

            while (flag) {
                String key = huffmanCode.substring(i, i+count);
                ch = map.get(key);
                if (ch == null) {
                    count++;
                } else {
                    flag = false;
                }
            }
            stringBuilder.append(ch);
            i += (count - 1);
        }
        return stringBuilder.toString();
    }

    /**
     *
     * @param s 接收原字符串
     * @return ArrayList<Node>
     */
    private static List<Node> getList(String s) {
        //创建ArrayList，里面存放Node
        List<Node> list = new ArrayList<>();

        //遍历字符串，统计每个字符串出现的次数
        Map<Character, Integer> counts = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Integer count = counts.get(s.charAt(i));
            if (count == null) {
                //Map还没有这个字符，需要添加
                counts.put(s.charAt(i), 1);
            } else {
                counts.put(s.charAt(i), count + 1);
            }
        }

        //把每一个键值对转化为Node对象，加到List中
        for (Map.Entry<Character, Integer> entry : counts.entrySet()) {
            list.add(new Node(entry.getKey(), entry.getValue()));
        }

        return list;
    }

    /**
     * 创建哈夫曼树
     * @param list
     * @return 哈夫曼树的根节点
     */
    private static Node createHuffmanTree(List<Node> list) {

        while (list.size() > 1) {
            //对list进行排序
            Collections.sort(list);

            //取出权值最小的两个节点
            Node leftNode = list.get(0);
            Node rightNode = list.get(1);

            //合并上诉两个节点，创建一个新节点
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;

            //在list中删除上诉两个节点
            list.remove(leftNode);
            list.remove(rightNode);

            //添加新节点
            list.add(parent);
        }

        //最后一个节点是HuffmanTree的根节点
        return list.get(0);
    }

    /**
     * 根据HuffmanTree生成哈夫曼编码表
     * @param root
     * @return
     */
    private static Map<Character, String> initCodesTable(Node root) {
        //遍历后生成的哈夫曼编码表，可以用于解压
        Map<Character, String> huffmanCodes = new HashMap<>();
        getCodes(root, "", new StringBuilder(), huffmanCodes);
        return huffmanCodes;
    }

    /**
     * 计算传入节点的所有叶子节点的哈夫曼编码，并合并到stringBuilder中
     * @param node 传入的节点
     * @param code 路径：左表示0，右表示1
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder, Map<Character, String> huffmanCodes) {
        StringBuilder stringBuilder1 = new StringBuilder(stringBuilder);
        //将根路径加入到stringBuilder1中
        stringBuilder1.append(code);
        if (node != null) {
            //判断当前节点是否是叶子节点
            if (node.data == null) {
                //不是叶子节点
                //递归处理
                getCodes(node.left, "0", stringBuilder1, huffmanCodes);
                getCodes(node.right, "1", stringBuilder1, huffmanCodes);
            } else {
                //是叶子节点
                //记录到哈夫曼编码表中
                huffmanCodes.put(node.data, stringBuilder1.toString());
            }
        }
    }

    //节点类
    private static class Node implements Comparable<Node> {

        Character data; //字符数据
        int weight; //节点权值，表示字符出现的次数
        Node left; //左子节点
        Node right; //右子节点

        public Node(Character data, int weight) {
            this.data = data;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", weight=" + weight +
                    '}';
        }

        /**
         * 根据节点权值比较大小
         * @param o
         * @return
         */
        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }

    }

}
