package bai2.strategy;

/**
 * Chiến lược tính thuế nhập khẩu (Thêm để demo khả năng mở rộng)
 */
public class ImportTaxStrategy implements TaxStrategy {
    private static final double TAX_RATE = 0.15; // Thuế nhập khẩu: 15%

    @Override
    public double calculateTax(double price) {
        return price * TAX_RATE;
    }

    @Override
    public String getTaxName() {
        return "Thuế nhập khẩu (15%)";
    }
}
