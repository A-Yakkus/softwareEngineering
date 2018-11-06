package w4;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class test {
    public static List ml = new ArrayList<String>();
    public static String res;
    public static Movie m;
    public static List movies = new ArrayList<Movie>();

    public static void main(String[] args) {
        JFrame home = new JFrame();

        //------Home Window------
        JButton hView = new JButton("View Film");
        hView.setBounds(75,75,100,40);

        JButton hList = new JButton("List");
        hList.setBounds(225,75,100,40);

        home.add(hView);
        home.add(hList);
        home.setLocationRelativeTo(null);
        home.setSize(400,200);
        home.setLayout(null);
        home.setResizable(false);
        home.setVisible(true);

        //------View Window------
        JTextField vIn = new JTextField();
        vIn.setBounds(100,25,200,20);
        vIn.setVisible(false);

        JButton vHome = new JButton("Home");
        vHome.setBounds(225,75,100,40);
        vHome.setVisible(false);

        JButton vSearch = new JButton("Search");
        vSearch.setBounds(75,75,100,40);
        vSearch.setVisible(false);

        home.add(vIn);
        home.add(vHome);
        home.add(vSearch);

        //------List Window------
        //JScrollPane lList = new JScrollPane();
        //test to get scrollbar working
        //set up list
        //String[] listString = {"test 1", "test 2", "test 3"};
        //put list into a jlist


        //------Found Window------
        JButton fAdd = new JButton("Add");
        fAdd.setBounds(25,75,100,40);
        fAdd.setVisible(false);

        JButton fHome = new JButton("Home");
        fHome.setBounds(150,75,100,40);
        fHome.setVisible(false);

        JButton fSearch = new JButton("Search again");
        fSearch.setBounds(275,75,100,40);
        fSearch.setVisible(false);

        home.add(fAdd);
        home.add(fHome);
        home.add(fSearch);


        hView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hView.setVisible(false);
                hList.setVisible(false);
                vHome.setVisible(true);
                vSearch.setVisible(true);
                vIn.setVisible(true);
            }
        });

        hList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hView.setVisible(false);
                hList.setVisible(false);
                JList listItems = new JList(ml.toArray());
                //put the new list into the pane
                JScrollPane lList = new JScrollPane(listItems);
                lList.setBounds(20,20,180,100);
                lList.setBackground(Color.BLACK);
                //lList.setVisible(false);
                home.add(lList);
                //lList.setVisible(true);
            }
        });

        vHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hView.setVisible(true);
                hList.setVisible(true);
                vHome.setVisible(false);
                vSearch.setVisible(false);
                vIn.setVisible(false);
            }
        });

        fHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fHome.setVisible(false);
                fAdd.setVisible(false);
                fSearch.setVisible(false);
                hView.setVisible(true);
                hList.setVisible(true);
            }
        });

        fSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fHome.setVisible(false);
                fAdd.setVisible(false);
                fSearch.setVisible(false);
                vHome.setVisible(true);
                vSearch.setVisible(true);
                vIn.setVisible(true);
            }
        });

        fAdd.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //if(m!=null)
                ml.add(m.Title);
                movies.add(m);
            }
        }));

        vSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = vIn.getText();
                res="";
                try{
                    URL url = new URL("http://www.omdbapi.com/?apikey=fc545d36&t="+title);
                    URLConnection con = url.openConnection();
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String input;
                    while((input=in.readLine())!=null){
                        res+=input;
                    }
                    in.close();
                    System.out.println(res);
                } catch (IOException f) {
                    f.printStackTrace();
                }
                if(!res.equals("")){

                    m= parseMovie(res);
                    String msg = String.format("<html>Title: %s,<br>Year: %s</html>", m.Title, m.Year);
                    JOptionPane.showMessageDialog(null, msg, "Movie Finder", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println(m.Title);
                    fAdd.setVisible(true);
                    fHome.setVisible(true);
                    fSearch.setVisible(true);
                    vIn.setVisible(false);
                    vHome.setVisible(false);
                    vSearch.setVisible(false);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Movie Not Found", "Movie Finder", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private static Movie parseMovie(String data){
        Gson g = new GsonBuilder().setPrettyPrinting().create();
        return g.fromJson(data, Movie.class);
    }
}
