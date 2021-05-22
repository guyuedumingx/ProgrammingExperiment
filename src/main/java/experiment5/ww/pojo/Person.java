package experiment5.ww.pojo;

import experiment5.ww.db.DBNode;

/**
 * 用户类
 * @author yohoyes
 * @date 2021/5/22 10:44
 */
public class Person implements DBNode {
    private String no;
    private String name;
    private String age;
    private Card card;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    @Override
    public String toString() {
        return "Person{" +
                "no='" + no + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", card=" + card +
                '}';
    }
}
