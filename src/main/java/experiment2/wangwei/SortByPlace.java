package experiment2.wangwei;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * 不是本人的代码，
 * 帮别人看的，后面会删
 */
public class SortByPlace {
    public static void main(String[] args) {
        String txtfile = SortByPlace.class.getClassLoader().getResource("experiment1/Presidents.csv").getFile(); // this line should be modified!!!
        Node<President> link = new Node<President>(); // create a link with head node
        try {
            // read data from file
            BufferedReader br = new BufferedReader(new FileReader(txtfile));
            String line;
            String[] content = new String[6];
            while ((line = br.readLine()) != null) {
                content = line.split(",");// 将内容拆分为一个数组
                President president = new President(content[0], content[1], content[2], content[3], content[4], content[5]);
                Node<President> anode = new Node<>(president); // 将数组中的数据存到链表中
                anode.next = link.next;// 插入新节点
                link.next = anode;
            }
            br.close();
            // sort the list by bubble sorting
            boolean flag = true;
            while (link.next.getData().equals(link.next.next.getData()) > 0 && flag) {

                Node<President> m = link;
                flag = false;
                if (m.next != null) {
                    Node<President> n = m.next;
                    while (m.next != null && n.next != null) {
                        if (m.next.getData().equals(n.next.getData()) > 0)// 出生地不同，交换位置，序号大的往后
                        {
                            m.next = n.next;
                            n.next = m.next.next;
                            m.next.next = n;
                            flag = true;
                        }
                        if (m.next.getData().equals(n.next.getData()) == 0)// 出生地相同，按序号排序
                        {
                            if (m.next.getData().compareTo(n.next.getData()) >= 0)// 按序号排序，交换位置，序号大的往后
                            {
                                m.next = n.next;
                                n.next = m.next.next;
                                m.next.next = n;
                                flag = true;
                            }
                        }
                        // else 出生地顺序 不交换
                        m = m.next;// 移动p,q的位置，比较下两个节点no的大小
                        n = m.next;
                    }//while
                }//if
                Node<President> cur = link.next;
                while (cur != null) {
                    System.out.println(cur.getData().getNo() + cur.getData().getBirthPlace() + '\t');
                    cur = cur.next;
                }
                System.out.println();
            }//while
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}

class Node<T> {
    T data;
    Node<T> next;

    public Node(T data) {
        this.data = data;
    }

    public Node() {
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
