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
import java.util.List;
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
                boolean a = dealOperator(in.nextInt());
                if(a){
                    showOpera();
                }
                break;
            case CARDUSER:
                UI.cardUserChoices();
                boolean c = dealCardUserOpera(in.nextInt());
                if(c){
                    showOpera();
                }
                break;
        }
        return false;
    }

    public boolean dealOperator(int opera) {
        switch (opera) {
            case 1:
                UI.noPromt();
                String no = in.next();
                UI.pwdPromt();
                String pwd = in.next();
                if (operator.login(no, pwd)) {
                    card = operator.query(no);
                    System.out.println("登录成功!");
                    return true;
                } else {
                    System.out.println("登录失败!");
                    return false;
                }
            case 2:
                UI.pwdPromt();
                String prePwd = in.next();
                UI.chPwdPromt();
                String afterPwd = in.next();
                return operator.chPwd(card.getNo(), prePwd, afterPwd);
            case 3:
                List<Card> list1 = db.selectAll();
                for(Card card : list1) {
                    System.out.println(card);
                }
                UI.noPromt();
                Card query = operator.query(in.next());
                System.out.println(query);
                return true;
            case 4:
                UI.namePromt();
                String name = in.next();
                UI.pwdPromt();
                String pwds = in.next();
                String register = operator.register(name, pwds);
                System.out.println("添加成功!");
                return true;
            case 5:
                UI.noPromt();
                boolean freezing = operator.freezing(in.next());
                if(freezing) {
                    System.out.println("挂失成功");
                }else {
                    System.out.println("挂失失败");
                }
                return freezing;
            case 6:
                UI.noPromt();
                String no2 = in.next();
                UI.pwdPromt();
                String pwd2 = in.next();
                boolean unfreezing = operator.unfreezing(no2, pwd2);
                if(unfreezing) {
                    System.out.println("解挂成功");
                }else {
                    System.out.println("解挂失败");
                }
                return unfreezing;
            case 7:
                List<Card> list = db.selectAll();
                for(Card card : list) {
                    System.out.println(card);
                }
                return true;
            case 8:
                login();
                return false;
        }
        return false;
    }

    public boolean dealCardUserOpera(int opera) {
        switch (opera) {
            case 1:
                UI.noPromt();
                String no = in.next();
                UI.pwdPromt();
                String pwd = in.next();
                if (user.login(no, pwd)) {
                    card = user.query(no);
                    System.out.println("登录成功!");
                    return true;
                } else {
                    System.out.println("登录失败!");
                    return false;
                }
            case 2:
                UI.pwdPromt();
                String prePwd = in.next();
                UI.chPwdPromt();
                String afterPwd = in.next();
                return user.chPwd(card.getNo(), prePwd, afterPwd);
            case 3:
                double balance = user.balance();
                System.out.println("您剩余的余额是: " + balance);
                return true;
            case 4:
                UI.topUpPromt();
                int i = in.nextInt();
                boolean b = user.topUp(i);
                if(b) {
                    System.out.println("充值成功!");
                }else {
                    System.out.println("充值失败!");
                }
                return b;
            case 5:
                UI.consumptionPromt();
                int money = in.nextInt();
                boolean consumption = user.consumption(money);
                if(consumption) {
                    System.out.println("消费成功!");
                }else {
                    System.out.println("消费失败!");
                }
                return consumption;
            case 6:
                List<Card> list = db.selectAll();
                for(Card card : list) {
                    System.out.println(card);
                }
                return true;
            case 7:
                login();
                return false;
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
            case 3:
                UI.namePromt();
                String name = in.next();
                UI.pwdPromt();
                String pwds = in.next();
                manager.register(name, pwds);
                System.out.println("添加成功!");
                List<Card> list = db.selectAll();
                for(Card card : list) {
                    System.out.println(card);
                }
                return true;
            case 4:
                List<Card> list1 = db.selectAll();
                for(Card card : list1) {
                    System.out.println(card);
                }
                UI.noPromt();
                String queryNo = in.next();
                Card query = manager.query(queryNo);
                System.out.println(query);
                return true;
            case 5:
                UI.noPromt();
                boolean delete = manager.delete(in.next());
                List<Card> list2 = db.selectAll();
                for(Card card : list2) {
                    System.out.println(card);
                }
                if(delete){
                    System.out.println("删除成功!");
                    return true;
                }else {
                    System.out.println("删除失败!");
                    return false;
                }
            case 6:
                List<Card> res = db.selectAll();
                for(Card card : res) {
                    System.out.println(card);
                }
                return true;
            case 7:
                login();
                return false;
        }
        return false;
    }

}
