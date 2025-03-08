package bai1.state;

import bai1.model.Order;

public class NewState implements OrderState {

    @Override
    public void verifyOrder(Order  order) {
        System.out.println("Đơn hàng #" + order.getId() + " đang được kiểm tra thông tin...");
        System.out.println("Thông tin khách hàng: " + order.getCustomerInfo());
        System.out.println("Tổng tiền: " + order.getTotalAmount());
        System.out.println("Kiểm tra thông tin đơn hàng thành công!");

        // Sau khi kiểm tra xong, chuyển sang trạng thái "Đang xử lý"
        order.setCurrentState(new ProcessingState());

    }

    @Override
    public void processOrder(Order order) {
        System.out.println("Không thể xử lý đơn hàng khi chưa kiểm tra thông tin. Hãy kiểm tra trước!");
    }

    @Override
    public void deliverOrder(Order order) {
        System.out.println("Không thể giao đơn hàng khi chưa được xử lý. Hãy kiểm tra và xử lý trước!");
    }

    @Override
    public void cancelOrder(Order order) {
        System.out.println("Đơn hàng #" + order.getId() + " đã được hủy khi đang ở trạng thái mới tạo.");
        order.setCurrentState(new CancelledState());
    }

    @Override
    public String getStateName() {
        return "Mới tạo";
    }
}