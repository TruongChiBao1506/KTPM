package bai3.strategy;

public interface PaymentStrategy {
    void pay(double amount);
    double calculateAmount(double amount);
    String getDescription();
}
