package experiment4.yhicxu.lqqqlmbj.busrouter;

import java.util.Scanner;

import experiment4.yhicxu.lqqqlmbj.busrouter.router.ALGraph;
import experiment4.yhicxu.lqqqlmbj.busrouter.router.Lines;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void showMenu() {


    }

    public static void main(String[] args) {
        ALGraph graph = new ALGraph();
        Lines lines = new Lines(graph);
        Action action = new Action(graph, lines);
        while (true) {
            showMenu();
            int menu = scanner.nextInt();
            switch (menu) {


            }
        }

    }
}

	