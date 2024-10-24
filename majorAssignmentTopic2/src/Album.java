import java.util.ArrayList;

public class Album {
    public String title;
    public String releaseDate;
    public ArrayList<Song> songs;

    public Album(String title, String releaseDate) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.songs = new ArrayList<>();
    }

    // Add song to album
    public void addSong(Song song) {
        songs.add(song);
        System.out.println("Song added to album: " + song.getTitle());
    }

    // Display all songs in the album
    public void displaySongs() {
        System.out.println("Songs in album: " + title);
        for (Song song : songs) {
            System.out.println("- Song: " + song.getTitle() + " (" + song.getDuration() + " seconds, Single: " + song.isSingle() + ")");
        }
    }

    
    /* 
    Fetces all the title, release date and songs
    */
    
    public String getTitle() {
        return title;
    }


    public String getReleaseDate() {
        return releaseDate;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }
}