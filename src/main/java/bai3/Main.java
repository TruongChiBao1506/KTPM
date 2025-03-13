package bai3;

import bai3.context.PaymentContext;
import bai3.decorator.DiscountCodeDecorator;
import bai3.decorator.ProcessingFeeDecorator;
import bai3.model.Order;
import bai3.strategy.CreditCardPayment;
import bai3.strategy.PayPalPayment;
import bai3.strategy.PaymentStrategy;

public class Main {
    public static void main(String[] args) {
        // Tạo đơn hàng
        Order order = new Order("ORD-2025-001", 1000000, "Laptop Gaming");

        // Tạo context cho xử lý thanh toán
        PaymentContext context = new PaymentContext();

        // Trường hợp 1: Thanh toán bằng thẻ tín dụng + phí xử lý
        PaymentStrategy creditCardWithFee =
                new ProcessingFeeDecorator(
                        new CreditCardPayment("1234567890123456", "Trương Chí Bảo", "123", "12/25"),
                        0.02);
        context.setPaymentStrategy(creditCardWithFee);
        context.processPayment(order);

        System.out.println();

        // Trường hợp 2: Thanh toán bằng PayPal + mã giảm giá
        PaymentStrategy paypalWithDiscount =
                new DiscountCodeDecorator(
                        new PayPalPayment("bao@example.com", "password"),
                        "SUMMER25", 0.25);
        context.setPaymentStrategy(paypalWithDiscount);
        context.processPayment(order);

        System.out.println();

        // Trường hợp 3: Thanh toán bằng thẻ tín dụng + cả phí xử lý và mã giảm giá
        PaymentStrategy creditCardWithFeeAndDiscount =
                new DiscountCodeDecorator(
                        new ProcessingFeeDecorator(
                                new CreditCardPayment("9876543210987654", "Nguyễn Văn A", "456", "05/26"),
                                0.01),
                        "WELCOME10", 0.10);
        context.setPaymentStrategy(creditCardWithFeeAndDiscount);
        context.processPayment(order);
    }
}
