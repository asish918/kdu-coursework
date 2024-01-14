package org.example.data;

import org.example.entities.Coin;
import org.example.entities.Trader;

import java.util.ArrayList;

public class Market {
    static ArrayList<Coin> coinsList = new ArrayList<>();
    public static ArrayList<Coin> getCoinsList() {
        return coinsList;
    }

    public void setCoinsList(ArrayList<Coin> coinsList) {
        Market.coinsList = coinsList;
    }

    public static ArrayList<Trader> getTraderList() {
        return traderList;
    }

    public void setTraderList(ArrayList<Trader> traderList) {
        Market.traderList = traderList;
    }

    static ArrayList<Trader> traderList = new ArrayList<>();
}
