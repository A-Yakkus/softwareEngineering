package acme;

import acme.data.DatabaseType;
import acme.data.MovieInfo;
import acme.data.SearchResults;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * File to handle networking requests
 */
public class NetUtils {

    protected static Gson g = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Searches for movies and returns a list of all responses
     * @param movieName The movie to search for
     * @param db The database to use
     * @return The found movies
     */
    public static List<MovieInfo> searchMovies(String movieName, DatabaseType db){
        String query="";
        StringBuilder sb =new StringBuilder();
        String returned = "";
        switch (db){
            case OMDB: query= "http://www.omdbapi.com/?apikey=fc545d36&s="+movieName;break;
            case TMDB: query= "Bad";
            default:query="Valid DB not used";
        }
        if(query.startsWith("http")){
            try {
                URL u = new URL(query);
                URLConnection connection = u.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String input;
                while((input=in.readLine())!=null){
                    sb.append(input);
                }
                in.close();
                returned = sb.toString();
            }
            catch (IOException e){
                e.printStackTrace();
            }
            SearchResults initial = g.fromJson(returned,SearchResults.class);
            List<MovieInfo> movies=new ArrayList<MovieInfo>();
            movies.addAll(initial.Search);
            int numPages = Math.floorDiv(initial.totalResults,10)+1;

            if(numPages>1){
                for(int i=2; i<=numPages; i++){
                    sb=new StringBuilder();
                    try {
                        URL u = new URL(query+"&page="+i);
                        URLConnection connection = u.openConnection();
                        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        String input;
                        while((input=in.readLine())!=null){
                            sb.append(input);
                        }
                        in.close();
                        returned = sb.toString();
                        System.out.println(returned);
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                    SearchResults res =g.fromJson(returned,SearchResults.class);
                    movies.addAll(res.Search);
                }
            }
            return movies;
        }
        else{
            return null;
        }
    }

}