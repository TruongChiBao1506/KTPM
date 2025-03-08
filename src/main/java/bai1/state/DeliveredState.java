package bai1.state;

import bai1.model.Order;

public class DeliveredState implements OrderState {

    @Override
    public void verifyOrder(Order order) {
        System.out.println("Không thể kiểm tra đơn hàng #" + order.getId() + " vì đã giao rồi!");
    }

    @Override
    public void processOrder(Order order) {
        System.out.println("Không thể xử lý đơn hàng #" + order.getId() + " vì đã giao rồi!");
    }

    @Override
    public void deliverOrder(Order order) {
        System.out.println("Đơn hàng #" + order.getId() + " đã được giao rồi!");
    }

    @Override
    public void cancelOrder(Order order) {
        System.out.println("Không thể hủy đơn hàng #" + order.getId() + " vì đã giao rồi!");
        System.out.println("Nếu muốn trả hàng, vui lòng liên hệ bộ phận chăm sóc khách hàng.");
    }

    @Override
    public String getStateName() {
        return "Đã giao";
    }
}