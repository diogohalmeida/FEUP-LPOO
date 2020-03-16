import java.util.ArrayList;
import java.util.List;

public class Band extends Act {
    List<Artist> artists = new ArrayList<>();

    public Band(String name, String country) {
        super(name, country);
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void addArtist(Artist artist) {
        artists.add(artist);
    }
}
