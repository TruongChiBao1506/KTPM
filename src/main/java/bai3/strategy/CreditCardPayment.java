package bai3.strategy;

public class CreditCardPayment implements PaymentStrategy{
    private String cardNumber;
    private String name;
    private String cvv;
    private String expirationDate;

    public CreditCardPayment(String cardNumber, String name, String cvv, String expirationDate) {
        this.cardNumber = cardNumber;
        this.name = name;
        this.cvv = cvv;
        this.expirationDate = expirationDate;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Thanh toán " + amount + "đ bằng thẻ tín dụng: " +
                cardNumber.substring(0, 4) + "XXXX");
    }

    @Override
    public double calculateAmount(double amount) {
        return amount; // Số tiền gốc không thay đổi
    }

    @Override
    public String getDescription() {
        return "Thẻ tín dụng";
    }
}
