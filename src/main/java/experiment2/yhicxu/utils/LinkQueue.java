package experiment2.yhicxu.utils;

import java.util.Iterator;

/**
 * <p><b>类名：</b>{@code LinkQueue}</p>
 * <p><b>功能：</b></p><br>链队列
 * <p><b>方法：</b></p>
 * <br> {@link #LinkQueue()}构造方法
 * <br> {@link #offer(Object)}将数据添加到队尾
 * <br> {@link #peek()}查看队首元素
 * <br> {@link #pool()}取出队首元素
 * <br> {@link #isEmpty()}判断队列是否为空
 * <br> {@link #size()}获取队列长度
 * <br> {@link #iterator()}覆盖重写迭代器方法，返回一个迭代器
 *
 * @author 60rzvvbj
 * @date 2021/3/20
 */
public class LinkQueue<T> implements Iterable<T> {

    /**
     * <p><b>类名：</b>{@code Node}</p>
     * <p><b>功能：</b></p><br>链队列节点
     * <p><b>方法：</b></p>
     * <br> {@link #Node(Object)} 构造方法
     *
     * @author 60rzvvbj
     * @date 2021/3/20
     */
    private class Node<T> {
        T data;
        Node<T> next;

        /**
         * <p><b>方法名：</b>{@code Node}</p>
         * <p><b>功能：</b></p><br>构造方法
         *
         * @param data 数据
         * @author 60rzvvbj
         * @date 2021/3/20
         */
        Node(T data) {
            this.data = data;
        }
    }

    /**
     * 头结点
     */
    private Node<T> head;

    /**
     * 尾节点
     */
    private Node<T> last;

    /**
     * 队列大小
     */
    private int size;

    /**
     * <p><b>方法名：</b>{@code LinkQueue}</p>
     * <p><b>功能：</b></p><br>构造方法
     *
     * @author 60rzvvbj
     * @date 2021/3/20
     */
    public LinkQueue() {
        head = null;
        last = null;
    }

    /**
     * <p><b>方法名：</b>{@code offer}</p>
     * <p><b>功能：</b></p><br>将数据添加到队尾
     *
     * @param t 数据
     * @author 60rzvvbj
     * @date 2021/3/20
     */
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

    /**
     * <p><b>方法名：</b>{@code peek}</p>
     * <p><b>功能：</b></p><br>查看队首元素
     *
     * @return 队首元素，如果队列为空则返回null
     * @author 60rzvvbj
     * @date 2021/3/20
     */
    public T peek() {
        if (isEmpty()) {
            return null;
        } else {
            return last.data;
        }
    }

    /**
     * <p><b>方法名：</b>{@code poll}</p>
     * <p><b>功能：</b></p><br>取出队首元素
     *
     * @return 队首元素，如果队列为空则返回null
     * @author 60rzvvbj
     * @date 2021/3/20
     */
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

    /**
     * <p><b>方法名：</b>{@code isEmpty}</p>
     * <p><b>功能：</b></p><br>判断队列是否为空
     *
     * @return 是否为空
     * @author 60rzvvbj
     * @date 2021/3/20
     */
    public boolean isEmpty() {
        return head == null && last == null;
    }

    /**
     * <p><b>方法名：</b>{@code size}</p>
     * <p><b>功能：</b></p><br>获取队列长度
     *
     * @return 队列长度
     * @author 60rzvvbj
     * @date 2021/3/20
     */
    public int size() {
        return size;
    }

    /**
     * <p><b>方法名：</b>{@code iterator}</p>
     * <p><b>功能：</b></p><br>覆盖重写迭代器方法，返回一个迭代器
     *
     * @return 迭代器
     * @author 60rzvvbj
     * @date 2021/3/20
     */
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
