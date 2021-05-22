package experiment5.ww;

import experiment5.ww.pojo.Card;
import experiment5.ww.pojo.CardOperator;
import experiment5.ww.pojo.CardUser;
import experiment5.ww.pojo.SystemManager;
import experiment5.ww.pojo.impl.CardOperatorImpl;
import experiment5.ww.pojo.impl.CardUserImpl;
import experiment5.ww.pojo.impl.SystemManagerImpl;
import experiment5.ww.util.NoUtil;
import java.util.Scanner;

/**
 * 程序入口
 * @author yohoyes
 * @date 2021/5/20 18:51
 */
public class Main {

    private static SystemManager manager = new SystemManagerImpl();
    private static CardOperator operator = new CardOperatorImpl();
    private static CardUser user = new CardUserImpl();
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        String name = in.nextLine();
        String build = NoUtil.build(name);
        Card card = new Card();
        card.setNo(build)
            .setBalance(100)
            .setUserPassword("123456")
            .setUserName(name);
    }
}
