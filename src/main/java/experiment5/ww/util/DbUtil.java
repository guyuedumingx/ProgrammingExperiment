package experiment5.ww.util;

import experiment5.ww.db.Database;
import experiment5.ww.db.impl.DatabaseImpl;
import experiment5.ww.pojo.Card;

/**
 * 数据库工具类
 * @author yohoyes
 * @date 2021/5/19 17:08
 */
public class DbUtil {
    private static Database<Card> cardDB = new DatabaseImpl();

    public static Database<Card> getCardDB() {
        return cardDB;
    }
}
