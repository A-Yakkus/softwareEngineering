package acme;

import acme.data.CardList;
import acme.data.DatabaseType;
import acme.data.MovieInfo;
import sun.misc.JavaLangAccess;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.Console;
import java.io.File;
import java.util.List;

/**
 * The file that manages the main window of the program
 */
public class MovieDBWindow {

    private JPanel cards;

    /**
     * Initialise the main logic of the program
     */

    /**
     * You shall use the TMDb logo to identify your use of the TMDb APIs.
     *
     * You shall place the following notice prominently on your application: "This product uses the TMDb API but is not endorsed or certified by TMDb."
     */
    public MovieDBWindow(){
        //Set up Main window
        JFrame mainFrm = new JFrame("Acme MovieData Client Home");
        mainFrm.setLocationRelativeTo(null);
        mainFrm.setSize(400,260);
        mainFrm.setResizable(false);
        mainFrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cards = new JPanel(new CardLayout());
        cards.add(homePanel(), CardList.HOME.toString());
        cards.add(searchPanel(),CardList.SEARCH.toString());
        cards.add(listPanel(),CardList.VIEW.toString());
        cards.add(noticePanel(),CardList.NOTICES.toString());
        mainFrm.add(cards);
        //set everything to visible
        mainFrm.setVisible(true);
    }

    /**
     * Configure the Homepage panel
     * This is the opening panel
     * @return The finalised panel.
     */
    private JPanel homePanel(){
        JPanel home = new JPanel(new GridLayout(3,2, 30, 0));
        JButton search = new JButton("Search");
        JButton viewLists = new JButton("Lists");
        JButton notice = new JButton("View notices");
        home.setBorder(BorderFactory.createMatteBorder(50,50,15,50, Color.lightGray));
        home.setBackground(Color.lightGray);
        home.add(search);
        home.add(viewLists);
        home.add(notice);
        search.addActionListener(changeVisible(CardList.SEARCH));
        viewLists.addActionListener(changeVisible(CardList.VIEW));
        notice.addActionListener(changeVisible(CardList.NOTICES));
        return home;
    }

    private JPanel noticePanel(){
        JPanel notices = new JPanel(new GridLayout(3,1,0,30));
        JTextArea notice = new JTextArea("This product uses the TMDb API but is not endorsed or certified by TMDb.");
        JButton back = new JButton("Back");
        System.out.println(System.getProperty("user.dir"));
        try {
            BufferedImage logo = ImageIO.read(new File(System.getProperty("user.dir")+"/src/main/java/acme/images/tmdb_logo.PNG"));
            JLabel logoLabel = new JLabel(new ImageIcon(logo));
            notices.add(logoLabel);
        }
        catch(Exception e){
        }
        back.setSize(50,20);
        notice.setLineWrap(true);
        notice.setWrapStyleWord(true);
        notice.setEditable(false);

        notices.add(notice);
        notices.add(back);
        back.addActionListener(changeVisible(CardList.HOME));
        return notices;
    }
    /**
     * Configure the Searching panel
     * @return The finalised panel.
     */
    private JPanel searchPanel(){
        JPanel pnl = new JPanel(new GridLayout(2,2, 50,50));
        pnl.add(new JPanel());//Empty to allow for changing of databases
        JTextField searchBox = new JTextField();
        JLabel lbl = new JLabel("Search for");
        lbl.setLabelFor(searchBox);
        JPanel sub = new JPanel(new GridLayout(2,1));
        sub.add(lbl);
        sub.add(searchBox);
        pnl.add(sub);
        JButton searchBtn = new JButton("Search MovieData");
        JButton home= new JButton("Home");
        pnl.add(home);
        pnl.add(searchBtn);
        home.addActionListener(changeVisible(CardList.HOME));
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchBox.getText();
                List<MovieInfo> movies = NetUtils.searchMovies(searchText, DatabaseType.OMDB);
                if(movies==null){
                    JOptionPane.showMessageDialog(null, "No movies found, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(null, String.format("Found %d Movies", movies.size()), "Acme MovieData Client", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        return pnl;
    }

    /**
     * TODO set me up
     * @return the finalised panel
     */
    private JPanel listPanel(){
        JPanel pnl = new JPanel();
        return pnl;
    }

    /**
     * Standardised way to change what is visible
     * @param layout the layout to change to
     * @return The ActionListener Object for buttons
     */
    private ActionListener changeVisible(CardList layout){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, layout.toString());
            }
        };
    }


}
