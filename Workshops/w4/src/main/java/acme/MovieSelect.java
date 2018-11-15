package acme;

import acme.data.MovieInfo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * TODO implement as a JDialog that appears when user clicks ok on a successful match to the db(s).
 * */
public class MovieSelect extends JDialog {


    public MovieSelect(JFrame parent, List<MovieInfo> movies){
        super(parent, "Select Movie");
        this.setSize(640,480);
        JPanel res = new JPanel();

    }

    public JPanel moviePanel(MovieInfo movie){
        JPanel ret = new JPanel(new GridLayout(4,1));
        JLabel title = new JLabel(movie.Title);
        ret.add(title);
        JLabel year= new JLabel(""+movie.Year);
        ret.add(year);
        try {
            ret.add(new JLabel(new ImageIcon(ImageIO.read(new URL(movie.Poster)))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }



}
