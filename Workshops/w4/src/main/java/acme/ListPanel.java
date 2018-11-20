package acme;

import acme.data.MovieList;

import javax.swing.*;
import java.awt.*;

public class ListPanel extends JPanel {
    public JButton home = new JButton("Home");
    public ListPanel(){
        super(new GridLayout(2,1,0,10));
        JComboBox lists = new JComboBox();
        //(MovieList[])FileManager.movieData.toArray()
        this.add(lists);

        this.add(home);
        this.setBorder(BorderFactory.createMatteBorder(50,50,50,50, Color.lightGray));
        this.setBackground(Color.lightGray);

    }
}
