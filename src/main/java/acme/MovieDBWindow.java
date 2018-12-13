package acme;

import acme.data.CardList;
import acme.ui.*;

import javax.swing.*;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The file that manages the main window of the program
 */
public class MovieDBWindow {

    public static JPanel cards;
    public static JFrame mainFrm = new JFrame("Acme MovieData Client Home");

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

        MovieDBWindow.mainFrm.setLocationRelativeTo(null);
        MovieDBWindow.mainFrm.setSize(400,260);
        MovieDBWindow.mainFrm.setResizable(false);
        MovieDBWindow.mainFrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MovieDBWindow.cards = new JPanel(new CardLayout());
        MovieDBWindow.cards.add(new HomePanel(), CardList.HOME.toString());
        MovieDBWindow.cards.add(SearchPanel.getInstance(),CardList.SEARCH.toString());
        MovieDBWindow.cards.add(ListPanel.getInstance(),CardList.VIEW.toString());
        MovieDBWindow.cards.add(NotificationPanel.getInstance(),CardList.NOTICES.toString());
        MovieDBWindow.mainFrm.add(MovieDBWindow.cards);
        //set everything to visible
        MovieDBWindow.mainFrm.setVisible(true);
    }
    /**
     * Standardised way to change what is visible
     * @param layout the layout to change to
     * @return The ActionListener Object for buttons
     */
    public static ActionListener changeVisible(CardList layout){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, layout.toString());
            }
        };
    }







}
