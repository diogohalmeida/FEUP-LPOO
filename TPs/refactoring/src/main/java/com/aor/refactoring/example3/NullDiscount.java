package com.aor.refactoring.example3;

public class NullDiscount extends Discount {

    public double applyDiscount(double price){
        return price;
    }
}
