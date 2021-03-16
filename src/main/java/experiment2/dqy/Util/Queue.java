package experiment2.dqy.Util;

public class Queue<T> {
     class Node<T> {
        T val;
        Node next;

        Node(T val) {
            this.val = val;
        }
    }

    //头结点
    public Node head = new Node(0);

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
