package com.aor.refactoring.example2;

//Result from Extract Class Method
public class Circle extends Shape{
    private double radius; // ONLY FOR CIRCLES

    public Circle(double x, double y, double radius){
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    public double getPerimeter() {
        return 2*Math.PI*radius;
    }

    public void draw(GraphicFramework graphics) {
        graphics.drawCircle(x, y, radius);
    }
}
