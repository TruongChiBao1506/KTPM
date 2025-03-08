package bai2;

import bai2.model.Product;
import bai2.strategy.ConsumptionTaxStrategy;
import bai2.strategy.LuxuryTaxStrategy;
import bai2.strategy.TaxStrategy;
import bai2.strategy.VATStrategy;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Format để hiển thị tiền tệ
        DecimalFormat currencyFormat = new DecimalFormat("#,##0.00 VND");

        // Tạo các chiến lược tính thuế
        TaxStrategy consumptionTax = new ConsumptionTaxStrategy();
        TaxStrategy vatTax = new VATStrategy();
        TaxStrategy luxuryTax = new LuxuryTaxStrategy();

        // Tạo danh sách sản phẩm với các loại thuế khác nhau
        List<Product> products = new ArrayList<>();
        products.add(new Product("Gạo", 200.0, consumptionTax));
        products.add(new Product("Laptop", 1500.0, vatTax));
        products.add(new Product("Điện thoại cao cấp", 2000.0, luxuryTax));
        products.add(new Product("Đồng hồ xa xỉ", 5000.0, luxuryTax));

        // Hiển thị thông tin và tính thuế cho từng sản phẩm
        for (Product product : products) {
            System.out.println("===================================");
            System.out.println("Sản phẩm: " + product.getName());
            System.out.println("Giá chưa thuế: " + currencyFormat.format(product.getPrice()));
            System.out.println("Áp dụng: " + product.getTaxType());
            System.out.println("Tiền thuế: " + currencyFormat.format(product.calculateTax()));
            System.out.println("Tổng tiền (đã bao gồm thuế): " + currencyFormat.format(product.calculateTotalPrice()));
        }

        System.out.println("\n===================================");
        System.out.println("Demo thay đổi chiến lược thuế:");

        // Demo thay đổi chiến lược thuế cho một sản phẩm
        Product laptop = products.get(1); // Laptop ban đầu áp dụng VAT
        System.out.println("Trước khi thay đổi:");
        System.out.println(laptop.getName() + " áp dụng " + laptop.getTaxType());
        System.out.println("Tiền thuế: " + currencyFormat.format(laptop.calculateTax()));
        System.out.println("Tổng tiền: " + currencyFormat.format(laptop.calculateTotalPrice()));

        // Chuyển sang áp dụng thuế xa xỉ
        laptop.setTaxStrategy(luxuryTax);

        System.out.println("\nSau khi thay đổi:");
        System.out.println(laptop.getName() + " áp dụng " + laptop.getTaxType());
        System.out.println("Tiền thuế: " + currencyFormat.format(laptop.calculateTax()));
        System.out.println("Tổng tiền: " + currencyFormat.format(laptop.calculateTotalPrice()));
    }
}
