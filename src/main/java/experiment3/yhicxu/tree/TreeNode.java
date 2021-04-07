package experiment3.yhicxu.tree;

public class TreeNode<T> {
    public TreeNode<T> left;
    public TreeNode<T> right;
    private T data;

    public TreeNode(T data){
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
