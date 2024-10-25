import java.util.ArrayList;
import java.util.Random;

public class Playlist {
    public String name;
    public ArrayList<Song> songs;
    public int currentIndex = -1;

    public Playlist(String name) {
        this.name = name;
        this.songs = new ArrayList<>();
    }

    // Add song to playlist (fixing the addSong(Song) issue)
    public void addSong(Song song) {
        songs.add(song);
        System.out.println("Song added to playlist: " + song.getTitle());
    }





    // retrieves the current index of the song
    public Song getCurrentSong() {
        if (currentIndex < 0 || songs.isEmpty()) {
            return null;  // No song is playing
        }
        return songs.get(currentIndex);
    }



    public void playCurrentSong() {
        if (songs.isEmpty()) {
            System.out.println("No songs in the playlist.");
            return;
        }
        if (songs.isEmpty() == false && currentIndex == -1){
            currentIndex = 0;
        }
        if (currentIndex == -1) {  // If no song has been played yet
            System.out.println("No song is currently playing.");
            return;
        }
        
        // Play and return the current song
        Song currentSong = songs.get(currentIndex);
        currentSong.play();  // Play the current song
    }




    // Play the next song
    public void playNext() {
        if (songs.isEmpty()) {
            System.out.println("No songs in the playlist.");
            return;
        }
        currentIndex = (currentIndex + 1) % songs.size();
        songs.get(currentIndex).play();
    }

    // Play the previous song
    public void playPrevious() {
        if (songs.isEmpty()) {
            System.out.println("No songs in the playlist.");
            return;
        }
        currentIndex = (currentIndex - 1 + songs.size()) % songs.size();
        songs.get(currentIndex).play();
    }

    // Shuffle play (recursive)
    public void shufflePlay() {
        if (songs.isEmpty()) {
            System.out.println("No songs in the playlist.");
            return;
        }
        int previousIndex = currentIndex;
        currentIndex = shuffleRecursive(previousIndex);
        songs.get(currentIndex).play();
    }

    // Recursive shuffle that prevents repeating the same song consecutively
    public int shuffleRecursive(int previousIndex) {
        Random random = new Random();
        int newIndex = random.nextInt(songs.size());
        if (newIndex != previousIndex) {
            return newIndex;
        }
        return shuffleRecursive(previousIndex);  // Recursively shuffle until a different song is selected
    }

    // Display all songs in the playlist
    public void displaySongs() {
        System.out.println("Playlist: " + name);
        for (Song song : songs) {
            System.out.println("- " + song.getTitle());
        }
    }
}
