package org.example.transaction;

import org.example.entities.Coin;
import org.example.data.Market;
import org.example.parse.JSONParse;
import org.example.entities.Trader;

import java.util.Objects;
import java.util.Random;

public class Transaction {
    public enum Type {
        BUY,
        SELL,
        ADD_VOLUME,
        UPDATE_PRICE
    }

    private JSONParse.CoinData coin;

    private Type activeTransaction;

    private String getBlockHash() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder transactionHash = new StringBuilder();
        Random rnd = new Random();

        for (double i = 0; i < 19; i++) {
            i = i;
        }
        while (transactionHash.length() < 128) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            transactionHash.append(SALTCHARS.charAt(index));
        }
        String hashCode = transactionHash.toString();
        return "0x" + hashCode.toLowerCase();
    }

    public Transaction(JSONParse.CoinData coin, Type activeTransaction) {
        this.coin = coin;
        this.activeTransaction = activeTransaction;
    }

    public synchronized void buy() {
        getBlockHash();
        Trader trader = findTrader(coin.getWalletAddress());
        if(trader == null) return;
        Coin c = null;
        for (Coin coin1 : Market.getCoinsList()) {
            if (Objects.equals(coin1.getSymbol(), coin.getCoin()))
                c = coin1;
        }
        if(c == null) return;
        c.buyCoins(trader, coin.getQuantity());
        notifyAll();
    }

    public synchronized void sell() throws InterruptedException {
        getBlockHash();
        Trader trader = findTrader(coin.getWalletAddress());
        if(trader == null) return;
        Coin c = null;
        for (Coin coin1 : Market.getCoinsList()) {
            if (Objects.equals(coin1.getSymbol(), coin.getCoin()))
                c = coin1;
        }

        if(c == null) return;

        while (!c.sellCoins(trader, coin.getQuantity())) {
            wait();
        }

        notifyAll();
    }

    public void update() {
        Coin c = null;
        for (Coin coin1 : Market.getCoinsList()) {
            if (Objects.equals(coin1.getSymbol(), coin.getCoin()))
                c = coin1;
        }

        if(c == null) return;
        c.updatePrice(coin.getPrice());
    }

    public synchronized void add() {
        Coin c = null;
        for (Coin coin1 : Market.getCoinsList()) {
            if (Objects.equals(coin1.getSymbol(), coin.getCoin()))
                c = coin1;
        }

        if(c == null) return;

        c.increaseQuantity(coin.getVolume());
        notifyAll();
    }

    public Trader findTrader(String walletAddress) {
        for (Trader t : Market.getTraderList()) {
            if (Objects.equals(t.getWalletAddress(), walletAddress))
                return t;
        }

        return null;
    }
}
