const { getByUsername } = require('../models/users')
const { getMessages, getUserMessages, addMessageToUser, addEmptyUser, messages } = require('./messages')

const users = [];

function userJoin(id, username) {
    const otherDetails = getByUsername(username)[0];

    const user = { id, username, name: otherDetails.name };

    users.push(user);

    if (!messages.has(id)) addEmptyUser(id);

    return { username: user.username, name: otherDetails.name };
}

function getCurrentUser(id) {
    return users.find(user => user.id == id);
}

function userLeave(id) {
    const index = users.findIndex(user => user.id === id);

    if (index !== -1) {
        return users.splice(index, 1)[0];
    }
}

function getChats(socketId, username) {
    const user = users.filter(u => u.username === username);

    const otherUser = getCurrentUser(socketId);

    if (user.length === 0) return;

    const messages = getUserMessages(user[0].id);

    if (messages.length === 0) return { name: otherUser.name, messages };

    return { id: otherUser.id, name: otherUser.name, messages, otherId: user[0].id, otherName: user[0].name, otherMessages: getUserMessages(otherUser.id) };
}

function addMessage({ id, username, content }) {
    const user = users.filter(u => u.username === username);

    messages.get(user[0].id).push({
        content,
        time: "25:08",
        type: "self",
        user_id: id
    });

    messages.get(id).push({
        content,
        time: "25:08",
        type: "other",
        user_id: user[0].id
    })
}

function getUsers() {
    return users;
}

module.exports = {
    userJoin,
    getCurrentUser,
    userLeave,
    getUsers,
    getChats,
    addMessage
};