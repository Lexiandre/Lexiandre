package ratings;

import java.util.ArrayList;

public class Movie extends Ratable{

    private ArrayList<String> CastMembers;

    public Movie(String movTitle, ArrayList<String> castMembers){
        this.setTitle(movTitle);
        this.CastMembers = castMembers;
    }


    public ArrayList<String> getCast(){ //should not return the same list from constructor,
        //but will contain same names in same order despite the casing
        //use equalsIgnoreCase when comparing the strings
        return this.CastMembers;
    }



}
