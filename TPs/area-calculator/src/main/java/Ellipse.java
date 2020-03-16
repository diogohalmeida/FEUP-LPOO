public class Ellipse implements AreaShape {
    private int x_radius;
    private int y_radius;

    public Ellipse(int x_radius, int y_radius){
        this.x_radius = x_radius;
        this.y_radius = y_radius;
    }

    public int getX_radius() {
        return x_radius;
    }

    public int getY_radius() {
        return y_radius;
    }

    @Override
    public double getArea(){
        return Math.PI * x_radius * y_radius;
    }


    public void draw() {
        System.out.println("Ellipse");
    }
}
