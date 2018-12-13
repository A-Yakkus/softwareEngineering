package acme.ui;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.*;


public abstract class BaseUIPanel extends JPanel {

    protected BaseUIPanel(LayoutManager manager){
        this.setBackground(Color.lightGray);
        this.setBorder(BorderFactory.createMatteBorder(50,50,50,50, Color.lightGray));
    }


}
