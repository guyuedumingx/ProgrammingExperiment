import org.junit.Test;
import util.Resource;

public class UtilsTest {

    @Test
    public void getTest() {
        String s = Resource.get("experiment1/Presidents.csv");
        System.out.println("google");
    }
}
