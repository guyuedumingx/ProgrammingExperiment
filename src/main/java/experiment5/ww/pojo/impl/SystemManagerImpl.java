package experiment5.ww.pojo.impl;

import experiment5.ww.db.Database;
import experiment5.ww.pojo.Card;
import experiment5.ww.pojo.SystemManager;
import experiment5.ww.util.DbUtil;
import experiment5.ww.util.NoUtil;

/**
 * 系统管理员：登录，修改密码，创建、删除及查询用户
 * @author yohoyes
 * @date 2021/5/20 18:47
 */
public class SystemManagerImpl implements SystemManager {
    /**
     * 被操作的卡
     */
    private Card card = null;

    /**
     * 卡数据库
     */
    private Database<Card> db = DbUtil.getCardDB();

    /**
     * 根据学号和密码登录
     * @param no 学号
     * @param pwd 用户密码
     * @return 如果登录成功，则返回true，否则返回false
     */
    @Override
    public boolean login(String no, String pwd) {
        card = db.selectByNo(no);
        if(card == null){
            return false;
        }
        return card.getPwd().equals(pwd);
    }

    /**
     * 修改密码
     * @param prePwd 原用户密码
     * @param afterPwd 现用户密码
     * @return
     */
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
    public boolean delete(String no) {
        return db.deleteByNo(no);
    }

    @Override
    public Card query(String no) {
        card = db.selectByNo(no);
        return card;
    }
}
