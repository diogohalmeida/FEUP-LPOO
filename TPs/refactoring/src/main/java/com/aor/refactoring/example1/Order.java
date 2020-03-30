package com.aor.refactoring.example1;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<OrderLine> lines;

    public Order() {
        lines = new ArrayList<>();
    }

    public void add(Product product, int quantity) {
        lines.add(new OrderLine(product, quantity));
    }

    public boolean isElegibleForFreeDelivery() {
        double total = getTotal();
        return total > 100;
    }

    public String printOrder() {
        StringBuffer printBuffer = new StringBuffer();

        for (OrderLine line : lines)
            printBuffer.append(line.getStr());

        double total = getTotal();

        printBuffer.append("Total: " + total);

        return printBuffer.toString();
    }

    //Extract method
    private double getTotal() {
        double total = 0;
        for (OrderLine line : lines)
            total += line.product.getPrice() * line.quantity;
        return total;
    }
}