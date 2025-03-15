package iuh.fit.se.bai2.stock_observer;

import java.util.ArrayList;
import java.util.List;

public class Stock {
    private String symbol;
    private double price;
    private List<StockObserver> observers = new ArrayList<>();

    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }

    public void attach(StockObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void detach(StockObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (StockObserver observer : observers) {
            observer.update(this);
        }
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        // Nếu giá không thay đổi, không cần thông báo
        if (this.price != price) {
            System.out.printf("Stock %s price changed: $%.2f -> $%.2f\n", symbol, this.price, price);
            this.price = price;
            notifyObservers();
        }
    }
}
