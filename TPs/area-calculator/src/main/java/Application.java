import org.w3c.dom.css.Rect;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        AreaAggregator aggregator = new AreaAggregator();

        AreaStringOutputter stringOutputter = new AreaStringOutputter(aggregator);
        AreaXMLOutputter  xmlOutputter = new AreaXMLOutputter(aggregator);

        Square sq1 = new Square(2);
        Circle cir1 = new Circle(2);
        Triangle tri1 = new Triangle(2,2);
        Rectangle rect1 = new Rectangle(4,2);
        Ellipse elli1 = new Ellipse(2,2);

        aggregator.addShape(sq1);
        aggregator.addShape(cir1);
        aggregator.addShape(tri1);
        aggregator.addShape(rect1);
        aggregator.addShape(elli1);

        System.out.println((stringOutputter.output()));
        System.out.println((xmlOutputter.output()));

        List<House> houses = new ArrayList<>();
        houses.add(new House(50));
        houses.add(new House(150));

        City city = new City(houses);

        AreaStringOutputter cityStringOutputter = new AreaStringOutputter(city);
        AreaXMLOutputter cityXmlOutputter = new AreaXMLOutputter(city);

        System.out.println(((cityStringOutputter.output())));
        System.out.println(((cityXmlOutputter.output())));

    }
}
