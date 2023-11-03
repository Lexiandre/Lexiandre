package ratings;

import ratings.datastructures.LinkedListNode;

import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class FileReader {
    //private ArrayList<Song> SongsList = new ArrayList<Song>();

    public static ArrayList<Song> readSongs(String fileName) {

        ArrayList<Song> SongsList = new ArrayList<Song>(); //SONG LIST TO RETURN
        //songID,artist,title,reviewerID,rating

        try {
            ArrayList<String> songFile = new ArrayList<String>(Files.readAllLines(Paths.get(fileName)));
            //using the titles as a check for present elements
            ArrayList<String> SongIdList = new ArrayList<>();

            for (String line : songFile) {
                //go through each Line
                //and for each line, process as list format
                //loop through line elements to obtain all details from line
                if(!(line.isBlank())){//redundant?
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
            //movieTitle,    castMember0,castMember1,castMember2,etc.
            //Toy Story,     Tom Hanks,Tim Allen,Don Rickles,Wallace Shawn....
            ArrayList<String> titles = new ArrayList<>();
            for (String line : songFile) {

                if (!(line.isBlank())) {
                    ArrayList<String> movieInfo = new ArrayList<>(Arrays.asList(line.split(",")));
                    //Toy Story,     Tom Hanks,Tim Allen,Don Rickles,Wallace Shawn....
                    System.out.println(":" + movieInfo.get(0) + ": ");
                    if(movieInfo.get(0).isBlank()){
                        String movieTitles = "";
                        if(!titles.contains(movieTitles)){//duplicates
                            titles.add(movieTitles);
                            ArrayList<String> castMembers = new ArrayList<>();
                            for (int i = 1; i < movieInfo.size(); i++) {
                                castMembers.add(movieInfo.get(i));
                            }
                            Movie movie_to_add = new Movie(movieTitles, castMembers);
                            moviesList.add(movie_to_add);
                        }
                    }
                    else{
                        String movieTitles = movieInfo.get(0); //need first element for title
                        if(!titles.contains(movieTitles)){
                            if (movieInfo.size() > 1) {
                                titles.add(movieTitles);
                                ArrayList<String> castMembers = new ArrayList<>();
                                for (int i = 1; i < movieInfo.size(); i++) {
                                    castMembers.add(movieInfo.get(i));
                                }
                                Movie movie_to_add = new Movie(movieTitles, castMembers);
                                moviesList.add(movie_to_add);
                            }
                        }
                    }



                }
                //for the rest we have to add them to an arrayList with a loop
                //there must be at least one cast member per movie??? Potential issue**********
            }
            return moviesList;

        } catch (IOException fileReadError) {
            return moviesList;
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
//
//
//        System.out.println("\n \n");
//        //Test for readMovies
//        var getMeMovies = readMovies("data\\moviesSmall.csv");
//        System.out.println("movieSize : "+getMeMovies.size());
//        for(Movie mov : getMeMovies){
//            if(mov.getTitle().isBlank()){
//                System.out.println(mov.getTitle().equals(""));
//                System.out.println(mov.getCast());
//            }
//        }
//
//
//
//    }
}
