package acme;

import acme.data.MovieData;
import acme.data.MovieList;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileManager {

    public static Map<String,List<MovieData>> movieData = new HashMap<>();

    /**
     * Read all text/json files from path
     */
    public static void readFiles(){
        try{
            File f = new File(System.getProperty("user.dir")+"/lists");
            if(!f.exists()){
                f.mkdir();
            }
            if(f.isFile()){
                System.out.println(f.getAbsolutePath());
                throw new RuntimeException("Y U DO THIS TO MEEEEEEE\n This should be a folder");
            }
            File[] files = f.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.endsWith(".json");
                }
            });
            for(File in : files){
                System.out.println(in.exists());
                BufferedReader br = new BufferedReader(new FileReader(in));
                StringBuilder sb = new StringBuilder();
                String input="";
                while((input=br.readLine())!=null){
                    sb.append(input);
                }
                MovieList movieList = NetUtils.g.fromJson(sb.toString(), MovieList.class);
                System.out.println(movieList);
                if(movieList.movies==null){
                    movieList.movies=new ArrayList<>();
                }
                movieData.put(in.getName().substring(0, in.getName().length()-5),movieList.movies);
                System.out.println(in.getName());
                br.close();
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            //cry
        }
    }

    public static void makeList(String listName){
        try{
            File f = new File(System.getProperty("user.dir")+"/lists");
            if(!f.exists()){
                f.mkdir();
            }
            if(f.isFile()){
                System.out.println(f.getAbsolutePath());
                throw new RuntimeException("Y U DO THIS TO MEEEEEEE\n This should be a folder");
            }
            File list = new File(f.getAbsolutePath()+"/"+listName+".json");
            if(!list.exists()){
                list.createNewFile();
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(list));
            bw.write("{}");
            bw.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            //cry
            List<MovieData> empty= new ArrayList<>();
            movieData.put(listName, empty);
        }
    }

    public static void addMovie(String listName, MovieList newList){
        try{
            File list = new File(System.getProperty("user.dir")+"/lists/"+listName+".json");
            if(list.exists()){
                BufferedReader br = new BufferedReader(new FileReader(list));
                StringBuilder sb = new StringBuilder();
                String input="";
                while((input=br.readLine())!=null){
                    sb.append(input);
                }
                MovieList current = NetUtils.g.fromJson(sb.toString(), MovieList.class);
                br.close();
                if(current!=newList){
                    current=newList;
                }
                String nl = NetUtils.g.toJson(current);
                BufferedWriter bw = new BufferedWriter(new FileWriter(list));
                bw.write(nl);
                bw.close();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            //cry
        }
    }

    public static MovieList readFile(String listName){
        try{
            File list = new File(System.getProperty("user.dir")+"/lists/"+listName+".json");
            if(list.exists()){
                BufferedReader br = new BufferedReader(new FileReader(list));
                StringBuilder sb = new StringBuilder();
                String input="";
                while((input=br.readLine())!=null){
                    sb.append(input);
                }
                br.close();
                return NetUtils.g.fromJson(sb.toString(), MovieList.class);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            //cry

        }
        return null;
    }

}
