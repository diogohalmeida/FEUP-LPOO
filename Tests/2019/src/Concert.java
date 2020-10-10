import java.util.ArrayList;
import java.util.Objects;

public class Concert {
    private String city, country, date;
    private int number;
    private ArrayList<Act> acts;

    public Concert(String city, String country, String date){
        this.city = city;
        this.country = country;
        this.date = date;
        this.acts = new ArrayList<>();
        this.number = 1;
    }

    public void addAct(Act act){
        acts.add(act);
    }

    public ArrayList<Act> getActs() {
        return acts;
    }

    public String getCountry() {
        return country;
    }

    public String getDate() {
        return date;
    }

    public String getCity() {
        return city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Concert concert = (Concert) o;
        return city.equals(concert.city) &&
                country.equals(concert.country) &&
                date.equals(concert.date) &&
                acts.equals(concert.acts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, country, date, acts);
    }


    public boolean isValid(Ticket ticket){
        return this.equals(ticket.getConcert());

    }

    public boolean participates(Artist artist) {
        for (Act act: acts){
            if (act instanceof Band){
                if (((Band) act).containsArtist(artist)){
                    return true;
                }
            }
            else{
                if (act.equals(artist)){
                    return true;
                }
            }
        }
        return false;
    }

    public void incrementNumber(){
        number++;
    }

    public int getNumber() {
        return number;
    }
}
