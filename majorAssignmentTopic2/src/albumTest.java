import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

class albumTest {

    private Album album;
    private Song song1;
    private Song song2;
    private Song song3;

    @BeforeEach
    void setUp() {
        album = new Album("Greatest Hits", "2024-01-01");
        song1 = new Song("Hit Song 1", 180, true);
        song2 = new Song("Hit Song 2", 240, false);
        song3 = new Song("Hit Song 3", 300, true);
    }

    @Test
    void testAddSong() {
        // Initially, album should have no songs
        assertEquals(0, album.getSongs().size());

        /*adding songs */
        album.addSong(song1);
        assertEquals(1, album.getSongs().size());
        assertTrue(album.getSongs().contains(song1));


        album.addSong(song2);
        assertEquals(2, album.getSongs().size());
        assertTrue(album.getSongs().contains(song2));


        album.addSong(song3);
        assertEquals(3, album.getSongs().size());
        assertTrue(album.getSongs().contains(song3));
    }

    @Test
    void testDisplaySongs() {
        // Capture the console output (optional, more complex)
        album.addSong(song1);
        album.addSong(song2);
        album.addSong(song3);

        // Check if the album has the correct songs
        ArrayList<Song> songsInAlbum = album.getSongs();
        assertEquals(3, songsInAlbum.size());
        assertEquals("Hit Song 1", songsInAlbum.get(0).getTitle());
        assertEquals("Hit Song 2", songsInAlbum.get(1).getTitle());
        assertEquals("Hit Song 3", songsInAlbum.get(2).getTitle());


        album.displaySongs();  
    }
}