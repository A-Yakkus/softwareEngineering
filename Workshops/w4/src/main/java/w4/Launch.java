package w4;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * This class runs the application
 */
public class Launch {

    /**
     * Entry point to the program, similar to int main() in C++
     * TODO add function to add to watch list and loop the program
     * @param args command line arguments
     */
    public static void main(String[] args){
        String title = JOptionPane.showInputDialog(null, "Enter movie title", "Movie Finder", JOptionPane.QUESTION_MESSAGE);
        String res="";
        //try to get data of movie
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(!res.equals("")){
            Movie m = parseMovie(res);
            String msg = String.format("<html>Title: %s,<br>Year: %s</html>", m.title, m.year);
            JOptionPane.showMessageDialog(null, msg, "Movie Finder", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(null, "Movie Not Found", "Movie Finder", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static Movie parseMovie(String data){
        Gson g = new GsonBuilder().setPrettyPrinting().create();
        return g.fromJson(data, Movie.class);
    }

}
