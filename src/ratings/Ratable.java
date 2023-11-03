package ratings;

import ratings.datastructures.LinkedListNode;

import java.util.ArrayList;
import java.util.Arrays;

public class Ratable {
    //song and movie have to inherit from here
    private String Title;
    protected LinkedListNode<Rating> list_of_Ratings = null; //linked list of ratings


    //TODO getSet Title
    //get title
    public String getTitle(){ return this.Title;}
    //set title
    public void setTitle(String title){ this.Title = title;}


    //TODO geSet Ratings plus LINKED LIST stuff
    //add rating

    public void addRating(Rating ref){

        if(this.list_of_Ratings == null){
            this.list_of_Ratings = new LinkedListNode<>(ref, null);
        }
        else{
            this.list_of_Ratings.append(ref);
        }

    }
    //get rating
    public LinkedListNode<Rating> getRatings(){
        if(this.list_of_Ratings == null){ //this is redundant. Just being safe
            return null;
        }
        return this.list_of_Ratings;
        //must return the linked list the order they were added
    }

    //avg rating
    public double averageRating(){
        int count = 0;
        double total = 0;

        LinkedListNode<Rating> curRatingList = this.list_of_Ratings;

        while (curRatingList != null){ //run until node is null/ last node
            Rating rate = curRatingList.getValue(); //obtain the rating object from linked list
            if(rate.getRating() != -1){ //check for invalid rating before summation
                total+= rate.getRating(); //add good ratings only
                count ++;
            }
            curRatingList = curRatingList.getNext();//move one to the element in Linked list and check if it's valid before sum
        }
        if(count == 0){ //empty or invalid ratings
            return 0.0;
        }

        //else
        return total/count;
    }


    //TODO removeRating
    //remove rating by reviewer
    public void removeRatingByReviewer(Reviewer reviewer) {
        this.list_of_Ratings = removeRatingByReviewerHelper(this.list_of_Ratings, reviewer);
    }

    private LinkedListNode<Rating> removeRatingByReviewerHelper(LinkedListNode<Rating> current, Reviewer reviewer) {
        if (current == null) {
            return null; //return null if nothing is obtained
        }

        Rating rating = current.getValue();
        if (rating.getReviewerID().equals(reviewer.getReviewerID())) {
            return current.getNext();// if we find a rating by the reviewer, skip over it and return the rest of the list
        }

        // repeat recursion for the rest
        current.setNext(removeRatingByReviewerHelper(current.getNext(), reviewer));
        return current;
    }



    //TODO Bayesian Avg
    //bayesian Avg
    public double bayesianAverageRating(int num_Fake_ratings, int val_Fake_rating) {
        if ((num_Fake_ratings < 0) || (val_Fake_rating < 1 || val_Fake_rating > 5)) {
            return 0.0;
        }
        //double avg = 0.0;
        LinkedListNode<Rating> curRatingList = this.list_of_Ratings;
        double total = 0.0;
        int count = 0;

        //code goes here
        //If the song has ratings of 4 and 5 and this method is called with parameters 2 and 3 bayAvg(2,3)
        // (2 extra ratings of value 3), then the bayesian average is (4+5+3+3)/4 == 3.75 instead
        // of the regular average of 4.5
        //invalid inputs (anything outside 1-5) returns 0.0

//else?
        while (curRatingList != null) { //run until node is null/ last node
            Rating rate = curRatingList.getValue(); //obtain the rating object from linked list
            if (rate.getRating() != -1) { //check for invalid rating before summation
                total += rate.getRating(); //add good ratings only
                count+=1;
            }
            curRatingList = curRatingList.getNext();//move one to the element in Linked list and check if it's valid before sum
        }


        int bayesian_Extras = num_Fake_ratings * val_Fake_rating;
        total += bayesian_Extras;
        count += num_Fake_ratings;

        if (count > 0) { //empty or invalid ratings
            return total / count; //since it before the average operations, it will return 0.0 before the bayesian is considered
        }
        else{
            return 0.0;
        }

    }



//The method returns the Bayesian average of the Ratable if the extra rating were added
}
