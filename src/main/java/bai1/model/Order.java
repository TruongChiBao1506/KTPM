package bai1.model;

import bai1.state.NewState;
import bai1.state.OrderState;

public class Order {
    private String id;
    private String customerInfo;
    private double totalAmount;
    private OrderState currentState;

    // Constructor
    public Order(String id, String customerInfo, double totalAmount) {
        this.id = id;
        this.customerInfo = customerInfo;
        this.totalAmount = totalAmount;
        // Thiết lập trạng thái ban đầu là "Mới tạo"
        this.currentState = new NewState();
    }

    // Getters và Setters
    public String getId() {
        return id;
    }

    public String getCustomerInfo() {
        return customerInfo;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public OrderState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(OrderState newState) {
        System.out.println("Đơn hàng #" + id + " chuyển từ trạng thái [" +
                currentState.getStateName() + "] sang [" + newState.getStateName() + "]");
        this.currentState = newState;
    }

    // Các phương thức hành động trên đơn hàng
    public void verifyOrder() {
        currentState.verifyOrder(this);
    }

    public void processOrder() {
        currentState.processOrder(this);
    }

    public void deliverOrder() {
        currentState.deliverOrder(this);
    }

    public void cancelOrder() {
        currentState.cancelOrder(this);
    }

    @Override
    public String toString() {
        return "Đơn hàng #" + id + " - Khách hàng: " + customerInfo +
                " - Tổng tiền: " + totalAmount + " - Trạng thái: " + currentState.getStateName();
    }
}
