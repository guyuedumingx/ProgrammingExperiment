package experiment3.dqy.util;

import experiment3.dqy.HuffmanTreeNode;

public class HuffmanHeap {
    // h[N]存储堆中的值, h[1]是堆顶，x的左儿子是2x, 右儿子是2x + 1
    final int N = 200010;
    int size = 0;
    HuffmanTreeNode[] h = new HuffmanTreeNode[N];

    private void swap(HuffmanTreeNode x, HuffmanTreeNode y) {
        HuffmanTreeNode tmp = x;
        x = y;
        y = tmp;
    }

    // 交换两个点，及其映射关系
    private void heap_swap(int a, int b) {
        swap(h[a], h[b]);
    }

    private void down(int u) {
        int t = u;
        if (u * 2 <= size && h[u * 2].getFre() < h[t].getFre()) t = u * 2;
        if (u * 2 + 1 <= size && h[u * 2 + 1].getFre() < h[t].getFre()) t = u * 2 + 1;
        if (u != t) {
            heap_swap(u, t);
            down(t);
        }
    }

    private void up(int u) {
        while (u / 2 != 0 && h[u].getFre() < h[u / 2].getFre()) {
            heap_swap(u, u / 2);
            u >>= 1;
        }
    }

    public void insert(HuffmanTreeNode x) {
        size++;
        h[size] = x;
        up(size);
    }

    public HuffmanTreeNode top() {
        return h[1];
    }

    public void pop() {
        heap_swap(1, size);
        size--;
        down(1);
    }

    public int getSize() {
        return size;
    }
}
