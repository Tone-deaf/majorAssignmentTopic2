import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class playListTest {

    public Playlist playlist;
    public Song song1;
    public Song song2;
    public Song song3;

    @BeforeEach
    void setUp() {
        playlist = new Playlist("My Playlist");
        song1 = new Song("Song 1", 120, true);
        song2 = new Song("Song 2", 200, true);
        song3 = new Song("Song 3", 70, false);

        // Add songs to playlist
        playlist.addSong(song1);
        playlist.addSong(song2);
        playlist.addSong(song3);
    }

    @Test
    void testPlayNext() {
        // Play first song
        playlist.playNext();
        assertEquals("Song 1", playlist.getCurrentSong().getTitle());

        // Play next song
        playlist.playNext();
        assertEquals("Song 2", playlist.getCurrentSong().getTitle());

        // Play next song again (should loop to the last song)
        playlist.playNext();
        assertEquals("Song 3", playlist.getCurrentSong().getTitle());

        // Loops back to song1
        playlist.playNext();
        assertEquals("Song 1", playlist.getCurrentSong().getTitle());
    }

    @Test
    void testPlayPrevious() {
        // Start by playing the first song
        playlist.playNext(); // plays song1

        // Play previous song (should loop to the last song)
        playlist.playPrevious();
        assertEquals("Song 3", playlist.getCurrentSong().getTitle());

        // Play previous song again (should move to song2)
        playlist.playPrevious();
        assertEquals("Song 2", playlist.getCurrentSong().getTitle());

        // Play previous song again (should move to song1)
        playlist.playPrevious();
        assertEquals("Song 1", playlist.getCurrentSong().getTitle());
    }

    @Test
    void testPlayCurrentSong() {
        // Initially, no song is playing
        playlist.playCurrentSong();  // Should print "No song is currently playing."
        assertEquals(null, playlist.getCurrentSong());  // Ensure no song is playing
    
        // Play the first song
        playlist.playNext();  // Move to song1
        assertEquals(song1, playlist.getCurrentSong()); 
    
        // Call playCurrentSong (this will play song1 again)
        playlist.playCurrentSong(); 
        assertEquals(song1, playlist.getCurrentSong());  
    
        // Move to the next song (song2)
        playlist.playNext(); 
        assertEquals(song2, playlist.getCurrentSong());  
    
        // Call playCurrentSong (this will play song2 again)
        playlist.playCurrentSong();  
        assertEquals(song2, playlist.getCurrentSong());  
    
        // Play the third song (song3)
        playlist.playNext();  // Move to song3
        playlist.playCurrentSong();  
        assertEquals(song3, playlist.getCurrentSong());
    }

}


