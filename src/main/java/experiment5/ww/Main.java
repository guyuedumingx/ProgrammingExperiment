package experiment5.ww;

import experiment5.ww.controller.MainController;
import experiment5.ww.db.Database;
import experiment5.ww.pojo.Card;
import experiment5.ww.util.DbUtil;
import experiment5.ww.util.NoUtil;
import java.util.List;
import java.util.Scanner;

/**
 * 程序入口
 * @author yohoyes
 * @date 2021/5/20 18:51
 */
public class Main {

    private static Scanner in = new Scanner(System.in);
    private static Database<Card> db = DbUtil.getCardDB();
    private static String[] nameList = {"aa", "bb", "cc"};

    public static void main(String[] args) {
        init();
        List<Card> list = db.selectAll();
        for(Card card : list){
            System.out.println(card);
        }
        MainController controller = new MainController(in);
        while(!controller.login()){}
        controller.showOpera();

    }

    public static void init(){
        for(String name : nameList) {
            String build = NoUtil.build(name);
            Card card = new Card();
            card.setNo(build)
                .setBalance(100)
                .setPwd("111")
                .setName(name);
            db.insert(card);
        }
    }
}
