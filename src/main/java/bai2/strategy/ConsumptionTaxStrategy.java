package bai2.strategy;

/**
 * Chiến lược tính thuế tiêu thụ (mức thuế 5%)
 */
public class ConsumptionTaxStrategy implements TaxStrategy {
    private static final double TAX_RATE = 0.05; // Thuế tiêu thụ: 5%

    @Override
    public double calculateTax(double price) {
        return price * TAX_RATE;
    }

    @Override
    public String getTaxName() {
        return "Thuế tiêu thụ (5%)";
    }
}
