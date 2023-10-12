package songsapplication;

public class PlayList {
    private int playlist_id;
    private String playlistName;
    private int songId;

    //getter and setter methods
    public int getPlaylist_id() {

        return playlist_id;
    }
    public void setPlaylist_id(int playlist_id) {
        this.playlist_id = playlist_id;
    }
    public String getPlaylistName() {
        return playlistName;
    }
    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }
    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    //constructor
    public PlayList(int playlist_id,String playlistName,int songId){
        this.playlist_id=playlist_id;
        this.playlistName=playlistName;
        this.songId=songId;
    }

    @Override
    public String toString() {
        return "PlayList{" +
                "playlist_id=" + playlist_id +
                ", playlistName='" + playlistName + '\'' +
                ", songId=" + songId +
                '}';
    }
}
