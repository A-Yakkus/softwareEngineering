package acme;

import acme.data.MovieData;
import acme.data.MovieInfo;
import acme.data.MovieList;
import sun.nio.ch.Net;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    public static List<MovieList> movieData = new ArrayList<>();

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
                BufferedReader br = new BufferedReader(new FileReader(in));
                StringBuilder sb = new StringBuilder();
                String input="";
                while((input=br.readLine())!=null){
                    sb.append(input);
                }
                movieData.add(NetUtils.g.fromJson(sb.toString(), MovieList.class));
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

    public static void writeFiles(){
        File f = new File(System.getProperty("user.dir")+"/lists");
        if(!f.exists()){
            f.mkdir();
        }
        if(f.isFile()){
            System.out.println(f.getAbsolutePath());
            throw new RuntimeException("Y U DO THIS TO MEEEEEEE\n This should be a folder");
        }

    }

}
