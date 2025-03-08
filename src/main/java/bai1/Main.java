package bai1;

import bai1.model.Order;

public class Main {
    public static void main(String[] args) {
            // Tạo một đơn hàng mới
            Order order = new Order("ORD-001", "Nguyễn Văn A - 0912345678", 1500000);
            System.out.println(order);

            // Kiểm tra thông tin đơn hàng (chuyển từ "Mới tạo" sang "Đang xử lý")
            order.verifyOrder();
            System.out.println(order);

            // Xử lý đơn hàng (vẫn ở trạng thái "Đang xử lý")
            order.processOrder();
            System.out.println(order);

            // Giao đơn hàng (chuyển từ "Đang xử lý" sang "Đã giao")
            order.deliverOrder();
            System.out.println(order);

            // Thử hủy đơn hàng khi đã giao (không thành công)
            order.cancelOrder();
            System.out.println(order);

            System.out.println("\n--- Trường hợp đơn hàng bị hủy ---");

            // Tạo một đơn hàng mới
            Order order2 = new Order("ORD-002", "Trần Thị B - 0987654321", 2000000);
            System.out.println(order2);

            // Kiểm tra thông tin đơn hàng (chuyển từ "Mới tạo" sang "Đang xử lý")
            order2.verifyOrder();
            System.out.println(order2);

            // Hủy đơn hàng (chuyển từ "Đang xử lý" sang "Đã hủy")
            order2.cancelOrder();
            System.out.println(order2);

            // Thử xử lý đơn hàng đã hủy (không thành công)
            order2.processOrder();
            System.out.println(order2);
    }
}