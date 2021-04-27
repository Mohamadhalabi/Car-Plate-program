import javax.swing.*;

public class Main {

    public static void main(String[] args) {


        JFrame frame = new JFrame("LoginPanel");
        frame.setContentPane(new LoginPanel().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

}