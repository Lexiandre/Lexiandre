package tests;
import org.junit.Test;
import ratings.Rating;
import ratings.Song;
import ratings.Ratable;

import ratings.Playlist;
import ratings.datastructures.*;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class TestDataStructures2 {

//
//    @Test
//    public void testBSTLinkedList(){
//        Song Gnat = new Song("Gnat","Eminem", "001");
//        Song Marsh = new Song("Marsh", "Em", "002");
//
//        Rating consumer1 = new Rating("Alex", 5);
//        Rating consumer2 = new Rating("Ben", 1);
//        Rating consumer3 = new Rating("King", 5);
//        Rating consumer4 = new Rating("Jnr", 5);
//        Rating consumer5 = new Rating("Lexa", 5);
//
//
//        Gnat.addRating(consumer1);
//        Gnat.addRating(consumer2);//5,4
//
//        Marsh.addRating(consumer3);
//        Marsh.addRating(consumer4);
//        Marsh.addRating(consumer5);//5,1,1
//
//        //var gnatAvg = Gnat.bayesianAverageRating(2,3);
//        //var marshAvg = Marsh.bayesianAverageRating(2,3);
//
//        //System.out.println("gnatBayeAvg: "+ gnatAvg);
//        //System.out.println("marshBayeAvg: "+marshAvg);
//
//        //test ratings.Playlist
//        Comparator<Song> music1 = new Comparator<Song>();
//        SongBayesianRatingComparator compBayesian= new SongBayesianRatingComparator();
//        //boolean compBaye = compBayesian.compare(Gnat, Marsh);
//        //System.out.println("compareBayes:" + compBaye);
//
//
//        SongTitleComparator compTitle = new SongTitleComparator();
//        //boolean titles =  compTitle.compare(Gnat, Marsh);
//        //System.out.println("titles:" + titles);
//
//
//        Playlist play_list1 = new Playlist(compBayesian);
//        Playlist play_list2 = new Playlist(compTitle);
//        //LinkedListNode<Song> playLinkedList =  play_list.getSongList();//should return Linked list of sorted songs
//
//        //Bayesian
//        play_list1.addSong(Gnat);
//        play_list1.addSong(Marsh);
//
//        //Title
//        play_list2.addSong(Marsh);
//        play_list2.addSong(Gnat);
//
//        //Bayesian
//        LinkedListNode<Song> playLinkedList1 =  play_list1.getSongList();
//        //System.out.print("bayesian :"+playLinkedList1.getValue().getTitle() + " ");
//        //System.out.println(playLinkedList1.getNext().getValue().getTitle());
//        //System.out.println(playLinkedList1.size());
//
//        //Title
//        LinkedListNode<Song> playLinkedList2 =  play_list2.getSongList();
//        //System.out.print("title: "+playLinkedList2.getValue().getTitle()+ " ");
//        //System.out.println(playLinkedList2.getNext().getValue().getTitle());
//        //System.out.println(playLinkedList2.size());
//
//
//
//        //loop to test out linked list elements
//        ArrayList<String> sortedAlphabetically = new ArrayList<String>(Arrays.asList("Gnat", "Marsh"));
//        ArrayList<String> sortedByBayesian = new ArrayList<String>(Arrays.asList("Marsh", "Gnat"));
//
//        assertNotNull(playLinkedList2);
//        assertNotNull(playLinkedList1);
//
//        CompareToList(playLinkedList2, sortedAlphabetically);
//        CompareToList(playLinkedList1, sortedByBayesian);
//
//
//        //
//        var root1 = play_list1.getSongTree(); //should return Gnat
//        assertNotNull(root1);
//        //System.out.println("root1 is: "+ root1.getValue().getTitle());
//        assertEquals("Gnat", root1.getValue().getTitle());//Gnat was added first to the BST, -> root
//
//
//        var root2 = play_list2.getSongTree(); //should return Gnat
//        assertNotNull(root2);
//        //System.out.println("root2 is: "+ root2.getValue().getTitle());
//        assertEquals("Marsh", root2.getValue().getTitle());//marsh was added first to the BST, -> root
//    }


    @Test
    public void moreSongs(){
        Song Gnat = new Song("Gnat","Eminem", "001");
        Song Marsh = new Song("Marsh", "Em", "002");
        Song Greatest = new Song("Greatest", "Em", "003");
        Song Superman = new Song("Superman", "Stan", "004");

        Rating consumer1 = new Rating("Alex", 5);
        Rating consumer2 = new Rating("Ben", 1);
        Rating consumer3 = new Rating("King", 5);
        Rating consumer4 = new Rating("Jnr", 5);
        Rating consumer5 = new Rating("Lexa", 5);


        Gnat.addRating(consumer1);
        Gnat.addRating(consumer2);//5,4

        Marsh.addRating(consumer3);
        Marsh.addRating(consumer4);
        Marsh.addRating(consumer5);//5,1,1

        Greatest.addRating(consumer1);
        Greatest.addRating(consumer2);
        Greatest.addRating(consumer4);
        Greatest.addRating(consumer5);

        Superman.addRating(consumer4);

        //Comparator<Song> music1 = new Comparator<Song>();
        SongTitleComparator compTitle = new SongTitleComparator();
        Playlist play_list2 = new Playlist(compTitle);

        //Titles
        play_list2.addSong(Marsh);
        play_list2.addSong(Gnat);
        play_list2.addSong(Greatest);
        play_list2.addSong(Superman);

        ArrayList<String> sortedAlphabetically = new ArrayList<>(Arrays.asList("Gnat","Greatest", "Marsh", "Superman"));
        LinkedListNode<Song> playLinkedList2 =  play_list2.getSongList();

        assertNotNull(playLinkedList2);
        CompareToList(playLinkedList2, sortedAlphabetically);

    }


    //TODO: Tests
    public void CompareToList(LinkedListNode<Song> playList, ArrayList<String> orderedName){//strings
        if(playList == null || orderedName == null){
            assertNull(playList);
        }

        else{
            assertNotNull(playList);
            var noder = playList;
            var index = 0;
            while (noder != null){
                //assertNotNull(playList);
                assertEquals(orderedName.get(index), noder.getValue().getTitle());
                index++;
                noder = noder.getNext();
            }
        }

    }


    @Test
    public void playlistMissingSong(){
        Song Gnat = new Song("Gnat","Eminem", "001");
        Song Marsh = new Song("Marsh", "Em", "002");
        Song Greatest = new Song("Greatest", "Em", "003");
        Song noAdd = new Song("Greatest", "Em", "003");

        SongTitleComparator titles = new SongTitleComparator();
        Playlist player1 = new Playlist(titles);
        player1.addSong(Gnat);
        player1.addSong(Marsh);
        player1.addSong(Greatest);

        var playList = player1.getSongList();
        assertNotNull(player1);
        assertEquals(3,playList.size());

        if(playList.size()<=3){
            assertNotNull(playList);
            player1.addSong(noAdd);
            assertEquals(3, playList.size());
        }
        else {
           assertFalse(playList.size() > 4);
        }

    }


    @Test
    public void testGetSongtree(){
        Song Gnat = new Song("Gnat","Eminem", "001");
        Song Marsh = new Song("Marsh", "Em", "002");
        Song Al = new Song("Al", "Em", "003");
        Song noAdd = new Song("Oracle", "Em", "003");

        SongTitleComparator titles = new SongTitleComparator();
        Playlist player1 = new Playlist(titles);
        player1.addSong(Gnat);
        player1.addSong(Marsh);
        player1.addSong(Al);
        player1.addSong(noAdd);

        var playList = player1.getSongList();//LINKED LIST
        BinaryTreeNode<Song> newRoot = player1.getSongTree(); //New Node


        var rootLeft = newRoot.getLeft();
        var rootRight = newRoot.getRight();
        System.out.println("root.Left: " + rootLeft.getValue().getTitle());
        assertEquals("Al", rootLeft.getValue().getTitle());

        System.out.println("root.Right: " + rootRight.getValue().getTitle());
        assertTrue(rootRight.getValue().getTitle().equals("Marsh"));

        System.out.println("root.Right.Right: " + rootRight.getRight().getValue().getTitle());
        assertTrue(rootRight.getRight().getValue().getTitle().equals("Oracle"));

        //System.out.println(newRoot.getValue().getTitle());
        //System.out.println(playList.size());
        assertTrue(4==playList.size());

        assertTrue(titles.compare(Al, newRoot.getValue()));
        assertFalse(titles.compare(noAdd, newRoot.getValue()));
        assertTrue(4 == playList.size());

//        var rootNode = newRoot.getValue().getTitle();
//        var idk = newRoot.getValue();
        //System.out.println(rootNode);


    }

    @Test
    public void lastOne(){
        Song Gnat = new Song("Gnat","Eminem", "001");
        Song Marsh = new Song("Marsh", "Em", "002");
        Song Greatest = new Song("Greatest", "Em", "003");
        Song Superman = new Song("Superman", "Stan", "004");


        Rating consumer1 = new Rating("Alex", 1);
        Rating consumer2 = new Rating("Ben", 2);
        Rating consumer3 = new Rating("King", 3);
        Rating consumer4 = new Rating("Jnr", 4);
        Rating consumer5 = new Rating("Lexa", 5);


        Gnat.addRating(consumer1);
        Gnat.addRating(consumer2);//5,4

        Marsh.addRating(consumer3);
        Marsh.addRating(consumer4);
        Marsh.addRating(consumer5);//5,1,1

        Greatest.addRating(consumer1);
        Greatest.addRating(consumer2);
        Greatest.addRating(consumer4);
        Greatest.addRating(consumer5);

        Superman.addRating(consumer4);

        //Comparator<Song> music1 = new Comparator<Song>();
        SongTitleComparator compTitle = new SongTitleComparator();
        Playlist play_list2 = new Playlist(compTitle);

        //Titles
        play_list2.addSong(Marsh);
        play_list2.addSong(Gnat);
        play_list2.addSong(Greatest);
        play_list2.addSong(Superman);
    }



}
