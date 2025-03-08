package bai2.strategy;

/**
 * Chiến lược tính thuế giá trị gia tăng (VAT) (mức thuế 10%)
 */
public class VATStrategy implements TaxStrategy {
    private static final double TAX_RATE = 0.10; // VAT: 10%

    @Override
    public double calculateTax(double price) {
        return price * TAX_RATE;
    }

    @Override
    public String getTaxName() {
        return "Thuế giá trị gia tăng - VAT (10%)";
    }
}