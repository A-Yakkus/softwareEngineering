package acme;

import acme.data.DatabaseType;
import acme.data.MovieData;
import acme.data.MovieInfo;
import acme.data.MovieList;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * TODO implement as a JDialog that appears when user clicks ok on a successful match to the db(s).
 * */
public class MovieSelect extends JDialog {

    public MovieList active;

    public MovieSelect(JFrame parent, List<MovieInfo> movies){
        super(parent, "Select Movie");
        this.setSize(640,480);
        JPanel res = new JPanel(new GridLayout(5,5));
        for(MovieInfo m : movies){
            res.add(moviePanel(m));
        }
        this.setVisible(true);
        JPanel JP = new JPanel(new GridLayout(2,1));
        JComboBox<MovieList> lists = new JComboBox(ListPanel.availableLists.toArray());
        JP.add(lists);
        JP.add(res);
        this.add(JP);
        active = (MovieList) lists.getSelectedItem();
    }

    public JPanel moviePanel(MovieInfo movie){
        JPanel ret = new JPanel(new GridLayout(4,1));
        JLabel title = new JLabel(movie.Title);

        ret.add(title);
        JLabel year= new JLabel(""+movie.Year);
        ret.add(year);
        try {
            if(!movie.Poster.equals("N/A")){
                ret.add(new JLabel(new ImageIcon(ImageIO.read(new URL(movie.Poster)))));
            }
            else{
                ret.add(new JLabel("poster not found"));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        JButton btn = new JButton("Add");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    MovieData data = NetUtils.getMovieData(movie.Title, DatabaseType.OMDB);
                    if(active!=null){
                        active.movies.add(data);
                    }
                    FileManager.addMovie(active.listName, active);
                }catch (Exception ex){

                }
            }
        });
        ret.add(btn);
        return ret;
    }



}
