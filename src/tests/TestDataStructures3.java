package tests;

import org.junit.Test;
import ratings.DegreesOfSeparation;
//import ratings.DegreesOfSeparation;

import static org.junit.Assert.*;
import static ratings.FileReader.readMovies;
import ratings.FileReader;
import ratings.Movie;

public class TestDataStructures3 {

    @Test
    public void testDegrees(){
        var movies = readMovies("data/degSeperation.csv");
        var deg = new DegreesOfSeparation(movies);

        var sep = deg.degreesOfSeparation("Alex", "Jnr");//1
        var sep2 = deg.degreesOfSeparation("Alex", "Alex");//0
        var sep3 = deg.degreesOfSeparation("Alex", "Ben");//3
        var sep4 = deg.degreesOfSeparation("Alex", "Quaye");//2
        var sep5 = deg.degreesOfSeparation("nrt","Ice");

        assertEquals(3, deg.degreesOfSeparation("Alex", "Ben"));
        assertEquals(0, deg.degreesOfSeparation("Alex", "Alex"));//0
        assertEquals(1, deg.degreesOfSeparation("Alex", "Jnr"));
        assertEquals(2, deg.degreesOfSeparation("Alex", "Quaye"));
        assertEquals(3, deg.degreesOfSeparation("Sam", "Alex"));
        assertEquals(sep5, -1);

    }

    @Test
    public void testDegOfSep(){
        var movies = readMovies("data/degSeperation.csv");
        var deg = new DegreesOfSeparation(movies);

        var sep = deg.degreesOfSeparation("Alex", "Jnr");//1
        var sep2 = deg.degreesOfSeparation("Alex", "Alex");//0
        var sep3 = deg.degreesOfSeparation("Alex", "Ben");//3
        var sep4 = deg.degreesOfSeparation("Alex", "Quaye");//2
        System.out.println(sep);
        System.out.println(sep2);
        System.out.println(sep3);
        System.out.println(sep4);


    }

}
