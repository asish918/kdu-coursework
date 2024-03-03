import { StockChangeType } from "../app";

export function generateRandomPrice(): number {
    return parseInt(Math.floor(Math.random() * 501).toFixed(2));
}

export function changeCalculator(originalPrice: number, changedPrice: number): number {
    return ((changedPrice - originalPrice) / originalPrice) * 100;
}

export function changeType(currentPrice: number, newPrice: number): StockChangeType {
    if (currentPrice <= newPrice)
        return 'increase';

    else if (currentPrice > newPrice)
        return 'decrease';

    return 'increase';
}