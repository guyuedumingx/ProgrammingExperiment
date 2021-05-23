package experiment5.ww.pojo;

import experiment5.ww.db.DBNode;

/**
 * 卡类
 * @author yohoyes
 * @date 2021/5/17 9:15
 */
public class Card implements DBNode {
    /**
     * 卡号
     */
    private String no;
    /**
     * 用户no
     */
    private String personNo;
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

    public String getPersonNo() {
        return personNo;
    }

    public Card setPersonNo(String personNo) {
        this.personNo = personNo;
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
