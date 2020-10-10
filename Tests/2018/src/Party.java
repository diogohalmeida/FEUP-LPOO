import java.lang.reflect.Array;
import java.util.ArrayList;

public class Party extends Event{
    ArrayList<Event> events = new ArrayList<>();

    public Party(String title, String date, String description) {
        super(title, date, description);
    }

    @Override
    public int getAudienceCount() {
        int result = 0;
        for (Event event: events){
            result += event.getAudienceCount();
        }
        return super.getAudienceCount() + result;
    }

    public void addEvent(Event e) {
        events.add(e);
    }
}
