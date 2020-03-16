import java.util.ArrayList;
import java.util.List;

public class AreaAggregator implements SumProvider{
    private List<HasArea> areas = new ArrayList<>();

    public void addShape(HasArea area) {
        areas.add(area);
    }

    public double sum() {
        double sum = 0;
        for (HasArea area : areas) {
            sum += area.getArea();
        }
        return sum;
    }
}