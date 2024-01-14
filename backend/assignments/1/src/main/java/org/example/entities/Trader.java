package org.example.entities;

import java.util.HashMap;
import java.util.Map;

public class Trader {
    private String firstName;
    private String lastName;
    private String phone;
    private String walletAddress;
    private double profitLoss;
    private HashMap<Coin, Long> wallet;

    public double getProfitLoss() {
        return profitLoss;
    }

    public void setProfitLoss(double profitLoss) {
        this.profitLoss = profitLoss;
    }


    public Map<Coin, Long> getWallet() {
        return wallet;
    }

    public void setWallet(HashMap<Coin, Long> wallet) {
        this.wallet = wallet;
    }


    public Trader(String firstName, String lastName, String phone, String walletAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.walletAddress = walletAddress;
        this.wallet = new HashMap<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWalletAddress() {
        return walletAddress;
    }

    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    public synchronized void addCoins(Coin coin, long quantity) {
        wallet.put(coin, wallet.getOrDefault(coin, 0L) + quantity);
        profitLoss -= coin.getPrice() * quantity;
    }

    public synchronized void removeCoins(Coin coin, long quantity) {
        if (wallet.containsKey(coin)) {
            long currentQuantity = wallet.get(coin);
            if (currentQuantity >= quantity) {
                wallet.put(coin, currentQuantity - quantity);
                profitLoss += coin.getPrice() * quantity;
            } else {
                System.out.println("Error: Trying to sell more coins than owned by " + firstName + "  " + lastName);
            }
        }
    }

    public synchronized long getOwnedQuantity(Coin coin) {
        return wallet.getOrDefault(coin, 0L);
    }
 }
