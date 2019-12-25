package Main;


import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("");
        frame.setSize(800,800);
        frame.setLocation(1080/2 , 1920/2 - 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new MainPanel());
        frame.setVisible(true);
    }
}
