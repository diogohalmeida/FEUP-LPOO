public class Square implements AreaShape {
    private int side;

    public Square(int side){
        this.side = side;
    }

    public int getSide() {
        return side;
    }

    @Override
    public double getArea() {
        return side * side;
    }

    public void draw() {
        System.out.println("Square");
    }
}
