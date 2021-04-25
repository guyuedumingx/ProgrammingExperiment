package experiment3.dqy.util;

import experiment3.dqy.HuffmanTreeNode;

public class HuffmanHeap {
    // h[N]存储堆中的值, h[1]是堆顶，x的左儿子是2x, 右儿子是2x + 1
    final int N = 200010;
    int size = 0;
    HuffmanTreeNode[] h = new HuffmanTreeNode[N];

    // 交换两个点，及其映射关系
    private void heap_swap(int a, int b) {
        HuffmanTreeNode temp = h[a];
        h[a]=h[b];
        h[b]=temp;
    }

    //下沉
    private void down(int u) {
        int t = u;
        //当前点与左子树进行比较
        if (u * 2 <= size && h[u * 2].getFre() < h[t].getFre()) t = u * 2;
        //当前点与右子树进行比较
        if (u * 2 + 1 <= size && h[u * 2 + 1].getFre() < h[t].getFre()) t = u * 2 + 1;
        //如果当前点比两个子节点都小，那么可以停止了
        if (u != t) {
            heap_swap(u, t);
            down(t);
        }
    }

    //上浮
    private void up(int u) {
        //只要当前点比父节点要小就一直上浮
        while (u / 2 != 0 && h[u].getFre() < h[u / 2].getFre()) {
            heap_swap(u, u / 2);
            u >>= 1;
        }
    }

    //插入
    public void insert(HuffmanTreeNode x) {
        size++;
        //先把这个点插入到最后后面
        h[size] = x;
        //把这个点上浮
        up(size);
    }

    //获取堆顶的值
    public HuffmanTreeNode top() {
        return h[1];
    }

    //弹出堆顶
    public void pop() {
        //把堆顶的值与最后一个交换
        heap_swap(1, size);
        //size - 1相当于删去最后一个点
        size--;
        down(1);
    }

    //获取堆的大小
    public int getSize() {
        return size;
    }
}
