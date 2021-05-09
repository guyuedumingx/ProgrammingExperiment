package experiment4.dqy.util;

import java.util.Iterator;

public class LinkList<T> {
    //结点类
    static class Node<T> {
        T data;
        Node next;

        //无参构造
        public Node() {
        }

        //有参构造
        public Node(T data) {
            this.data = data;
        }
    }

    //生成一个头结点
    private Node head = new Node();

    //重写迭代器
    private class Itr implements Iterator<T> {
        Node cur = head;

        @Override
        public boolean hasNext() {
            return cur.next != null;
        }

        @Override
        public T next() {
            cur = cur.next;
            return (T) cur.data;
        }
    }

    public Itr iterator() {return new Itr();}

    public boolean contains(T data) {
        Node cur = head;
        while (cur.next != null) {
            cur = cur.next;
            if (cur.data.equals(data)) return true;
        }
        return  false;
    }

    //从前面插入结点
    public void addFront(T date) {
        Node cur = new Node(date);
        cur.next = head.next;
        head.next = cur;
    }

    //从后面插入结点
    public void addBack(T date) {
        Node cur = head;
        while (cur.next != null) cur = cur.next;
        cur.next = new Node(date);
    }

    //在第index个点后面添加结点
    public boolean addAt(int index, T data) {
        Node cur = head;
        for(int i = 1; i <= index; i++) {
            cur = cur.next;
            if(cur == null) return false;
        }
        cur.next = new Node(data);
        return true;
    }

    //修改第index个点
    public boolean modify(int index, T data) {
        Node cur = head;
        for(int i = 1; i <= index - 1; i++) {
            cur = cur.next;
            if(cur == null) return false;
        }
        Node tmp = cur.next.next;
        cur.next = new Node(data);
        cur = cur.next;
        cur.next = tmp;
        return true;
    }

    public void delete(T v) {
        Node cur = head;
        while (cur.next != null) {
            if(cur.next.equals(v)) {
                cur.next = cur.next.next;
                return;
            }
        }
    }
}