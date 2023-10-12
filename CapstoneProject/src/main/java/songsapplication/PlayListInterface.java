package songsapplication;

public interface PlayListInterface {
    void displayAllPlaylists();
    void createPlaylist(int pId,String pName,int id);
    void searchPlayList(String playListName);
    void deletePlaylist(String name);
    void deleteSongFromPlaylist(String name,int id);
}
