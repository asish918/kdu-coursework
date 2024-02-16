const { findChangePercentage, findChangeType } = require('../models/stockInfo')

const operations = [
    {
        change: -10,
        type: "negative"
    },
    {
        change: +20,
        type: "positive"
    },
    {
        change: +50,
        type: "positive"
    },
    {
        change: -30,
        type: "negative"
    },
    {
        change: +80,
        type: "positive"
    },
    {
        change: -30,
        type: "negative"
    },
    {
        change: -50,
        type: "negative"
    },
    {
        change: +130,
        type: "positive"
    },
    {
        change: -30,
        type: "negative"
    },
    {
        change: -30,
        type: "negative"
    },
];

function operationExecutor(changeOperation, stock) {
    stock.prevPrice = stock.currentPrice;
    stock.currentPrice = stock.currentPrice + changeOperation.change;

    return {
        price: stock.currentPrice,
        change: findChangePercentage(stock).toPrecision(2),
        type: findChangeType(stock)
    };
}

module.exports = {
    operations,
    operationExecutor,
}