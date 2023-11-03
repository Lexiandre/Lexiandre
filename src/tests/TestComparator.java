package tests;

import org.junit.Test;
import ratings.Rating;
import ratings.Song;
import ratings.datastructures.SongBayesianRatingComparator;
import ratings.datastructures.SongTitleComparator;

import static org.junit.Assert.*;
//import ratings.datastructures.Comparator;

public class TestComparator {

    @Test
    public void testComp(){
        Song track1 = new Song("Runaway","Aurora","serene");
        Rating rate1 = new Rating("Alex",5);
        track1.addRating(rate1);

        Song track2 = new Song("Wolves","Aurora","serene");
        Rating rate2 = new Rating("Alex",1);
        track2.addRating(rate2);

        SongBayesianRatingComparator compBayesian= new SongBayesianRatingComparator();
        SongTitleComparator compTitle = new SongTitleComparator();
        var bAvg = compBayesian.compare(track1, track2);
        var titles = compTitle.compare(track1,track2);

        //System.out.println("bayesian Avg: "+ bAvg);
        //System.out.println("Titles: " + titles);

        assertTrue(bAvg);
        assertTrue(titles);
        //assertTrue(bAvg == true);


    }
}
