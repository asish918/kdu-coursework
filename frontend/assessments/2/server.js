const express = require('express');
require('dotenv').config();
const http = require('http');
const path = require('path');
const { Server } = require('socket.io');
var morgan = require('morgan')

let stocks = require('./data/stocks');

const stockApi = require('./routes/stockData')
const historyApi = require('./routes/history')

const { operations, operationExecutor } = require('./models/stockManipulator');

const app = express();
const server = http.createServer(app);
const io = new Server(server);

const PORT = process.env.PORT || 8000;

app.use(express.json());
app.use(morgan('tiny'))
app.use(express.static(path.join(__dirname, 'public')));

app.use('/stocks', stockApi);
app.use('/history', historyApi);

io.on('connection', socket => {
    console.log("WebSocket Connected...\nID - " + socket.id);

    socket.on('history', (payload) => {
        console.log("History received - " + payload.toString());
        socket.emit('addHistory', { ...payload, date: new Date().toUTCString() });
    })

    for (let i = 0; i < operations.length; i++) {
        setTimeout(function () {
            const res = operationExecutor(operations[i], stocks[0]);
            socket.emit('stockUpdate', {
                graphHeight: res.price,
                change: res.change,
                type: operations[i].type,
                changeType: res.type
            });
        }, 1000 * i);
    }
});

server.listen(PORT, () => console.log(`Server up and running : ${PORT}`));