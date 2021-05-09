package experiment4.yhicxu.managementsystem;

import java.util.Scanner;

public class InputDevice {

    private final Scanner sca;

    public InputDevice() {
        sca = new Scanner(System.in);
    }

    public int readInt(String tips) {
        while (true) {
            System.out.print(tips);
            String in = sca.next();
            Integer res = null;
            try {
                res = Integer.parseInt(in);
            } catch (Exception e) {
                System.out.println("输入有误！");
            }
            if (res != null) {
                return res;
            }
        }
    }

    public String readString(String tips) {
        System.out.print(tips);
        return sca.next();
    }

    public int readRangeInt(String tips, int l, int r) {
        System.out.print(tips);
        while (true) {
            String in = sca.next();
            Integer res = null;
            try {
                res = Integer.parseInt(in);
            } catch (Exception e) {
                System.out.print("输入有误，请重新输入：");
                continue;
            }
            if (res != null && res >= l && res <= r) {
                return res;
            }
            System.out.print("输入有误，请重新输入：");
        }
    }
}
