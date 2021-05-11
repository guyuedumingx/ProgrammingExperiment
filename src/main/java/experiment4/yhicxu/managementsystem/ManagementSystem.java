package experiment4.yhicxu.managementsystem;

public class ManagementSystem {
    private InputDevice inputDevice;
    private Monitor monitor;
    private Controller controller;

    public ManagementSystem() {
        inputDevice = new InputDevice();
        monitor = new Monitor();
        controller = new Controller(inputDevice, monitor);
    }

    public void run() {
        monitor.showGreetings();
        while (true) {
            monitor.showMainMenu();
            int type = controller.inputMainMenuOption();
            if (type == 1) {
                controller.createTrafficNetwork();
            } else if (type == 2) {
                controller.operationTN();
            } else if (type == 3) {
                controller.save();
            } else if (type == 4) {
                controller.importTN();
            } else {
                monitor.showEnd();
                break;
            }
        }
    }

}
