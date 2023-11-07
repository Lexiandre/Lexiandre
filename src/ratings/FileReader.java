package ratings;

import ratings.datastructures.LinkedListNode;

import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.*;

public class FileReader {
    //private ArrayList<Song> SongsList = new ArrayList<Song>();

    public static ArrayList<Song> readSongs(String fileName) {
        ArrayList<Song> SongsList = new ArrayList<Song>(); //SONG LIST TO RETURN
        //songID,artist,title,reviewerID,rating

        try {
            ArrayList<String> songFile = new ArrayList<String>(Files.readAllLines(Paths.get(fileName)));
            //using the titles as a check for present elements
            ArrayList<String> SongIdList = new ArrayList<>();
            //HashMap<String, Song> Id_to_Song = new HashMap<>();

            for (String line : songFile) {
                //go through each Line
                //and for each line, process as list format
                //loop through line elements to obtain all details from line
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

                    if (SongIdList.contains(title)) {
                        //used the ID, assuming they are unique
                        //just add rating if song is already present
                        int indexOfSong = SongIdList.indexOf(title);
                        SongsList.get(indexOfSong).addRating(rating_to_add);
                    } else {
                        //else add new song to songList
                        music.addRating(rating_to_add);
                        SongsList.add(music);
                        SongIdList.add(title);//add the title here in order to check it later in the if statement
                    }
                }

            }
            return SongsList;

        } catch (IOException fileReadError) {
            return SongsList;
        }

    }


    public static ArrayList<Movie> readMovies(String fileName) {
        //movies.csv
        //number of castMembers vary
        //if input does not exist, return an empty ArrayList
        //cast must be in the oder they appear in file, but Movies can appear in any order.
        ArrayList<Movie> moviesList = new ArrayList<Movie>();

        try {
            ArrayList<String> songFile = new ArrayList<String>(Files.readAllLines(Paths.get(fileName)));
            ArrayList<String> titles = new ArrayList<>();
            HashMap<String, ArrayList<String>> movies = new HashMap<>();
            for (String line : songFile) {

                if (!(line.isBlank())) {
                    ArrayList<String> movieInfo = new ArrayList<>(Arrays.asList(line.split(",")));
                    //Toy Story,     Tom Hanks,Tim Allen,Don Rickles,Wallace Shawn....
                    //ArrayList
                    String movieTitles = movieInfo.get(0); //need first element for title
                    if (!(movies.keySet().contains(movieTitles))) {
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


    public static ArrayList<Song> readSongs2(String fileName) {
        ArrayList<Song> SongsList = new ArrayList<Song>(); //SONG LIST TO RETURN
        //songID,artist,title,reviewerID,rating

        try {
            ArrayList<String> songFile = new ArrayList<String>(Files.readAllLines(Paths.get(fileName)));
            //using the titles as a check for present elements
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
                    Song music = new Song(title, artist, SongId);


                    if(Id_to_Song.keySet().contains(SongId)){
                        Id_to_Song.get(SongId).addRating(rating_to_add);
                    }
                    else {
                        //else add new song to songList
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



//Main was here ;)
//    public static void main(String[] args) {
//
//        //Temporal testcase
//        //TEST for readSongs
////        var getMeSongs = readSongs("data\\ratingsSong.csv");
////        var size = getMeSongs.size();
////
////        System.out.println("Songs size : "+size+"\n");
////
////        for (Song music: getMeSongs) {
////            System.out.println("|" +music.getTitle()+ "| " +music.getArtist() );
////            System.out.print("avgRating :"+music.averageRating()+"\t");
////            System.out.println("|bayesianAvg: "+ music.bayesianAverageRating(2,3));
////        }
////        //check ratings
////
////        LinkedListNode<Rating> songLinked = getMeSongs.get(1).getRatings();
////        var header = songLinked;
////
////        System.out.println(header.getValue().getReviewerID());
////        System.out.println(header.getNext().getValue().getReviewerID());
//        //System.out.println(header.getValue().getReviewerID());
//
//        System.out.println("\n \n");
//        //Test for readMovies
//        var getMeMovies = readMovies("data/moviesSmall.csv");
//        System.out.println("movieSize : "+getMeMovies.size());
//        for(Movie mov : getMeMovies){
//            if(mov.getTitle().isBlank()){
//                System.out.println(mov.getTitle().equals(""));
//                System.out.println(mov.getCast());
//            }
//        }
//
//    }
}
