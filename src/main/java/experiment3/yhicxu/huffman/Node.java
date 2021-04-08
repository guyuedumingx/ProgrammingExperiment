package experiment3.yhicxu.huffman;

// 哈弗曼树节点类
public class Node<T> {
    public Node<T> left;
    public Node<T> right;

    // 权值
    public double weight;

    // 数据
    private T data;

    public Node() {
        data = null;
    }

    public Node(T data) {
        this.data = data;
    }

    public Node(T data, double weight) {
        this.weight = weight;
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
