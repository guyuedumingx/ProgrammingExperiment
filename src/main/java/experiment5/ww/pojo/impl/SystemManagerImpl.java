package experiment5.ww.pojo.impl;

import experiment5.ww.db.Database;
import experiment5.ww.pojo.Card;
import experiment5.ww.pojo.SystemManager;
import experiment5.ww.util.DbUtil;
import experiment5.ww.util.NoUtil;

/**
 * @author yohoyes
 * @date 2021/5/20 18:47
 */
public class SystemManagerImpl implements SystemManager {
    /**
     * 被操作的卡
     */
    private Card card = null;
    private Database<Card> db = DbUtil.getDb();

    @Override
    public boolean login(String name, String pwd) {
//        card = db.selectByName(name);
//        if(card!=null && card.getUserPassword().equals(pwd)){
//            return true;
//        }
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
    public String register(String userName, String pwd) {
        card = new Card();
        String no = NoUtil.build(userName);
        while (db.selectByNo(no) != null){
            no = NoUtil.build(userName);
        }

        card.setName(userName)
                .setUserPassword(pwd)
                .setBalance(0)
                .setNo(no);
        db.insert(card);

        return card.getNo();
    }

    @Override
    public boolean delete(String no) {
        boolean b = db.deleteByNo(no);
        return b;
    }

    @Override
    public Card query(String no) {
        card = db.selectByNo(no);
        return card;
    }
}
