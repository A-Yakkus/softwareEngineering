package acme.ui;

import acme.MovieDBWindow;
import acme.NetUtils;
import acme.data.CardList;
import acme.data.DatabaseType;
import acme.data.MovieInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class SearchPanel extends BaseUIPanel {
    private static DatabaseType TYPE;
    private static JTextField searchBox = new JTextField();

    /**
     * Object reference of the class for the singleton design pattern.
     */
    private static SearchPanel INSTANCE;

    /**
     * Initializer for the singleton. Only one instance of this object is needed/
     * It has been implemented in a thread safe manner,
     * as java swing is inherently thread-based.
     * @return the current instance of the class
     */
    public static SearchPanel getInstance() {
        if(INSTANCE==null){
            synchronized (SearchPanel.class){
                if(INSTANCE == null){
                    INSTANCE=new SearchPanel();
                }
            }
        }
        return INSTANCE;
    }

    private SearchPanel() {
        super(new GridLayout(2,2, 50,50));

        this.add(setupRadioButtons());//Empty to allow for changing of databases
        this.add(createSearchBox());
        JButton searchBtn = new JButton("Search MovieData");
        JButton home= new JButton("Home");
        this.add(home);
        this.add(searchBtn);
        home.addActionListener(MovieDBWindow.changeVisible(CardList.HOME));
        searchBtn.addActionListener(searchForMovies());
    }

    private JPanel createSearchBox(){
        JLabel lbl = new JLabel("Search for");
        lbl.setLabelFor(searchBox);
        JPanel sub = new JPanel(new GridLayout(2,1));
        sub.setBackground(Color.lightGray);
        sub.add(lbl);
        sub.add(searchBox);
        return sub;
    }


    private JPanel setupRadioButtons(){
        JRadioButton imdb = new JRadioButton(DatabaseType.OMDB.name().toUpperCase());
        imdb.setActionCommand(DatabaseType.OMDB.name().toLowerCase());
        imdb.addActionListener(databaseSelector());

        JRadioButton tmdb = new JRadioButton(DatabaseType.TMDB.name().toUpperCase());
        tmdb.setActionCommand(DatabaseType.TMDB.name().toLowerCase());
        tmdb.addActionListener(databaseSelector());

        ButtonGroup btnGrp = new ButtonGroup();
        btnGrp.add(imdb);
        btnGrp.add(tmdb);

        JPanel databaseSelector = new JPanel(new GridLayout(1,2));
        databaseSelector.add(imdb);
        databaseSelector.add(tmdb);
        return databaseSelector;
    }

    private ActionListener databaseSelector(){
        return e -> {
            switch (e.getActionCommand()){
                case "tmdb":TYPE=DatabaseType.TMDB;break;
                case "omdb":TYPE=DatabaseType.OMDB;break;
            }
        };
    }

    private ActionListener searchForMovies(){
        return e->{
            String searchText = searchBox.getText();
            List<MovieInfo> movies = null;
            switch (TYPE){
                case OMDB:
                    movies=NetUtils.searchMovies(searchText, DatabaseType.OMDB);
                    break;
                case TMDB:
                    movies=NetUtils.searchMovies(searchText, DatabaseType.TMDB);
                    break;
            }
            if (movies == null) {
                JOptionPane.showMessageDialog(null, "No movies found, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, String.format("Found %d Movies", movies.size()), "Acme MovieData Client", JOptionPane.INFORMATION_MESSAGE);
                new MovieSelect(MovieDBWindow.mainFrm, movies);
            }
        };
    }

}












//This is redundant.
/*if(dbOp == "omdb") {
                    java.util. = NetUtils.searchMovies(searchText, DatabaseType.OMDB);

                }

                else if(dbOp == "tmdb"){
                    List<MovieInfo> movies = NetUtils.searchMovies(searchText, DatabaseType.TMDB);
                    if (movies == null) {
                        JOptionPane.showMessageDialog(null, "No movies found, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, String.format("Found %d Movies", movies.size()), "Acme MovieData Client", JOptionPane.INFORMATION_MESSAGE);
                        new MovieSelect(mainFrm,movies);
                    }
                }*/
