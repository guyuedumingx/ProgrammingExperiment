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
        return Color.color(getNumbers(),getNumbers(),getNumbers());
    }

    private static float getNumbers() {
        float left = 0.62f;
        float right = 0.86f;
        return  (float)Math.random()*(right-left) + left;
    }
}
