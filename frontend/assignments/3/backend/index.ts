
import express from 'express';
import dotenv from 'dotenv';
import cors from 'cors';
import morgan from 'morgan';
import { createServer } from 'node:http';
import { Server } from 'socket.io';
import router from './routes/dummyApi';
import { addUser } from './models/user';
import type { Transaction } from './types';
import { getRandomName } from './dummy/randomNames';

dotenv.config();

const app = express();
const server = createServer(app);
const io = new Server(server, {
    cors: {
        origin: "http://localhost:5173"
    }
});

app.use(cors());
app.use(morgan('dev'))

app.use(router);

io.on('connection', (socket) => {
    console.log('Client connected with ID - ' + socket.id);
    addUser(socket.id);

    socket.on('joinSocketRoom', (stockSymbol) => {
        socket.join(stockSymbol);
        console.log(socket.id + " joined " + stockSymbol)
    })

    socket.on('transaction', (transaction: Transaction) => {
        const randomName = getRandomName();

        if (transaction.status === 'Failed') {
            return;
        }

        io.to(transaction.stock_symbol).except(socket.id).emit('newTransaction', { name: randomName, ...transaction });
    })

    socket.on('disconnect', () => {

        console.log(socket.id + " left")
    })
});

const PORT = process.env.PORT || 3000;
server.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}`);
});


