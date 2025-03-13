package bai3.model;

public class Order {
    private String orderId;
    private double totalAmount;
    private String description;

    public Order(String orderId, double totalAmount, String description) {
        this.orderId = orderId;
        this.totalAmount = totalAmount;
        this.description = description;
    }

    public String getOrderId() {
        return orderId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getDescription() {
        return description;
    }
}
