package experiment5.ww.util;

import experiment5.ww.db.Database;
import experiment5.ww.db.DatabaseImpl;

/**
 * 数据库工具类
 * @author yohoyes
 * @date 2021/5/19 17:08
 */
public class DbUtil {
    private static Database db = new DatabaseImpl();

    public static Database getDb() {
        return db;
    }
}
