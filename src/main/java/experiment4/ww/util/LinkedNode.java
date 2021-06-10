package experiment4.ww.util;

import experiment4.dqy.util.LinkList;
import util.graphutil.GraphNode;

import java.util.Iterator;

/**
 * 邻接链表的节点
 * @author yohoyes
 * @date 2021/5/7 22:12
 */
public class LinkedNode<T> {
    private LinkedNode<T> next = null;
    private T data = null;
    //站点间距离
    private float len = 0;

    public LinkedNode(){
    }


    public LinkedNode(T data) {
        this.data = data;
    }

    public LinkedNode(T data, float len) {
        this.data = data;
        this.len = len;
    }

    public LinkedNode<T> getNext() {
        return next;
    }

    public void setNext(LinkedNode<T> next) {
        this.next = next;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public float getLen() {
        return len;
    }

    public void setLen(float len) {
        this.len = len;
    }

    @Override
    public String toString() {
        String str = ((GraphNode)data).getName();
        if(len != 0){
            str += "(" + len + ")";
        }
        return str;
    }
}
