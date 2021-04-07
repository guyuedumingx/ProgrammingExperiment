package experiment3.yhicxu.huffman;

public class Node<T> {
    public Node<T> left;
    public Node<T> right;
    public int weight;
    private T data;

    public Node() {
        data = null;
    }

    public Node(T data) {
        this.data = data;
    }

    public Node(T data, int weight) {
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
