package ratings.datastructures;

import ratings.Song;

public class SongTitleComparator extends Comparator<Song>{

//    public SongTitleComparator(){
//
//    }

    //sort songs by titles in alphabetical order
    @Override
    public boolean compare(Song a , Song b){
        //return true if a < b
        var left = a.getTitle();
        var right = b.getTitle();
        return left.compareToIgnoreCase(right) < 0;
    }

}
