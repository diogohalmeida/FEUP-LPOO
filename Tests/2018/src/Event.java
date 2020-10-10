import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

public class Event {
    private String title, date, description;
    private ArrayList<Person> people = new ArrayList<>();

    public Event(String title) {
        this.title = title;
        this.date = "";
        this.description = "";
    }

    public Event(String title, String date) {
        this.title = title;
        this.date = date;
        this.description = "";
    }

    public Event(String title, String date, String description) {
        this.title = title;
        this.date = date;
        this.description = description;
    }

    public Event(Event e) {
        this.title = e.title;
        this.description = e.description;
        this.date = e.date;
    }


    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title + " is a " + description + " and will be held at " + date + ".";
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return title.equals(event.title) &&
                date.equals(event.date) &&
                description.equals(event.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, date, description);
    }

    public void addPerson(Person person) {
        boolean found = false;
        for (Person p: people){
            if (person.getName().equals(p.getName())){
                found = true;
                break;
            }
        }
        if (!found) people.add(person);
    }

    public int getAudienceCount() {
        return people.size();
    }
}
