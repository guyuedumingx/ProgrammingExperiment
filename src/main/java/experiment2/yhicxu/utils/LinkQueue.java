package experiment2.yhicxu.utils;

import java.util.Iterator;

public class LinkQueue<T> implements Iterable<T> {

    private class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }

    private Node<T> head;
    private Node<T> last;
    private int size;

    public LinkQueue() {
        head = null;
        last = null;
    }

    public void offer(T t) {
        if (head == null) {
            last = new Node<>(t);
            head = last;
        } else {
            head.next = new Node<>(t);
            head = head.next;
        }
        size++;
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        } else {
            return last.data;
        }
    }

    public T pool() {
        if (isEmpty()) {
            return null;
        } else {
            T res = last.data;
            if (head == last) {
                head = null;
            }
            last = last.next;
            size--;
            return res;
        }
    }

    public boolean isEmpty() {
        return head == null && last == null;
    }

    public int size(){
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> now = last;

            @Override
            public boolean hasNext() {
                return now != null;
            }

            @Override
            public T next() {
                T res = now.data;
                now = now.next;
                return res;
            }
        };
    }
}
