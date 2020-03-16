public class Triangle implements AreaShape {
    int base_size;
    int height;

    public Triangle(int base_size, int height){
        this.base_size = base_size;
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public int getBase_size() {
        return base_size;
    }

    @Override
    public double getArea() {
        return (height * base_size) / 2;
    }

    public void draw() {
        System.out.println("Triangle");
    }
}
