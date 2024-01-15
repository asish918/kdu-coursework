package org.example.entities;

public class Coin {
    private int rank;
    private String name;
    private double price;
    private long circulatingSupply;
    private long quantity;
    private String symbol;

    public Coin(int rank, String name, String symbol, double price, long circulatingSupply) {
        this.rank = rank;
        this.name = name;
        this.symbol = symbol;
        this.price = price;
        this.quantity = circulatingSupply;
        this.circulatingSupply = circulatingSupply;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public synchronized double getPrice() {
        return price;
    }

    public synchronized void setPrice(double price) {
        this.price = price;
    }

    public long getCirculatingSupply() {
        return circulatingSupply;
    }

    public void setCirculatingSupply(long circulatingSupply) {
        this.circulatingSupply = circulatingSupply;
    }

    public synchronized long getQuantity() {
        return quantity;
    }

    public synchronized void increaseQuantity(long amount) {
        if (quantity + amount <= circulatingSupply) {
            quantity += amount;
        } else {
            System.out.println("Error: Exceeded max quantity for " + name);
        }
    }

    public synchronized void updatePrice(double newPrice) {
        this.price = newPrice;
    }

    public synchronized boolean buyCoins(Trader buyer, long quantityToBuy) {
        if (quantity >= quantityToBuy) {
            quantity -= quantityToBuy;
            buyer.addCoins(this, quantityToBuy);
            return true;
        }

        return false;
    }

    public synchronized boolean sellCoins(Trader seller, long quantityToSell) {
        if(seller.getOwnedQuantity(this) > quantityToSell && quantityToSell + quantity <= circulatingSupply) {
            quantity += quantityToSell;
            seller.removeCoins(this, quantityToSell);
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "Coin{" +
                "name=" + name + '\'' +
                ", quantity=" + quantity +
                ", supply=" + circulatingSupply +
                ", price='" + price + '\'' +
                ", symbol='" + symbol + '\'' +
                ", rank=" + rank +
                '}';
    }
}
