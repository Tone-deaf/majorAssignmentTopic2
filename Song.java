public class Song {
    public String title;
    public int duration;  // in seconds
    public boolean isSingle;

    public Song(String title, int duration, boolean isSingle) {
        this.title = title;
        this.duration = duration;
        this.isSingle = isSingle;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }

    public boolean isSingle() {
        return isSingle;
    }

    public void play() {
        System.out.println("Playing song: " + title + " (" + duration + " seconds)");
    }
}
