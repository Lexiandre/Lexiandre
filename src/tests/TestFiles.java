package tests;

import org.junit.Test;
import ratings.Movie;
import ratings.Rating;
import ratings.Song;
import ratings.datastructures.LinkedListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.*;
import static ratings.FileReader.*;


public class TestFiles {
//Tests lie here in the comments
    //test lies here
    @Test
    public void testSongReader(){
        var getMeSongs = readSongs("data/ratingsSong.csv");
        //var size = getMeSongs.size();
        String id = "0wJoRiX5K5BxlqZTolB2LD";

        //testing for titles
        String theTitle = "Purple Haze";
        for(Song track: getMeSongs){
            if(theTitle.equals(track.getTitle())){
                assertEquals(theTitle, "Purple Haze");
            }
        }

        //for wrong fieName
        var wrongFile = readSongs("data/ratingsSongs1.csv.");//cant find file so return an empty array
        assertTrue(wrongFile.isEmpty());
        assertEquals(0, wrongFile.size());
    }

    @Test
    public void testingRatingsSong(){
        var getMeSongs = readSongs("data/ratingsSong.csv");
        String theTitle = "Purple Haze";
        String id = "0wJoRiX5K5BxlqZTolB2LD";
        String artist = "Jimmy Hendrix";
        ArrayList<Integer> ratingsList = new ArrayList<>(Arrays.asList(5,2,4,3));

        ArrayList<String> titles = new ArrayList<>();//should have the same index as the
        for(Song song : getMeSongs){//accumulate titles into titles as a list
            titles.add(song.getTitle());
        }
        assertNotNull(getMeSongs);
        assertTrue(getMeSongs.size() == 13);
        assertTrue(titles.contains("Chandelier"));

        int songIndex = 0;
        if(titles.contains(theTitle)){
            songIndex = titles.indexOf(theTitle);
            assertEquals("Purple Haze", getMeSongs.get(songIndex).getTitle());
            assertEquals(id, getMeSongs.get(songIndex).getSongID());
            assertEquals(ratingsList.size(), getMeSongs.get(songIndex).getRatings().size());
            assertTrue(artist.equals(getMeSongs.get(songIndex).getArtist()));
        }

        LinkedListNode<Rating> songLinked = getMeSongs.get(songIndex).getRatings();
        assertNotNull(getMeSongs);
        ArrayList<String> reviewers = new ArrayList<>(Arrays.asList("0","159","176","264"));

        int indexer = 0;
        for(Song track: getMeSongs){
            assertNotNull(getMeSongs);
            assertNotNull(songLinked);
            assertNotNull(track);

            if(theTitle.equals(track.getTitle())){
                assertEquals(theTitle, "Purple Haze");
                var head = songLinked;
                while(head != null){
                    //System.out.println(head.getValue().getReviewerID());
                    assertEquals(reviewers.get(indexer), head.getValue().getReviewerID());
                    assertEquals((int) ratingsList.get(indexer), head.getValue().getRating());
                    assertTrue(reviewers.get(indexer).equals(head.getValue().getReviewerID()));//reviewerID
                    head = head.getNext();
                    indexer ++;
                }
            }
        }
    }


    @Test
    public void testMovieReader(){
        //Test for readMovies
        var getMeMovies = readMovies("data/moviesSmall.csv");
        assertNotNull(getMeMovies);
        String check = "Toy Story";
        String castMem = "John Morris";
        //String testAssassins = "Assassins";
        assertNotNull(getMeMovies);

        //get titles here too?
        //accounting for unordered items
        ArrayList<String> titles = new ArrayList<>();
        HashMap<String, Movie> movies = new HashMap<>();

        for(Movie movie : getMeMovies){//accumulate titles into titles
            titles.add(movie.getTitle());
            movies.put(movie.getTitle(), movie);
        }

        assertTrue(movies.containsKey("Jumanji"));
        assertEquals(movies.size(), 5);

        int movIndex = 0;
        //String check = "Toy Story";
        if(titles.contains(check)){
            movIndex = titles.indexOf(check);
            assertEquals("Toy Story", getMeMovies.get(movIndex).getTitle()); //title
            assertEquals(10, getMeMovies.get(movIndex).getCast().size()); //size
            assertEquals("Tom Hanks", getMeMovies.get(movIndex).getCast().get(0));
            assertTrue(getMeMovies.get(movIndex).getCast().contains(castMem));

            //System.out.println(getMeMovies.get(movIndex).getCast().size());
            //test for cast members
            //Tom Hanks,Tim Allen,Don Rickles,Wallace Shawn,John Ratzenberger,Annie Potts,John Morris,Laurie Metcalf,R. Lee Ermey,Penn Jillette
            ArrayList<String> castMembers_ToyStory = new ArrayList<>(Arrays.asList("Tom Hanks","Tim Allen","Don Rickles","Wallace Shawn",
                    "John Ratzenberger","Annie Potts","John Morris",
                    "Laurie Metcalf","R. Lee Ermey","Penn Jillette"));

            ArrayList<String> actualCast_ToyStory =  getMeMovies.get(movIndex).getCast();
            assertNotNull(getMeMovies);
            for(int i =0; i< actualCast_ToyStory.size(); i++){
                assertEquals(actualCast_ToyStory.get(i), castMembers_ToyStory.get(i));
            }
        }

//        var emptyMov = readMovies("data/moviesSmalls.csv");//Smalls instead of Small file not found
//        assertEquals(0, emptyMov.size());
//        assertTrue(emptyMov.isEmpty());

    }



    @Test
    public void bad_title(){//bad_title, max_two movies
        var getMeMovies = readMovies("data/moviesSmall.csv");

        HashMap<String, Movie> movies = new HashMap<>();
        assertNotNull(getMeMovies);

        for(Movie movie : getMeMovies){//accumulate titles into titles
            movies.put(movie.getTitle(), movie);
        }

        assertNotNull(movies);
        assertTrue(movies.containsKey("Jumanji"));
        assertEquals(movies.size(), 5);

//        movies.get("Copycat").getCast().add("Alex");
//        for(String key: movies.keySet()){
//            System.out.println(key);
//            System.out.println(movies.get(key).getCast());
//        }
        //System.out.println(movies.get("Movie").getCast());

    }

    @Test
    public void emptyTest(){
        var emptyMov = readMovies("data/moviesSmalls.csv");//Smalls instead of Small file not found
        assertEquals(0, emptyMov.size());
        assertTrue(emptyMov.isEmpty());

    }


    @Test
    public void testhashSongs(){
        var getMeSongs = readSongs("data/ratingsSong.csv");
        //var size = getMeSongs.size();
        String id = "0wJoRiX5K5BxlqZTolB2LD";

        HashMap<String, Song> songs = new HashMap<>();
        //testing for titles
        String theTitle = "Purple Haze";
        for(Song track: getMeSongs){
            songs.put(track.getSongID(), track);

            if(theTitle.equals(track.getTitle())){
                assertEquals(theTitle, "Purple Haze");
            }
        }

        var purple = songs.get("0wJoRiX5K5BxlqZTolB2LD");
        var start = purple.getRatings(); //place holder
        while(start != null){
            System.out.print(" Id:" + start.getValue().getReviewerID());
            System.out.print(" rating: " + start.getValue().getRating()+"| ");
            start = start.getNext();
        }

        //for wrong fieName
        var wrongFile = readSongs("data/ratingsSongs1.csv.");//cant find file so return an empty array
        assertTrue(wrongFile.isEmpty());
        assertEquals(0, wrongFile.size());
    }

    /*
    movie_badTitle ***
    movie_cast_in_wrong_order
    movie file not found
    movie max two movies ***
    movie missing a movie ***


    Song bad artist
    song bad id
    song bad reviewer id
    Song bad title
    Song duplicates Songs
    Song File not found
    song max, two songs. There can only be one song?
    Song only one rating
    Song ratings in wrong order

     */


}
