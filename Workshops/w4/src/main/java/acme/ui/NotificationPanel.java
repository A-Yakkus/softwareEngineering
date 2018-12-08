package acme.ui;

import acme.MovieDBWindow;
import acme.data.CardList;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class NotificationPanel extends BaseUIPanel {
    private static NotificationPanel INSTANCE;

    public static NotificationPanel getInstance() {
        if(INSTANCE==null){
            synchronized (NotificationPanel.class){
                if(INSTANCE == null){
                    INSTANCE=new NotificationPanel();
                }
            }
        }
        return INSTANCE;
    }

    public NotificationPanel() {
        super(new GridLayout(3,1,0,30));
        JTextArea notice = new JTextArea("This product uses the TMDb API but is not endorsed or certified by TMDb.");
        JButton back = new JButton("Back");
        try {
            BufferedImage logo = ImageIO.read(new File(System.getProperty("user.dir")+"/src/main/java/acme/images/tmdb_logo.png"));
            JLabel logoLabel = new JLabel(new ImageIcon(logo));
            this.add(logoLabel);
        }
        catch(Exception e){
        }
        back.setSize(50,20);
        notice.setLineWrap(true);
        notice.setWrapStyleWord(true);
        notice.setEditable(false);

        this.add(notice);
        this.add(back);
        back.addActionListener(MovieDBWindow.changeVisible(CardList.HOME));
    }

}
