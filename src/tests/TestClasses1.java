package tests;
import org.junit.Assert;
import org.junit.Test;
import ratings.Rating;
import ratings.Reviewer;
import ratings.Song;

import static org.junit.Assert.*;
public class TestClasses1 {


    //edge cases: no id after rating song
    //no ratings cap constructor
    //no ratings min//
    //no ratings min after rating song
    //wrong rating after rating song//

    // RATING
    @Test
    public void testRating(){
        Rating rate = new Rating("Alex", 2);
        assertEquals(rate.getReviewerID(), "Alex");
        assertTrue(rate.getRating() == 2);

        rate.setRating(8);
        rate.setReviewerID("Ben");
        assertEquals(rate.getReviewerID(), "Ben");
        assertTrue(rate.getRating() == -1); //values greater than 5


        rate.setRating(-9);
        assertTrue(rate.getRating() == -1); //values less than 1

        rate.setRating(Integer.parseInt("6"));//parsed int bad value
        assertTrue(rate.getRating() == -1);



        Rating rate2 = new Rating("", 2);//new rating obj with noID
        assertTrue(rate2.getRating()== 2);
        assertEquals(rate2.getReviewerID(), "");

        rate2.setReviewerID("Second");
        rate2.setRating(Integer.MAX_VALUE);
        assertTrue(rate2.getRating() == -1);

        rate2.setRating(Integer.MIN_VALUE);
        assertTrue(rate2.getRating() == -1);

    }
    //SONG
    @Test
    public void testSong(){
        Song music = new Song("Gnat", "Eminem","6969");
        //testing the get values
        assertEquals(music.getTitle(), "Gnat");
        assertEquals(music.getArtist(), "Eminem");
        assertEquals(music.getSongID(), "6969");

        //set values
        music.setTitle("AssLikeThat");
        music.setSongID("ShakeIt");
        assertEquals(music.getTitle(), "AssLikeThat");
        assertEquals(music.getSongID(), "ShakeIt");

        Song music2 = new Song("Dreaming", "SmallPools", "");
        assertEquals(music2.getSongID(), "");

    }
    //REVIEWER_ID
    @Test
    public void testReviewer(){
        Reviewer rev= new Reviewer("Jnr"); //reviewer object
        Reviewer rev2 = rev;
        var rate = rev.rateSong(5); //creates rating object
        var test = rev.getReviewerID();

        assertEquals(rev, rev2);
        assertEquals(test, "Jnr");
        rev.setReviewerID("Alex");
        assertEquals(rev.getReviewerID(), "Alex");
        assertEquals(rate.getRating(), 5);

        rate.setRating(Integer.MAX_VALUE);
        assertEquals(rate.getRating(), -1);
        //System.out.println(rate.getRating());

        Reviewer noID = new Reviewer(""); //no ID
        var iD = noID.rateSong(4); //rating 4
        assertEquals(iD.getReviewerID(), "");
        //System.out.println(iD.getReviewerID());
        iD.setRating(Integer.MIN_VALUE);
        assertEquals(iD.getRating(), -1);




        Reviewer rev3 = new Reviewer("name");
        var testcase = rev3.rateSong(0);
        //testcase.getRating();
        // VAR
        var bread = testcase.getRating();
        //System.out.println(bread);

        assertEquals(testcase.getRating(), -1);
        assertEquals(testcase.getReviewerID(), "name");

    }
    //no rating min after rating song
}