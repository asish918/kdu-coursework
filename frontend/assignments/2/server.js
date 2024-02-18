const express = require('express');
const dotenv = require('dotenv');
dotenv.config();
const http = require('http');
const path = require('path');
const { Server } = require('socket.io');
const morgan = require('morgan');

const app = express();
const server = http.createServer(app);
const PORT = process.env.PORT || 8000;
const io = new Server(server);

const { userJoin, getCurrentUser, userLeave, getUsers, getChats, addMessage } = require('./memory/users');

const authApi = require('./routes/authApi');
const userApi = require('./routes/userApi');
const postApi = require('./routes/postApi');


app.use(express.json());
app.use(morgan('tiny'));
app.use(express.static(path.join(__dirname, 'public')));

app.use('/api', authApi);
app.use('/api', userApi);
app.use('/api', postApi);

io.on('connection', (socket) => {
    console.log("WebSocket Connected...\nID - " + socket.id);

    socket.on('joinRoom', ({ username }) => {
        const user = userJoin(socket.id, username);
        const activeUsers = getUsers();
        io.emit('activeUsers', activeUsers);
    });

    socket.on('messagePage', () => {
        const activeUsers = getUsers();
        io.emit('activeUsers', activeUsers);
    })

    socket.on('chatOpen', ({ socketId, username }) => {
        socket.emit('chatDetails', getChats(socketId, username));
    })

    socket.on('sendMessage', ({ id, username, content }) => {
        const senderId = addMessage({ id, username, content });
        socket.emit('chatDetails', getChats(id, username));
        io.sockets.to(id).emit('updateChatDetails', getChats(id, username));
    })

    socket.on('disconnect', () => {
        const user = userLeave(socket.id);

        if (user) {
            const activeUsers = getUsers();
            io.emit('activeUsers', activeUsers);
        }
    })
})

server.listen(PORT, () => console.log(`Server up and running : ${PORT}`));
