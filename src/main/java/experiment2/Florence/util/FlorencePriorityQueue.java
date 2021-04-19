package experiment2.Florence.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author Florence
 */
public class FlorencePriorityQueue<T> {
    private static final int DEFAULT_INITIAL_CAPACITY = 20;
    private T[] objects;
    private Comparator<T> comparator;
    private int index=0;
    public FlorencePriorityQueue(Comparator<T> comparator){
        this.objects= (T[]) new Object[DEFAULT_INITIAL_CAPACITY];
        this.comparator=comparator;
    }
    FlorencePriorityQueue(int initSize,Comparator<T> comparator){
        this.objects= (T[]) new Object[initSize];
        this.comparator=comparator;
    }
    private void swap(int index1,int index2){
        T temp = objects[index1];
        objects[index1]=objects[index2];
        objects[index2]=  temp;
    }
    public boolean empty(){
        return index==0;
    }
    public T poll(){
        if (!empty()) {
            T value = objects[1];
            swap(1, index--);
            objects[index+1]=null;
            sink(1);
            return value;
        }
        return null;
    }
    public T peek(){
        if (!empty()) {
            return objects[1];
        }
        return null;
    }
    public int size(){
        return index;
    }
    public void offer(T element){
        if (index+1>=objects.length){
            resize();
        }
        objects[++index]=element;
        swim(index);
    }
    private void swim(int k){
        if (k>1&&compare(objects[k],objects[k/2])<0){
            swap(k,k/2);
            swim(k/2);
        }
    }
    private void sink(int k){
        if (k*2<=index){
            int maxIndex=k*2;
            if (maxIndex<index){
                int compare = compare(objects[maxIndex], objects[maxIndex + 1]);
                if (compare>0){
                    maxIndex+=1;
                }
            }
            if (compare(objects[k],objects[maxIndex])>0) {
                swap(k, maxIndex);
                sink(maxIndex);
            }
        }
    }

    private int compare(T object1,T object2){
        T element1 = object1;
        T element2 = object2;
        return comparator.compare(element1,element2);
    }

    public static void main(String[] args) {
        FlorencePriorityQueue<Integer> integerFlorencePriorityQueue = new FlorencePriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });
        for (int i=9;i>=0;i--){
          integerFlorencePriorityQueue.offer(i);
        }
        while (!integerFlorencePriorityQueue.empty()){
            Integer poll = integerFlorencePriorityQueue.poll();
            System.out.println(poll);
        }
        System.out.println(integerFlorencePriorityQueue);
    }
    private void resize(){
        int oldCapacity = objects.length;
        int newCapacity = oldCapacity + ((oldCapacity < 64) ?
                (oldCapacity + 2) :
                (oldCapacity >> 1));
        objects = Arrays.copyOf(objects, newCapacity);
    }
    @Override
    public String toString() {
        return "FlorencePriorityQueue{" +
                "objects=" + Arrays.toString(objects) +
                '}';
    }

}
