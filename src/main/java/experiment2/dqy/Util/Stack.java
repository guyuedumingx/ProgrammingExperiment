package experiment2.dqy.Util;

public class Stack<T> {
     class Node<T> {
        T data;
        Node next;

        //有参构造
        public Node(T data) {
            this.data = data;
        }
    }

    private Node head = new Node(null);

    //压栈
    public void push(T v) {
        Node tmp = new Node(v);
        tmp.next = head.next;
        head.next = tmp;

    }

    //获取栈顶元素
    public T getTop() {
        return (T) head.next.data;
    }

    //弹出栈
    public void pop() {
        if (head.next != null)
            head.next = head.next.next;
    }

    //判断是否为空
    public boolean isEmpty() {
        if (head.next == null) {
            return true;
        } else {
            return false;
        }
    }

    //获取栈的大小
    public int size() {
        Stack.Node cur = this.head.next;
        int s = 0;
        while (cur != null) {
            cur = cur.next;
            s++;
        }
        return s;
    }

}
