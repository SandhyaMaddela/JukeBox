package songsapplication;

import java.util.Scanner;

public class JukeBoxImplementation {
    public static void main(String[] args) {
        UserImpl u = new UserImpl();
        JukeBoxImplementation i=new JukeBoxImplementation();
        System.out.println("Enter 1------------------->To Create Account");
        System.out.println("      2------------------->Login");
        System.out.println("      3------------------->Continue withOut login");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        switch (n){
            case 1:
                System.out.println("Enter username");
                String userName = sc.next();
                System.out.println("Enter password");
                String userPassword = sc.next();
                System.out.println("Enter mail id");
                String mailId = sc.next();
                System.out.println("Enter mobile no");
                int mNo = sc.nextInt();
                u.newUser(userName, userPassword, mailId, mNo);
                break;
            case 2:
                System.out.println("Enter username");
                String name = sc.next();
                System.out.println("Enter password");
                String pass = sc.next();
                u.login(name, pass);
                break;
            case 3:
                i.jukeBoxImpl();
                break;
        }
    }
    public void jukeBoxImpl(){
        Scanner sc=new Scanner(System.in);
        SongsData songsData=new SongsData();
        PlayListData playListData=new PlayListData();
        System.out.println("<----------------welcome to Juke Box-------------------->");
        System.out.println(" ");
        int choice;

        do{
            System.out.println("Enter 1----> Songs");
            System.out.println("      2----> Playlists");
            System.out.println("      3----> Exit");
            choice= sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("These are all the songs present in the jukebox....");
                    System.out.println(" ");
                    songsData.displayAllSongs();
                    System.out.println("Enter 1----->Search by Name");
                    System.out.println("      2----->Search by Genre");
                    System.out.println("      3----->Search by Album Artist");
                    System.out.println("      4----->Exit");
                    int choice2 = sc.nextInt();
                    switch (choice2) {
                        case 1:
                            System.out.println("Enter song name you want to play");
                            String songNameUWant = sc.next();
                            songsData.SearchASongByName(songNameUWant);
                            break;
                        case 2:
                            System.out.println("Enter song genre you want to play");
                            String songGenreUWant = sc.next();
                            songsData.SearchASongByGenre(songGenreUWant);
                            break;
                        case 3:
                            System.out.println("Enter artist name");
                            String songUWant = sc.next();
                            songsData.SearchASongByArtist(songUWant);
                            break;
                    }
                    break;
                case 2:
                    System.out.println("Enter 1----->View Playlists");
                    System.out.println("      2----->Insert Song Into Playlist/create playlist");
                    System.out.println("      3----->Search for Playlist");
                    System.out.println("      4----->Delete Entire Playlist");
                    System.out.println("      5------>Delete Song From a Playlist");
                    System.out.println("      6------>Exit");
                    int choice3 = sc.nextInt();
                    switch (choice3) {
                        case 1:
                            playListData.displayAllPlaylists();
                            break;
                        case 2:
                            System.out.println("Enter playlist id");
                            int playId = sc.nextInt();
                            System.out.println("Enter playlist name");
                            String playName = sc.next();
                            System.out.println("Enter song id");
                            int sId = sc.nextInt();
                            playListData.createPlaylist(playId, playName, sId);
                            break;
                        case 3:
                            System.out.println("Enter playlist name you want to search");
                            String pLName = sc.next();
                            playListData.searchPlayList(pLName);
                            break;
                        case 4:
                            System.out.println("Enter playlist name you want to delete");
                            String playListName=sc.next();
                            playListData.deletePlaylist(playListName);
                            break;
                        case 5:
                            System.out.println("Enter playlist name ");
                            String name=sc.next();
                            System.out.println("Enter song id to delete from playlist");
                            int songId= sc.nextInt();
                            playListData.deleteSongFromPlaylist(name,songId);
                            break;
                    }
            }
        }while (choice!=3);
    }
}

