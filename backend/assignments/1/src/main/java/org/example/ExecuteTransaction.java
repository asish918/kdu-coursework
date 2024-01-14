package org.example;

import org.example.parse.JSONParse;
import org.example.transaction.Transaction;

import java.util.Objects;
import java.util.concurrent.CountDownLatch;

public class ExecuteTransaction implements Runnable {
    private JSONParse transaction;
    private CountDownLatch latch;

    public ExecuteTransaction(JSONParse transaction, CountDownLatch latch) {
        this.transaction = transaction;
        this.latch = latch;
    }

    @Override
    public void run() {
        Transaction.Type type = null;

        System.out.println(transaction.toString());

        if(Objects.equals(transaction.getType(), "BUY")){
            type = Transaction.Type.BUY;
            Transaction execTransact = new Transaction(transaction.getCoinData(), type);
            try {
                execTransact.buy();
                latch.countDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        else if(Objects.equals(transaction.getType(), "SELL")) {
            type = Transaction.Type.SELL;
            Transaction execTransact = new Transaction(transaction.getCoinData(), type);
            try {
                execTransact.sell();
                latch.countDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        else if(Objects.equals(transaction.getType(), "UPDATE_PRICE")){
            type = Transaction.Type.UPDATE_PRICE;
            Transaction execTransact = new Transaction(transaction.getCoinData(), type);
            execTransact.update();
            latch.countDown();
        }
        else if(Objects.equals(transaction.getType(), "ADD_VOLUME")){
            type = Transaction.Type.ADD_VOLUME;
            Transaction execTransact = new Transaction(transaction.getCoinData(), type);
            execTransact.add();
            latch.countDown();
        }
    }
}
