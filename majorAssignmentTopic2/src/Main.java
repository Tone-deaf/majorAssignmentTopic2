import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);  // Global scanner for input
    private static final ArrayList<Artist> artists = new ArrayList<>();  // Store all artists

    public static void main(String[] args) {
        Library musicLibrary = new Library("My Music Library");  // Initialize library
        Playlist playlist = new Playlist("My Playlist");  // Initialize playlist

        // Main menu loop
        while (true) {
            printMenu();  // Print the menu options
            int choice = getUserChoice();  // Input validation for menu choice
        
            switch (choice) {
                case 1:
                    addArtist();  // New option to add an artist
                    break;
                case 2:
                    addSongToLibrary(musicLibrary, playlist);
                    break;
                case 3:
                    addAlbumToLibrary(musicLibrary);
                    break;
                case 4:
                    playlist.playNext();
                    break;
                case 5:
                    playlist.playPrevious();
                    break;
                case 6:
                    playlist.shufflePlay();
                    break;
                case 7:
                    musicLibrary.displayLibrary();
                    break;
                case 8:
                    playlist.displaySongs();
                    break;
                case 9:
                    displayArtistAlbums();
                    break;
                case 10:
                    System.out.println("\nExiting the program... Goodbye!");
                    scanner.close();
                    Runtime.getRuntime().exit(10); 
                    return;
                default:
                    System.out.println("\nInvalid option. Please try again.");
            }
        }
    }

    // Function to print the menu
    private static void printMenu() {
        System.out.println("\n===========================");
        System.out.println("Choose an option:");
        System.out.println("1. Add an Artist");
        System.out.println("2. Add a Song to Library");
        System.out.println("3. Add an Album to Library");
        System.out.println("4. Play Next Song in Playlist");
        System.out.println("5. Play Previous Song in Playlist");
        System.out.println("6. Shuffle Play Playlist");
        System.out.println("7. Display Library");
        System.out.println("8. Display Playlist");
        System.out.println("9. Display Artist's Albums");
        System.out.println("10. Exit");
        System.out.println("===========================\n");
    }

    // Get valid user input for the menu choice
    private static int getUserChoice() {
        int choice = -1;
        while (true) {
            try {
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline
                break;
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid input. Please enter a number.");
                scanner.nextLine();  // Clear the invalid input
            }
        }
        return choice;
    }

    // New Function to add an artist
    private static void addArtist() {
        System.out.println("\nAdding a new artist...");
        System.out.println("Enter artist name:");
        String artistName = scanner.nextLine();

        System.out.println("Enter artist genre:");
        String artistGenre = scanner.nextLine();

        // Check if the artist already exists
        Artist artist = findArtistByName(artistName);
        if (artist != null) {
            System.out.println("\nArtist already exists: " + artist.getName());
        } else {
            // Create a new artist
            artist = new Artist(artistName, artistGenre);
            artists.add(artist);
            System.out.println("\nArtist added: " + artistName + " (Genre: " + artistGenre + ")");
        }
    }

    // Function to add a song to the library
    private static void addSongToLibrary(Library library, Playlist playlist) {
        System.out.println("\nAdding a new song...");
        System.out.println("Enter song title:");
        String title = scanner.nextLine();

        int duration = getPositiveInt("Enter song duration (in seconds):");

        System.out.println("Is it a single? (true/false):");
        boolean isSingle = getBooleanInput();

        Song song = new Song(title, duration, isSingle);
        library.addSong(song);
        System.out.println("\nSong added to library: " + song.getTitle());

        System.out.println("Do you want to add this song to the playlist? (yes/no)");
        String addToPlaylist = scanner.nextLine();
        if (addToPlaylist.equalsIgnoreCase("yes")) {
            playlist.addSong(song);
            System.out.println("\nSong added to playlist: " + song.getTitle());
        }
    }

    // Function to add an album to the library
    private static void addAlbumToLibrary(Library library) {
        System.out.println("\nAdding a new album...");
        System.out.println("Enter artist name:");
        String artistName = scanner.nextLine();
        System.out.println("Enter artist genre:");
        String artistGenre = scanner.nextLine();

        // Find artist if exists or create a new artist
        Artist artist = findOrCreateArtist(artistName, artistGenre);

        System.out.println("Enter album title:");
        String albumTitle = scanner.nextLine();
        System.out.println("Enter album release date (e.g., 2023-10-19):");
        String releaseDate = scanner.nextLine();

        Album album = new Album(albumTitle, releaseDate);

        int songCount = getPositiveInt("How many songs are in this album?");

        // Add songs to the album
        for (int i = 0; i < songCount; i++) {
            System.out.println("Enter song title:");
            String songTitle = scanner.nextLine();

            int duration = getPositiveInt("Enter song duration (in seconds):");

            System.out.println("Is it a single? (true/false):");
            boolean isSingle = getBooleanInput();

            Song song = new Song(songTitle, duration, isSingle);
            album.addSong(song);
        }

        // Add album to library and artist
        library.addAlbum(album);
        artist.addAlbum(album);  // Add album to the artist
        System.out.println("\nAlbum added to library and artist: " + albumTitle);
    }

    // Display albums of an artist
    private static void displayArtistAlbums() {
        if (artists.isEmpty()) {
            System.out.println("\nNo artists available.");
            return;
        }

        System.out.println("\nEnter the artist's name:");
        String artistName = scanner.nextLine();

        Artist artist = findArtistByName(artistName);

        if (artist != null) {
            System.out.println("\nAlbums for artist: " + artist.getName() + " (Genre: " + artist.getGenre() + ")");
            artist.displayAlbums();
        } else {
            System.out.println("\nArtist not found.");
        }
    }

    // Helper method to find or create an artist
    private static Artist findOrCreateArtist(String name, String genre) {
        for (Artist artist : artists) {
            if (artist.getName().equalsIgnoreCase(name)) {
                return artist;  // Return existing artist
            }
        }
        // If artist not found, create a new one
        Artist newArtist = new Artist(name, genre);
        artists.add(newArtist);
        return newArtist;
    }

    // Helper method to find an artist by name
    private static Artist findArtistByName(String name) {
        for (Artist artist : artists) {
            if (artist.getName().equalsIgnoreCase(name)) {
                return artist;
            }
        }
        return null;
    }

    // Helper method to get a positive integer from the user
    private static int getPositiveInt(String message) {
        int number;
        while (true) {
            try {
                System.out.print(message);
                number = scanner.nextInt();
                scanner.nextLine();  // Consume newline
                if (number > 0) {
                    break;
                } else {
                    System.out.println("Please enter a positive number.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid input. Please enter a valid number.");
                scanner.nextLine();  // Clear invalid input
            }
        }
        return number;
    }

    // Helper method to get a boolean input (true/false)
    private static boolean getBooleanInput() {
        while (true) {
            System.out.print("Enter true or false: ");
            String input = scanner.nextLine().toLowerCase();
            if (input.equals("true") || input.equals("false")) {
                return Boolean.parseBoolean(input);
            } else {
                System.out.println("\nInvalid input. Please enter 'true' or 'false'.");
            }
        }
    }
}
