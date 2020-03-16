public class Rectangle implements AreaShape {
    private int width;
    private int height;

    public Rectangle(int width, int height){
        this.width = width;
        this.height = height;
    }


    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    public void draw() {
        System.out.println("Rectangle");
    }
}
