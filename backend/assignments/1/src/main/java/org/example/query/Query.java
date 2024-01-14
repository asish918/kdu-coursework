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

        CustomLogger.printLogger("Top " + n + " coins based on price:", CustomLogger.LoggerType.INFO);
        topCoins.forEach(coin -> CustomLogger.printLogger(coin.getName() + ": $" + coin.getPrice(), CustomLogger.LoggerType.INFO));
    }

    public static void traderPortfolio(String walletAddress) {
        Trader t = null;
        for(Trader x : Market.getTraderList()) {
            if(Objects.equals(x.getWalletAddress(), walletAddress))
                t = x;
        }

        assert t != null;
        CustomLogger.printLogger("Trader " + t.getFirstName() + " " +  t.getLastName() + "'s Portfolio:", CustomLogger.LoggerType.INFO);
        t.getWallet().forEach((coin, quantity) -> CustomLogger.printLogger(coin + " " + quantity, CustomLogger.LoggerType.INFO));
    }

    public static void traderProfitLoss(String walletAddress) {
        Trader t = null;
        for(Trader x : Market.getTraderList()) {
            if(Objects.equals(x.getWalletAddress(), walletAddress))
                t = x;
        }

        assert t != null;
        CustomLogger.printLogger("Trader " + t.getFirstName() + " " +  t.getLastName() + "'s Profit Loss:", CustomLogger.LoggerType.INFO);
        CustomLogger.printLogger(Double.toString(t.getProfitLoss()), CustomLogger.LoggerType.INFO);
    }

    public static void topBottomTraders() {
        List<Trader> top5Traders = Market.getTraderList().stream()
                .sorted(Comparator.comparingDouble(Trader::getProfitLoss).reversed())
                .limit(5)
                .toList();

        CustomLogger.printLogger("Top 5 Traders based on Profit/Loss:", CustomLogger.LoggerType.INFO);
        top5Traders.forEach(trader ->
                CustomLogger.printLogger(trader.getFirstName() + ": $" + trader.getProfitLoss(), CustomLogger.LoggerType.INFO)
        );

        List<Trader> bottom5Traders = Market.getTraderList().stream()
                .sorted(Comparator.comparingDouble(Trader::getProfitLoss))
                .limit(5)
                .toList();

        CustomLogger.printLogger("\nBottom 5 Traders based on Profit/Loss:", CustomLogger.LoggerType.INFO);
        bottom5Traders.forEach(trader ->
                CustomLogger.printLogger(trader.getFirstName() + ": $" + trader.getProfitLoss(), CustomLogger.LoggerType.INFO)
        );
    }
}
