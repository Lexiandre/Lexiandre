package ratings;

public class Rating {
    private String ReviewerID;
    private int Rating;



    public Rating(String reviewerID, int rating){
        if(rating > 5 || rating < 1){
            this.Rating = -1;
        }
        else{
            this.Rating = rating;
        }
        this.ReviewerID = reviewerID;

        //this.RatingCap = 5;
    }


    public String getReviewerID() {
        return this.ReviewerID;
    }
    public void setReviewerID(String reviewerID) {
        this.ReviewerID = reviewerID;
    }

    public int getRating() {
        return this.Rating;
    }


    public void setRating(int rating) {

        if (rating > 5 || rating < 1) {
            this.Rating = -1;
        } else{
            this.Rating = rating;
        }
    }




}