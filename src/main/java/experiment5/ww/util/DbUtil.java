package experiment5.ww.util;

import experiment5.ww.db.Database;
import experiment5.ww.db.impl.DatabaseImpl;
import experiment5.ww.pojo.Card;
import util.Resource;

import java.net.URL;

/**
 * 数据库工具类
 * @author yohoyes
 * @date 2021/5/19 17:08
 */
public class DbUtil {
    private static String file = "experiment5/db.json";
    private static Database<Card> cardDB = new DatabaseImpl(Resource.get(file));

    public static Database<Card> getCardDB() {
        return cardDB;
    }
}
