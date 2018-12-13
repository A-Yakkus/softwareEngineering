package acme.ui;

import acme.FileManager;
import acme.MovieDBWindow;
import acme.data.CardList;
import acme.data.MovieData;
import acme.data.MovieList;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ListPanel extends BaseUIPanel {
    private JButton home = new JButton("Home");
    protected static List<String> availableLists = new ArrayList<String>();

    private static ListPanel INSTANCE;

    public static ListPanel getInstance() {
        if(INSTANCE==null){
            synchronized (ListPanel.class){
                if(INSTANCE == null){
                    INSTANCE=new ListPanel();
                }
            }
        }
        return INSTANCE;
    }

    public ListPanel(){
        super(new GridLayout(2,1,0,10));

        for(String s : FileManager.movieData.keySet()){
            availableLists.add(s);
        }
        JComboBox lists = new JComboBox(availableLists.toArray());
        //(MovieList[])FileManager.movieData.toArray()
        this.add(lists);

        this.add(home);
        home.addActionListener(MovieDBWindow.changeVisible(CardList.HOME));
        this.setBorder(BorderFactory.createMatteBorder(50,50,50,50, Color.lightGray));
        this.setBackground(Color.lightGray);

        JButton createList = new JButton("Create List");

        createList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newListName = JOptionPane.showInputDialog("Enter List Name");
                FileManager.makeList(newListName);
                availableLists.add(newListName);
                lists.addItem(newListName);


            }
        });
        this.add(createList);
        JButton veiwList = new JButton("View");
        veiwList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String active = (String)lists.getSelectedItem();
                MovieList ml = FileManager.readFile(active);
                new ListViewer(MovieDBWindow.mainFrm, ml);
            }
        });
        this.add(veiwList);
    }


}
