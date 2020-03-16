import java.awt.*;
import java.lang.module.InvalidModuleDescriptorException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Concert {
    List<Act> acts = new ArrayList<>();
    String city, country, date;

    public Concert(String city, String country, String date){
        this.city = city;
        this.country = country;
        this.date = date;

    }

    public void addAct(Act act){
        acts.add(act);
    }

    public List<Act> getActs(){
        return acts;
    }

    public String getCity(){
        return city;
    }

    public String getCountry(){
        return country;
    }

    public String getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Concert concert = (Concert) o;
        return Objects.equals(acts, concert.acts) &&
                Objects.equals(city, concert.city) &&
                Objects.equals(country, concert.country) &&
                Objects.equals(date, concert.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(acts, city, country, date);
    }

    public boolean isValid(Ticket ticket) {
        if (ticket.getConcert().equals(this))
            return true;
        else
            return false;
    }
}
