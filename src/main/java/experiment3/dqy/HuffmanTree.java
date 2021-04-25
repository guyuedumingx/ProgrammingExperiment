package experiment3.dqy;

import experiment3.dqy.util.CodingMap;
import experiment3.dqy.util.Frequency;
import experiment3.dqy.util.HuffmanHeap;
import util.Resource;
import util.TreeUtil;
import java.io.*;

public class HuffmanTree {
    //哈希表用于记录每一个字符出现的次数
    Frequency cnt = new Frequency();
    //用于创建每个字母对应的哈夫曼编码
    CodingMap mp = new CodingMap();
    //优先队列用于创建哈夫曼树
    HuffmanHeap helper = new HuffmanHeap();
    //树的根节点
    HuffmanTreeNode root;
    //对文件中的字母进行统计
    public void getFrequency() throws IOException {
        //获取资源文件
        String url = Resource.get("experiment3/Demo.txt");
        FileReader fileReader = new FileReader(url);
        BufferedReader br = new BufferedReader(fileReader);
        String read;
        //读入文件资源中的每行数据
        while ((read = br.readLine()) != null) {
            //遍历整行
            for (int i = 0; i < read.length(); i++) {
                char x = read.charAt(i);
                //对应的字符数量加一
                cnt.add(x);
            }
        }
    }

    //创建哈夫曼树
    public void BuildHuffmanTree() {
        //先把所有的点放入优先队列中
        int[][] all = cnt.getAll();
        for (int i = 0; i < all.length; i++) {
            helper.insert(new HuffmanTreeNode(all[i][1], "", (char) all[i][0]));
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
        root = helper.top();
    }

    //dfs遍历整棵树同时设置字符对应的code
    void dfs(HuffmanTreeNode cur, String code) {
        //把当前节点的code设置一下
        cur.setCode(code);
        //当遍历到叶子节点时我们记录下字符对应的编码
        if (cur.getLeft() == null && cur.getRight() == null) {
            mp.add(cur.getKey(), code);
            return;
        }
        //遍历左子树
        dfs((HuffmanTreeNode)cur.getLeft(), code + "0");
        //遍历右子树
        dfs((HuffmanTreeNode)cur.getRight(), code + "1");
    }

    //进行编码
    public void getCode() {
        //先建树
        BuildHuffmanTree();
        dfs(root, "");
        TreeUtil.printTree(root);
    }

    //用于遍历展示cnt的键与值（频率统计）
    void showCnt() {
        cnt.showFrequency();
    }

    //用于遍历展示mp的键与值（编码）
    void showMp() {
        mp.showCodingMap();
    }

    void code() throws IOException {
        //获取资源文件
        String url = Resource.get("experiment3/Demo.txt");
        FileReader fileReader = new FileReader(url);
        BufferedReader br = new BufferedReader(fileReader);
        String read;
        //读入文件资源中的每行数据
        while ((read = br.readLine()) != null) {
            //遍历整行
            for (int i = 0; i < read.length(); i++) {
                char x = read.charAt(i);
                System.out.print(mp.get(x));
            }
            System.out.println();
        }
    }

    void decode() throws IOException {
        String url = Resource.get("experiment3/Demo.txt");
        FileReader fileReader = new FileReader(url);
        BufferedReader br = new BufferedReader(fileReader);
        String read;
        HuffmanTreeNode cur = root;
        while ((read = br.readLine()) != null) {
            //编译出一个词
            int idx = 0;
            while (idx < read.length()) {
                //当到达了根节点时我们输出结果
                if (cur.getLeft() == null && cur.getRight() == null) {
                    System.out.print(cur.getKey());
                    cur = root;
                }
                //否则我们继续往下走
                cur = read.charAt(idx) == '1' ? (HuffmanTreeNode)cur.getRight() : (HuffmanTreeNode)cur.getLeft();
                idx++;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        HuffmanTree tree = new HuffmanTree();
        tree.getFrequency();
        tree.getCode();
//        tree.showCnt();
        System.out.println();
//        tree.showMp();
        tree.code();
    }
}