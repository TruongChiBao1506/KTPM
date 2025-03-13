package bai3.decorator;

import bai3.strategy.PaymentStrategy;

public class ProcessingFeeDecorator extends PaymentDecorator{
    private double feeRate; // tỷ lệ phí (ví dụ: 0.02 tương đương 2%)

    public ProcessingFeeDecorator(PaymentStrategy payment, double feeRate) {
        super(payment);
        this.feeRate = feeRate;
    }

    @Override
    public void pay(double amount) {
        double finalAmount = calculateAmount(amount);
        System.out.println("Thêm phí xử lý: " + (finalAmount - amount) + "đ");
        payment.pay(finalAmount);
    }

    @Override
    public double calculateAmount(double amount) {
        double baseAmount = payment.calculateAmount(amount);
        return baseAmount + (baseAmount * feeRate);
    }

    @Override
    public String getDescription() {
        return payment.getDescription() + " + Phí xử lý (" + (feeRate * 100) + "%)";
    }
}
