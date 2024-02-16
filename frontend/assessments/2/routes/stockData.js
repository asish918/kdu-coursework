const express = require('express')
const router = express.Router();
const stocks = require('../data/stocks')

const { stockHeaderInfo } = require('../models/stockInfo')

router.get('/', (req, res) => {
    const stockInfo = stockHeaderInfo(stocks[0]);

    res.status(200).json(stockInfo);
})

module.exports = router;