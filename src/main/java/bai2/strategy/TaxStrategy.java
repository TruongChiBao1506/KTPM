package bai2.strategy;

/**
 * Interface chiến lược tính thuế (Strategy Pattern)
 * Mỗi chiến lược tính thuế sẽ triển khai interface này
 */
public interface TaxStrategy {
    /**
     * Tính thuế dựa trên giá sản phẩm
     *
     * @param price giá sản phẩm chưa thuế
     * @return số tiền thuế
     */
    double calculateTax(double price);

    /**
     * Lấy tên của loại thuế
     *
     * @return tên loại thuế
     */
    String getTaxName();
}
