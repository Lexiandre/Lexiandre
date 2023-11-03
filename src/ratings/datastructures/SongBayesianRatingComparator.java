package ratings.datastructures;

import ratings.Rating;
import ratings.Song;

public class SongBayesianRatingComparator extends Comparator<Song>{


//    public SongBayesianRatingComparator(){
//
//    }
    @Override
    public boolean compare(Song a, Song b){
        var left = a.bayesianAverageRating(2,3);
        var right = b.bayesianAverageRating(2,3);

        return right < left;
    }


//    public static void main(String[] args) {
//        Song track1 = new Song("Runaway","Aurora","serene");
//        Rating rate1 = new Rating("Alex",5);
//        track1.addRating(rate1);
//
//        Song track2 = new Song("Runaway","Aurora","serene");
//        Rating rate2 = new Rating("Alex",5);
//        track2.addRating(rate2);
//
//        boolean comp = compare(track1, track2);
//    }
}
