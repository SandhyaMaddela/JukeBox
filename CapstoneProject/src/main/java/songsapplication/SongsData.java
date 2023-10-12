package songsapplication;

import javax.sound.sampled.*;
import java.io.*;
import java.sql.*;
import java.util.*;


public class SongsData implements SongInterface{
    public static Connection getConnection(){
        Connection connection=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/jukeboxdb","root","Sanju@14");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    @Override
    public void displayAllSongs(){
        List<Song> allSongs=new ArrayList<>();
        try{
            Statement statement=getConnection().createStatement();
            String query="select * from songs";
            ResultSet resultSet=statement.executeQuery(query);
            while (resultSet.next()){
                int songId=resultSet.getInt("id");
                String songName=resultSet.getString("song_name");
                String songGenre=resultSet.getString("song_genre");
                String songArtist=resultSet.getString("album_artist");
                double songDuration=resultSet.getDouble("duration");
                allSongs.add(new Song(songId,songName,songGenre,songArtist,songDuration));
            }
            System.out.println("----------------------------------------------------------------------------");
            System.out.println("Song Id     Song Name       Song Genre    Album artist     Duration ");
            System.out.println("----------------------------------------------------------------------------");
            for(Song song:allSongs){
                System.out.println(song.getSongId()+"        "+song.getSongName()+"            "+song.getGenre()+"        "+song.getAlbumArtists()+"            "+song.getDuration());
            }
            System.out.println("-------------------------------------------------------------------------------");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void SearchASongByName(String Name) {
        try {
            Connection connection = SongsData.getConnection();
            String query = "select * from songs where song_name=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, Name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String name=resultSet.getString("song_name");
                System.out.println(name + " will play now");
                File f = new File(resultSet.getString("path_url"));
                AudioInputStream audio = AudioSystem.getAudioInputStream(f);
                Clip clip = AudioSystem.getClip();
                clip.open(audio);
                Scanner scanner = new Scanner(System.in);
                int choice=0;
                while (choice!=6) {
                    System.out.println("Enter 1 ----> play the song ");
                    System.out.println("      2 ----> play the song on loop ");
                    System.out.println("      3 ----> restart the song");
                    System.out.println("      4 ----> pause");
                    System.out.println("      5 ----> resume");
                    System.out.println("      6 ----> stop the song ");

                    choice = scanner.nextInt();
                    switch (choice) {
                        case 1:
                            clip.start();
                            break;
                        case 2:
                            clip.loop(10);
                            break;
                        case 3:
                            clip.setMicrosecondPosition(0);
                            break;
                        case 4:
                            if (clip.isRunning()) {
                                clip.stop();
                            }
                            break;
                        case 5:
                            if (!clip.isRunning()) {
                                clip.start();
                            }
                            break;
                        case 6:
                            clip.stop();
                            break;
                    }
                }
            }
        } catch (UnsupportedAudioFileException | SQLException | IOException |  LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void SearchASongByGenre(String genre){
        List<Song> listOfGenre=new ArrayList<>();
        try{
            Connection connection=SongsData.getConnection();
            String query="select * from songs where song_genre=?";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,genre);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                listOfGenre.add(new Song(resultSet.getInt("id"),resultSet.getString("song_name"),resultSet.getString("song_genre"),resultSet.getString("album_artist"),resultSet.getDouble("duration")));
            }
            for(Song song:listOfGenre){
                System.out.println(song.getSongName());
            }
            Scanner scanner=new Scanner(System.in);
            System.out.println("Enter song name you want");
            String songName=scanner.next();
            SongsData sd=new SongsData();
            sd.SearchASongByName(songName);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    @Override
    public void SearchASongByArtist(String artist) {
        List<Song> listOfAlbumArtist=new ArrayList<>();
        try {
            Connection connection = SongsData.getConnection();
            String query = "select * from songs where album_artist=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, artist);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                listOfAlbumArtist.add(new Song(resultSet.getInt("id"),resultSet.getString("song_name"),resultSet.getString("song_genre"),resultSet.getString("album_artist"),resultSet.getDouble("duration")));
            }
            for(Song song:listOfAlbumArtist){
                System.out.println(song.getSongName());
            }
            Scanner scanner=new Scanner(System.in);
            System.out.println("Enter song name you want");
            String songName=scanner.next();
            SongsData sd=new SongsData();
            sd.SearchASongByName(songName);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

