package experiment5.ww.controller;

import experiment5.ww.db.Database;
import experiment5.ww.pojo.Card;
import experiment5.ww.pojo.CardOperator;
import experiment5.ww.pojo.CardUser;
import experiment5.ww.pojo.SystemManager;
import experiment5.ww.pojo.impl.CardOperatorImpl;
import experiment5.ww.pojo.impl.CardUserImpl;
import experiment5.ww.pojo.impl.SystemManagerImpl;
import experiment5.ww.util.DbUtil;
import experiment5.ww.view.UI;
import java.util.Scanner;

/**
 * @author yohoyes
 * @date 2021/5/24 22:39
 */
public class MainController {
    private static UserType type = null;
    private static SystemManager manager = new SystemManagerImpl();
    private static CardOperator operator = new CardOperatorImpl();
    private static CardUser user = new CardUserImpl();
    private static Database<Card> db = DbUtil.getCardDB();
    private Card card = null;
    private Scanner in = null;

    public MainController(Scanner in) {
        this.in = in;
    }

    public boolean login() {
        UI.UserChoices();
        int i = in.nextInt();
        switch (i) {
            case 1:
                type = UserType.SYSTEMMANAGER;
                return true;
            case 2:
                type = UserType.CARDOPERATOR;
                return true;
            case 3:
                type = UserType.CARDUSER;
                return true;
        }
        return false;
    }

    public boolean showOpera() {
        switch (type) {
            case SYSTEMMANAGER:
                UI.systemManagerChoices();
                boolean b = dealSystemOpera(in.nextInt());
                if(b){
                    showOpera();
                }
                break;
            case CARDOPERATOR:
                UI.cardOperatorChoices();
                break;
            case CARDUSER:
                UI.cardUserChoices();
                break;
        }
        return false;
    }

    public boolean dealSystemOpera(int opera) {
        switch (opera) {
            case 1:
                UI.noPromt();
                String no = in.next();
                UI.pwdPromt();
                String pwd = in.next();
                if(manager.login(no, pwd)) {
                    card = manager.query(no);
                    System.out.println("登录成功!");
                    return true;
                }else {
                    System.out.println("登录失败!");
                    return false;
                }
            case 2:
                UI.pwdPromt();
                String prePwd = in.next();
                UI.chPwdPromt();
                String afterPwd = in.next();
                return manager.chPwd(card.getNo(),prePwd, afterPwd);
        }
        return false;
    }

}
