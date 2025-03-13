package bai3.context;

import bai3.model.Order;
import bai3.strategy.PaymentStrategy;

public class PaymentContext {
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void processPayment(Order order) {
        if (paymentStrategy == null) {
            throw new IllegalStateException("Vui lòng chọn phương thức thanh toán trước khi xử lý");
        }

        System.out.println("---------- Xử lý thanh toán ----------");
        System.out.println("Mã đơn hàng: " + order.getOrderId());
        System.out.println("Mô tả: " + order.getDescription());
        System.out.println("Số tiền ban đầu: " + order.getTotalAmount() + "đ");
        System.out.println("Phương thức thanh toán: " + paymentStrategy.getDescription());

        double finalAmount = paymentStrategy.calculateAmount(order.getTotalAmount());
        System.out.println("Số tiền cuối cùng: " + finalAmount + "đ");

        paymentStrategy.pay(finalAmount);
        System.out.println("Thanh toán hoàn tất!");
        System.out.println("-------------------------------------");
    }
}
