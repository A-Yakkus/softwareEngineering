package w4;
import javax.swing.*;

public class test {
    public static void main(String[] args) {
        JFrame home = new JFrame();

        JButton homeView = new JButton("View Film");
        homeView.setBounds(75,75,100,40);

        JButton homeList = new JButton("List");
        homeList.setBounds(225,75,100,40);

        home.add(homeView);
        home.add(homeList);

        home.setSize(400,200);
        home.setLayout(null);
        home.setResizable(false);
        home.setVisible(true);
    }
}
