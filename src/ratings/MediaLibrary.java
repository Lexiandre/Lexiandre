package ratings;

import java.util.*;

import static ratings.FileReader.*;

import ratings.datastructures.*;
import ratings.datastructures.Comparator;

public class MediaLibrary {

    private ArrayList<Song> songs_Rated;
    private ArrayList<Movie> movies_Unrated;
    private ArrayList<Movie> movies_Rated;

    //constructor that takes no params
    public MediaLibrary() {
    }

    public void populateLibrary(String ratedSongsFile, String unratedMoviesFile, String movieRatingsFile) {
        this.songs_Rated = readSongs(ratedSongsFile);
        this.movies_Unrated = readMovies(unratedMoviesFile);
        this.movies_Rated = readMovieRatings(this.movies_Unrated, movieRatingsFile);
    }


    /////////////////////////////////////////////////////////////////TOPk RATABLES///////////////////////////////////////////
    public ArrayList<Ratable> topKRatables(int k) {

        ArrayList<Ratable> ratable = new ArrayList<>();
        RatableBayesianRatingComparator bayesian = new RatableBayesianRatingComparator();
        Playlist2 sortedRatable = new Playlist2(bayesian); //using new Playlist2 class

        Set<Ratable> combined = new LinkedHashSet<>(this.songs_Rated);//combine Movies and Songs
        combined.addAll(this.movies_Rated);
        ArrayList<Ratable> combined_toSort = new ArrayList<>(combined);

        for (int i = 0; i < combined_toSort.size(); i++) {
            sortedRatable.addRatable(combined_toSort.get(i));
        }

        var sort = sortedRatable.getSortedRateable();
        if (k > sort.size()) {
            while (sort != null) {
                ratable.add(sort.getValue());
                sort = sort.getNext();
            }
        } else {
            for (int i = 0; i < k; i++) {
                ratable.add(sort.getValue());
                sort = sort.getNext();

            }
        }
        return ratable;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //modified Playlist
    private static class Playlist2 {
        private LinkedListNode<Ratable> SortedRateable = null;
        private BinaryTreeNode<Ratable> RatableBST = null;//root
        private Comparator<Ratable> RatableComparator;

        public Playlist2(Comparator<Ratable> RatableComparator) {
            //sort songs by given Comparator
            this.RatableComparator = RatableComparator;
        }

        public void addRatable(Ratable ref) {
            if (this.RatableBST == null) {
                this.RatableBST = new BinaryTreeNode<>(ref, null, null);
            } else {
                this.insertHelper(this.RatableBST, ref);
            }
        }

        private void insertHelper(BinaryTreeNode<Ratable> node, Ratable toInsert) {
            if (this.RatableComparator.compare(toInsert, node.getValue())) {
                if (node.getLeft() == null) {
                    node.setLeft(new BinaryTreeNode<>(toInsert, null, null));
                } else {
                    insertHelper(node.getLeft(), toInsert);
                }
            } else {
                if (node.getRight() == null) {
                    node.setRight(new BinaryTreeNode<>(toInsert, null, null));
                } else {
                    insertHelper(node.getRight(), toInsert);
                }
            }
        }

        public LinkedListNode<Ratable> getSortedRateable() {
            this.SortedRateable = getRatableListHelper(this.RatableBST, this.SortedRateable);  // Call the helper function to build the list
            return this.SortedRateable;
        }

        private LinkedListNode<Ratable> getRatableListHelper(BinaryTreeNode<Ratable> node, LinkedListNode<Ratable> sortedRatable) {

            if (node != null) {
                sortedRatable = getRatableListHelper(node.getRight(), sortedRatable);  // Traverse right subtree
                sortedRatable = new LinkedListNode<>(node.getValue(), sortedRatable);  // Add the current node's value to the list
                sortedRatable = getRatableListHelper(node.getLeft(), sortedRatable);  // Traverse left subtree
            }
            return sortedRatable;
        }

    }

    private static class RatableBayesianRatingComparator extends Comparator<Ratable> {
        @Override
        public boolean compare(Ratable a, Ratable b) {
            var left = a.bayesianAverageRating(2, 3);
            var right = b.bayesianAverageRating(2, 3);

            return right < left;
        }
    }


    ////////////////////////////////PALINDROME/////////////////////////////////////////////
    public static boolean isPalindrome(long x) {
        String y = Long.toString(x);
        char[] mystr = y.toCharArray();
        //System.out.println(y);

        ArrayList<String> abc = new ArrayList<>();
        for (char alpha : mystr) {
            abc.add(Character.toString(alpha));
        }

        if (abc.size() <= 2) {
            return abc.get(0).equalsIgnoreCase(abc.get(abc.size() - 1));
        } else {
            int j = abc.size()-1;
            for (int i = 0; i < abc.size(); i++) {
                if (!(abc.get(i).equals(abc.get(j)))) {
                    return false;
                }
                j--;
            }
        }

        return true;

    }
    public static boolean isPalindrome2(int x) {
        //when using chars, we can use old school iterations chatAt(i)
        String y = Integer.toString(x);//this can be traversed with CharAt

        var lent = y.length();
        if(lent <=2){
            return y.charAt(0) == y.charAt(lent-1);
        }

        int i = 0;
        int j = lent - 1; //ignore the middle element i=j, because it'll be the same for both
        while (i < j) {
            if (y.charAt(i) != y.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public static int thirdMax(int[] nums) {
        //third distinct number
        //if it doesn't exist return the third max
        ArrayList<Integer> dist = new ArrayList<>();

        for(int i : nums){
            if(!(dist.contains(i))){
                dist.add(i);
            }
        }
        int sized = dist.size();
        if(sized >=3){
            return dist.get(2);
        }else{
            var d = dist.stream().max(Integer::compare);
            return d.get();
        }

    }
    public static int thirdMax2(int[] nums) {
        Integer max1 = null;
        Integer max2 = null;
        Integer max3 = null;
        for (Integer n : nums) {
            if (n.equals(max1) || n.equals(max2) || n.equals(max3)) continue;
            if (max1 == null || n > max1) {
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if (max2 == null || n > max2) {
                max3 = max2;
                max2 = n;
            } else if (max3 == null || n > max3) {
                max3 = n;
            }
        }
        return max3 == null ? max1 : max3;
    }


    public static void main(String[] args) {
        var x = isPalindrome2(10);
        var y = isPalindrome(Integer.parseInt("789"));
        System.out.println(x);
        System.out.println(y);


        System.out.println("New lines");
        int[] names = {1,2,2,5,3,5};
        var intter = thirdMax2(names);
        System.out.println(intter);
    }

}
