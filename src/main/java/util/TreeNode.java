package util;

/**
 * @author yohoyes
 * @date 2021/4/17 13:48
 */
public interface TreeNode {
    void setLeft();

    void setRight();

    TreeNode getLeft();

    TreeNode getRight();

    void setData(Object object);

    String getData();
}
