export type APIState = "success" | "error" | "loading" | null;

export type Stock = {
    stock_name: string;
    stock_symbol: string;
    base_price: number;
    wishlist: boolean;
    current_price?: number
}

export type TransactionType = 'Buy' | 'Sell'

export type StockChangeType = 'increase' | 'decrease'

export type GraphType = {
    value: number;
    change: StockChangeType
}

export type Transaction = {
    stock_name: string;
    stock_symbol: string;
    transaction_price: number;
    timestamp: string;
    status?: "Failed" | "Passed";
    type?: TransactionType;
    quantity?: number;
    name?: string;
}

export type UserPortfolio = {
    stock_symbol: string;
    quantity: number;
}