package experiment2.yhicxu.utils;

import java.util.Iterator;

public class SeqStack<T> implements Iterable<T> {
    private final int capacity;
    private T[] arr;
    private int index;

    public SeqStack(int capacity) {
        this.capacity = capacity;
        arr = (T[]) new Object[capacity];
        index = 0;
    }

    public boolean isEmpty() {
        return index == 0;
    }

    public boolean isFull() {
        return index == capacity - 1;
    }

    public T push(T t) {
        if (isFull()) {
            return null;
        } else {
            arr[index++] = t;
            return t;
        }
    }

    public T top() {
        if (isEmpty()) {
            return null;
        } else {
            return arr[index - 1];
        }
    }

    public T pop() {
        if (isEmpty()) {
            return null;
        } else {
            index--;
            return arr[index];
        }
    }

    public int size() {
        return index;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int p = 0;

            @Override
            public boolean hasNext() {
                return p != index;
            }

            @Override
            public T next() {
                return arr[p++];
            }
        };
    }

    public static void main(String[] args) {
        SeqStack<Integer> stack = new SeqStack<>(10);
        for(Integer t:stack){

        }
    }

}


