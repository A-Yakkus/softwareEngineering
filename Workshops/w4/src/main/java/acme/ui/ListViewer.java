package acme.ui;

import acme.data.MovieData;
import acme.data.MovieList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class ListViewer extends JDialog {
    public ListViewer(JFrame parent, MovieList ml){
        super(parent, "Veiwing List");

        this.setSize(640,480);
        JScrollPane scroll = new JScrollPane();
        scroll.setSize(new Dimension(100, 480));
        List<String> titles = new ArrayList<>();
        JPanel movDet = new JPanel(new CardLayout());
        movDet.add(new JPanel());
        for(int i = 0; i<ml.movies.size();i++){
            MovieData m = ml.movies.get(i);
            boolean flag= false;
            if(m.Title!=null){
                titles.add(m.Title);
                flag=!flag;
            }
            else if (m.original_title!=null||m.Title!=null){
                titles.add(m.title);
                flag=!flag;
            }
            else if (m.name!=null||m.original_name!=null){
                titles.add(m.name);
                flag=!flag;
            }
            if(flag){
                movDet.add(dispMovieDetails(m), titles.get(titles.size()-1));
            }

        }


        JList li = new JList(titles.toArray());
        li.addListSelectionListener(e -> {
            String i = titles.get(li.getSelectedIndex());
            CardLayout cl = (CardLayout)(movDet.getLayout());
            cl.show(movDet,i);
        });

        scroll.add(li);

        this.add(li, BorderLayout.WEST);
        this.add(movDet);
        this.setVisible(true);

    }

    public JPanel dispMovieDetails(MovieData m){
        JPanel ret =new JPanel(new GridLayout(1,2));
        JPanel data = new JPanel(new GridLayout(0,1));
        JPanel image= new JPanel();
        try{
            String path= m.poster_path==null?m.Poster:m.poster_path;
            ImageIcon io = new ImageIcon((path));
            image.add(new JLabel(io));
        }catch (Exception e){

        }
        JLabel lbl = null;
        for(Field f: m.getClass().getFields()){
            try {
                if(m.getClass().getField(f.getName())==null){
                    continue;
                }
                if(f.getName().contains("_")){
                    continue;
                }
                lbl = new JLabel(
                        f.getName()+" : "+
                                m.getClass().getField(
                                        f.getName()).
                                        get(m));

            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            finally{
                if(lbl!=null)
                    data.add(lbl);
            }
        }
        ret.add(data);
        ret.add(image);
        System.out.println("Added "+ m.Title );
        return ret;
    }
}
