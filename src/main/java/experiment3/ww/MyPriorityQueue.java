package experiment3.ww;

/**
 * 优先队列
 * @author yohoyes
 * @date 2021/4/17 16:06
 */
public class MyPriorityQueue<T extends Comparable<T>> {
    private Object[] heap = null;
    private int N = 0;

    public MyPriorityQueue(int maxSize){
        heap = new Object[maxSize+1];
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void add(T t) {
        heap[++N] = t;
        heapfiy(1);
    }

    public T poll() {
        if (N > 0){
            T node = (T)heap[1];
            swap(1,N);
            N--;
            heapfiy(1);
            return  node;
        }
        return null;
    }

    /**
     * 堆调整
     */
    protected void heapfiy(int cur) {
        int left = 2 * cur;
        int right = 2 * cur + 1;
        int min = cur;

        if(left<=N && ((T)heap[left]).compareTo((T)heap[min])<0) {
            min = left;
        }

        if(right<=N && ((T)heap[right]).compareTo(((T)heap[min]))<0) {
            min = right;
        }

        if(min != cur) {
            swap(min,cur);
            //主要用于下沉
            heapfiy(min);
        }
    }

    protected void swap(int max, int cur) {
        Object tmp = heap[max];
        heap[max] = heap[cur];
        heap[cur] = tmp;
    }
}
