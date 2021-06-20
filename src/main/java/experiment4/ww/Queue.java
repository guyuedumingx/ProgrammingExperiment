package experiment4.ww;

/**
 * @author yohoyes
 * @date 2021/5/12 21:19
 */
public class Queue<T> {
    private Node<T> head = new Node<>();
    private int size = 0;

    public void poll(T data) {
        Node node = new Node(data);
        Node p = head;

        while (p.getNext() != null){
            p = p.getNext();
        }
        p.setNext(node);
    }

    public T pop() {
        Node p = head;
        if(head.getNext() != null){
            p = p.getNext();
            head.setNext(p.getNext());
            return (T)p.getData();
        }
        return null;
    }

}

class Node<T> {
    private T data;
    private Node<T> next;

    public Node(T data) {
        this.data = data;
    }

    public Node(){}

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
}

class Stack<T> {
    private int top = -1;
    private int size = 20;
    Object[] arr = new Object[size];

    public void push(T data) {
        if(top > size/2) {
            size = size * 2;
            Object[] objects = new Object[size];
        }
        arr[++top] = data;
    }

    public T pop() {
        return (T)arr[top--];
    }

    public boolean isEmpty() {
        return top == -1;
    }
}

