const history = require('../data/history');

function addHistory(h) {
    history.push(h);
}

module.exports = addHistory;