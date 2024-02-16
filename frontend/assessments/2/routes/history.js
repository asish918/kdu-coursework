const express = require('express')
const router = express.Router();
const history = require('../data/history')
const addHistory = require('../models/history')

router.get('/', (req, res) => {
    res.status(200).json(history);
})

router.post('/', (req, res) => {
    addHistory({
        quantity: req.body.quantity.toString(),
        date: new Date().toUTCString(),
        type: req.body.type
    });
    res.status(200).send("History added successfully");
})

module.exports = router;