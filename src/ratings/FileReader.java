package ratings;

import ratings.datastructures.LinkedListNode;

import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.*;

public class FileReader {
    //private ArrayList<Song> SongsList = new ArrayList<Song>();

    //READ SONGS/////////////////////////////////////////////////////////
    public static ArrayList<Song> readSongs(String fileName) { //faster than ArrayList implementation
        ArrayList<Song> SongsList = new ArrayList<>(); //SONG LIST TO RETURN
        //songID,artist,title,reviewerID,rating

        try {
            ArrayList<String> songFile = new ArrayList<String>(Files.readAllLines(Paths.get(fileName)));
            //using the ID as a check for present elements
            HashMap<String, Song> Id_to_Song = new HashMap<>();

            for (String line : songFile) {
                if (!(line.isBlank())) {//redundant?
                    ArrayList<String> songInfo = new ArrayList<>(Arrays.asList(line.split(",")));
                    //songID,artist,title,reviewerID,rating
                    //idx0,      1,     2,         3,     4
                    String SongId = songInfo.get(0);
                    String artist = songInfo.get(1);
                    String title = songInfo.get(2);
                    String Reviewer = songInfo.get(3);
                    int ratingInt = Integer.parseInt(songInfo.get(4));
                    Rating rating_to_add = new Rating(Reviewer, ratingInt);

                    if(Id_to_Song.containsKey(SongId)){
                        Id_to_Song.get(SongId).addRating(rating_to_add);
                    }
                    else {
                        //else add new song to songList
                        Song music = new Song(title, artist, SongId);
                        music.addRating(rating_to_add);
                        Id_to_Song.put(SongId, music);
                    }
                }

            }
            // add values of the hashmap to the songslist
            for(String id: Id_to_Song.keySet()){
                SongsList.add(Id_to_Song.get(id));
            }
            return SongsList;

        } catch (IOException fileReadError) {
            return SongsList;
        }

    }

    public static ArrayList<Song> readSongs2(String fileName) { //using arrayList. SLOW
        ArrayList<Song> SongsList = new ArrayList<>();
        //songID,artist,title,reviewerID,rating

        try {
            ArrayList<String> songFile = new ArrayList<String>(Files.readAllLines(Paths.get(fileName)));
            //using the SongID as a check for present elements, because they are unique
            ArrayList<String> SongIdList = new ArrayList<>();


            for (String line : songFile) {
                if (!(line.isBlank())) {//redundant?
                    ArrayList<String> songInfo = new ArrayList<>(Arrays.asList(line.split(",")));

                    //songID,artist,title,reviewerID,rating
                    //idx0,      1,     2,         3,     4
                    String SongId = songInfo.get(0);
                    String artist = songInfo.get(1);
                    String title = songInfo.get(2);
                    String ReviewerID = songInfo.get(3);
                    int ratingInt = Integer.parseInt(songInfo.get(4));

                    Rating rating_to_add = new Rating(ReviewerID, ratingInt);
                    Song music = new Song(title, artist, SongId);

                    if (SongIdList.contains(SongId)) {
                        //used the ID, assuming they are unique
                        //just add rating if song is already present
                        int indexOfSong = SongIdList.indexOf(SongId);
                        SongsList.get(indexOfSong).addRating(rating_to_add);
                    } else {
                        //else add new song to songList
                        music.addRating(rating_to_add);
                        SongsList.add(music);
                        SongIdList.add(SongId);//add the title here in order to check it later in the if statement
                    }
                }

            }
            return SongsList;

        } catch (IOException fileReadError) {
            return SongsList;
        }

    }

//////READ MOVIES///////////////////////////////////////////////////////
    public static ArrayList<Movie> readMovies(String fileName) {
        ArrayList<Movie> moviesList = new ArrayList<Movie>();

        try {
            ArrayList<String> songFile = new ArrayList<String>(Files.readAllLines(Paths.get(fileName)));
            //ArrayList<String> titles = new ArrayList<>();
            HashMap<String, ArrayList<String>> movies = new HashMap<>();
            for (String line : songFile) {
                if (!(line.isBlank())) {
                    ArrayList<String> movieInfo = new ArrayList<>(Arrays.asList(line.split(",")));
                    //Toy Story,     Tom Hanks,Tim Allen,Don Rickles,Wallace Shawn....
                    //ArrayList
                    String movieTitles = movieInfo.get(0); //need first element for title
                    if (!(movies.containsKey(movieTitles))) {
                        ArrayList<String> castMembers = new ArrayList<>();
                        for (int i = 1; i < movieInfo.size(); i++) {
                            castMembers.add(movieInfo.get(i));
                        }
                        movies.put(movieTitles, castMembers);
                    }
                }
            }

            //loop through hashmap
            for (Map.Entry<String, ArrayList<String>> entry : movies.entrySet()) {
                // get the key and value from the entry
                String title = entry.getKey();
                ArrayList<String> casts = entry.getValue();

                Movie movie_to_add = new Movie(title, casts);
                moviesList.add(movie_to_add);
            }

            return moviesList;

        } catch (IOException fileReadError) {
            return moviesList;
        }

    }




    //TASK 7 POLYMORPH //////////////////////////////////////////////////////////////////////////////////
    public static ArrayList<Movie> readMovieRatings(ArrayList<Movie> movies, String ratingsFile){

        ArrayList<Movie> ratedMovies = new ArrayList<>();
        //FORMAT: of ratings File
        //title,reviewerId,rating

        try{
            ArrayList<String> ratings = new ArrayList<String>(Files.readAllLines(Paths.get(ratingsFile)));

            HashMap<String, Movie> acc_ratings = new HashMap<>();
            for(Movie mov : movies){//accumulate the movies into hashmap for use later
                acc_ratings.put(mov.getTitle(), mov);
            }

            for(String lines: ratings){
                ArrayList<String> line = new ArrayList<>(Arrays.asList(lines.split(",")));
                String title = line.get(0);
                String Reviewer = line.get(1);
                int rate = Integer.parseInt(line.get(2));

                if(acc_ratings.containsKey(title)){//add ratings based on title
                    Rating mov_rating = new Rating(Reviewer, rate);
                    acc_ratings.get(title).addRating(mov_rating);
                }
            }

            for(String title: acc_ratings.keySet()){//looping through the created hashmap
                if(acc_ratings.get(title).getRatings()!= null){
                    ratedMovies.add(acc_ratings.get(title));
                }
            }
            return ratedMovies;

        }catch(IOException fileReadErr){
            return ratedMovies;
        }

    }


}
