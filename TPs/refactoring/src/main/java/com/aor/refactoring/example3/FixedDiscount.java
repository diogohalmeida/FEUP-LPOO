package com.aor.refactoring.example3;

public class FixedDiscount extends Discount {
    private double fixed;

    public FixedDiscount(double fixed){
        this.fixed = fixed;
    }

    public double applyDiscount(double price){
        return price - fixed;
    }
}
