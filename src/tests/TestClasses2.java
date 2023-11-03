package tests;

import org.junit.Test;
import ratings.Movie;
import ratings.Rating;
import ratings.Song;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class TestClasses2 {

    //TODO: bayesian Avg tests
    public final double Epsilon = 0.01;
    public void compareDoubles(double a, double b){
        assertTrue(Math.abs(a-b) < Epsilon);
    }
    @Test
    public void testBayesianAvg(){

        Song track1 = new Song("Runaway","Aurora","serene");
        Rating rate1 = new Rating("Alex",5);
        Rating rate2 = new Rating("Jnr",4);
        Rating rate3 = new Rating("Bad", 6); //bad rating setter should not be added when calculating the average.

        //addRating before calculating bayesian_average
        track1.addRating(rate1);
        track1.addRating(rate2);

        //avg vs bayesian_avg
        var avg1 = track1.averageRating();
        var avg2 = track1.bayesianAverageRating(3,3);
        compareDoubles(avg1,4.5);
        compareDoubles(avg2, 3.6);
        //System.out.println("average: " + avg1);
        //System.out.println("bayesian_Average: " + avg2);

    }
    @Test
    public void testBayesian_others(){

        //bad on no fake ratings
        //bayesian bad on no real or fake ratings
        //bayesian bad on no real ratings

        //empty Rating_list returns 0.0
        Song track2 = new Song("Marsh","Em","89");
        Rating r1 = new Rating("Lex",10);
        track2.addRating(r1);
        var avg3 = track2.bayesianAverageRating(3,0);
        assertTrue(avg3 == 0.0);

        //var avg4 = track2.bayesianAverageRating(1,1);
        //System.out.println(avg4);
        //assertTrue(avg4 == 0.0);
        //System.out.println("avg3 should be 0.0: " + avg3);
    }
    @Test
    public void anotherBayesian(){

        //passed bad on no fake ratings
        ArrayList<String> castMembers = new ArrayList<String>(Arrays.asList("Director","Spider"));

        Song thatSong = new Song("no","yes","00");
        Song noRat = new Song("","","");//trouble
        Movie thatMovie = new Movie("that_Movie", castMembers);

        Rating rater = new Rating("nameless", 2); //good
        Rating noReal = new Rating("blah", 9);//bad
        thatSong.addRating(rater);
        thatMovie.addRating(rater);
        //noRat.addRating(noReal);//trouble


        var goodSong = thatSong.bayesianAverageRating(1, 2);
        var goodMovie = thatMovie.bayesianAverageRating(1,2);
        var Rat = noRat.bayesianAverageRating(1,2);
        compareDoubles(goodSong, 2.0); //good fakes
        compareDoubles(goodMovie, 2.0);
        //compareDoubles(Rat, 0.0);//trouble
        //System.out.println(Rat);


        //System.out.println(good);

        var bad_Song = thatSong.bayesianAverageRating(0,0);//bad no fake rating
        var bad_Movie = thatMovie.bayesianAverageRating(2,0);
        //var badRat = noRat.bayesianAverageRating(0, 2);//trouble
        //var _noRat_ = noRat.bayesianAverageRating(1,2);
        //System.out.println(badRat);

        compareDoubles(bad_Song, 0.0);
        compareDoubles(bad_Movie, 0.0);
        //compareDoubles(badRat, 0.0);//trouble
        //compareDoubles(_noRat_, 0.0);
        //System.out.println(bad);
    }
    @Test
    public void testBayesianAvgNoRatings(){//no real or fake
        Song track1 = new Song("Runaway", "Aurora", "serene");

        // Calculate Bayesian average without any ratings
        var avg = track1.bayesianAverageRating(0, 3); // 0 real ratings and 3 fake ratings with a value of 3

        // Check if the Bayesian average is undefined
        compareDoubles(0.0, avg);
        //System.out.println(avg);
    }
    @Test
    public void testBadBayesian(){
        //testing for invalid inputs
        Song track3 = new Song("bad","good","excellent");
        Rating one = new Rating("one", 1);
        Rating two = new Rating("two", 2);

        track3.addRating(one);
        track3.addRating(two);

        var simple = track3.averageRating();
        var simple_bayesian = track3.bayesianAverageRating(2,2);
        compareDoubles(simple, 1.5);
        compareDoubles(simple_bayesian, 1.75);
        //System.out.println(simple); //1.5
        //System.out.println(simple_bayesian); //1.75

        //bad examples: return 0.0
        var bad_bayesian = track3.bayesianAverageRating(-1,2);
        assertEquals(0.0, bad_bayesian, 0.01);

        var bad_bayesian2 = track3.bayesianAverageRating(4, 0);
        assertEquals(0.0, bad_bayesian2, 0.01);

        var bad_bayesian3 = track3.bayesianAverageRating(3, 0);
        assertEquals(0.0, bad_bayesian3, 0.01);
    }



    //TODO: Movie class tests
    @Test
    public void testMovies(){

        ArrayList<String> cast1 = new ArrayList<String>(Arrays.asList("Director","Spider"));
        Movie action = new Movie("B13",cast1);

        Rating viewer1 = new Rating("Alex", 5);
        action.addRating(viewer1);

        var avg1 = action.averageRating();
        var bAvg1 = action.bayesianAverageRating(2,3);
        var bAvg2 = action.bayesianAverageRating(2,3);

//        System.out.println(action.getRatings().getValue().getReviewerID());
//        System.out.println(action.getTitle());
//        System.out.println(action.getCast());
//        System.out.println(avg1);
        //System.out.println(bAvg1);
        //System.out.println(bAvg2);

        //tests
        assertEquals("Alex", action.getRatings().getValue().getReviewerID());
        assertEquals("B13" ,action.getTitle());
        assertTrue(action.getCast().get(0).equalsIgnoreCase("director"));//comparing first element ignoring case
        compareDoubles(5.0, avg1);
        compareDoubles(3.666, bAvg1);
        compareDoubles(3.666, bAvg2);



        //spaced out names :First and Last Names
        ArrayList<String> cast2 = new ArrayList<String>(Arrays.asList("Director Shaw","Spider Man", "Last"));
        Movie action2 = new Movie("Transporter",cast2);

        Rating viewer2 = new Rating("Alex", 5);
        action2.addRating(viewer1);
        //System.out.println(action2.getCast());
        assertTrue(action2.getCast().get(0).equalsIgnoreCase("Director Shaw"));
        //assertEquals(Arrays.asList("Director Shaw","Spider Man"), action2.getCast());
        assertTrue(action2.getCast().get(1).equalsIgnoreCase("Spider Man"));
        //var last = action2.getCast().get(action2.getCast().size() -1);
        //System.out.println(last);

    }
    @Test
    public void lastCast(){
        ArrayList<String> cast3 = new ArrayList<String>(){};
        cast3.add("Di");
        cast3.add("Ti");
        cast3.add("Fi");
        Movie action3 = new Movie("Edge",cast3);

        Rating viewer3 = new Rating("Alex", 5);
        action3.addRating(viewer3);
        assertTrue(3 == cast3.size());
        cast3.remove(2); //remove last cast

        var casts = action3.getCast();
        assertTrue(casts.get(0).equalsIgnoreCase("Di"));
        assertTrue(2 == cast3.size());


        //assertTrue(casts.get(casts.size()-1).equalsIgnoreCase("Ti"));

    }
    @Test
    public void testMissingLastCastMember() {
        //movie with a cast list that is missing the last cast member
        ArrayList<String> cast = new ArrayList<>(Arrays.asList("Director Shaw", "Spider Man"));
        Movie movie = new Movie("Transporter", cast);

        // Add a rating to the movie
        Rating viewer = new Rating("Alex", 5);
        movie.addRating(viewer);

        // Ensure that the last cast member is missing
        assertEquals(2, movie.getCast().size()); // Assuming there should be only 2 cast members
        assertTrue(3 != movie.getCast().size() );
    }


    //missing last cast member : movie class??


}

//at least create 2 comparator classes else no auto-grader compilation
//SONGBayesianRating
//MovieBayesian Rating
//Song Title Comparator