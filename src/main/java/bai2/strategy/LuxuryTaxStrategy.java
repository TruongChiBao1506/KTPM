package bai2.strategy;

/**
 * Chiến lược tính thuế đặc biệt cho sản phẩm xa xỉ (mức thuế cơ bản 20% + thêm 5% cho phần giá trị trên 1000)
 */
public class LuxuryTaxStrategy implements TaxStrategy {
    private static final double BASE_TAX_RATE = 0.20; // Thuế xa xỉ cơ bản: 20%
    private static final double ADDITIONAL_TAX_RATE = 0.05; // Thuế bổ sung: 5%
    private static final double THRESHOLD = 1000.0; // Ngưỡng áp dụng thuế bổ sung

    @Override
    public double calculateTax(double price) {
        double baseTax = price * BASE_TAX_RATE;

        // Nếu giá sản phẩm vượt ngưỡng, tính thêm thuế bổ sung
        if (price > THRESHOLD) {
            double additionalTax = (price - THRESHOLD) * ADDITIONAL_TAX_RATE;
            return baseTax + additionalTax;
        }

        return baseTax;
    }

    @Override
    public String getTaxName() {
        return "Thuế đặc biệt cho sản phẩm xa xỉ (20% + 5% cho phần trên 1000)";
    }
}
