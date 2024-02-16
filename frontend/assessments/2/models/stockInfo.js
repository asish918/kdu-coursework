// stock model
/**
 * {
 *  name:
 *  changeType:
 *  activePrice:
 * changePercentage
 * } 
 */

function findChangeType(stock) {
    if (stock.prevPrice < stock.currentPrice) {
        return "positive"
    }
    else {
        return "negative"
    }
}

function findChangePercentage(stock) {
    return (stock.prevPrice - stock.currentPrice) / stock.prevPrice;
}

function stockHeaderInfo(stock) {
    return {
        name: stock.name,
        changeType: findChangeType(stock),
        activePrice: stock.currentPrice,
        changePercentage: findChangePercentage(stock),
        imageUrl: stock.imageUrl
    }
}

module.exports = {
    findChangePercentage,
    findChangeType,
    stockHeaderInfo
}