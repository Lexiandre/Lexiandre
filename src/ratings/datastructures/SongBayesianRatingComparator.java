package ratings.datastructures;

import ratings.Rating;
import ratings.Song;

public class SongBayesianRatingComparator extends Comparator<Song>{

    @Override
    public boolean compare(Song a, Song b){
        var left = a.bayesianAverageRating(2,3);
        var right = b.bayesianAverageRating(2,3);

        return right < left;
    }

}
