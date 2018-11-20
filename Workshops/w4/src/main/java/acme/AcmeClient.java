package acme;

import javax.swing.SwingUtilities;

/**
 * Main file for the project
 */
public class AcmeClient {

    /**
     * Runs the program
     * @param args any program arguements (there are none :P)
     */
    public static void main(String[] args){
        FileManager.readFiles();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MovieDBWindow();
            }
        });
    }

}
