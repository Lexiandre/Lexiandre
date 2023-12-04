package tests;

import org.junit.Test;
import ratings.MediaLibrary;
import ratings.Movie;
import ratings.Ratable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.*;
import static ratings.FileReader.readMovieRatings;
import static ratings.FileReader.readMovies;
import ratings.MediaLibrary.*;

public class TestClasses3 {

    @Test
    public void testReadMovieRatings(){
        ArrayList<Movie> movies = readMovies("data/moviesSmall.csv");
        var ratedMovRatings = readMovieRatings(movies,"data/small_movie_ratings.csv");

        assertNotNull(ratedMovRatings);
        assertNotNull(movies);
        assertEquals(2, ratedMovRatings.size());

        HashMap<String, Movie> ratedMov = new HashMap<>();
        assertNotNull(ratedMov);
        for(Movie mov: ratedMovRatings){
            ratedMov.put(mov.getTitle(), mov);
        }

        assertEquals(4, ratedMov.get("Toy Story").getRatings().size());

        for(Movie mov: ratedMovRatings){
            System.out.println("movie: " + mov.getTitle());
            var ratings = mov.getRatings();
            System.out.print("ratings: ");
            while (ratings != null){
                System.out.print(ratings.getValue().getRating()+ "| ");
                ratings = ratings.getNext();
            }
            System.out.println("\n");
        }

    }

    @Test
    public void fileReadError(){
        ArrayList<Movie> movies = readMovies("data/moviesSmallsss.csv");
        var ratedMovRatings = readMovieRatings(movies,"data/small_movie_ratingssss.csv");

        assertNotNull(ratedMovRatings);
        assertTrue(ratedMovRatings.isEmpty());
        assertTrue(ratedMovRatings.size() == 0);
    }

    @Test
    public void testMediaLibrary(){
        MediaLibrary library = new MediaLibrary();
        var songFile = "data/ratingsSong.csv";
        var movie_unrated_File = "data/moviesSmall.csv";
        var movie_Ratings_File = "data/small_movie_ratings.csv";


        assertNotNull(library);
        library.populateLibrary(songFile, movie_unrated_File, movie_Ratings_File);
        var topK = library.topKRatables(100);


        assertNotNull(topK);
        //assertEquals("Toy Story", topK.get(0).getTitle());
        //assertEquals(10, topK.size());

        int i =1;
        for(Ratable k: topK){
            System.out.print(i+"-> " +k.getTitle() + ": ");
            i++;
            System.out.println(k.bayesianAverageRating(2,3));

        }
    }


//    @Test
//    public void testMediaLibrary2(){
//        MediaLibrary library = new MediaLibrary();
//        var songFile = "data/ratingsSong.csv";
//        var movie_unrated_File = "data/moviesSmall.csv";
//        var movie_Ratings_File = "data/small_movie_ratings.csv";
//
//
//        library.populateLibrary(songFile, movie_unrated_File, movie_Ratings_File);
//        var topK = library.topKRatables2(10);
//
//        for(Ratable k: topK){
//            System.out.println(k.getTitle() + ": ");
//            System.out.print(k.bayesianAverageRating(2,3) +"\n");
//        }
//    }

}
