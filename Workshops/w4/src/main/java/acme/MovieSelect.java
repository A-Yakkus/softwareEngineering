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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * TODO implement as a JDialog that appears when user clicks ok on a successful match to the db(s).
 * */
public class MovieSelect extends JDialog {

    public String current;

    public MovieSelect(JFrame parent, List<MovieInfo> movies){
        super(parent, "Select Movie");
        this.setSize(640,480);
        JPanel res = new JPanel(new GridLayout(5,5));
        for(MovieInfo m : movies){
            res.add(moviePanel(m));
        }
        this.setVisible(true);
        JPanel JP = new JPanel(new BorderLayout());
        JComboBox<String> lists = new JComboBox(ListPanel.availableLists.toArray());
        JP.add(lists, BorderLayout.PAGE_START);
        JP.add(res, BorderLayout.CENTER);
        this.add(JP);
        current = (String)lists.getSelectedItem();
    }

    public JPanel moviePanel(MovieInfo movie){
        JPanel ret = new JPanel(new GridLayout(4,1));
        JLabel title = new JLabel(movie.Title);

        ret.add(title);
        JLabel year= new JLabel(""+movie.Year);
        ret.add(year);
        try {
            JLabel PosterLabel;
            if(!movie.Poster.equals("N/A")){
                PosterLabel = (new JLabel(new ImageIcon(ImageIO.read(new URL(movie.Poster)))));
                PosterLabel.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        //ImageIcon IO = new ImageIcon();
                        //IO.getImage().
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                });
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
                    ml.listName=current;
                    ml.movies = FileManager.movieData.get(current);
                    ml.movies.add(data);
                    FileManager.addMovie(current, ml);

                }catch (Exception ex){

                }
            }
        });
        ret.add(btn);
        return ret;
    }



}
