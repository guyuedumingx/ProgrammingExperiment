package experiment5.ww.pojo.impl;

import experiment5.ww.db.Database;
import experiment5.ww.pojo.Card;
import experiment5.ww.pojo.CardOperator;
import experiment5.ww.util.DbUtil;
import experiment5.ww.util.NoUtil;

/**
 * 卡业务员实现类
 * 功能: 登录，修改密码，查询卡信息，开卡，挂失及解挂等
 * @author yohoyes
 * @date 2021/5/17 10:21
 */
public class CardOperatorImpl implements CardOperator {
    /**
     * 被操作的卡
     */
    private Card card = null;
    private Database<Card> db = DbUtil.getCardDB();

    @Override
    public Card query(String no) {
        card = db.selectByNo(no);
        return card;
    }

    @Override
    public boolean login(String no, String pwd) {
        card = db.selectByNo(no);
        if(card == null){
            return false;
        }
        return card.getPwd().equals(pwd);
    }

    @Override
    public boolean chPwd(String name, String prePwd, String afterPwd) {
        if(login(name,prePwd)){
            card.setPwd(afterPwd);
            db.update(card);
            return true;
        }
        return false;
    }

    @Override
    public String register(String userName, String pwd) {
        card = new Card();
        String no = NoUtil.build(userName);
        while (db.selectByNo(no) != null){
            no = NoUtil.build(userName);
        }

        card.setName(userName)
            .setPwd(pwd)
            .setBalance(0)
            .setNo(no);
        db.insert(card);

        return card.getNo();
    }

    @Override
    public boolean freezing(String no) {
        card = db.selectByNo(no);
        if(card != null) {
            card.setActive(false);
            db.update(card);
            return true;
        }
        return false;
    }

    @Override
    public boolean unfreezing(String no, String pwd) {
        card = db.selectByNo(no);
        if(card != null) {
            card.setActive(true);
            db.update(card);
            return true;
        }
        return false;
    }
}
