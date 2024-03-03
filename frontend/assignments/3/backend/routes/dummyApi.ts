import { Router } from "express";
import { stockData } from "../dummy/stocks";
import { transactions } from "../dummy/portfolioTransactions";
const router = Router();

router.get('/stocks', (req, res) => {
    res.json(stockData);
})

router.get('/portfolio', (req, res) => {
    return res.json(transactions);
})

export default router;