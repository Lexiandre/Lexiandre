package ratings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ProblemSet {

    // TODO: Implement this method to return the average of all the numbers in the input ArrayList
    //       If the ArrayList is empty, return 0.0
    public static double average(ArrayList<Double> numbers) {
        int lent = numbers.size();
        double total = 0.0;
        //double retval = 0.0;

        if (numbers.isEmpty()) {
            return 0.0;
        }

        for (Double number : numbers) {
            total += number;
        }

        return total / lent;



    }


    // TODO: Write a public static method named sumOfDigits that takes an int as a parameter and
    //       returns the sum of the digits of the input as an int

    //TODO: Epsilon to compare doubles
    public static int sumOfDigits(int num) {
        if (num == 0) {
            return num;
        }
        num = Math.abs(num); //for the negatives

        int total = 0;

        while (num > 0) {
            int val = num % 10;
            total += val;
            num /= 10;
        }

        return total;
    }

    //TODO: Write a public static method named bestKey that takes a HashMap of String to Integer
    //as a parameter and returns a key mapping to the largest Integer. Ties can be broken arbitrarily.
    //If the HashMap is empty, return the empty String

    //testing bestkey3 failed test with new method
    //it's best to use hashmap functions to handle Dictionaries
    public static String bestKey(HashMap<String, Integer> map) {
        String bestKey = "";
        int maxValue = Integer.MIN_VALUE;

        for (HashMap.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > maxValue) {
                maxValue = entry.getValue();
                bestKey = entry.getKey();
            }
        }

        return bestKey;
    }
}
