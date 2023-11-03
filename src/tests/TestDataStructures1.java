package tests;

import org.junit.Test;
import ratings.Rating;
import ratings.Song;
import ratings.Reviewer;
import ratings.datastructures.LinkedListNode;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class TestDataStructures1 {

    public final double Epsilon = 0.01;
    public void compareDoubles(double a, double b){
        assertTrue(Math.abs(a-b) < Epsilon);
    }

    //test average of ratings in Song
    //TODO averageRating : takes not params returns a double
    @Test
    public void testAverageRating(){
        Song track1 = new Song("Runaway","Aurora","serene");
        Rating rate1 = new Rating("Alex",5);
        Rating rate2 = new Rating("Jnr",4);
        Rating rate3 = new Rating("Bad", 6); //bad rating setter should not be added when calculating the average.

        //addRating before calculating average
        track1.addRating(rate1);
        track1.addRating(rate2);
        var avg = track1.averageRating();
        //assertTrue(avg == 4.5);

        compareDoubles(avg, 4.5);


        track1.addRating(rate3); //adding bad rating
        var out = track1.getRatings();
        var out2 = out.getNext();
        var out3 = out2.getNext();

        //System.out.println("out is: "+out);
        //System.out.println("Alex rating: "+out.getValue().getRating());
        //System.out.println("Jnr rating: "+out2.getValue().getRating());
        var avg2 = track1.averageRating();

        //assertTrue(avg2 == 4.5);
        compareDoubles(avg2, 4.5);
        assertEquals(out.getValue().getRating(), 5);
        assertEquals(out2.getValue().getRating(), 4);
        assertEquals(out3.getValue().getRating(), -1);



        //now change the list to something else
        //TESTING setRatings

        Song newSong = new Song("Marsh","Eminem","11");
        Rating newRate = new Rating("Abeiku",4);
        Rating newBadRate = new Rating("bad", -3);
        LinkedListNode<Rating> newLoR = new LinkedListNode<>(newRate, null);
        newLoR.append(newBadRate);

        //newSong.addRating();
        track1.setRatings(newLoR);
        var newOut = track1.getRatings();
        //System.out.println("newOut: "+newOut.getValue().getReviewerID());
        assertEquals(newOut.getValue().getReviewerID(), "Abeiku");

        var newOut2 = newOut.getNext();
        //System.out.println("newOut2: "+newOut2.getValue().getReviewerID());
        assertEquals(newOut2.getValue().getReviewerID(), "bad");

        var newAvg = track1.averageRating();
        //System.out.println(newAvg);
        //assertTrue(newAvg == 4.0);
        compareDoubles(newAvg, 4.0);

        //test for setRatings then calculate average

        //test for empty list
        //test for large list
        //doesn't remove end of list
        // NullPointerException: Cannot invoke ratings.datastructures.LinkedListNode.getValue() because <local7> is null

        Song track3 = new Song("Dreaming","SmallPools","22");
        var emptyList = track3.averageRating();
        //System.out.println(emptyList);
        compareDoubles(emptyList, 0.0); //EMPTY list


        //LARGE LIST
        Song track4 = new Song("Ghosts", "Banners", "27");
        Rating r1 = new Rating("One", 1);
        Rating r2 = new Rating("Two", 2);
        Rating r3 = new Rating("Three", 3);
        Rating r4 = new Rating("Four", 4);
        Rating r5 = new Rating("Five", 5);
        ArrayList<Rating> listed = new ArrayList<>(Arrays.asList(r1, r2, r3, r4, r5)); //list of elements
        for(var elem : listed){
            track4.addRating(elem);
        }

        double large = track4.averageRating();
        compareDoubles(large, 3.0);
        //System.out.println("large is: "+large);

    }

    //TODO removeRatingByReviewer:  takes a Reviewer as a parameter, returns void
    //remove rating made for this song by the given reviewer
    //if the reviewer did not rate this song, this method has no effect
    //if linked list contains multiple instances of the by the reviewer, then only remove the first instance
    @Test
    public void testRemoveHead(){
        Song track4 = new Song("Runaway","Aurora","serene");
        Rating rate1 = new Rating("1",5);
        Rating rate2 = new Rating("2",4);


        //reviewer.rateSong(3);
        track4.addRating(rate1);
        track4.addRating(rate2);


        //reviewer objects
        Reviewer rev1 = new Reviewer("1");
        track4.removeRatingByReviewer(rev1);

        if(track4.getRatings() != null){
            assertNotNull(track4.getRatings());
            assertEquals(track4.getRatings().getValue().getReviewerID(), "2");
            //System.out.println(track4.getRatings().getValue().getReviewerID());
        }
        else{
            assertNotNull(track4.getRatings());
        }

    }
    @Test
    public void testRemoveRatingBy_ID_not_in_List(){ //END OF LIST and NOT IN LIST
        Song track2 = new Song("Runaway","Aurora","serene");
        Rating rate1 = new Rating("first",5);
        Rating rate2 = new Rating("second",4);
        Rating rate3 = new Rating("third", 3);


        track2.addRating(rate1);
        track2.addRating(rate2);
        track2.addRating(rate3);

        //reviewer
        Reviewer reviewer = new Reviewer("Abeiku"); //not in the list
        track2.removeRatingByReviewer(reviewer); //nothing happens
        var test1 = track2.getRatings().getValue().getReviewerID();
        //System.out.println("head should be first, and is: "+ test1); //head first

        //
        Reviewer reviewer3 = new Reviewer("third"); //last item in the list
        track2.removeRatingByReviewer(reviewer3);
        var test2 = track2.getRatings().getValue().getReviewerID();
        //System.out.println(test2); //prints first as head

        var last = track2.getRatings().getNext().getValue().getReviewerID();
        var size = track2.getRatings().size();
        //System.out.println(size); //should be 2 instead of three
        assertEquals(size, 2);
        assertEquals(last, "second"); //make second the last item now
        //System.out.println(last);

    }
    @Test
    public void testRemoveMany(){
        Song track2 = new Song("Runaway","Aurora","serene");
        Rating rate1 = new Rating("Alex",5);
        Rating rate2 = new Rating("Jnr",4);
        Rating rate4 = new Rating("Kwai", 2);
        Rating rate3 = new Rating("Abeiku", 3);

        //reviewer.rateSong(3);
        track2.addRating(rate1);
        track2.addRating(rate2);
        track2.addRating(rate4);
        track2.addRating(rate3);


        //reviewer objects
        Reviewer rev1 = new Reviewer("Jnr");
        Reviewer rev2 = new Reviewer("Kwai");
        Reviewer rev3 = new Reviewer("Abeiku");

        //removing ratings by reviewer names
        track2.removeRatingByReviewer(rev1);
        assertNotNull(track2.getRatings());

        track2.removeRatingByReviewer(rev2);
        assertNotNull(track2.getRatings().getNext());

        track2.removeRatingByReviewer(rev3);
        assertNotNull(track2.getRatings());


    }
}
