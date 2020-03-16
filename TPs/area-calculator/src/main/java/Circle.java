public class Circle implements AreaShape {
    private int radius;

    public Circle(int radius){
        this.radius = radius;
    }
    public int getRadius() {
        return radius;
    }

    public double getArea(){
        return Math.PI * Math.pow(radius, 2);
    }

    public void draw() {
        System.out.println("Circle");
    }
}
