package bai2.model;

import bai2.strategy.TaxStrategy;

/**
 * Lớp Product đại diện cho sản phẩm (Context trong Strategy Pattern)
 */
public class Product {
    private String name;
    private double price;
    private TaxStrategy taxStrategy;

    /**
     * Constructor tạo đối tượng sản phẩm
     *
     * @param name        tên sản phẩm
     * @param price       giá sản phẩm chưa thuế
     * @param taxStrategy chiến lược tính thuế áp dụng cho sản phẩm này
     */
    public Product(String name, double price, TaxStrategy taxStrategy) {
        this.name = name;
        this.price = price;
        this.taxStrategy = taxStrategy;
    }

    /**
     * Tính thuế cho sản phẩm dựa trên chiến lược thuế được áp dụng
     *
     * @return số tiền thuế
     */
    public double calculateTax() {
        return taxStrategy.calculateTax(price);
    }

    /**
     * Tính tổng giá sản phẩm bao gồm cả thuế
     *
     * @return tổng giá (giá gốc + thuế)
     */
    public double calculateTotalPrice() {
        return price + calculateTax();
    }

    /**
     * Thay đổi chiến lược tính thuế cho sản phẩm
     *
     * @param taxStrategy chiến lược tính thuế mới
     */
    public void setTaxStrategy(TaxStrategy taxStrategy) {
        this.taxStrategy = taxStrategy;
    }

    /**
     * Lấy thông tin về tên loại thuế áp dụng
     *
     * @return tên loại thuế
     */
    public String getTaxType() {
        return taxStrategy.getTaxName();
    }

    /**
     * Lấy tên sản phẩm
     *
     * @return tên sản phẩm
     */
    public String getName() {
        return name;
    }

    /**
     * Lấy giá sản phẩm chưa thuế
     *
     * @return giá sản phẩm chưa thuế
     */
    public double getPrice() {
        return price;
    }
}
