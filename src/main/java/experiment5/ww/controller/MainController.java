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

    public boolean opera() {
        switch (type) {
            case SYSTEMMANAGER:
                UI.systemManagerChoices();
                return dealSystemOpera(in.nextInt());
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
                String no = in.nextLine();
                UI.pwdPromt();
                String pwd = in.nextLine();
                if(manager.login(no, pwd)) {
                    card = manager.query(no);
                    return true;
                }else {
                    return false;
                }
            case 2:
                UI.pwdPromt();
                String prePwd = in.nextLine();
                UI.chPwdPromt();
                String afterPwd = in.nextLine();
                return manager.chPwd(card.getNo(),prePwd, afterPwd);
        }
        return false;
    }

}
