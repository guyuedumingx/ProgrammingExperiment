package experiment5.ww.pojo.impl;

import experiment5.ww.db.Database;
import experiment5.ww.pojo.Card;
import experiment5.ww.pojo.CardUser;
import experiment5.ww.util.DbUtil;

/**
 * 卡用户
 * 功能： 登录，修改密码，查询卡余额，充值及消费等
 * @author yohoyes
 * @date 2021/5/20 10:34
 */
public class CardUserImpl implements CardUser {
    /**
     * 被操作的卡
     */
    private Card card = null;
    private Database<Card> db = DbUtil.getDb();

    /**
     * 空实现
     */
    @Override
    public Card query(String no) {
        return null;
    }

    @Override
    public double balance() {
        return card.getBalance();
    }

    @Override
    public boolean login(String name, String pwd) {
        card = db.selectByName(name);
        if(card!=null && card.getUserPassword().equals(pwd)){
            return true;
        }
        return false;
    }

    @Override
    public boolean chPwd(String name, String prePwd, String afterPwd) {
        if(login(name,prePwd)){
            card.setUserPassword(afterPwd);
            return true;
        }
        return false;
    }

    @Override
    public boolean topUp(int money) {
        if(card == null) {
            return false;
        }
        card.setBalance(card.getBalance()+money);
        db.update(card);
        return true;
    }

    @Override
    public boolean consumption(int money) {
        if(card == null) {
            return false;
        }
        card.setBalance(card.getBalance()-money);
        db.update(card);
        return true;
    }
}
