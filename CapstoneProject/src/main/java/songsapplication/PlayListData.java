package songsapplication;

import java.sql.*;
import java.util.*;

public class PlayListData implements PlayListInterface{
    Scanner scanner = new Scanner(System.in);

    @Override
    public void displayAllPlaylists() {
        List<PlayList> listOfPlayLists = new ArrayList<>();
        List<String> playLists = new ArrayList<>();

        try {
            Statement statement = SongsData.getConnection().createStatement();
            String query = "select * from playlist";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int playlistId = resultSet.getInt("playlist_id");
                String name = resultSet.getString("playlist_name");
                int id = resultSet.getInt("id");
                if (!playLists.contains(name)) {
                    PlayList playList = new PlayList(playlistId, name, id);
                    listOfPlayLists.add(playList);
                    playLists.add(name);
                }
            }
            for (PlayList playList : listOfPlayLists) {
                System.out.println(playList.getPlaylist_id() + "             " + playList.getPlaylistName());
            }
            System.out.println("Enter playlist name you want");
            String name = scanner.next();
            PlayListData playListData = new PlayListData();
            playListData.searchPlayList(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


     @Override
    public void createPlaylist(int pId, String pName, int id) {
        try {
            String query = "insert into playlist (playlist_id,playlist_name,id) values (?,?,?)";
            PreparedStatement preparedStatement = SongsData.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, pId);
            preparedStatement.setString(2, pName);
            preparedStatement.setInt(3, id);
            int rowsChanged = preparedStatement.executeUpdate();
            if (rowsChanged > 0) {
                System.out.println("playlist created");
            } else {
                System.out.println("playlist not created");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void searchPlayList(String playListName) {
        try {
            String query = "select * from songs where id in (select id from playlist where playlist_name=?)";
            PreparedStatement preparedStatement = SongsData.getConnection().prepareStatement(query);
            preparedStatement.setString(1, playListName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println(playListName);
                do {
                    String name = resultSet.getString("song_name");
                    int sId = resultSet.getInt("id");
                    System.out.println(sId + "---->" + name);
                } while (resultSet.next());
                System.out.println("Enter song name you want to play");
                String songName = scanner.next();
                SongsData songsData = new SongsData();
                songsData.SearchASongByName(songName);
            } else {
                System.out.println("not found");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePlaylist(String name) {
        try {
            String query = "delete from playlist where playlist_name=?";
            PreparedStatement preparedStatement = SongsData.getConnection().prepareStatement(query);
            preparedStatement.setString(1, name);
            int rows = preparedStatement.executeUpdate();
            if (rows > 0) {
                System.out.println("deleted Sucessfully");
            } else {
                System.out.println("not deleted ");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteSongFromPlaylist(String name, int id) {
        try {
            String query = "delete from playlist where playlist_name=? and id=?";
            PreparedStatement preparedStatement = SongsData.getConnection().prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            int rows = preparedStatement.executeUpdate();
            if (rows > 0) {
                System.out.println("deleted Sucessfully");
            } else {
                System.out.println("not deleted ");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
