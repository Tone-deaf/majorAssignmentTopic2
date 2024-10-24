import java.util.ArrayList;

public class Artist {
    public String name;
    public String genre;
    public ArrayList<Album> albums;

    //Parameterizng new Artist and initializing their arrayList of album (if they have one)
    public Artist(String name, String genre) {
        this.name = name;
        this.genre = genre;
        this.albums = new ArrayList<>(); 
    }

    // Add an album to the artist's collection
    public void addAlbum(Album album) {
        albums.add(album);
        System.out.println("Album added: " + album.getTitle());
    }

    // Display all albums of the artist
    public void displayAlbums() {
        if (albums.isEmpty()) {
            System.out.println("No albums available for artist: " + name);
        } else {
            for (Album album : albums) {
                System.out.println("Album: " + album.getTitle() + " (Released: " + album.getReleaseDate() + ")");
                album.displaySongs();  // Assuming Album class has a displaySongs() method
            }
        }
    }


    // fetching both name and genre of an artist
    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }
    
}
