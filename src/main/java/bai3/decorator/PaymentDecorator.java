package bai3.decorator;

import bai3.strategy.PaymentStrategy;

public abstract class PaymentDecorator implements PaymentStrategy {
    protected PaymentStrategy payment;

    public PaymentDecorator(PaymentStrategy payment) {
        this.payment = payment;
    }
}
