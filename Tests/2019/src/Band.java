import java.util.ArrayList;

public class Band extends Act {
    private ArrayList<Artist> artists;

    public Band(String name, String country) {
        super(name, country);
        this.artists = new ArrayList<>();
    }

    public void addArtist(Artist artist) {
        artists.add(artist);
    }

    public ArrayList<Artist> getArtists() {
        return artists;
    }

    public boolean containsArtist(Artist artist) {
        for (Artist art: artists){
            if (art.equals(artist)){
                return true;
            }
        }
        return false;
    }
}
