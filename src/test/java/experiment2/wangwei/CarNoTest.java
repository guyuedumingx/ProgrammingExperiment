package experiment2.wangwei;

import org.junit.Test;
import util.CarNoUtil;

/**
 * @author yohoyes
 * @date 2021/3/23 21:40
 */
public class CarNoTest {

    @Test
    public void buildTest() {
        String build = CarNoUtil.build();
        System.out.println(build);
    }
}
