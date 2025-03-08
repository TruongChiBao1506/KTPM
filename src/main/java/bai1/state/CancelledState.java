package bai1.state;

import bai1.model.Order;

public class CancelledState implements OrderState {

    @Override
    public void verifyOrder(Order order) {
        System.out.println("Không thể kiểm tra đơn hàng #" + order.getId() + " vì đã bị hủy!");
    }

    @Override
    public void processOrder(Order order) {
        System.out.println("Không thể xử lý đơn hàng #" + order.getId() + " vì đã bị hủy!");
    }

    @Override
    public void deliverOrder(Order order) {
        System.out.println("Không thể giao đơn hàng #" + order.getId() + " vì đã bị hủy!");
    }

    @Override
    public void cancelOrder(Order order) {
        System.out.println("Đơn hàng #" + order.getId() + " đã bị hủy rồi!");
    }

    @Override
    public String getStateName() {
        return "Đã hủy";
    }
}