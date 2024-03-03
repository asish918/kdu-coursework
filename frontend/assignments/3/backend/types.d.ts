export type Stock = {
    stock_name: string;
    stock_symbol: string;
    base_price: number;
    wishlist: boolean;
}

export type TransactionType = "Buy" | "Sell";

export type Transaction = {
    stock_name: string;
    stock_symbol: string;
    transaction_price: number;
    timestamp: string;
    status: "Failed" | "Passed";
    type?: TransactionType;
    quantity?: number;
}