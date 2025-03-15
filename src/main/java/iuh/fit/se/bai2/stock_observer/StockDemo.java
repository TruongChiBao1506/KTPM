package iuh.fit.se.bai2.stock_observer;

public class StockDemo {
    public static void main(String[] args) {
        // Tạo các cổ phiếu
        Stock appleStock = new Stock("AAPL", 150.25);
        Stock googleStock = new Stock("GOOGL", 2750.80);

        // Tạo các nhà đầu tư
        Investor investor1 = new Investor("Trương Chí Bảo");
        Investor investor2 = new Investor("Nguyễn Văn A");
        Investor investor3 = new Investor("Trần Thị B");

        // Đăng ký theo dõi cổ phiếu
        appleStock.attach(investor1);
        appleStock.attach(investor2);
        googleStock.attach(investor2);
        googleStock.attach(investor3);

        System.out.println("===== Thay đổi giá cổ phiếu =====");

        // Thay đổi giá cổ phiếu và quan sát thông báo
        appleStock.setPrice(152.35);
        googleStock.setPrice(2775.50);

        // Investor1 không theo dõi Google nữa
        System.out.println("\n===== Hủy đăng ký theo dõi =====");
        googleStock.detach(investor3);
        System.out.println("Investor " + investor3.getName() + " đã hủy đăng ký theo dõi cổ phiếu " + googleStock.getSymbol());

        // Tiếp tục thay đổi giá
        googleStock.setPrice(2780.25);
    }
}
