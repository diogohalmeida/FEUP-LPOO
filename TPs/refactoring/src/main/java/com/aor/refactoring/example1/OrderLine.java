package com.aor.refactoring.example1;

public class OrderLine {
    public Product product;
    public int quantity;

    public OrderLine(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    String getStr() {
        return product.getName() + "(x" + quantity + "): " + (product.getPrice() * quantity) + "\n";
    }
}
