package experiment2.wangwei.utils;

import java.util.Iterator;

/**
 * 链队列
 *
 * @author yohoyes
 */
public class WQueue<T> {
    private Node<T> head = new Node<>();
    private Node<T> last = head;

    public WQueue() {
    }

    public T poll() {
        if(head.getNext() != null) {
            Node<T> next = head.getNext();
            head.setNext(next.getNext());
            return next.getData();
        }
        throw new RuntimeException("空队列异常");
    }

    public void add(T t) {
        Node<T> tNode = new Node<>(t);
        last.setNext(tNode);
        last = tNode;
    }

    public boolean isEmpty() {
        return last == head;
    }

    public Itr iterator() {
        return new Itr();
    }


    private class Itr implements Iterator<T> {
        Node<T> cursor = head.getNext();

        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        @Override
        public T next() {
            T data = cursor.getData();
            cursor = cursor.getNext();
            return data;
        }
    }
}

class Node<T> {
    private T data = null;
    private Node<T> next = null;

    public Node() {

    }

    public Node(T data) {
        this.data = data;
    }

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
