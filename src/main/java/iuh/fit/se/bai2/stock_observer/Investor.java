package iuh.fit.se.bai2.stock_observer;

public class Investor implements StockObserver {
    private String name;

    public Investor(String name) {
        this.name = name;
    }

    @Override
    public void update(Stock stock) {
        System.out.printf("Investor %s received notification: %s stock price updated to $%.2f\n",
                name, stock.getSymbol(), stock.getPrice());
    }

    public String getName() {
        return name;
    }
}
