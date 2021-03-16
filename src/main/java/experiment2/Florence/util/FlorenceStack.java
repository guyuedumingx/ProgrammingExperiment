package experiment2.Florence.util;

import java.util.Iterator;

/**
 * @author Florence
 */
public class FlorenceStack<T> implements Iterable<T> {
    private T top = null;
    private int size = 0;
    private Node<T> head = new Node<>();

    /**
     * 采用头插法插入元素
     *
     * @param data 要插入的元素
     */
    public void push(T data) {
        Node<T> temp = new Node<>(data);
        temp.setNext(head.getNext());
        head.setNext(temp);
        size++;
        top = data;
    }

    /**
     * 弹出元素
     *
     * @return 返回一个值
     */
    public T pop() {
        if (size == 0) {
            System.out.println("栈为空");
            return null;
        }
        T data = head.getNext().getData();
        head.setNext(head.getNext().getNext());
        size--;
        if (size != 0) {
            top = head.getNext().getData();
        } else {
            top = null;
        }
        return data;
    }

    public int size() {
        return size;
    }

    /**
     * 是否为空
     *
     * @return 布尔值 如果为空就是true 如果不为空就是false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 去顶端值
     *
     * @return 获取顶端的值
     */
    public T top() {
        return top;
    }

    @Override
    public Iterator<T> iterator() {
        return new FlorenceStackIterator();
    }


    class FlorenceStackIterator implements Iterator<T> {
        Node<T> tempNode = head.getNext();
        @Override
        public boolean hasNext() {
            return tempNode != null;
        }

        @Override
        public T next() {
            T data = tempNode.getData();
            tempNode = tempNode.getNext();
            return data;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator<T> iterator = iterator();
        while (iterator.hasNext()){
            stringBuilder.append(iterator.next());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
