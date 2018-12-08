package acme.ui;

import javax.swing.*;


public class ListViewer extends JDialog {
    public ListViewer(JFrame parent){
        super(parent, "Veiwing List");
        JScrollPane scroll = new JScrollPane();
        JList li = new JList();
        scroll.add(li);
        this.add(scroll);
        this.setVisible(true);
    }
}
