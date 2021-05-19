package experiment5.ww.pojo.impl;

import experiment5.ww.db.Database;
import experiment5.ww.pojo.Card;
import experiment5.ww.pojo.CardOperator;
import experiment5.ww.util.DbUtil;

/**
 * 卡业务员实现类
 * @author yohoyes
 * @date 2021/5/17 10:21
 */
public class CardOperatorImpl implements CardOperator {
    /**
     * 被操作的卡
     */
    private Card card = null;
    private Database db = DbUtil.getDb();

    @Override
    public Card query(String no) {

        return null;
    }

    @Override
    public boolean login(String name, String pwd) {

        return false;
    }

    @Override
    public boolean chPwd(String name, String prePwd, String afterPwd) {

        return false;
    }

    @Override
    public String register(String userName, String pwd) {

        return null;
    }

    @Override
    public boolean freezing(String no) {

        return false;
    }

    @Override
    public boolean unfreezing(String no, String pwd) {

        return false;
    }
}
