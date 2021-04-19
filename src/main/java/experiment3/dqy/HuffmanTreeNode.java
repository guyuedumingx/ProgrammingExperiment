package experiment3.dqy;

import util.TreeNode;

public class HuffmanTreeNode implements TreeNode {
    HuffmanTreeNode l, r;
    int fre;
    String code;
    char key;

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
}
