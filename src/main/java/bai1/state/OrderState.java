package bai1.state;

import bai1.model.Order;

public interface OrderState {
    /**
     * Kiểm tra thông tin đơn hàng
     */
    void verifyOrder(Order order);

    /**
     * Xử lý đóng gói và vận chuyển
     */
    void processOrder(Order order);

    /**
     * Đánh dấu đơn hàng đã giao
     */
    void deliverOrder(Order order);

    /**
     * Hủy đơn hàng
     */
    void cancelOrder(Order order);

    /**
     * Lấy tên trạng thái hiện tại
     */
    String getStateName();
}