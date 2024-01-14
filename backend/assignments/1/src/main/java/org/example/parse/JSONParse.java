package org.example.parse;

import com.fasterxml.jackson.annotation.JsonProperty;


public class JSONParse {
    @JsonProperty("type")
    private String type;

    @JsonProperty("data")
    private CoinData coinData;

    public JSONParse() {
    }

    public JSONParse(String type, CoinData coinData) {
        this.type = type;
        this.coinData = coinData;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CoinData getCoinData() {
        return coinData;
    }

    public void setCoinData(CoinData coinData) {
        this.coinData = coinData;
    }

    @Override
    public String toString() {
        return "Data{" +
                "type='" + type + '\'' +
                ", coinData=" + coinData +
                '}';
    }

    public static class CoinData {
        private String coin;

        public long getVolume() {
            return volume;
        }

        public void setVolume(long volume) {
            this.volume = volume;
        }

        private long volume;
        private long quantity;
        private String walletAddress;
        private double price;

        public CoinData() {
        }

        public CoinData(String coin, int quantity, String walletAddress) {
            this.coin = coin;
            this.quantity = quantity;
            this.walletAddress = walletAddress;
        }

        public CoinData(String coin, double price) {
            this.coin = coin;
            this.price = price;
        }

        public CoinData(String coin, int volume) {
            this.coin = coin;
            this.volume = volume;
        }

        public String getCoin() {
            return coin;
        }

        public void setCoin(String coin) {
            this.coin = coin;
        }

        public long getQuantity() {
            return quantity;
        }

        public void setQuantity(long quantity) {
            this.quantity = quantity;
        }

        public String getWalletAddress() {
            return walletAddress;
        }

        public void setwalletAddress(String walletAddress) {
            this.walletAddress = walletAddress;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        @Override
        public String toString() {
            return "CoinData{" +
                    "org.example.coin='" + coin + '\'' +
                    ", quantity=" + quantity +
                    ", walletAddress='" + walletAddress + '\'' +
                    ", volume='" + volume + '\'' +
                    ", price=" + price +
                    '}';
        }
    }
}
