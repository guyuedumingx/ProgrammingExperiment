package experiment5.ww.pojo;

import experiment5.ww.db.DbNode;

/**
 * 卡类
 * @author yohoyes
 * @date 2021/5/17 9:15
 */
public class Card implements DbNode {
    /**
     * 卡号
     */
    private String no;
    /**
     * 用户名
     */
    private String name;
    /**
     * 用户密码
     */
    private String userPassword;
    /**
     * 余额
     */
    private double balance;

    /**
     * 是否激活
     */
    private boolean active = true;

    @Override
    public String getNo() {
        return no;
    }

    public Card setNo(String no) {
        this.no = no;
        return this;
    }

    @Override
    public String getName() {
        return name;
    }

    public Card setName(String name) {
        this.name = name;
        return this;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public Card setUserPassword(String userPassword) {
        this.userPassword = userPassword;
        return this;
    }

    public double getBalance() {
        return balance;
    }

    public Card setBalance(double balance) {
        this.balance = balance;
        return this;
    }

    public boolean isActive() {
        return active;
    }

    public Card setActive(boolean active) {
        this.active = active;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        Card card = (Card) obj;
        return this.getNo().equals(card.getNo());
    }
}
