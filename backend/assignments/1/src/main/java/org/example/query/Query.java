package org.example.query;

import org.example.entities.Coin;
import org.example.data.Market;
import org.example.entities.Trader;
import org.example.logging.CustomLogger;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Query {
    public static void retrieveCoinDetails(String coinName) {
        Coin c = null;
        for(Coin n : Market.getCoinsList())
            if(Objects.equals(n.getName(), coinName)) {
                c = n;
            } else if(Objects.equals(n.getSymbol(), coinName)) {
                c = n;
            }

        assert c != null;
        CustomLogger.printLogger(c.toString(), CustomLogger.LoggerType.INFO);
    }

    public static void retrieveTopCoins(int n) {
        List<Coin> topCoins = Market.getCoinsList().stream()
                .sorted(Comparator.comparingDouble(Coin::getPrice).reversed())
                .limit(n)
                .toList();

        System.out.println("Top " + n + " coins based on price:");
//        CustomLogger.printLogger("Top " + n + " coins based on price:", CustomLogger.LoggerType.INFO);
        topCoins.forEach(coin -> System.out.println(coin.getName() + ": $" + coin.getPrice()));
    }

    public static void traderPortfolio(String walletAddress) {
        Trader t = null;
        for(Trader x : Market.getTraderList()) {
            if(Objects.equals(x.getWalletAddress(), walletAddress))
                t = x;
        }

        assert t != null;
        System.out.println("Trader " + t.getFirstName() + " " +  t.getLastName() + "'s Portfolio:");
        t.getWallet().forEach((coin, quantity) -> System.out.println(coin + " " + quantity));
    }

    public static void traderProfitLoss(String walletAddress) {
        Trader t = null;
        for(Trader x : Market.getTraderList()) {
            if(Objects.equals(x.getWalletAddress(), walletAddress))
                t = x;
        }

        assert t != null;
        System.out.println("Trader " + t.getFirstName() + " " +  t.getLastName() + "'s Profit Loss:");
        System.out.println(Double.toString(t.getProfitLoss()));
    }

    public static void topBottomTraders() {
        List<Trader> top5Traders = Market.getTraderList().stream()
                .sorted(Comparator.comparingDouble(Trader::getProfitLoss).reversed())
                .limit(5)
                .toList();

        System.out.println("Top 5 Traders based on Profit/Loss:");
        top5Traders.forEach(trader ->
                System.out.println(trader.getFirstName() + ": $" + trader.getProfitLoss())
        );

        List<Trader> bottom5Traders = Market.getTraderList().stream()
                .sorted(Comparator.comparingDouble(Trader::getProfitLoss))
                .limit(5)
                .toList();

        System.out.println("\nBottom 5 Traders based on Profit/Loss:");
        bottom5Traders.forEach(trader ->
                System.out.println(trader.getFirstName() + ": $" + trader.getProfitLoss())
        );
    }
}
