
package ratings;

import ratings.datastructures.*;

public class Playlist {

    private LinkedListNode<Song> SortedSong = null;
    private BinaryTreeNode<Song> songBST = null;//root
    private Comparator<Song> SongComparator;

    //should be a Binary tree?
    public Playlist(Comparator<Song> songComparator) {
        //sort songs by given Comparator
        this.SongComparator = songComparator;
    }

    public void addSong(Song ref) { //add song to playlist
        //implement the sort functionality on add?
        //we can use code from lecture slides
        if (this.songBST == null) {
            this.songBST = new BinaryTreeNode<>(ref, null, null);
        } else {
            this.insertHelper(this.songBST, ref);
        }
        //SortedSongs.append(ref);
    }
    private void insertHelper(BinaryTreeNode<Song> node, Song toInsert) {
        if (this.SongComparator.compare(toInsert, node.getValue())) {
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


    //TODO: getSongTree
    public BinaryTreeNode<Song> getSongTree() {
        return this.songBST;//root of BST
    }


    //hardest part??
    public LinkedListNode<Song> getSongList() {
        this.SortedSong = getSongListHelper(this.songBST, this.SortedSong);  // Call the helper function to build the list
        return this.SortedSong;
    }

    public LinkedListNode<Song> getSongList(BinaryTreeNode<Song> root) {
        this.SortedSong = getSongListHelper(root, this.SortedSong);  // Call the helper function to build the list
        return this.SortedSong;
    }

    private LinkedListNode<Song> getSongListHelper(BinaryTreeNode<Song> node, LinkedListNode<Song> sortedSong) {

        //node = this.songBST;
//        //return this.SortedSong;
//        //the returned node would be the head of the Linked list according to the Comparator
//        //So, songTitle comparator will return head based on title
//        //And songBayesian will return head based on Bayesian rating,
//        //Sorted in decreasing Order, Highest first, remember Bayesian adds 2 extra 3's
//        //Note: returned songs might be different from the Song objects but contain the same info
//        //If no songs have been added, return null;
//
//        //This method traverses the tree from the parameter
//        // and returns all the Songs in the tree in a Linked List in sorted order
//        // (Assuming the parameter is the root of a BST)
        if (node != null) {
            sortedSong = getSongListHelper(node.getRight(), sortedSong);  // Traverse left subtree
            sortedSong = new LinkedListNode<>(node.getValue(), sortedSong);  // Add the current node's value to the list
            sortedSong = getSongListHelper(node.getLeft(), sortedSong);  // Traverse right subtree
        }
        return sortedSong;
    }


}




