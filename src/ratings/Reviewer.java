package ratings;

public class Reviewer {
    private String ReviewerID;


    public Reviewer(String reviewerID){
        this.ReviewerID = reviewerID;
    }

    public String getReviewerID() {
        return this.ReviewerID;}

    public void setReviewerID(String reviewerID) {
        this.ReviewerID = reviewerID;
    }


    public Rating rateSong(int ref){

        if(ref < 1 || ref > 5 ){
            ref = -1;
        }
        //System.out.println(ref);
        return new Rating(this.ReviewerID, ref);
    }


}