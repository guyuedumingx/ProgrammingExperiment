package experiment2.yhicxu.carpark;

import org.junit.Test;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

// 运行CarPark的测试类
public class TestCarPark {
    public static void main(String[] args) {

        // 每分钟的价钱3.2和最大容量3是随便设置的
        new CarPark(3.2, 3).run();
    }

    private PipedOutputStream pout;

    @Test
    public void test() throws IOException {
        PipedInputStream pin = new PipedInputStream();
        pout = new PipedOutputStream();
        pin.connect(pout);
        System.setIn(pin);
        write("1\n");
        write("aaa\n");
        write("3\n");
        write("1\n");
        write("bbb\n");
        write("1\n");
        write("ccc\n");
        write("1\n");
        write("ddd\n");
        write("3\n");
        write("1\n");
        write("eee\n");
        write("2\n");
        write("aaa\n");
        write("3\n");
        write("4\n");
        new CarPark(3.2 ,3).run();
    }

    private void write(String str) {
        try {
            pout.write(str.getBytes());
            System.out.write(str.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
