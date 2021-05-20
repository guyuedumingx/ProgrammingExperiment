package experiment5.ww.db;

import experiment5.ww.pojo.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库实现类
 * @author yohoyes
 * @date 2021/5/17 10:26
 */
public class DatabaseImpl implements Database{
    private static Node<Card> head = new Node<>();

    public void insert(Card card) {
        Node<Card> cardNode = new Node<>(card);
        cardNode.setNext(head.getNext());
        head.setNext(cardNode);
    }

    public void deleteByNo(String no) {
        Node<Card> cur = head;
        while (cur.getNext() != null) {
            Card data = cur.getNext().getData();
            if(data.getNo().equals(no)) {
                cur.setNext(cur.getNext().getNext());
            }
            cur = cur.getNext();
        }
    }

    public Card selectByName(String name) {
        Node<Card> cur = head.getNext();
        while (cur != null) {
            Card data = cur.getData();
            if(data.getUserName().equals(name)) {
                return data;
            }
            cur = cur.getNext();
        }
       return null;
    }

    public List<Card> selectAll() {
        List<Card> list = new ArrayList<>();
        Node<Card> cur = head.getNext();
        while (cur != null) {
            Card data = cur.getData();
            list.add(data);
        }
        return list;
    }


    public void update(Card card) {
       Node<Card> cur = head.getNext();
       while (cur != null) {
            if(cur.getData().equals(card)) {
                cur.setData(card);
            }
            cur = cur.getNext();
       }
    }

    public Card selectByNo(String no) {
        Node<Card> cur = head.getNext();
        while (cur != null) {
            if(cur.getData().getNo().equals(no)) {
                return cur.getData();
            }
            cur = cur.getNext();
        }
        return null;
    }
}

class Node<T> {
    private T data;
    private Node<T> next;

    public Node() {
    }

    public Node(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
}