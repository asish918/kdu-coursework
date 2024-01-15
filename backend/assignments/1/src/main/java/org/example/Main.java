package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.data.Market;
import org.example.entities.Coin;
import org.example.entities.Trader;
import org.example.parse.JSONParse;
import org.example.query.Query;

import java.io.*;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonTransactions = mapper.readTree(new File(Constants.FILE_JSON_PATH));
            parseCSV(Path.of(Constants.COIN_CSV_PATH));
            parseCSV(Path.of(Constants.TRADER_CSV_PATH));
            int transactionCount = jsonTransactions.size();
            CountDownLatch latch = new CountDownLatch(transactionCount);
            executeTransactions(jsonTransactions, latch);
            latch.await();
            Query.retrieveTopCoins(5);
            Query.topBottomTraders();
            Query.retrieveCoinDetails("LUNA");
            Query.traderPortfolio("0x344427a90da861f79cc80bac2ff8638f");
            Query.traderProfitLoss("0x344427a90da861f79cc80bac2ff8638f");
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }
    public static ArrayList<String[]> parseCSV(Path path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path.toString()))) {
            ArrayList<String[]> data = new ArrayList<>();

            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");

                if(columns.length == 6) {
                    Coin c = new Coin(Integer.parseInt(columns[1]), columns[2], columns[3], Double.parseDouble(columns[4]), Long.parseLong(columns[5]));
                    Market.getCoinsList().add(c);
                } else if (columns.length == 5) {
                    Trader t = new Trader(columns[1], columns[2], columns[3], columns[4]);
                    Market.getTraderList().add(t);
                }

                data.add(columns);
            }
            return data;
        } catch (IOException I) {
            System.out.println(I);
        }

        return null;
    }
    public static JsonNode parseJsonFile(String path) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readTree(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void executeTransactions(JsonNode jsonTransactions, CountDownLatch
            latch) throws JsonProcessingException, InterruptedException {
        parseCSV(Path.of(Constants.COIN_CSV_PATH));
        parseCSV(Path.of(Constants.TRADER_CSV_PATH));
        ExecutorService execService = Executors.newFixedThreadPool(10);

        ObjectMapper objectMapper = new ObjectMapper();
        JSONParse[] transactions = objectMapper.treeToValue(jsonTransactions, JSONParse[].class);

        ArrayList<JSONParse> transactionArrayList = new ArrayList<>();
        Collections.addAll(transactionArrayList, transactions);

        for(JSONParse obj : transactionArrayList) {
            execService.execute(new ExecuteTransaction(obj));
            latch.countDown();
        }

        execService.shutdown();
    }
}
