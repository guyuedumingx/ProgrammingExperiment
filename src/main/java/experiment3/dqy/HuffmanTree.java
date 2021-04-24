package experiment3.dqy;
import util.Resource;
import util.TreeUtil;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanTree {
    //哈希表用于记录每一个字符出现的次数
    HashMap<Character, Integer> cnt = new HashMap<Character, Integer>();
    //用于创建每个字母对应的哈夫曼编码
    HashMap<Character, String> mp = new HashMap<Character, String>();
    //优先队列用于创建哈夫曼树
    PriorityQueue<HuffmanTreeNode> helper = new PriorityQueue<HuffmanTreeNode>((o1, o2) -> o1.getFre() - o2.getFre());
    //对文件中的字母进行统计
    public void getFrequency() throws IOException {
        //获取资源文件
        String url = Resource.get("experiment3/Demo.txt");
        FileReader fileReader = new FileReader(url);
        BufferedReader br = new BufferedReader(fileReader);
        char read;
        //读入文件资源中的每一个字符
        while ((read = (char)br.read()) != -1) {
            //存在了则更新数量
            if (cnt.containsKey(read)) {
                int cur = cnt.get(read);
                cnt.replace(read, cur + 1);
            //则如果还没出现过要初始化
            } else {
                cnt.put(read, 1);
            }
            System.out.println(read - 0);
        }
        System.out.println("???????");
    }

    //创建哈夫曼树
    public HuffmanTreeNode BuildHuffmanTree() {
        //先把所有的点放入优先队列中
        for (Map.Entry entry:cnt.entrySet()) {
            helper.add(new HuffmanTreeNode((int)entry.getValue(), "", (char)entry.getKey()));
        }
        //当合并剩下一个节点时跳出循环
        while (helper.size() != 1) {
            //先拿出当前最小的点
            HuffmanTreeNode cur1 = helper.poll();
            //再拿出次小的点
            HuffmanTreeNode cur2= helper.poll();
            //合并连两个点
            System.out.println(cur1);
            System.out.println(cur2);
            HuffmanTreeNode newTree = new HuffmanTreeNode(cur1.getFre() + cur2.getFre(), "", '*');
            //设置当前树的大小
            newTree.size = cur1.size + cur2.size;
            //根据子树的大小决定
            newTree.setLeft(cur1.size > cur2.size ? cur2 : cur1);
            newTree.setRight(cur1.size > cur2.size ? cur1: cur2);
            //把当前点放回去
            helper.add(newTree);
        }
        return helper.peek();
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

    public void getCode() {
        //先建树
        HuffmanTreeNode head = BuildHuffmanTree();
        dfs(head, "");
        TreeUtil.printTree(head);
    }

    void transform() throws IOException {
        //获取资源文件
        String in = Resource.get("experiment3/Demo.txt");
        String out = Resource.get("experiment3/dqy/code/code1.txt");
        FileReader fileReader1 = new FileReader(in);
        FileWriter fileReader2 = new FileWriter(out);
        BufferedReader ori = new BufferedReader(fileReader1);
        BufferedWriter tran = new BufferedWriter(fileReader2);
        char read;
        //读入文件资源中的每一个字符
        while ((read = (char)ori.read()) != -1) {
            tran.write(mp.get(read));
        }
        tran.flush();
    }

    void showCnt() {

    }

    void showMp() {

    }

    public static void main(String[] args) throws IOException {
        new HuffmanTree().getFrequency();
        new HuffmanTree().getCode();

//        new HuffmanTree().transform();
    }
}