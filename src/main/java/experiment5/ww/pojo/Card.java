package experiment5.ww.pojo;

/**
 * 卡类
 * @author yohoyes
 * @date 2021/5/17 9:15
 */
public class Card {
    /**
     * 卡号
     */
    private String no;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户密码
     */
    private String userPassword;
    /**
     * 余额
     */
    private String balance;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object obj) {
        Card card = (Card) obj;
        return this.getNo().equals(card.getNo());
    }
}
