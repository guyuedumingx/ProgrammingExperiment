package experiment3.ww2;

import util.Resource;

import java.io.*;
import java.util.Scanner;

/**
 * 空格 . 分号
 * @author yohoyes
 * @date 2021/5/31 10:22
 */
public class Huffman {
    private static int PRIVATY = 29;
    // 空格
    private static int SPACE_INDEX = 26;
    //,
    private static int COMMA_INDEX = 27;
    //.
    private static int DOT_INDEX = 28;
    private static int[] nums = new int[PRIVATY];

    private static Heap heap = new Heap();

    public Huffman() {
        for (int i=0; i<nums.length; i++) {
            nums[i] = 0;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String str = readResource();
        char[] chars = str.toCharArray();
        for (char ch : chars) {
            if(ch >= 'a' && ch <= 'z') {
                nums[ch-'a']++;
            }else if(ch == ' ') {
                nums[SPACE_INDEX]++;
            }else if(ch == ',') {
                nums[COMMA_INDEX]++;
            }else if(ch == '.') {
                nums[DOT_INDEX]++;
            }
        }

        for (int i=0; i< PRIVATY-3; i++) {
            HuffmanNode node = new HuffmanNode();
            node.setKey((char)('a'+i));
            node.setNums(nums[i]);
            heap.add(node);
        }

        HuffmanNode spaceNode = new HuffmanNode();
        spaceNode.setKey(' ');
        spaceNode.setNums(nums[SPACE_INDEX]);
        heap.add(spaceNode);

        HuffmanNode commaNode = new HuffmanNode();
        commaNode.setKey(',');
        commaNode.setNums(nums[COMMA_INDEX]);
        heap.add(commaNode);

        HuffmanNode dotNode = new HuffmanNode();
        dotNode.setKey('.');
        dotNode.setNums(nums[DOT_INDEX]);
        heap.add(dotNode);
        while (heap.size > 1) {
            HuffmanNode min1 = heap.poll();
            HuffmanNode min2 = heap.poll();
            HuffmanNode parent = new HuffmanNode(min1, min2);
            parent.setNums(min1.getNums()+ min2.getNums());
            parent.setKey('\0');
            heap.add(parent);
        }
        HuffmanNode root = heap.poll();
        buildCode(root,"");
        String s = encoding("i am a good boy", root);
        System.out.println(s);
        String decoding = decoding(s, root);
        System.out.println(decoding);
    }

    public static void buildCode(HuffmanNode node, String code) {
        if (node == null) {
            return;
        }
        node.setCode(code);
        buildCode(node.getLeft(), code + "0");
        buildCode(node.getRight(), code + "1");
    }

    public static String encoding(String str, HuffmanNode root) {
        char[] chars = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(char ch : chars) {
            sb.append(findCode(ch, root));
        }
        return sb.toString();
    }

    public static String findCode(char ch, HuffmanNode node) {
        if(node == null) {
            return null;
        }
        if(node.getKey() == ch) {
            return node.getCode();
        }
        String str = findCode(ch, node.getLeft());
        if(str == null) {
           str = findCode(ch, node.getRight());
        }
        return str;
    }

    public static String decoding(String str, HuffmanNode root) {
        char[] chars = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        HuffmanNode cur = root;
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

    public static String readResource() {
        String file = Resource.get("experiment3/Demo.txt");
        FileReader reader = null;
        char[] chars = new char[1024];
        int len;
        StringBuilder res = new StringBuilder();
        try {
            reader = new FileReader(file);
            while ((len = reader.read(chars)) != -1) {
                res.append(new String(chars, 0, len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return res.toString();
    }
}
//
//class Link<T extends Comparable> {
//    private LinkNode<T> head = new LinkNode<>();
//
//    public void add(T data) {
//        LinkNode<T> node = new LinkNode<>(data);
//        node.setNext(head.getNext());
//        head.setNext(node);
//    }
//
//    public void sort(){
//        LinkNode<T> link = new LinkNode<>();
//        sort(head, link);
//        head = link;
//    }
//
//    private void sort(LinkNode<T> node, LinkNode<T> link) {
//        if(node.getNext() == null) {
//            return;
//        }
//        LinkNode<T> cur = node.getNext();
//        LinkNode<T> max = cur;
//        LinkNode<T> maxPre = node;
//
//        while (cur.getNext() != null) {
//            LinkNode<T> next = cur.getNext();
//            if(cur.compareTo(next)<0) {
//                max = next;
//                maxPre = cur;
//            }
//            cur = next;
//        }
//        LinkNode<T> nod = new LinkNode<>(max.getData());
//        nod.setNext(link.getNext());
//        link.setNext(nod);
//        maxPre.setNext(max.getNext());
//        sort(head, link);
//    }
//}
//
//class LinkNode<T extends Comparable> implements Comparable{
//    private T data;
//    private LinkNode<T> next;
//
//    public LinkNode() {
//
//    }
//    public LinkNode(T data) {
//        this.data = data;
//    }
//
//    public T getData() {
//        return data;
//    }
//
//    public void setData(T data) {
//        this.data = data;
//    }
//
//    public LinkNode<T> getNext() {
//        return next;
//    }
//
//    public void setNext(LinkNode<T> next) {
//        this.next = next;
//    }
//
//    @Override
//    public int compareTo(Object o) {
//        LinkNode o1 = (LinkNode) o;
//        return this.data.compareTo(o1.getData());
//    }
//}