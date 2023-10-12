package songsapplication;

public class Song {
    private int songId;
    private String songName;
    private String genre;
    private String albumArtists;
    private double duration;

    //getter and setter methods
    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }
    public void setSongName(String songName) {
        this.songName = songName;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public String getAlbumArtists() {
        return albumArtists;
    }
    public void setAlbumArtists(String albumArtists) {
        this.albumArtists = albumArtists;
    }
    public double getDuration() {
        return duration;
    }
    public void setDuration(double duration) {
        this.duration = duration;
    }
    //constructor
    public Song(int songId,String songName, String genre, String albumArtists, double duration){
        this.songId=songId;
        this.songName=songName;
        this.genre=genre;
        this.albumArtists=albumArtists;
        this.duration=duration;
    }

    @Override
    public String toString() {
        return "Song{" +
                "songId=" + songId +
                ", songName='" + songName + '\'' +
                ", genre='" + genre + '\'' +
                ", albumArtists='" + albumArtists + '\'' +
                ", duration=" + duration +
                '}';
    }
}
