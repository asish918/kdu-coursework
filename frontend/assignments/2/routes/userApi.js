const express = require('express');
const { getByUsername } = require('../models/users');
const router = express.Router();

router.get('/user/:username', (req, res) => {
    const username = req.params.username;

    const user = getByUsername(username);

    res.json({
        name: user[0].name,
        username: user[0].user_name
    });
})

module.exports = router;