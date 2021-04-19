package experiment3.Florence;

import experiment2.Florence.util.FlorencePriorityQueue;

import java.io.*;
import java.util.*;

/**
 * @author Florence
 */
public class HuffMan {

    /**
     * 权重类
     */
    static final Map<Character,String> encodeMap = new HashMap<>(50);

    static class HuffManTreeNode {
        HuffManTreeNode left;
        HuffManTreeNode right;
        HuffManTreeNode root;
        int weight;
        Character c;
        HuffManTreeNode(Character c,int weight){
            this.c=c;
            this.weight=weight;
        }

        public HuffManTreeNode getLeft() {
            return left;
        }

        public void setLeft(HuffManTreeNode left) {
            this.left = left;
        }

        public HuffManTreeNode getRight() {
            return right;
        }

        public void setRight(HuffManTreeNode right) {
            this.right = right;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public Character getC() {
            return c;
        }

        public void setC(Character c) {
            this.c = c;
        }

        public HuffManTreeNode getRoot() {
            return root;
        }

        public void setRoot(HuffManTreeNode root) {
            this.root = root;
        }
    }
    /**
     * 获取要分析的数据
     * @param path
     * @return
     * @throws IOException
     */
    public static String getAnalyzeData(String path) throws IOException {
        File file = new File(path);
        BufferedReader bufferedInputStream = new BufferedReader(new FileReader(file));
        StringBuilder stringBuilder = new StringBuilder();
        String str="";
        while ((str=bufferedInputStream.readLine())!=null){
            stringBuilder.append(str);
        }
        return stringBuilder.toString();
    }

    /**
     * 给数据分字符计数
     * @param data
     * @return
     */
    public static Map<Character,Integer> countCharacterFromData(String data){
        Map<Character,Integer> map = new HashMap<>(50);
        for (Character c :data.toCharArray()){
            map.put(c,map.getOrDefault(c,0)+1);
        }
        return map;
    }

    /**
     * 初始化数据的优先队列
     * @param dataMap
     * @return
     */
    public static FlorencePriorityQueue<HuffManTreeNode> getWeightPriorityQueue(Map<Character,Integer> dataMap){
        FlorencePriorityQueue<HuffManTreeNode> weightFlorencePriorityQueue = new FlorencePriorityQueue<>(new Comparator<HuffManTreeNode>() {

            @Override
            public int compare(HuffManTreeNode o1, HuffManTreeNode o2) {
                return o1.weight-o2.weight;
            }
        });
        for (Map.Entry<Character, Integer> entry : dataMap.entrySet()) {
            weightFlorencePriorityQueue.offer(new HuffManTreeNode(entry.getKey(),entry.getValue()));
        }
        return weightFlorencePriorityQueue;
    }
    public static HuffManTreeNode buildHuffManTree(FlorencePriorityQueue<HuffManTreeNode> weight){
        while (weight.size()!=1){
            //获取最小的两个哈夫曼子树
            HuffManTreeNode minFirst = weight.poll();
            HuffManTreeNode minSecond = weight.poll();
            //建立新子树
            HuffManTreeNode tempRoot = new HuffManTreeNode('#', minFirst.getWeight() + minSecond.getWeight());
            tempRoot.setLeft(minFirst);
            tempRoot.setRight(minSecond);
            //加入优先队列
            weight.offer(tempRoot);
        }
        return weight.poll();
    }

    /**
     * dfs初始化HuffManTree
     * @param root
     * @param stringBuilder
     */
    public static void initEncodeMap(HuffManTreeNode root,StringBuilder stringBuilder,HuffManTreeNode realRoot){
        if (root!=null){
            if (root.c!='#'){
                encodeMap.put(root.getC(),stringBuilder.toString());
                root.setRoot(realRoot);
                return;
            }
            stringBuilder.append('0');
            initEncodeMap(root.left,stringBuilder,realRoot);
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
            stringBuilder.append('1');
            initEncodeMap(root.right,stringBuilder,realRoot);
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
        }
    }

    /**
     * 编码
     * @param source
     * @return
     */
    public static String encode(String source){
        StringBuilder target = new StringBuilder();
        for (Character c :source.toCharArray()){
            target.append(encodeMap.getOrDefault(c,"???"));
        }
        return target.toString();
    }
    public static String decode(String target,HuffManTreeNode huffManTree){
        StringBuilder source= new StringBuilder();
        dfsHuffManTree(0,target,source,huffManTree);
        return source.toString();
    }

    private static void dfsHuffManTree(int index, String target, StringBuilder stringBuilder,HuffManTreeNode huffManTree) {
        //找到要翻译的词
        if (huffManTree.getC()!='#'){
            stringBuilder.append(huffManTree.getC());
            huffManTree=huffManTree.getRoot();
            dfsHuffManTree(index,target,stringBuilder,huffManTree);
            return;
        }
        if (index<target.length()){
            Character value = target.charAt(index);
            index+=1;
            if (value=='0'){
                dfsHuffManTree(index,target,stringBuilder,huffManTree.getLeft());
            }
            else if (value=='1'){
                dfsHuffManTree(index,target,stringBuilder,huffManTree.getRight());
            }
            else {
                System.out.println("译码错误");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        //获取数据
        String analyzeData = getAnalyzeData("src\\main\\resources\\experiment3\\Demo.txt");
        //计算频率
        Map<Character, Integer> countMap = countCharacterFromData(analyzeData);
        //获取权重优先队列
        FlorencePriorityQueue<HuffManTreeNode> weightPriorityQueue = getWeightPriorityQueue(countMap);
        //建树
        HuffManTreeNode huffManTreeNode = buildHuffManTree(weightPriorityQueue);
        //遍历获取编码
        initEncodeMap(huffManTreeNode,new StringBuilder(),huffManTreeNode);
        System.out.println(encodeMap);
        String decode = decode("1111011", huffManTreeNode);
        System.out.println(decode);
    }

}
