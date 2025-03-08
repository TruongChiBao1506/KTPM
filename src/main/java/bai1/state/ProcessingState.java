package bai1.state;

import bai1.model.Order;

public class ProcessingState implements OrderState {

    @Override
    public void verifyOrder(Order order) {
        System.out.println("Đơn hàng #" + order.getId() + " đã được kiểm tra rồi!");
    }

    @Override
    public void processOrder(Order order) {
        System.out.println("Đơn hàng #" + order.getId() + " đang được đóng gói...");
        System.out.println("Đơn hàng đã được đóng gói và bàn giao cho đơn vị vận chuyển.");
        System.out.println("Đơn hàng đang được vận chuyển đến khách hàng: " + order.getCustomerInfo());
    }

    @Override
    public void deliverOrder(Order order) {
        System.out.println("Đơn hàng #" + order.getId() + " đã được giao thành công!");
        order.setCurrentState(new DeliveredState());
    }

    @Override
    public void cancelOrder(Order order) {
        System.out.println("Đơn hàng #" + order.getId() + " đã được hủy khi đang xử lý.");
        System.out.println("Hệ thống sẽ hoàn lại tiền cho khách hàng.");
        order.setCurrentState(new CancelledState());
    }

    @Override
    public String getStateName() {
        return "Đang xử lý";
    }
}
