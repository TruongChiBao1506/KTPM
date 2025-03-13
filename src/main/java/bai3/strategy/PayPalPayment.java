package bai3.strategy;

public class PayPalPayment implements PaymentStrategy {
    private String email;
    private String password;

    public PayPalPayment(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Thanh toán " + amount + "đ bằng PayPal: " + email);
    }

    @Override
    public double calculateAmount(double amount) {
        return amount; // Số tiền gốc không thay đổi
    }

    @Override
    public String getDescription() {
        return "PayPal";
    }
}
