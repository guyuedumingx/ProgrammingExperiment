package experiment3.dqy;
import experiment3.dqy.util.HuffmanHeap;
import util.Resource;
import util.TreeUtil;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanTree {
    //哈希表用于记录每一个字符出现的次数
    public HashMap<Character, Integer> cnt = new HashMap<Character, Integer>();
    //用于创建每个字母对应的哈夫曼编码
    public HashMap<Character, String> mp = new HashMap<Character, String>();
    //优先队列用于创建哈夫曼树
    HuffmanHeap helper = new  HuffmanHeap();
    //对文件中的字母进行统计
    public void getFrequency() throws IOException {
        //获取资源文件
        String url = Resource.get("experiment3/Demo.txt");
        FileReader fileReader = new FileReader(url);
        BufferedReader br = new BufferedReader(fileReader);
        String a;
        //读入文件资源中的每一个字符
        while ((a = br.readLine()) != null) {
            //存在了则更新数量
            for (int i = 0; i < a.length(); i++) {
                char read = a.charAt(i);
                if (cnt.containsKey(read)) {
                    int cur = cnt.get(read);
                    cnt.replace(read, cur + 1);
//                    System.out.println(cur + 1);
                    //则如果还没出现过要初始化
                } else {
                    cnt.put(read, 1);
//                    System.out.println(read);
                }
            }
        }
    }

    //创建哈夫曼树
    public HuffmanTreeNode BuildHuffmanTree() {
        //先把所有的点放入优先队列中
        for (Character key : cnt.keySet()) {
            helper.insert(new HuffmanTreeNode(cnt.get(key), "", key));
        }
        //当合并剩下一个节点时跳出循环
        while (helper.getSize() != 1) {
            //先拿出当前最小的点
            HuffmanTreeNode cur1 = helper.top();
            helper.pop();
            //再拿出次小的点
            HuffmanTreeNode cur2= helper.top();
            helper.pop();
            //合并连两个点
            HuffmanTreeNode newTree = new HuffmanTreeNode(cur1.getFre() + cur2.getFre(), "", '*');
            //设置当前树的大小
            newTree.size = cur1.size + cur2.size;
            //根据子树的大小决定
            newTree.setLeft(cur1.size > cur2.size ? cur2 : cur1);
            newTree.setRight(cur1.size > cur2.size ? cur1: cur2);
            //把当前点放回去
            helper.insert(newTree);
        }
        return helper.top();
    }

    //dfs遍历整棵树同时设置字符对应的code
    void dfs(HuffmanTreeNode cur, String code) {
        //当遍历到叶子节点时我们记录下字符对应的编码
        if (cur.getLeft() == null && cur.getRight() == null) {
            mp.put(cur.getKey(), code);
            return;
        }
        //把当前节点的code设置一下
        cur.setCode(code);
        //遍历左子树
        dfs((HuffmanTreeNode)cur.getLeft(), code + "0");
        //遍历右子树
        dfs((HuffmanTreeNode)cur.getRight(), code + "1");
    }

    //进行编码
    public void getCode() {
        //先建树
        HuffmanTreeNode head = BuildHuffmanTree();
        dfs(head, "");
        TreeUtil.printTree(head);
    }

//    void transform() throws IOException {
//        //获取资源文件
//        String in = Resource.get("experiment3/Demo.txt");
//        String out = "experiment3/dqy/code/code1.txt";
//        FileReader fileReader1 = new FileReader(in);
//        FileWriter fileReader2 = new FileWriter(new File(out));
//        BufferedReader ori = new BufferedReader(fileReader1);
//        BufferedWriter tran = new BufferedWriter(fileReader2);
//        char read;
//        //读入文件资源中的每一个字符
//        while ((read = (char)ori.read()) != -1) {
//            tran.write(mp.get(read));
//        }
//        tran.flush();
//    }

    //用于遍历展示cnt的键与值（频率统计）
    void showCnt() {
        for (Character key : cnt.keySet()) {
            System.out.println(key + " : " + cnt.get(key));
        }
    }

    //用于遍历展示mp的键与值（编码）
    void showMp() {
        for (Character key : mp.keySet()) {
            System.out.println(key + " : " + mp.get(key));
        }
    }

    public static void main(String[] args) throws IOException {
        HuffmanTree tree = new HuffmanTree();
        tree.getFrequency();
        tree.getCode();
        tree.showCnt();
        System.out.println();
        tree.showMp();
    }
}