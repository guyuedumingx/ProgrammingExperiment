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
    private String cardNo;

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

    public String getCard() {
        return cardNo;
    }

    public void setCard(String cardNo) {
        this.cardNo = cardNo;
    }

    @Override
    public String toString() {
        return "Person{" +
                "no='" + no + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", card=" + cardNo +
                '}';
    }
}
