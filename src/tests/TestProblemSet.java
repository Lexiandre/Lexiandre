package tests;


import org.junit.Test;
import ratings.ProblemSet;

import java.util.*;

import static org.junit.Assert.*;

public class TestProblemSet {

    // TODO: Write testing for all 3 methods of the ratings.ProblemSet class


    //comparing doubles
    public final double Epsilon = 0.0001;
    public void compareDoubles(double a, double b){
        assertTrue(Math.abs(a-b) < Epsilon);
    }

    @Test
    public void testAvg(){
        // Examples
        // [1.0,2.0,3.0] returns 2.0
        // [-5.0,5.0] returns 0.0
        // [6.5,6.5,8.5,8.5] returns 7.5
        // [] returns 0.0

        ArrayList<Double> avg = new ArrayList<>();
        ArrayList<Double> avg1 = new ArrayList<>(Arrays.asList(0.15, 0.08, 0.07)); //testing for a 0.1
        ArrayList<Double> avg2 = new ArrayList<>(List.of(0.3)); //testing 0.3 as a singleton
        ArrayList<Double> avg3 = new ArrayList<>(Arrays.asList(-1.0,1.0,0.0)); //testing negatives
        ArrayList<Double> avg4 = new ArrayList<>(Arrays.asList(-1.0,1.0,0.0,2.0,3.0,4.0)); //large set
        ArrayList<Double> avg5 = new ArrayList<>(Arrays.asList(1.0, 2.0,3.0)); //get 2.0
        ArrayList<Double> avgSingleton = new ArrayList<>(Arrays.asList(1.0, 2.0, 3.0, 2.0, 1.0)); //singleton case with 3.0 being my singleton. Check for occurrences for other multiples


        //avg.add(1.0);
        compareDoubles(ProblemSet.average(avg3), 0.0); //negs
        compareDoubles(ProblemSet.average(avg),  0.0); //empty
        compareDoubles(ProblemSet.average(avg4), 1.5); //large
        compareDoubles(ProblemSet.average(avg1), 0.1); //0.1
        compareDoubles(ProblemSet.average(avgSingleton), 1.8); //singleton
        compareDoubles(ProblemSet.average(avg2), 0.3);

    }
    @Test
    public void test_SumOfDigits(){
        // Examples
        // 123 returns 6
        // 57 returns 12
        // -36 returns 9
        int test1 = 123;
        assertEquals(6, ProblemSet.sumOfDigits(test1));
        assertEquals(9, ProblemSet.sumOfDigits(-36));
    }

    @Test
    public void testBestKey(){

        // Examples
        // {"CSE": 100, "MTH": 90, "MGT": 10} returns "CSE"}
        // {"cat": 5, "dog": 5, "fox": 4} can return either "cat" or "dog"
        // {} returns ""
        HashMap<String, Integer> dict = new HashMap<>();
        HashMap<String, Integer> dict2 = new HashMap<>(); //base
        HashMap<String, Integer> dict3 = new HashMap<>(); //negatives
        HashMap<String, Integer> dict4 = new HashMap<>(); //multiples
        HashMap<String, Integer> dict5 = new HashMap<>(); //singleton : when a key has more than one value
        HashMap<String, Integer> dict6 = new HashMap<>(); //multiple maxes

        //base
        dict2.put("CSE", 100);
        dict2.put("MTH", 90);
        //negatives
        dict3.put("A", -5);
        dict3.put("B", -4);
        //multiples1
        dict4.put("A", 2);
        dict4.put("B", 4);
        dict4.put("C", 6);
        //multiple maxes
        dict6.put("A", 2);
        dict6.put("B", 3);
        dict6.put("C", 3);
        //singleton
        dict5.put("A", 2);
        dict5.put("B", 4);
        dict5.put("C", 6);
        dict5.put("A", 7);


        assertSame("", ProblemSet.bestKey(dict)); //empty
        assertSame("CSE", ProblemSet.bestKey(dict2)); //base
        assertSame("B", ProblemSet.bestKey(dict3)); //negatives
        assertSame("C", ProblemSet.bestKey(dict4)); //multiple
        //assertTrue(ProblemSet.bestKey(dict5) == "A");
        //assertTrue(singletonHelper(dict5) == "Singleton");//singleton
        assertEquals(ProblemSet.bestKey(dict6), "B");//duplicates
        assertEquals("B",ProblemSet.bestKey(dict6));

    }



}