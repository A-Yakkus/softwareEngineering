package acme.ui;

import acme.FileManager;
import acme.NetUtils;
import acme.data.DatabaseType;
import acme.data.MovieData;
import acme.data.MovieInfo;
import acme.data.MovieList;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.List;

/**
 * TODO implement as a JDialog that appears when user clicks ok on a successful match to the db(s).
 * */
public class MovieSelect extends JDialog {

    public String current;
    JComboBox<String> lists;

    public MovieSelect(JFrame parent, List<MovieInfo> movies){
        super(parent, "Select Movie");
        this.setSize(640,480);
        JPanel pnl= new JPanel(new GridLayout(1,0));
        JScrollPane selector = new JScrollPane(pnl, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        for(MovieInfo m : movies){
            pnl.add(moviePanel(m));
        }
        this.setVisible(true);
        JPanel JP = new JPanel(new BorderLayout());
        lists = new JComboBox(ListPanel.availableLists.toArray());
        JP.add(lists, BorderLayout.PAGE_START);
        JP.add(selector, BorderLayout.CENTER);
        this.add(JP);
    }

    public JPanel moviePanel(MovieInfo movie){
        JPanel ret = new JPanel(new GridLayout(4,1));
        JLabel title = new JLabel(movie.Title);
        Dimension d = new Dimension(200,30);
        ret.setMaximumSize(d);
        ret.setSize(d);
        ret.setMinimumSize(d);
        ret.setPreferredSize(d);
        ret.add(title);
        JLabel year= new JLabel(""+movie.Year);
        ret.add(year);
        try {
            JLabel PosterLabel;
            if(!movie.Poster.equals("N/A")){
                PosterLabel = (new JLabel(new ImageIcon(ImageIO.read(new URL(movie.Poster)))));

            }
            else{
                PosterLabel = (new JLabel("poster not found"));
            }
            ret.add(PosterLabel);

        } catch (Exception e) {
            e.printStackTrace();
        }
        JButton btn = new JButton("Add");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    MovieData data = NetUtils.getMovieData(movie.Title, DatabaseType.OMDB);
                    MovieList ml = new MovieList();
                    ml.listName=(String)lists.getSelectedItem();
                    ml.movies = FileManager.movieData.get(ml.listName);
                    ml.movies.add(data);
                    FileManager.addMovie(ml.listName, ml);

                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        ret.add(btn);
        return ret;
    }



}
