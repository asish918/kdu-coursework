const messages = new Map();

function getMessages() {
    return messages;
}

function getUserMessages(id) {
    return messages.get(id);
}

function addMessageToUser(id, messageData) {
    messages.get(id).push(messageData);
}

function addEmptyUser(id) {
    messages.set(id, []);
}

module.exports = {
    messages,
    getMessages,
    getUserMessages,
    addMessageToUser,
    addEmptyUser
}