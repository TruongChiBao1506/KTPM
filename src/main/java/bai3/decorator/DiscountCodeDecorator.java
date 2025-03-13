package bai3.decorator;

import bai3.strategy.PaymentStrategy;

public class DiscountCodeDecorator extends PaymentDecorator{
    private String discountCode;
    private double discountRate; // tỷ lệ giảm giá (ví dụ: 0.10 tương đương 10%)

    public DiscountCodeDecorator(PaymentStrategy payment, String discountCode, double discountRate) {
        super(payment);
        this.discountCode = discountCode;
        this.discountRate = discountRate;
    }

    @Override
    public void pay(double amount) {
        double finalAmount = calculateAmount(amount);
        System.out.println("Áp dụng mã giảm giá " + discountCode + ": -" +
                (amount - finalAmount) + "đ");
        payment.pay(finalAmount);
    }

    @Override
    public double calculateAmount(double amount) {
        double baseAmount = payment.calculateAmount(amount);
        return baseAmount * (1 - discountRate);
    }

    @Override
    public String getDescription() {
        return payment.getDescription() + " + Mã giảm giá " + discountCode +
                " (" + (discountRate * 100) + "% giảm)";
    }
}
