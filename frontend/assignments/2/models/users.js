const users = require('../data/users')

function getByUsername(username) {
    return users.filter(u => u.user_name === username);
}

module.exports = {
    getByUsername
}