package acme.ui;

import acme.MovieDBWindow;
import acme.data.CardList;
import javax.swing.*;
import java.awt.*;

public class HomePanel extends BaseUIPanel {

    public static HomePanel INSTANCE;

    public static HomePanel getInstance() {
        if(INSTANCE==null){
            synchronized (HomePanel.class){
                if(INSTANCE == null){
                    INSTANCE=new HomePanel();
                }
            }
        }
        return INSTANCE;
    }

    public HomePanel() {
        super(new GridLayout(3,2, 30, 0));
        JButton search = new JButton("Search");
        JButton viewLists = new JButton("Lists");
        JButton notice = new JButton("View notices");
        this.add(search);
        this.add(viewLists);
        this.add(notice);
        search.addActionListener(MovieDBWindow.changeVisible(CardList.SEARCH));
        viewLists.addActionListener(MovieDBWindow.changeVisible(CardList.VIEW));
        notice.addActionListener(MovieDBWindow.changeVisible(CardList.NOTICES));
    }
}
