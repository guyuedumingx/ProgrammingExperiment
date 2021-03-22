package experiment2.dqy.Util;

import java.util.Iterator;

public class Queue<T> {

    //重写迭代器
    private class Itr implements Iterator<T> {
        Node<T> cur = head;

        @Override
        public boolean hasNext() {
            return cur.next != null;
        }

        @Override
        public T next() {
            cur = cur.next;
            return cur.val;
        }
    }

    public Itr iterator() {return new Itr();}

    class Node<T> {
        T val;
        Node next;

        Node(T val) {
            this.val = val;
        }
    }

    //头结点
    private Node head = new Node(0);

    //压入队列
    public void push(T num) {
        Node news = new Node(num);
        Node cur = this.head;
        while (cur.next != null)
            cur = cur.next;
        cur.next = news;
    }

    //弹出元素
    public void pop() {
        if (head.next != null)
            head.next = head.next.next;
    }

    //获取前面的元素
    public T getFront() {
        return (T) head.next.val;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        if (head.next == null)
            return true;
        else
            return false;
    }

    //获取队列的大小
    public int size() {
        Node cur = this.head.next;
        int s = 0;
        while (cur != null) {
            cur = cur.next;
            s++;
        }
        return s;
    }


}
