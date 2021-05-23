package experiment5.ww.util;

import experiment5.ww.db.Database;
import experiment5.ww.db.impl.DatabaseImpl;
import experiment5.ww.pojo.Card;
import experiment5.ww.pojo.Person;

/**
 * 数据库工具类
 * @author yohoyes
 * @date 2021/5/19 17:08
 */
public class DbUtil {
    private static Database<Card> cardDB = new DatabaseImpl();
    private static Database<Person> personDB = new DatabaseImpl<>();

    public static Database<Card> getCardDB() {
        return cardDB;
    }

    public static Database<Person> getPersonDB() {
        return personDB;
    }
}
