package acme;

import acme.data.MovieData;

import javax.swing.SwingUtilities;
import java.lang.reflect.Field;

/**
 * Main file for the project
 */
public class AcmeClient {

    /**
     * Runs the program
     * @param args any program arguements (there are none :P)
     */
    public static void main(String[] args){
        for(Field f : MovieData.class.getFields()){

        }

        FileManager.makeList("Default");
        FileManager.readFiles();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MovieDBWindow();
            }
        });
    }

}
