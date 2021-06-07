package experiment3.yhicxu.huffman;

import util.treeUtil.HuffmanNode;

// 哈弗曼树节点类
public class Node<T> implements HuffmanNode {
    public Node<T> left;
    public Node<T> right;

    // 权值
    public double weight;

    // 数据
    private T message;

    // 编码
    private String code;

    public Node() {
        message = null;
    }

    public Node(T data) {
        this.message = data;
    }

    public Node(T data, double weight) {
        this.weight = weight;
        this.message = data;
    }

    public T getMessage() {
        return message;
    }

    public void setMessage(T message) {
        this.message = message;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public void setLeft(HuffmanNode node) {
    }

    @Override
    public void setRight(HuffmanNode node) {
    }

    @Override
    public HuffmanNode getLeft() {
        return left;
    }

    @Override
    public HuffmanNode getRight() {
        return right;
    }

    @Override
    public void setData(Object object) {
    }

    @Override
    public String getData() {
        if ("".equals(code)) {
            return "root";
        }
        if (message == null) {
            return code;
        } else {
            char key = message.toString().charAt(0);
            if (key == '\n') {
                key = '↲';
            }
            if (key == '\r') {
                key = '↓';
            }
            if (key == ' ') {
                key = '凵';
            }
            return code + " " + key;
        }
    }
}
