import java.util.ArrayList;
import java.util.List;

public class StringBar extends Bar {
    private boolean happy_hour;

    public StringBar(){
        happy_hour = false;
        observers = new ArrayList<>();
    }

    @Override
    public boolean isHappyHour() {
        return happy_hour;
    }

    @Override
    public void startHappyHour() {
        happy_hour = true;
        super.notifyObservers();
    }

    @Override
    public void endHappyHour() {
        happy_hour = false;
        super.notifyObservers();
    }

    public void order(StringDrink drink, StringRecipe recipe){
        recipe.mix(drink);
    }
}
