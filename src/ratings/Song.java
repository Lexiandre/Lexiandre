package ratings;
import ratings.datastructures.LinkedListNode;

public class Song extends Ratable{
    private String Artist;
    private  String SongID;

    public Song (String title, String artist, String songID){
        //super();
        this.setTitle(title);
        this.Artist = artist;
        this.SongID =songID;
    }


    public String getArtist(){return this.Artist;}
    public void setArtist(String artist){ this.Artist = artist;}

    public String getSongID(){return this.SongID;}
    public void setSongID(String songID){this.SongID = songID;}







    //TODO: LINKED LIST :TASK 3
    //private LinkedListNode<Rating> list_of_Ratings = null;
    public void setRatings(LinkedListNode<Rating> new_LoR){
        this.list_of_Ratings = new_LoR;
    }




    //TODO: TASK 4
    //TODO: Bayesian Average Rating
   //moved to Ratable




}
