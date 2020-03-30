package com.aor.refactoring.example2;

public abstract class Shape {
    protected double x;
    protected double y;

    public abstract double getArea();

    public abstract double getPerimeter();

    public abstract void draw(GraphicFramework graphics);
}
