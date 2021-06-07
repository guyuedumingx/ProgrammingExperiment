package util.treeUtil;

/**
 * @author yohoyes
 * @date 2021/4/17
 */
public interface HuffmanNode {
    void setLeft(HuffmanNode l);

    void setRight(HuffmanNode r);

    HuffmanNode getLeft();

    HuffmanNode getRight();

    void setData(Object object);

    String getData();
}
