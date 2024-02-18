const express = require('express');
const { getByUsername } = require('../models/users');
const router = express.Router();

router.post('/login', (req, res) => {
    const username = req.body.username;
    const password = req.body.password;

    const user = getByUsername(username);

    if (user.length == 0 || password !== user[0].password) {
        res.status(200).json({
            authorized: false,
            message: "Authentication unsuccessful"
        });
    } else if (password === user[0].password) {
        res.status(200).json({
            authorized: true,
            message: "Authentication successful"
        });
    } else {
        res.status(500).json({
            message: "Some error occurred"
        });
    }
});

module.exports = router;
