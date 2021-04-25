package experiment3.dqy;

import util.TreeNode;

/**
 * 哈夫曼树的结点
 * 至于为什么要继承那个接口我也不知道
 * 大佬们叫我去继承一下我就去了
 */

public class HuffmanTreeNode implements TreeNode {
    private HuffmanTreeNode l, r;
    private int fre;
    private String code;
    private char key;
    int size = 1;

    @Override
    public void setLeft(TreeNode l) {
        this.l = (HuffmanTreeNode) l;
    }

    @Override
    public void setRight(TreeNode r) {
        this.r = (HuffmanTreeNode) r;
    }

    @Override
    public TreeNode getLeft() {
        return l;
    }

    @Override
    public TreeNode getRight() {
        return r;
    }

    @Override
    public void setData(Object object) {

    }

    @Override
    public String getData() {
        return code;
    }

    public int getFre() {
        return fre;
    }

    public void setFre(int fre) {
        this.fre = fre;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public char getKey() {
        return key;
    }

    public void setKey(char key) {
        this.key = key;
    }

    //有参的构造方法
    HuffmanTreeNode(int fre, String code , char key) {
        this.code = code;
        this.fre = fre;
        this.key = key;
    }
}
