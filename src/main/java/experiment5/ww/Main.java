package experiment5.ww;

import experiment5.ww.db.Database;
import experiment5.ww.pojo.Card;
import experiment5.ww.pojo.CardOperator;
import experiment5.ww.pojo.CardUser;
import experiment5.ww.pojo.SystemManager;
import experiment5.ww.pojo.impl.CardOperatorImpl;
import experiment5.ww.pojo.impl.CardUserImpl;
import experiment5.ww.pojo.impl.SystemManagerImpl;
import experiment5.ww.util.DbUtil;
import experiment5.ww.util.NoUtil;
import java.util.Scanner;

/**
 * 程序入口
 * @author yohoyes
 * @date 2021/5/20 18:51
 */
public class Main {

    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        String name = in.nextLine();
        String build = NoUtil.build(name);
        Card card = new Card();
        card.setNo(build)
            .setBalance(100)
            .setPwd("123456")
            .setName(name);
//        db.insert(card);
//        Card card1 = db.selectByNo(card.getNo());
//        System.out.println(card1);
    }
}
