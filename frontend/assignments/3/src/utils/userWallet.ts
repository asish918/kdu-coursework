
import { Transaction, UserPortfolio } from "../app";
export function generateRandomBalance(): number {
    const randomNumber = Math.random();

    const scaledNumber = randomNumber * (50000 - 10000) + 10000;

    const randomNumberInRange = Math.floor(scaledNumber);

    console.log("Balance - " + randomNumberInRange);
    return randomNumberInRange;
}

export function validTransaction(currentBalance: number, transaction: Transaction, userStocks: UserPortfolio[]): boolean {
    if (transaction.type === 'Buy') {
        return transaction.transaction_price <= currentBalance;
    }
    else if (transaction.type === 'Sell') {
        const index = userStocks.findIndex(stock => stock.stock_symbol);

        return index !== -1 && transaction.quantity <= userStocks[index].quantity;
    }

    return false;
}