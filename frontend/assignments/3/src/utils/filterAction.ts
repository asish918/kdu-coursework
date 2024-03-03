import { Transaction } from "../app";
import moment from "moment";

export type GroupedTransaction = {
    date: string;
    transactions: Transaction[];
}

export function groupSortAndSortNewestFirst(
    transactions: Transaction[]
): GroupedTransaction[] {
    const convertTimestampToDate = (timestamp: string): Date =>
        new Date(timestamp);
    const transactionsWithDates = transactions.map((transaction) => ({
        ...transaction,
        date: convertTimestampToDate(transaction.timestamp),
    }));
    const groupedTransactions: Record<string, Transaction[]> = {};
    transactionsWithDates.forEach((transaction) => {
        const dateString = transaction.date.toLocaleDateString();
        if (!groupedTransactions[dateString]) {
            groupedTransactions[dateString] = [];
        }
        groupedTransactions[dateString].push(transaction);
    });
    Object.values(groupedTransactions).forEach((group) => {
        group.sort((a, b) => moment(b.timestamp).valueOf() - moment(a.timestamp).valueOf());
    });
    const result: GroupedTransaction[] = Object.entries(groupedTransactions).map(
        ([date, transactions]) => ({
            date,
            transactions,
        })
    );
    result.sort(
        (a, b) =>
            moment(b.transactions[0].timestamp).valueOf() - moment(a.transactions[0].timestamp).valueOf()
    );
    return result;
}