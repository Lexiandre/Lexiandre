package tests;

import org.junit.Test;
import ratings.Movie;
import ratings.Rating;
import ratings.Song;
import ratings.datastructures.LinkedListNode;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;
import static ratings.FileReader.readSongs;
import static ratings.FileReader.readMovies;


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

//    @Test
//    public void scannerSong(){
//        var getMeSongs = readSongs("data/ratingsSong.csv");
//        String theTitle = "Bitter";
//
//        ArrayList<String> titles = new ArrayList<>();//should have the same index as the
//        for(Song song : getMeSongs){//accumulate titles into titles as a list
//            titles.add(song.getTitle());
//        }
//
//        int idx1;
//        if(titles.contains(theTitle)){
//            idx1 = titles.indexOf(theTitle);
//            assertNotNull(getMeSongs);
//            assertEquals("Bitter", getMeSongs.get(idx1).getTitle());
//            //System.out.println("idx1: " + idx1);
//            LinkedListNode<Rating> songLinked = getMeSongs.get(idx1).getRatings();
//
//            ArrayList<String> reviewers = new ArrayList<>();
//            reviewers.add("264");//Bitter ratings
//            var rating1 = 5;
//
//            Song track = getMeSongs.get(idx1);
//            assertNotNull(track);
//
//            int indexer = 0;
//            if(theTitle.equals(track.getTitle())){
//                assertEquals(theTitle, "Bitter");
//                //assertEquals(5, track.);
//                var head = songLinked;
//                assertTrue(rating1 == track.getRatings().getValue().getRating());//just one rating
//
//                while(head != null){
//                    assertNotNull(reviewers);
//                    assertTrue(reviewers.size()>0);
//                    assertEquals(reviewers.get(indexer), head.getValue().getReviewerID());
//                    assertTrue(reviewers.contains(head.getValue().getReviewerID()));//same as above
//                    head = head.getNext();
//                    indexer ++;
//                }
//            }
//        }
//
//    }

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
        String testAssassins = "Assassins";

        //get titles here too?
        //accounting for unordered items
        ArrayList<String> titles = new ArrayList<>();

        for(Movie movie : getMeMovies){//accumulate titles into titles
            titles.add(movie.getTitle());
        }

        try {
            assertNotNull(getMeMovies);
            var movIndex2 = titles.indexOf(check);
            System.out.println(movIndex2);
            assertEquals("Toy Story", getMeMovies.get(movIndex2).getTitle()); //title
            assertEquals(10, getMeMovies.get(movIndex2).getCast().size()); //size
            assertEquals("Tom Hanks", getMeMovies.get(movIndex2).getCast().get(0));
            assertTrue(getMeMovies.get(movIndex2).getCast().contains(castMem));
        }catch(IndexOutOfBoundsException iE){
            assertNotNull(getMeMovies);
        }



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

        var emptyMov = readMovies("data/moviesSmalls.csv");//Smalls instead of Small file not found
        assertEquals(0, emptyMov.size());
        assertTrue(emptyMov.isEmpty());

    }


    @Test
    public void movieOrder(){
        var getMeMovies = readMovies("data/moviesSmall.csv");
        assertNotNull(getMeMovies);
        //get titles here too?
        //accounting for unordered items
        ArrayList<String> titles = new ArrayList<>();
        String testAssassins = "Copycat";
        System.out.println(getMeMovies.size());

        assertNotNull(getMeMovies);

        for(Movie movie : getMeMovies){//accumulate titles into titles
            titles.add(movie.getTitle());
            //assertFalse(movie.getTitle().equals("Movie"));

            //Assassins,Sylvester Stallone,Antonio Banderas,Julianne Moore,Muse Watson,Steve Kahan,Kelly Rowan,Reed Diamond
            if(movie.getTitle().equals(testAssassins)){
                assertNotNull(getMeMovies);
                //System.out.println("Movies: "+ movie.getCast());
                assertTrue(getMeMovies.size() == 5);
                assertTrue(testAssassins.equals(movie.getTitle()));
            }
        }
        //assertFalse(titles.contains("Movie"));
    }

    //ARRAYLIST MAY BE RETURNED IN ANY ORDER
    //the edge cases


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
