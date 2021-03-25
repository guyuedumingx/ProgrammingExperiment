package experiment2.wangwei.utils;

import javafx.scene.paint.Color;

/**
 * @author yohoyes
 * @date 2021/3/24 13:51
 */
public class ColorUtil {
    public static Color ACTIVECOLOR = Color.RED;
    public static Color NORMALCOLOR = Color.DARKCYAN;

    public static Color getRamdonColor() {
        return Color.color((float)Math.random(),(float)Math.random(),(float)Math.random());
    }
}
