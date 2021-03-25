package experiment2.wangwei.utils;

import java.util.Arrays;
import java.util.Iterator;

/**
 * 自制Stack
 * @author yohoyes
 */
public class WStack<T> {
    private int top = -1;
    private static final int DEFAULT_SIZE = 10;
    private int size = DEFAULT_SIZE;
    private Object[] objects = new Object[size];

    public WStack() {

    }

    public void push(T t) {
        if(top > size / 2) {
            objects = expand();
        }
        objects[++top] = t;
    }

    public T peek(){
        if(top > -1){
            return (T)objects[top];
        }
        return null;
    }

    public T pop() {
        if(top > -1) {
            return (T)objects[top--];
        }
        throw new RuntimeException("空栈异常");
    }

    public int size() {
        return top + 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 搜索元素
     * 无该元素返回-1
     * @return 返回该元素与栈顶的距离
     */
    public int search(Object obj) {
        for(int i=top; i >= 0; i--) {
            if(objects[i].equals(obj)) {
                return top - i;
            }
        }
        return -1;
    }

    private Object[] expand() {
        return Arrays.copyOf(objects, size * 2);
    }

    public Itr iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<T> {
        int cursor = -1;

        @Override
        public boolean hasNext() {
            return cursor != top;
        }

        @Override
        public T next() {
            return (T)objects[++cursor];
        }
    }
}
