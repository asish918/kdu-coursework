const dashboardHeader = document.querySelector('.dashboard__header');
const dashboardHistory = document.querySelector('.dashboard__stock-history');
const stockInput = document.getElementById('stock-quantity');
const stockGraph = document.querySelector('.dashboard__graph-container');

const buyButton = document.querySelector('.dashboard__buy');
const sellButton = document.querySelector('.dashboard__sell');

let dashboardHeaderActivePrice;
let dashboardHeaderActiveChange;

const socket = io();

function addStockUpdateGraph(data) {
    const bar = document.createElement('div')
    bar.classList.add('dashboard__bar');

    if (data.type === "positive") {
        bar.classList.add('positive-graph');
    } else if (data.type === "negative") {
        bar.classList.add('negative-graph');
    }

    bar.style.height = `${data.graphHeight}px`

    stockGraph.appendChild(bar);

    dashboardHeaderActiveChange.textContent = data.change;

    if (data.changeType === "positive") {
        dashboardHeaderActivePrice.classList.remove("negative-price");
        dashboardHeaderActivePrice.classList.add('positive-price')
        dashboardHeaderActivePrice.textContent = `${data.graphHeight} ↑`
    } else if (data.changeType === "negative") {
        dashboardHeaderActivePrice.classList.remove("positive-price");
        dashboardHeaderActivePrice.classList.add('negative-price')
        dashboardHeaderActivePrice.textContent = `${data.graphHeight} ↓`
    }

    console.log(dashboardHeaderActivePrice);
}

function addStockHeaderInfo(stock) {
    const dashboardStockName = document.createElement('div')
    dashboardStockName.classList.add('dashboard__stock-name')
    const dashboardStockImg = document.createElement('img')
    dashboardStockImg.src = stock.imageUrl;
    const dashboardStockH1 = document.createElement('h1')
    dashboardStockH1.textContent = stock.name;

    dashboardStockName.appendChild(dashboardStockImg)
    dashboardStockName.appendChild(dashboardStockH1)

    const dashboardStockPrice = document.createElement('div')
    dashboardStockPrice.classList.add('dashboard__price-wrapper')
    const dashboardStockPriceH1 = document.createElement('h1')
    dashboardStockPriceH1.textContent = "Price";
    const dashboardStockPriceP = document.createElement('p')
    dashboardStockPriceP.classList.add('dashboard__price')
    dashboardStockPriceP.textContent = stock.activePrice;

    dashboardHeaderActivePrice = dashboardStockPriceP;

    const dashboardStockPriceChange = document.createElement('p')
    dashboardStockPriceChange.classList.add('dashboard__change')
    dashboardStockPriceChange.textContent = stock.changePercentage;

    dashboardHeaderActiveChange = dashboardStockPriceChange;

    dashboardStockPrice.appendChild(dashboardStockPriceH1);
    dashboardStockPrice.appendChild(dashboardStockPriceP);
    dashboardStockPrice.appendChild(dashboardStockPriceChange);

    dashboardHeader.appendChild(dashboardStockName)
    dashboardHeader.appendChild(dashboardStockPrice)
}

function addHistoryInfo(history) {
    const historyItem = document.createElement('div');
    historyItem.classList.add('dashboard__history-item');

    const historyInfo = document.createElement('div');
    historyInfo.classList.add('dashboard__purchase-info')
    const historyH1 = document.createElement('h1');
    historyH1.textContent = `${history.quantity} stocks`
    const historyP = document.createElement('p');
    historyP.textContent = history.date;

    historyInfo.appendChild(historyH1);
    historyInfo.appendChild(historyP);

    const historyType = document.createElement('div');
    historyType.classList.add('dashboard__purchase-type')
    const historyTypeP = document.createElement('p');

    if (history.type === "Buy") {
        historyTypeP.classList.add('buy');
    } else if (history.type === "Sell") {
        historyTypeP.classList.add('sell');
    }

    historyTypeP.textContent = history.type;

    historyType.appendChild(historyTypeP)

    historyItem.appendChild(historyInfo)
    historyItem.appendChild(historyType)

    dashboardHistory.appendChild(historyItem)
}

async function fetchStockHeaderInfo() {
    const res = await fetch('http://localhost:8000/stocks');
    const data = await res.json();
    console.log(data);
    addStockHeaderInfo(data)
}

async function fetchHistoryInfo() {
    const res = await fetch('http://localhost:8000/history');
    const data = await res.json();
    console.log(data);

    data.forEach(d => addHistoryInfo(d));
}

async function addHistory(history) {
    const res = await fetch('http://localhost:8000/history', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(history)
    })

    socket.emit('history', history)
}

fetchStockHeaderInfo();
fetchHistoryInfo();

buyButton.addEventListener('click', () => {
    const value = stockInput.value;
    console.log(value);
    addHistory({
        quantity: value,
        type: "Buy"
    })
})

sellButton.addEventListener('click', () => {
    const value = stockInput.value;
    console.log(value);
    addHistory({
        quantity: value,
        type: "Sell"
    })
})

socket.on('addHistory', (payload) => {
    addHistoryInfo(payload);
})

socket.on('stockUpdate', (payload) => {
    addStockUpdateGraph(payload);
})