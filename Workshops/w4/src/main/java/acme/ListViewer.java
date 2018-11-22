package acme;

import javax.swing.*;


public class ListViewer extends JDialog {
    public ListViewer(JFrame parent){
        super(parent, "Veiwing List");
        JScrollPane scroll = new JScrollPane();
        JList li = new JList(ListPanel.availableLists.get(0).movies.toArray());
        scroll.add(li);
        this.add(scroll);
        this.setVisible(true);
    }
}
