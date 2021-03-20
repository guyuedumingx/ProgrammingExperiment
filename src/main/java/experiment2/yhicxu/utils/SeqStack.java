package experiment2.yhicxu.utils;

import java.util.Iterator;

/**
 * <p><b>类名：</b>{@code SeqStack}</p>
 * <p><b>功能：</b></p><br>顺序栈
 * <p><b>方法：</b></p>
 * <br> {@link #SeqStack(int)}构造方法
 * <br> {@link #isEmpty()}判断栈是否为空
 * <br> {@link #isFull()}判断栈是否已满
 * <br> {@link #push(Object)}将数据压栈
 * <br> {@link #top()}查看栈顶元素
 * <br> {@link #pop()}弹出栈顶元素
 * <br> {@link #size()}获取栈中元素个数
 * <br> {@link #iterator()}覆盖重写迭代器方法，返回一个迭代器
 *
 * @author 60rzvvbj
 * @date 2021/3/20
 */
public class SeqStack<T> implements Iterable<T> {

    /**
     * 容量
     */
    private final int capacity;

    /**
     * 用来存放数据的数组
     */
    private T[] arr;

    /**
     * 栈当前长度
     */
    private int length;

    /**
     * <p><b>方法名：</b>{@code SeqSttck}</p>
     * <p><b>功能：</b></p><br>构造方法
     *
     * @param capacity 容量
     * @author 60rzvvbj
     * @date 2021/3/20
     */
    public SeqStack(int capacity) {
        this.capacity = capacity;
        arr = (T[]) new Object[capacity];
        length = 0;
    }

    /**
     * <p><b>方法名：</b>{@code isEmpty}</p>
     * <p><b>功能：</b></p><br>判断栈是否为空
     *
     * @return 是否为空
     * @author 60rzvvbj
     * @date 2021/3/20
     */
    public boolean isEmpty() {
        return length == 0;
    }

    /**
     * <p><b>方法名：</b>{@code full}</p>
     * <p><b>功能：</b></p><br>判断栈是否已满
     *
     * @return 是否已满
     * @author 60rzvvbj
     * @date 2021/3/20
     */
    public boolean isFull() {
        return length == capacity;
    }

    /**
     * <p><b>方法名：</b>{@code push}</p>
     * <p><b>功能：</b></p><br>将数据压栈
     *
     * @param t 数据
     * @return 如果压栈成功，返回压栈的元素，否则返回null
     * @author 60rzvvbj
     * @date 2021/3/20
     */
    public T push(T t) {
        if (isFull()) {
            return null;
        } else {
            arr[length++] = t;
            return t;
        }
    }

    /**
     * <p><b>方法名：</b>{@code top}</p>
     * <p><b>功能：</b></p><br>查看栈顶元素
     *
     * @return 栈顶元素
     * @author 60rzvvbj
     * @date 2021/3/20
     */
    public T top() {
        if (isEmpty()) {
            return null;
        } else {
            return arr[length - 1];
        }
    }

    /**
     * <p><b>方法名：</b>{@code pop}</p>
     * <p><b>功能：</b></p><br>弹出栈顶元素
     *
     * @return 栈顶元素
     * @author 60rzvvbj
     * @date 2021/3/20
     */
    public T pop() {
        if (isEmpty()) {
            return null;
        } else {
            length--;
            return arr[length];
        }
    }

    /**
     * <p><b>方法名：</b>{@code size}</p>
     * <p><b>功能：</b></p><br>获取栈中元素个数
     *
     * @return 栈中元素个数
     * @author 60rzvvbj
     * @date 2021/3/20
     */
    public int size() {
        return length;
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
            private int p = length - 1;

            @Override
            public boolean hasNext() {
                return p != -1;
            }

            @Override
            public T next() {
                return arr[p--];
            }
        };
    }
}


