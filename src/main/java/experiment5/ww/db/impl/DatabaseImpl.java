package experiment5.ww.db.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import experiment5.ww.db.Database;
import experiment5.ww.db.DBNode;
import experiment5.ww.pojo.Card;

import java.io.*;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 数据库实现类
 * 用链表来实现一个基本的数据存储功能
 * @author yohoyes
 * @date 2021/5/17 10:26
 */
public class DatabaseImpl<T extends DBNode> implements Database<T> {
    private Node<T> head = new Node<T>();
    private ObjectMapper mapper = new ObjectMapper();
    private String file;
    private FileWriter writer;

    private void save() {
        List<T> ts = selectAll();
        try {
            writer = new FileWriter(file);
            String s = mapper.writeValueAsString(ts);
            writer.write(s);
            writer.flush();
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public DatabaseImpl(String file) {
        this.file = file;
    }

    public void insert(T t) {
        Node<T> node = new Node<T>(t);
        node.setNext(head.getNext());
        head.setNext(node);
        save();
    }

    public boolean deleteByNo(String no) {
        Node<T> cur = head;
        while (cur.getNext() != null) {
            T data = cur.getNext().getData();
            if(data.getNo().equals(no)) {
                cur.setNext(cur.getNext().getNext());
                return true;
            }
            cur = cur.getNext();
        }
        return false;
    }

    public List<T> selectAll() {
        List<T> list = new ArrayList<>();
        Node<T> cur = head.getNext();
        while (cur != null) {
            T data = cur.getData();
            list.add(data);
            cur = cur.getNext();
        }
        return list;
    }

    public void update(T t) {
       Node<T> cur = head.getNext();
       while (cur != null) {
            if(cur.getData().equals(t)) {
                cur.setData(t);
            }
            cur = cur.getNext();
       }
       save();
    }

    public T selectByNo(String no) {
        Node<T> cur = head.getNext();
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